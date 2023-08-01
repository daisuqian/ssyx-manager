package com.dai.ssyx.acl.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dai.ssyx.common.result.Result;
import com.dai.ssyx.acl.service.RoleService;
import com.dai.ssyx.model.acl.Role;
import com.dai.ssyx.vo.acl.RoleQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "角色接口")
@RestController
@RequestMapping("/admin/acl/role")
@CrossOrigin
public class RoleController {
    @Autowired
    private RoleService roleService;

    @ApiOperation(value = "获取角色分页列表")
    @GetMapping("{page}/{limit}")
    public Result index(
                        @ApiParam(name = "page", value = "当前页码", required = true)
                        @PathVariable Long page,
                        @ApiParam(name = "limit", value = "每页记录数", required = true)
                        @PathVariable Long limit,
                        @ApiParam(name = "roleQueryVo", value = "查询对象", required = false)
                        RoleQueryVo roleQueryVo) {
        Page<Role> pageParam = new Page<>(page, limit);
        IPage<Role> pageModel = roleService.selectRolePage(pageParam, roleQueryVo);
        return Result.ok(pageModel);
    }

    @ApiOperation(value = "根据id查询")
    @GetMapping("get/{id}")
    public Result get(
            @ApiParam(name = "id", value = "角色id", required = true)
            @PathVariable Long id) {
        Role role = roleService.getById(id);
        return Result.ok(role);
    }

    @ApiOperation(value = "增加")
    @PostMapping("save")
    public Result save(
            @ApiParam(name = "role", value = "Role", required = true)
            @RequestBody Role role) {
        roleService.save(role);
        return Result.ok(null);
    }
    @ApiOperation(value = "删除")
    @DeleteMapping("remove/{id}")
    public Result remove(
            @ApiParam(name = "id", value = "RoleId", required = true)
            @PathVariable Long id) {
        roleService.removeById(id);
        return Result.ok(null);
    }

    @ApiOperation(value = "更新role")
    @PutMapping("update")
    public Result updateById(
            @ApiParam(name = "id", value = "RoleId", required = true)
            @RequestBody Role role) {
        roleService.updateById(role);
        return Result.ok(null);
    }

    @ApiOperation(value = "根据id列表删除角色")
    @DeleteMapping("batchRemove")
    public Result batchRemove(@RequestBody List<Long> idList) {
        roleService.removeByIds(idList);
        return Result.ok(null);
    }
}
