package com.wanmait.scmdemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wanmait.scmdemo.aop.LogInfo;
import com.wanmait.scmdemo.entity.*;
import com.wanmait.scmdemo.mapper.AttachmentMapper;
import com.wanmait.scmdemo.mapper.ContactinfoMapper;
import com.wanmait.scmdemo.mapper.QualificationinfoMapper;
import com.wanmait.scmdemo.mapper.SupplierMapper;
import com.wanmait.scmdemo.service.SupplierService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wanmait.scmdemo.util.AjaxResult;
import com.wanmait.scmdemo.util.GYSCodeGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 供应商信息表 服务实现类
 * </p>
 *
 * @author wanmait
 * @since 2024-12-04
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class SupplierServiceImpl extends ServiceImpl<SupplierMapper, Supplier> implements SupplierService {
    @Resource
    private SupplierMapper supplierMapper;
    @Resource
    private ContactinfoMapper contactinfoMapper;
    @Resource
    private AttachmentMapper attachmentMapper;

    //供应商信息录入
    @LogInfo("供应商信息录入")
    @Override
    public AjaxResult register(SupplierDTO dto) {
        //获取操作管理员
        ServletRequestAttributes requestAttributes=(ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        Admin admin = (Admin) request.getAttribute("admin");
        System.out.println(admin.getUsername());

        //供应商准入注册
        Supplier supplier = dto.getSupplier();
        supplier.setSupplierCode(GYSCodeGenerator.generateCode());
        supplier.setSupplierNum(GYSCodeGenerator.generateEntryCode());
        if (admin!=null){
            supplier.setEnterPerson(admin.getUsername());
        }

        supplierMapper.insert(supplier);
        //联系人信息
        Integer id = supplier.getId();
        Contactinfo contactinfo = dto.getContactinfo();
        contactinfo.setSupplierId(id);
        contactinfoMapper.insert(contactinfo);
        Integer id1 = contactinfo.getId();
        //附件信息
        Attachment attachment = dto.getAttachment();
        attachment.setSupplierId(id);
        attachmentMapper.insert(attachment);

        System.out.println(dto.getSupplier());
        System.out.println(dto.getContactinfo());
        System.out.println(dto.getAttachment());
        return AjaxResult.success("成功录入");
    }

    @Override
    public AjaxResult findBySupplierState() {
        LambdaQueryWrapper<Supplier>  supplierLambdaQueryWrapper= new LambdaQueryWrapper<>();
        supplierLambdaQueryWrapper.eq(Supplier::getSupplierState,"潜在供应商");
        List<Supplier> suppliers = supplierMapper.selectList(supplierLambdaQueryWrapper);
        return AjaxResult.success("查找成功").put("suppliers",suppliers);
    }

}
