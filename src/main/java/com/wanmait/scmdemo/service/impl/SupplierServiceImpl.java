package com.wanmait.scmdemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wanmait.scmdemo.aop.LogInfo;
import com.wanmait.scmdemo.entity.*;
import com.wanmait.scmdemo.mapper.*;
import com.wanmait.scmdemo.service.SupplierService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wanmait.scmdemo.esdao.SupplierRepository;
import com.wanmait.scmdemo.util.AjaxResult;
import com.wanmait.scmdemo.util.GYSCodeGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Optional;

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
public class SupplierServiceImpl extends ServiceImpl<SupplierMapper, Supplier> implements SupplierService  {
    @Resource
    private SupplierMapper supplierMapper;
    @Resource
    private ContactinfoMapper contactinfoMapper;
    @Resource
    private AttachmentMapper attachmentMapper;
    @Resource
    private SupplierSignMapper supplierSignMapper;
    @Resource
    private SupplierRepository supplierRepository;

    //供应商信息录入
    @LogInfo("供应商信息录入")
    @Override
    public AjaxResult register(SupplierDTO dto) {
        //获取操作管理员
        ServletRequestAttributes requestAttributes=(ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        Admin admin = (Admin) request.getAttribute("admin");
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
        Optional<Attachment> optionalAttachment = Optional.ofNullable(dto.getAttachment());
        optionalAttachment.ifPresent(attachment -> {
            attachment.setSupplierId(id);
            if (attachmentMapper != null) {
                attachmentMapper.insert(attachment);
            } else {
                throw new IllegalStateException("attachmentMapper is not injected properly");
            }
        });
//        Attachment attachment = dto.getAttachment();
//        attachment.setSupplierId(id);
//        attachmentMapper.insert(attachment);
        return AjaxResult.success("成功录入");
    }

    @Override
    public AjaxResult findBySupplierState() {
        LambdaQueryWrapper<Supplier>  supplierLambdaQueryWrapper= new LambdaQueryWrapper<>();
        supplierLambdaQueryWrapper.isNull(Supplier::getSupplierLevel).eq(Supplier::getSupplierState,"潜在供应商");
        ServletRequestAttributes requestAttributes=(ServletRequestAttributes)RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        Admin admin = (Admin) request.getAttribute("admin");

        supplierLambdaQueryWrapper.eq(Supplier::getEnterPerson,admin.getUsername());
        List<Supplier> suppliers = supplierMapper.selectList(supplierLambdaQueryWrapper);
        return AjaxResult.success("查找成功").put("suppliers",suppliers).put("length",suppliers.size());
    }

    @Override
    public PageInfo<Supplier> supplierWithAttachAndContact(Supplier supplier1,int pageNum, int pageSize) {
        LambdaQueryWrapper<Supplier> supplierLambdaQueryWrapper=new LambdaQueryWrapper<>(supplier1);
        PageHelper.startPage(pageNum, pageSize);
        List<Supplier> suppliers = supplierMapper.selectList(supplierLambdaQueryWrapper);
        List<Attachment> attachments=attachmentMapper.selectList(null);
        List<Contactinfo> contactinfos = contactinfoMapper.selectList(null);
        List<SupplierSign> supplierSigns = supplierSignMapper.selectList(null);

        Map<Integer, Supplier> supplierMap = suppliers.stream().collect(Collectors.toMap(Supplier::getId, supplier -> supplier));
        for (Attachment attachment : attachments) {
            Integer supplierId = attachment.getSupplierId();
            Supplier supplier = supplierMap.get(supplierId);
            if (supplier != null) {
                supplier.setAttachment(attachment);
            }
        }
        for (Contactinfo contactinfo : contactinfos){
            Integer supplierId = contactinfo.getSupplierId();
            Supplier supplier = supplierMap.get(supplierId);
            if (supplier != null) {
                supplier.setContactinfo(contactinfo);
            }
        }
        for (SupplierSign supplierSign : supplierSigns) {
            Integer supplierId = supplierSign.getSupplierId();
            Supplier supplier = supplierMap.get(supplierId);
            if (supplier != null) {
                supplier.setSupplierSign(supplierSign);
            }
        }

        return new PageInfo<>(suppliers,14);
    }

}
