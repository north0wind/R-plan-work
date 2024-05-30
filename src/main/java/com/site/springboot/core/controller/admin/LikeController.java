package com.site.springboot.core.controller.admin;

import com.site.springboot.core.entity.News;
import com.site.springboot.core.service.LikeService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Set;

/**
 * @author xiaolong
 * @date 2024/5/30
 */
@RestController
@RequestMapping("/detail")
public class LikeController {

    @Autowired
    private LikeService likeService;
    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping("/like/{newsId}")
    public ResponseEntity<?> likeNews(@PathVariable("newsId") Long newsId, @RequestParam("userId") String userId) {
        redisTemplate.opsForSet().add("like:news" + newsId, userId);
        return ResponseEntity.ok("Liked");
    }

    @PostMapping("/unlike/{newsId}")
    public ResponseEntity<?> unlikeNews(@PathVariable("newsId") Long newsId, @RequestParam("userId") String userId) {
        redisTemplate.opsForSet().remove("like:news" + newsId, userId);
        return ResponseEntity.ok("Unliked");
    }

    @GetMapping("/isLiked/{newsId}")
    public ResponseEntity<Boolean> isLiked(@PathVariable("newsId") Long newsId, @RequestParam("userId") String userId) {
        Boolean isLiked = redisTemplate.opsForSet().isMember("like:news" + newsId, userId);
        return ResponseEntity.ok(isLiked);
    }

    @GetMapping("/likes/{newsId}")
    public ResponseEntity<Set<String>> getLikes(@PathVariable("newsId") Long newsId) {
        Set<String> likes = redisTemplate.opsForSet().members("like:news" + newsId);
        return ResponseEntity.ok(likes);
    }

    @GetMapping("/likes/count/{newsId}")
    public ResponseEntity<Long> getLikesCount(@PathVariable("newsId") Long newsId) {
        long count = redisTemplate.opsForSet().size("like:news" + newsId);
        return ResponseEntity.ok(count);
    }
}
