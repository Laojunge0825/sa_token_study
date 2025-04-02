package com.shuke.sa_token_study.satoken;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 舒克、舒克
 * @date 2025/3/19 16:25
 * @description: SaToken 权限认证 配置类
 */
@Configuration
public class SaTokenConfigure implements WebMvcConfigurer {

    /**
     * 注册 Sa-Token 拦截器打开注解鉴权功能
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册 Sa-Token 拦截器，打开注解式鉴权功能
        registry.addInterceptor(new SaInterceptor( handler -> {
            // 指定 [拦截路由] 与 [放行路由]
            SaRouter.match("/**")
                    .notMatch("/loginAuth/doLogin")
                    .notMatch("/loginAuth/isLogin")
                    .notMatch("/rememberMe/doLogin01")
                    .notMatch("/rememberMe/doLogin02")
                    .notMatch("/rememberMe/doLogin03")
                    .notMatch("/searchSession/login")
                    .notMatch("/mutexLogin/*")
                    .check(r -> StpUtil.checkLogin());
        }));
    }
}
