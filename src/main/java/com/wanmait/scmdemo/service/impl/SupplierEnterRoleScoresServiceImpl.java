package com.wanmait.scmdemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wanmait.scmdemo.aop.LogInfo;
import com.wanmait.scmdemo.entity.Admin;
import com.wanmait.scmdemo.entity.Approval;
import com.wanmait.scmdemo.entity.Supplier;
import com.wanmait.scmdemo.entity.SupplierEnterRoleScores;
import com.wanmait.scmdemo.mapper.ApprovalMapper;
import com.wanmait.scmdemo.mapper.SupplierEnterRoleScoresMapper;
import com.wanmait.scmdemo.mapper.SupplierMapper;
import com.wanmait.scmdemo.service.SupplierEnterRoleScoresService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wanmait.scmdemo.service.SupplierService;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 供应商准入得分 服务实现类
 * </p>
 *
 * @author wanmait
 * @since 2024-12-25
 */
@Service
public class SupplierEnterRoleScoresServiceImpl extends ServiceImpl<SupplierEnterRoleScoresMapper, SupplierEnterRoleScores> implements SupplierEnterRoleScoresService {
    @Resource
    private SupplierEnterRoleScoresMapper supplierEnterRoleScoresMapper;
    @Resource
    private SupplierMapper supplierMapper;
    @Resource
    ApprovalMapper approvalMapper;
    @LogInfo("供应商准入条例打分")
    @Override
    public void saveScores(Integer supplierId,String clauseName,Integer totalScore, List<Map<String, Object>> clauseItems,String supplierLevel,Integer ruleId) {
        //获取操作管理员
        ServletRequestAttributes requestAttributes=(ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        Admin admin = (Admin) request.getAttribute("admin");

        List<SupplierEnterRoleScores> supplierEnterRoleScores=clauseItems.stream().map(item -> {
            SupplierEnterRoleScores supplierEnterRoleScore = new SupplierEnterRoleScores();
            supplierEnterRoleScore.setSupplierId(supplierId);
            supplierEnterRoleScore.setRuleName(clauseName);
            supplierEnterRoleScore.setTotalScore(totalScore);
            supplierEnterRoleScore.setRuleId(ruleId);
            supplierEnterRoleScore.setRuleDataName((String) item.get("rules"));
            supplierEnterRoleScore.setScore((Integer) item.get("score"));
            supplierEnterRoleScore.setRemark((String) item.get("description"));
            supplierEnterRoleScore.setCreatPerson(admin.getUsername());
            return supplierEnterRoleScore;
        }).collect(Collectors.toList());
        supplierEnterRoleScoresMapper.insert(supplierEnterRoleScores);
        //添加签约信息
        Approval approval = new Approval();
        approval.setSymbol(1);
        approval.setAimId(supplierId);
        approval.setUserinfoId(admin.getId());
        approvalMapper.insert(approval);
    }

    @Override
    public List<SupplierEnterRoleScores> getBySupplierId(Integer supplierId) {
        LambdaQueryWrapper<SupplierEnterRoleScores> supplierEnterRoleScoresLambdaQueryWrapper = new LambdaQueryWrapper<>();
        supplierEnterRoleScoresLambdaQueryWrapper.eq(SupplierEnterRoleScores::getSupplierId,supplierId);

        return supplierEnterRoleScoresMapper.selectList(supplierEnterRoleScoresLambdaQueryWrapper);
    }
}
