package com.dai.ssyx.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dai.ssyx.acl.mapper.RoleMapper;
import com.dai.ssyx.acl.service.AdminRoleService;
import com.dai.ssyx.acl.service.RoleService;
import com.dai.ssyx.model.acl.AdminRole;
import com.dai.ssyx.model.acl.Role;
import com.dai.ssyx.vo.acl.RoleQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Autowired
    private AdminRoleService adminRoleService;

    @Override
    public IPage<Role> selectRolePage(Page<Role> pageParam, RoleQueryVo roleQueryVo) {
        String roleName = roleQueryVo.getRoleName();
        LambdaQueryWrapper<Role> roleLambdaQueryWrapper = new LambdaQueryWrapper<>();
        //使用wrapper中的 模糊查询
        if (!StringUtils.isEmpty(roleName)){
            roleLambdaQueryWrapper.like(Role::getRoleName,roleName);
        }

        return baseMapper.selectPage(pageParam, roleLambdaQueryWrapper);
    }

    @Override
    public Map<String, Object> findRoleByUserId(Long adminId) {
        List<Role> allRoles = baseMapper.selectList(null);
        LambdaQueryWrapper<AdminRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(AdminRole::getAdminId,adminId);
        List<AdminRole> haveRole = adminRoleService.list(wrapper);
        List<Long> haveRoleList = haveRole.stream().map(AdminRole::getRoleId).collect(Collectors.toList());

        List<Role> assin = new ArrayList<>();

        for (Role role:allRoles){
            if (haveRoleList.contains(role.getId())){
                assin.add(role);
            }
        }

        HashMap<String, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("allRolesList",allRoles);
        objectObjectHashMap.put("assignRoles",assin);
        return objectObjectHashMap;
    }

    @Override
    public void saveUserRoleRealtionShip(Long adminId, Long[] roleId) {
        //1sh删除用户角色数据
        LambdaQueryWrapper<AdminRole> objectLambdaQueryWrapper = new LambdaQueryWrapper<>();
        objectLambdaQueryWrapper.eq(AdminRole::getAdminId,adminId);
        adminRoleService.remove(objectLambdaQueryWrapper);

        //重新分配
        ArrayList<AdminRole> list = new ArrayList<>();
        for (Long id: roleId
              ) {
            AdminRole role = new AdminRole();
            role.setAdminId(adminId);
            role.setRoleId(id);
            list.add(role);
        }
        adminRoleService.saveBatch(list);

    }
}
