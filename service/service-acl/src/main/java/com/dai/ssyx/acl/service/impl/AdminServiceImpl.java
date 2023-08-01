package com.dai.ssyx.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dai.ssyx.acl.mapper.AdminMapper;
import com.dai.ssyx.acl.service.AdminService;
import com.dai.ssyx.model.acl.Admin;
import com.dai.ssyx.vo.acl.AdminQueryVo;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {
    @Override
    public IPage<Admin> selectPage(Page<Admin> pageParam, AdminQueryVo userQueryVo) {
        //获取用户名称条件值
        String name = userQueryVo.getName();
        String username = userQueryVo.getUsername();
        //创建条件构造器
        LambdaQueryWrapper<Admin> wrapper = new LambdaQueryWrapper<>();
        if (!StringUtils.isEmpty(username)){
            wrapper.eq(Admin::getUsername,username);
        }
        if(!StringUtils.isEmpty(name)) {
            //封装条件
            wrapper.like(Admin::getName, name);
        }
       return baseMapper.selectPage(pageParam,wrapper);
    }


}
