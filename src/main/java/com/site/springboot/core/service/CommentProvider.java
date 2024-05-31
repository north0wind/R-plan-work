package com.site.springboot.core.service;

import com.site.springboot.core.config.FeignConfig;
import com.site.springboot.core.util.Result;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "comments-provider",configuration = FeignConfig.class)
public interface CommentProvider {

    @GetMapping("/admin/comments")
    String list(HttpServletRequest request);

    @GetMapping("/admin/comments/list")
    Result list(@RequestParam Map<String, Object> params);

    @PostMapping("/admin/comments/checkDone")
    Result checkDone(@RequestBody Integer[] ids);

    @PostMapping("/admin/comments/delete")
    Result deleteBatch(@RequestBody Integer[] ids);
}