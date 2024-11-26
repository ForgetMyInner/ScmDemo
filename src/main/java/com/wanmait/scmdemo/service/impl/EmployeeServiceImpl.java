package com.wanmait.scmdemo.service.impl;

import com.wanmait.scmdemo.entity.Employee;
import com.wanmait.scmdemo.mapper.EmployeeMapper;
import com.wanmait.scmdemo.service.EmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 员工表 服务实现类
 * </p>
 *
 * @author wanmait
 * @since 2024-11-26
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

}
