package com.shuke.sa_token_study.cases.use.up;


import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.shuke.sa_token_study.model.SysUser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 舒克、舒克
 * @date 2025/4/2 14:29
 * @description SAToken 会话查询控制类
 */
@RestController
@RequestMapping("/searchSession")
public class SearchSessionController {

    /**
     * 模拟登录多个账号
     */
    @RequestMapping("/login")
    public SaResult login(){
        for (int i = 0; i < 5; i++) {
            SysUser user = new SysUser();
            user.setId(i);
            user.setName("user:" + i);
            user.setAge(10 + i);
            StpUtil.login("user:" + i);
        }
        return SaResult.ok("登录成功");
    }

    /**
     * 查询指定类型的会话列表
     * @param key 关键字
     * @param start 起始位置
     * @param end 结束位置
     * @return 会话列表
     */
    @RequestMapping("/getSessionList")
    public SaResult getSessionList(String key ,int start, int end){
        List<String> sessionIdList = StpUtil.searchSessionId(key, start, end, false);
        return SaResult.data(sessionIdList);
    }

    /**
     * 查询指定类型的Token列表
     * @param key 关键字
     * @param start 起始位置
     * @param end 结束位置
     * @return Token列表
     */
    @RequestMapping("/getTokenList")
    public SaResult getTokenList(String key ,int start, int end){
        List<String> getTokenList = StpUtil.searchTokenValue(key, start, end, false);
        return SaResult.data(getTokenList);
    }




}
