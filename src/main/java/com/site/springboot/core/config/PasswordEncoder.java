package com.site.springboot.core.config;

import org.springframework.stereotype.Component;

/**
 * @author xiaolong
 * @date 2024/5/27
 */
@Component
public interface PasswordEncoder {
    String encode(CharSequence rawPassword);
    boolean matches(CharSequence rawPassword,String encodedPassword);
}
