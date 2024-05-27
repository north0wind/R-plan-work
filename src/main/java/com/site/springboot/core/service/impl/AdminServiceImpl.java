package com.site.springboot.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.site.springboot.core.dao.AdminMapper;
import com.site.springboot.core.entity.Admin;
import com.site.springboot.core.service.AdminService;
import com.site.springboot.core.util.MD5Util;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {
    @Resource
    private AdminMapper adminMapper;

    @Override
    public Admin login(String userName, String password) {
        String passwordMd5 = MD5Util.MD5Encode(password, "UTF-8");
        LambdaQueryWrapper<Admin> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Admin::getLoginName, userName)
                .eq(Admin::getLoginPassword, passwordMd5)
                .eq(Admin::getLocked, 0);
        return getOne(queryWrapper);
        // return adminMapper.login(userName, passwordMd5);
    }

    @Override
    public Admin getUserDetailById(Long loginUserId) {
        return adminMapper.selectById(loginUserId);
        // return adminMapper.selectByPrimaryKey(loginUserId);
    }

    @Override
    public Boolean updatePassword(Long loginUserId, String originalPassword, String newPassword) {
        Admin adminUser = adminMapper.selectById(loginUserId);
        //当前用户非空才可以进行更改
        if (adminUser != null) {
            String originalPasswordMd5 = MD5Util.MD5Encode(originalPassword, "UTF-8");
            String newPasswordMd5 = MD5Util.MD5Encode(newPassword, "UTF-8");
            //比较原密码是否正确
            if (originalPasswordMd5.equals(adminUser.getLoginPassword())) {
                //设置新密码并修改
                adminUser.setLoginPassword(newPasswordMd5);
                //修改成功则返回true
                return this.updateById(adminUser);

            }
        }
        return false;
    }

    @Override
    public Boolean updateName(Long loginUserId, String loginUserName, String nickName) {
        Admin adminUser = adminMapper.selectById(loginUserId);
        //当前用户非空才可以进行更改
        if (adminUser != null) {
            //设置新密码并修改
            adminUser.setLoginName(loginUserName);
            adminUser.setAdminNickName(nickName);
            //修改成功则返回true
            return this.updateById(adminUser);
            // if (adminMapper.updateByPrimaryKeySelective(adminUser) > 0) {
            //     //修改成功则返回true
            //     return true;
            // }
        }
        return false;
    }
}
