package com.wanmait.scmdemo.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wanmait.scmdemo.entity.ProductShow;
import com.wanmait.scmdemo.entity.ProductWarehouse;
import com.wanmait.scmdemo.entity.Warehouse;
import com.wanmait.scmdemo.mapper.ProductShowMapper;
import com.wanmait.scmdemo.mapper.ProductWarehouseMapper;
import com.wanmait.scmdemo.mapper.WarehouseMapper;
import com.wanmait.scmdemo.esdao.ProductWarehouseRepository;
import com.wanmait.scmdemo.service.ProductWarehouseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 产品在库存 服务实现类
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProductWarehouseServiceImpl extends ServiceImpl<ProductWarehouseMapper, ProductWarehouse> implements ProductWarehouseService {

    @Resource
    private ProductShowMapper productShowMapper;
    @Resource
    private WarehouseMapper warehouseMapper;
    @Resource
    private ProductWarehouseRepository productWarehouseRepository;
    @Resource
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Override
    public PageInfo<ProductWarehouse> assistantPageList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ProductWarehouse> productWarehouses = this.list();
        productWarehouses.forEach(productWarehouse -> {
            //仓库表中没有产品信息id   所以从产品库存表中取出产品展示id 产品id是唯一的   仓库id不唯一  可能多个商品对应一个仓库
            int productShowId = productWarehouse.getProductShow();
            ProductShow productShow = productShowMapper.selectById(productShowId);
            productWarehouse.setProductShowEntity(productShow);
            //查询产品所在的仓库
            //仓库id
            int warehouseId = productWarehouse.getWarehouseId();
            Warehouse warehouse = warehouseMapper.selectById(warehouseId);
            productWarehouse.setWarehouseEntity(warehouse);
        });
        return new PageInfo<>(productWarehouses, 11);
    }

    @Override
    public Page<ProductWarehouse> searchByEnterPerson(String keyword, int pageNum) {
//        Pageable pageable = PageRequest.of(1 - 1, 10, Sort.by(Sort.Order.desc("enter_time")));
        Pageable pageable = PageRequest.of(pageNum - 1, 10);

        return productWarehouseRepository.findAllByEnterPersonLike(keyword, pageable);
    }

    @Override
    public PageInfo<ProductWarehouse> search(String keyword, int pageNum) {
        int pageSize = 10;//页面数据
        int navigatePages = 11;//页码

        NativeSearchQueryBuilder searchQueryBuilder = new NativeSearchQueryBuilder();
        //指定查询条件
        searchQueryBuilder.withQuery(QueryBuilders.multiMatchQuery(keyword, "enter_person", "warehouseEntity.name", "productShowEntity.productName"));

        //指定分页
        searchQueryBuilder.withPageable(PageRequest.of(pageNum - 1, pageSize));

        //设置高亮
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        //设置高亮字段
        highlightBuilder.field("enter_person").field("warehouseEntity.name").field("productShowEntity.productName");
        //设置高亮标签,如果不设置默认为<em> </em>
        highlightBuilder.preTags("<span style=\"color:red\">").postTags("</span>");
        searchQueryBuilder.withHighlightBuilder(highlightBuilder);


        //创建查询对象
        NativeSearchQuery searchQuery = searchQueryBuilder.build();
        //执行查询
        SearchHits<ProductWarehouse> searchHits = elasticsearchRestTemplate.search(searchQuery, ProductWarehouse.class);
        List<SearchHit<ProductWarehouse>> searchHitList = searchHits.getSearchHits();
        //总条数
        long total = searchHits.getTotalHits();
        //总页数
        int pages = (int) (total - 1) / pageSize + 1;

        List<ProductWarehouse> productWarehouses = new ArrayList<>();

        searchHitList.forEach(productWarehouseSearchHit -> {
            ProductWarehouse productWarehouse = productWarehouseSearchHit.getContent();
            Integer productShowId = productWarehouse.getProductShow(); // 产品展示id
            ProductShow productShow = productShowMapper.selectById(productShowId);
            productWarehouse.setProductShowEntity(productShow);

            Integer warehouseId = productWarehouse.getWarehouseId(); // 仓库id
            Warehouse warehouse = warehouseMapper.selectById(warehouseId);
            productWarehouse.setWarehouseEntity(warehouse);
            System.out.println("获得高亮信息前：" + productWarehouse);

            //获得高亮信息
            try {
                String enterPerson = productWarehouseSearchHit.getHighlightField("enterPerson").get(0);
                productWarehouse.setEnterPerson(enterPerson);
                System.out.println("66666666666666666");
            } catch (Exception e) {

            }
            try {
                String warehouseEntityName = productWarehouseSearchHit.getHighlightField("warehouseEntity.name").get(0);
                productWarehouse.getWarehouseEntity().setName(warehouseEntityName);
                System.out.println("111111111111");

            } catch (Exception e) {
            }
            try {
                String productShowEntityProductName = productWarehouseSearchHit.getHighlightField("productShowEntity.productName").get(0);
                productWarehouse.getProductShowEntity().setProductName(productShowEntityProductName);
                System.out.println("22222222222");

            } catch (Exception e) {

            }
            System.out.println("获得高亮信息后：" + productWarehouse);

            productWarehouses.add(productWarehouse);
        });
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize, Sort.by(Sort.Order.desc("enter_person")));

        PageInfo<ProductWarehouse> pageInfo = new PageInfo<>();
        pageInfo.setList(productWarehouses);
        pageInfo.setTotal(total);
        pageInfo.setPages(pages);
        pageInfo.setPageNum(pageNum);
        pageInfo.setPageSize(pageSize);
        pageInfo.calcByNavigatePages(navigatePages);
        return pageInfo;
    }


    @Override
    public PageInfo<ProductWarehouse> searchByEnterPersonPageInfo(String keyword, int pageNum) {
        int pageSize = 10;//从数据库中获取
        int navigatePages = 11;//从数据库中获取
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);

        Page<ProductWarehouse> page = productWarehouseRepository.findAllByEnterPersonLike(keyword, pageable);
        PageInfo<ProductWarehouse> pageInfo = new PageInfo<>();
        pageInfo.setList(page.getContent());
        pageInfo.setTotal(page.getTotalElements());
        pageInfo.setPages(page.getTotalPages());
        pageInfo.setPageNum(pageNum);
        pageInfo.setPageSize(pageSize);
        pageInfo.calcByNavigatePages(navigatePages);
        return pageInfo;
    }

    @Override
    public PageInfo<ProductWarehouse> searchByEnterPersonPageInfoWithHighlight(String keyword, int pageNum) {
        int pageSize = 10;//从数据库中获取
        int navigatePages = 11;//从数据库中获取
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize);

        Page<ProductWarehouse> page = productWarehouseRepository.findAllByEnterPersonLike(keyword, pageable);
        PageInfo<ProductWarehouse> pageInfo = new PageInfo<>();
        pageInfo.setList(page.getContent());
        pageInfo.setTotal(page.getTotalElements());
        pageInfo.setPages(page.getTotalPages());
        pageInfo.setPageNum(pageNum);
        pageInfo.setPageSize(pageSize);
        pageInfo.calcByNavigatePages(navigatePages);
        return pageInfo;
    }


}
