package com.shuke.sa_token_study.cases.use;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 舒克、舒克
 * @date 2025/3/19 15:26
 * @description: 强制注销 和 踢人下线
 */
@RestController
@RequestMapping("/kickout/")
public class KickoutController {

    /**
     * 强制注销
     */
    @RequestMapping("logout")
    public SaResult logout(long userId) {

        // 强制注销等价于对方主动调用了注销方法，再次访问会提示：Token无效。
        StpUtil.logout(userId);
        // 强制指定账号指定端注销下线，例如：
        // StpUtil.logout(10001, "PC");

        // 返回
        return SaResult.ok();
    }

    /**
     * 踢人下线
     */
    @RequestMapping("kickout")
    public SaResult kickout(long userId) {

        // 踢人下线不会清除Token信息，而是将其打上特定标记，再次访问会提示：Token已被踢下线。
        StpUtil.kickout(userId);

        // 返回
        return SaResult.ok();
    }

    /**
     * 根据Token值踢人下线
     */
    @RequestMapping("kickoutByTokenValue")
    public SaResult kickoutByTokenValue(String tokenValue) {

        StpUtil.kickoutByTokenValue(tokenValue);

        // 返回
        return SaResult.ok();
    }
}
