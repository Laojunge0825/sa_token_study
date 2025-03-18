package com.shuke.sa_token_study.current;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import cn.dev33.satoken.util.SaResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author 舒克、舒克
 * @date 2025/3/18 17:15
 * @description: 全局异常处理
 */
@RestControllerAdvice
@Slf4j
public class GlobalException {

    @ExceptionHandler(NotLoginException.class)
    public SaResult notLoginException(NotLoginException e) {
        // 打印堆栈，以供调试
        log.error(e.getMessage());
        // 返回给前端
        return SaResult.error(e.getMessage());
    }

    // 拦截：缺少权限异常
    @ExceptionHandler(NotPermissionException.class)
    public SaResult handlerException(NotPermissionException e) {
        log.error(e.getMessage());
        return SaResult.error("缺少权限：" + e.getPermission());
    }

    // 拦截：缺少角色异常
    @ExceptionHandler(NotRoleException.class)
    public SaResult handlerException(NotRoleException e) {
        e.printStackTrace();
        return SaResult.error("缺少角色：" + e.getRole());
    }

}
