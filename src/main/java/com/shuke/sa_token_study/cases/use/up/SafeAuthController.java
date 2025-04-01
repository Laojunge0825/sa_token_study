package com.shuke.sa_token_study.cases.use.up;

import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 舒克、舒克
 * @date 2025/4/1 14:42
 * @description: 二级认证
 */
@RestController
@RequestMapping("/safeAuth")
public class SafeAuthController {

    /**
     * 二级认证示例
     */
    @RequestMapping("/deleteProject")
    public SaResult deleteProject(String projectId) {
        // 1. 检查当前用户是否已经通过二级认证
        if (StpUtil.isSafe()) {
            // 2. 已经通过二级认证，执行删除操作

            // ...
            return SaResult.ok("删除成功");
        }   else {
            // 3. 未通过二级认证，返回错误信息
            return SaResult.error("未通过二级认证，无法删除项目");
        }
    }


    /**
     * 打开二级认证
     */
    @RequestMapping("/openSafe")
    public SaResult openSafe(String password) {
        // 比较密码 二级验证块
        if ("123456".equals(password)) {
            // 比对成功，为当前会话打开二级认证，有效期为120秒，意为在120秒内再调用 deleteProject 接口都无需提供密码
            StpUtil.openSafe(120);
            return SaResult.ok("二级认证成功");
        } else {
            return SaResult.error("密码错误，二级认证失败");
        }
    }

    /**
     * 关闭二级认证
     */
    @RequestMapping("/closeSafe")
    public SaResult closeSafe() {
        StpUtil.closeSafe();
        return SaResult.ok("二级认证关闭");
    }

    // ---------- 指定业务类型进行二次验证 ------------

    /**
     * 获取应用密钥
     */
    @RequestMapping("/getClientSecret")
    public SaResult getClientSecret() {
        // 第1步，先检查当前会话是否已完成 client业务 的二级认证
        StpUtil.checkSafe("client");

        // 第2步，如果已完成二级认证，则返回数据
        return SaResult.data("aaaa-bbbb-cccc-dddd-eeee");
    }

    // 提供手势密码进行二级认证
    @RequestMapping("openClientSafe")
    public SaResult openClientSafe(String gesture) {
        // 比对手势密码（此处只是举例，真实项目时可拿其它参数进行校验）
        if("35789".equals(gesture)) {

            // 比对成功，为当前会话打开二级认证：
            // 业务类型为：client
            // 有效期为60秒==1分钟，意为在1分钟内，调用 getClientSecret 时都无需再提供手势密码
            StpUtil.openSafe("client", 60);
            return SaResult.ok("二级认证成功");
        }

        // 如果密码校验失败，则二级认证也会失败
        return SaResult.error("二级认证失败");
    }

    // 查询当前会话是否已完成指定的二级认证
    @RequestMapping("isClientSafe")
    public SaResult isClientSafe() {
        return SaResult.ok("当前是否已完成 client 二级认证：" + StpUtil.isSafe("client"));
    }


}
