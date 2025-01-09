package com.wanmait.scmdemo.esdao;


import com.wanmait.scmdemo.entity.ProductWarehouse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


public interface ProductWarehouseRepository extends ElasticsearchRepository<ProductWarehouse, Integer> {


    Page<ProductWarehouse> findAllByEnterPersonLike(String keyword, Pageable pageable);
}
