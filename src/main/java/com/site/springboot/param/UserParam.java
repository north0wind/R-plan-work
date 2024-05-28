package com.site.springboot.param;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户密码请求对象
 *
 * @author ahsz
 */
@Data
public class UserParam implements Serializable {

    /**
     * 账户
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 确认密码
     */
    private String confirmPass;
}
