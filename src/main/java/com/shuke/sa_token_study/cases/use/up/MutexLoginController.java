package com.shuke.sa_token_study.cases.use.up;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 舒克、舒克
 * @date 2025/4/1 16:09
 * @description: 同端互斥登录    同一个账号  同一个设备  只能登录一次
 *  测试条件 ：配置文件需要把 sa-token.is-concurrent 的值改为 false
 */
@RestController
@RequestMapping("/mutexLogin")
public class MutexLoginController {

    /**
     * 登录 记录设备信息 实现同端互斥登录
     */
    @RequestMapping("/login")
    public SaResult login(long userId, String device) {
        /*
         * 参数1：要登录的账号
         * 参数2：此账号在什么设备上登录的
         */
        StpUtil.login(userId, device);
        return SaResult.ok("登录成功");
    }

    /**
     * 判断是否登录
     */
    @RequestMapping("/isLogin")
    public SaResult isLogin() {
        // StpUtil.isLogin() 查询当前客户端是否登录，返回 true 或 false
        boolean isLogin = StpUtil.isLogin();
        return SaResult.ok("当前客户端是否登录：" + isLogin + "，登录的设备是：" + StpUtil.getLoginDevice());
    }
}
