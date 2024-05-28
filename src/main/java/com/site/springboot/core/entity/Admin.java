package com.site.springboot.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("tb_admin_user")
public class Admin {
    /**
     * 主键
     */
    @TableId(value = "admin_id", type = IdType.AUTO)
    private Long adminId;

    private String loginName;

    private String loginPassword;

    private String adminNickName;

    private Byte locked;


}