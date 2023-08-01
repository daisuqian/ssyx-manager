package com.dai.ssyx.acl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dai.ssyx.model.acl.Admin;
import com.dai.ssyx.vo.acl.AdminQueryVo;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminService extends IService<Admin> {
    IPage<Admin> selectPage(Page<Admin> pageParam, AdminQueryVo userQueryVo);
}
