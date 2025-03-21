package com.shuke.sa_token_study.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 舒克、舒克
 * @date 2025/3/21 10:09
 * @description: 模拟用户
 */
@Data
public class SysUser implements Serializable {

    private int id;

    private String name;

    private int age;

    private static final long SerialVersionUID = 1L;
}
