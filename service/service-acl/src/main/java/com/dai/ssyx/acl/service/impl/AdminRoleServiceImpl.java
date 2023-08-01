package com.dai.ssyx.acl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dai.ssyx.acl.mapper.AdminRoleMapper;
import com.dai.ssyx.acl.service.AdminRoleService;
import com.dai.ssyx.model.acl.AdminRole;
import org.springframework.stereotype.Service;

@Service
public class AdminRoleServiceImpl extends ServiceImpl<AdminRoleMapper, AdminRole> implements AdminRoleService {
}
