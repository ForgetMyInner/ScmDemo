package com.wanmait.scmdemo.esdao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wanmait.scmdemo.entity.PurchaseNeedPool;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PurchaseNeedPoolRepository extends ElasticsearchRepository<PurchaseNeedPool, Integer> {

}
