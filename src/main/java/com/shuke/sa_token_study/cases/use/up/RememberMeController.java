package com.shuke.sa_token_study.cases.use.up;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 舒克、舒克
 * @date 2025/3/31 16:13
 * @description: 登录 记住我
 */
@RestController
@RequestMapping("/rememberMe")
public class RememberMeController {

    /**
     * 记住我
     */
    @RequestMapping("/doLogin01")
    public SaResult rememberMe(String name, String pwd ){
        if("zhang".equals(name) && "123456".equals(pwd)) {
            // 为true时，代表记住我，下次登录时，会自动登录，无需再次输入账号密码 过期时间是30天
            StpUtil.login(10001,true);
        }
        return SaResult.ok();
    }

    /**
     * 不记住我 浏览器关闭后需要重新登录
     */
    @RequestMapping("/doLogin02")
    public SaResult noRememberMe(String name, String pwd ){
        if("zhang".equals(name) && "123456".equals(pwd)) {
            // 为false时，代表不记住我，下次登录时，需要再次输入账号密码
            StpUtil.login(10001,false);
        }
        return SaResult.ok();
    }

    @RequestMapping("/doLogin03")
    public SaResult rememberMeSeven(String name, String pwd ){
        if("zhang".equals(name) && "123456".equals(pwd)) {
            // 7天过期时间 单位：秒
            StpUtil.login(10001,60 * 60 * 24 * 7);
        }
        return SaResult.ok();
    }


}
