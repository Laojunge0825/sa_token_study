package com.shuke.sa_token_study.satoken;

import cn.dev33.satoken.model.wrapperInfo.SaDisableWrapperInfo;
import cn.dev33.satoken.stp.StpInterface;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 舒克、舒克
 * @date 2025/3/18 16:54
 * @description: 实现 SaToken 的 StpInterface 接口，获取当前帐号权限码集合
 */
@Service
public class StpInterfaceImpl implements StpInterface {

    /**
     * 返回一个账号所拥有的权限码集合
     * loginId：账号id，即你在调用 StpUtil.login(id) 时写入的标识值。
     * loginType：账号体系标识，此处可以暂时忽略，在 [ 多账户认证 ] 章节下会对这个概念做详细的解释。
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        // 本 list 仅做模拟，实际项目中要根据具体业务逻辑来查询权限
        List<String> list = new ArrayList<String>();
        list.add((String) loginId);
        list.add("user.add");
        list.add("user.update");
        list.add("user.get");
        // list.add("user.delete");
        list.add("art.*");
        return list;
    }

    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        // 本 list 仅做模拟，实际项目中要根据具体业务逻辑来查询角色
        List<String> list = new ArrayList<String>();
        list.add("admin");
        list.add("super-admin");
        return list;
    }

    /**
     * 返回一个账号的封禁信息，包含：
     * isDisable：是否封禁 (true=已封禁)
     * disableTime：封禁时间 (毫秒时间戳)
     * disableReason：封禁原因
     */
    @Override
    public SaDisableWrapperInfo isDisabled(Object loginId, String service) {
        return StpInterface.super.isDisabled(loginId, service);
    }
}
