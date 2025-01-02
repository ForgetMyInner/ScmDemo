package com.wanmait.scmdemo.service;

import com.wanmait.scmdemo.entity.SupplierEnterRoleScores;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 供应商准入得分 服务类
 * </p>
 *
 * @author wanmait
 * @since 2024-12-25
 */
public interface SupplierEnterRoleScoresService extends IService<SupplierEnterRoleScores> {
    void saveScores(Integer supplierId,String clauseName,Integer maxScore, List<Map<String, Object>> clauseItems,String supplierLevel,Integer RuleId);

    List<SupplierEnterRoleScores> getBySupplierId(Integer supplierId);
}
