package com.dai.ssyx.acl.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dai.ssyx.model.acl.Role;
import com.dai.ssyx.vo.acl.RoleQueryVo;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface RoleService extends IService<Role> {
    IPage<Role> selectRolePage(Page<Role> pageParam, RoleQueryVo roleQueryVo);

    Map<String, Object> findRoleByUserId(Long adminId);

    void saveUserRoleRealtionShip(Long adminId, Long[] roleId);
}
