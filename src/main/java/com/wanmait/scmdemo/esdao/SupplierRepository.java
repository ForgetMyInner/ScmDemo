package com.wanmait.scmdemo.esdao;


import com.github.pagehelper.PageInfo;
import com.wanmait.scmdemo.entity.Supplier;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface SupplierRepository extends ElasticsearchRepository<Supplier,Integer> {
//    PageInfo<Supplier> listPageInfoWithHighlight(String title, int pageNum);

}
