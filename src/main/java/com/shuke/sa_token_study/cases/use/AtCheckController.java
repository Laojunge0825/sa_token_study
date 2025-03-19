package com.shuke.sa_token_study.cases.use;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaMode;
import cn.dev33.satoken.util.SaResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 舒克、舒克
 * @date 2025/3/19 16:24
 * @description: 注解鉴权
 */
@RestController
@RequestMapping("/atCheck/")
@Slf4j
public class AtCheckController {

    /**
     * 注解鉴权示例
     * 注意：此注解只能标注在方法上，不能标注在类上，否则会报错。
     */
    @SaCheckLogin
    @RequestMapping("checkLogin")
    public SaResult checkLogin() {
        // 通过注解鉴权后才能进入方法 ...
        log.info("checkLogin ： 通过注解鉴权后才能进入方法...");
        return SaResult.ok();
    }

    /**
     * 校验权限  只用拥有 user.add 权限的 账号 才能访问此方法
     */
    @SaCheckPermission("user.add")
    @RequestMapping("checkPermission")
    public SaResult checkPermission() {
        log.info("checkPermission ： 只用拥有 add 权限的 user 才能访问此方法...");
        return SaResult.ok();
    }

    /**
     * 校验权限  一次性校验多个权限，必须全部拥有才可以进入
     * mode = SaMode.AND 表示必须全部拥有 mode = SaMode.OR 表示只要有一个就可以进入
     */
    @SaCheckPermission(value = {"user.add", "user.delete", "user.update"}, mode = SaMode.AND)
    @RequestMapping("checkPermission2")
    public SaResult checkPermission2() {
        log.info("checkPermission2 ： 一次性校验多个权限，必须全部拥有才能访问此方法...");
        return SaResult.ok();
    }

    /**
     * 校验角色  必须是 super-admin 角色才能访问此方法
     */
    @SaCheckRole("super-admin")
    @RequestMapping("checkRole")
    public SaResult checkRole() {
        log.info("checkRole ： 角色校验，只用拥有 super-admin 角色的账号才能访问此方法...");
        return SaResult.ok();
    }

    /**
     * 角色权限双校验
     * orRole = "admin" 表示如果是 admin 角色，也可以访问此方法
     */
    @RequestMapping("userAdd")
    @SaCheckPermission(value = "user.add", orRole = "admin")
    public SaResult userAdd() {
        log.info("userAdd ： 角色权限双校验，拥有 user.add 权限，或者是 admin 角色的账号才能访问此方法...");
        return SaResult.data("用户信息");
    }
}
