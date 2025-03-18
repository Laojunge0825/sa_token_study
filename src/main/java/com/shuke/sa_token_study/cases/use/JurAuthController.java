package com.shuke.sa_token_study.cases.use;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 舒克、舒克
 * @date 2025/3/18 16:52
 * @description: SaToken 权限认证
 */
@RestController
@RequestMapping("/jur/")
public class JurAuthController {

    /**
     * 查询当前登录账号的权限信息
     */
    @RequestMapping("getPermission")
    public SaResult getPermission() {
        // 查询权限信息 ，如果当前会话未登录，会返回一个空集合
        List<String> permissionList = StpUtil.getPermissionList();
        System.out.println("当前登录账号拥有的所有权限：" + permissionList);

        // 查询角色信息 ，如果当前会话未登录，会返回一个空集合
        List<String> roleList = StpUtil.getRoleList();
        System.out.println("当前登录账号拥有的所有角色：" + roleList);

        // 返回给前端
        return SaResult.ok()
                .set("roleList", roleList)
                .set("permissionList", permissionList);
    }

    /**
     * 校验当前登录账号是否具备指定权限
     */
    @RequestMapping("checkPermission")
    public SaResult checkPermission() {

        // 判断：当前账号是否拥有一个权限，返回 true 或 false
        // 		如果当前账号未登录，则永远返回 false
        StpUtil.hasPermission("user.add");
        StpUtil.hasPermissionAnd("user.add", "user.delete", "user.get");  // 指定多个，必须全部拥有才会返回 true
        StpUtil.hasPermissionOr("user.add", "user.delete", "user.get");	 // 指定多个，只要拥有一个就会返回 true

        // 校验：当前账号是否拥有一个权限，校验不通过时会抛出 `NotPermissionException` 异常
        // 		如果当前账号未登录，则永远校验失败
        StpUtil.checkPermission("user.add");
//        StpUtil.checkPermissionAnd("user.add", "user.delete", "user.get");  // 指定多个，必须全部拥有才会校验通过
        StpUtil.checkPermissionOr("user.add", "user.delete", "user.get");  // 指定多个，只要拥有一个就会校验通过

        return SaResult.ok();
    }

    /**
     * 校验当前登录账号是否具备指定角色
     */
    @RequestMapping("checkRole")
    public SaResult checkRole() {

        // 判断：当前账号是否拥有一个角色，返回 true 或 false
        // 		如果当前账号未登录，则永远返回 false
        StpUtil.hasRole("admin");
        StpUtil.hasRoleAnd("admin", "ceo", "cfo");  // 指定多个，必须全部拥有才会返回 true
        StpUtil.hasRoleOr("admin", "ceo", "cfo");	  // 指定多个，只要拥有一个就会返回 true

        // 校验：当前账号是否拥有一个角色，校验不通过时会抛出 `NotRoleException` 异常
        // 		如果当前账号未登录，则永远校验失败
        StpUtil.checkRole("admin");
        StpUtil.checkRoleAnd("admin", "ceo", "cfo");  // 指定多个，必须全部拥有才会校验通过
        StpUtil.checkRoleOr("admin", "ceo", "cfo");  // 指定多个，只要拥有一个就会校验通过

        return SaResult.ok();
    }
}
