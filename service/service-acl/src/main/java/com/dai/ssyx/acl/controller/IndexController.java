package com.dai.ssyx.acl.controller;

import com.dai.ssyx.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/acl/index")
@CrossOrigin //跨域
@Api(tags = "登录接口")
public class IndexController {

    /**
     * 1、请求登陆的login
     */
    @ApiOperation("登录")
    @PostMapping("login")
    public Result login() {
        Map<String,Object> map = new HashMap<>();
        map.put("token","admin-token");
        return Result.ok(map);
    }

    /**
     * 2 获取用户信息
     */
    @ApiOperation("获取登录信息")
    @GetMapping("info")
    public Result info(){
        Map<String,Object> map = new HashMap<>();
        map.put("name","atguigu");
        map.put("avatar","https://img.zcool.cn/community/015ce65ae9442ca801219b7f9a2e9b.gif");
        return Result.ok(map);
    }
    /**
     * 3 退出
     */
    @ApiOperation("退出")
    @PostMapping("logout")
    public Result logout(){
        return Result.ok(null);
    }



}
