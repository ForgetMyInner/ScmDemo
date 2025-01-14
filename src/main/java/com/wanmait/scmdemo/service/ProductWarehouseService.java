package com.wanmait.scmdemo.service;

import com.github.pagehelper.PageInfo;
import com.wanmait.scmdemo.entity.ProductWarehouse;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.data.domain.Page;

import java.util.List;


/**
 * <p>
 * 产品在库存 服务类
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
public interface ProductWarehouseService extends IService<ProductWarehouse> {
    /*仓库辅助页list*/
    PageInfo<ProductWarehouse> assistantPageList(int pageNum, int PageSize);

    /*lzq 仓库辅助页关键词搜索*/
    PageInfo<ProductWarehouse> search(String keyword, int pageNum);

    Page<ProductWarehouse> searchByEnterPerson(String keyword, int pageNum);

    PageInfo<ProductWarehouse> searchByEnterPersonPageInfo(String keyword, int pageNum);

    PageInfo<ProductWarehouse> searchByEnterPersonPageInfoWithHighlight(String keyword, int pageNum);

}
