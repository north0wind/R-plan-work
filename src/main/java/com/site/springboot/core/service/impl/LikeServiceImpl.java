package com.site.springboot.core.service.impl;

import com.site.springboot.core.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author xiaolong
 * @date 2024/5/30
 */
@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public String likeNews(Long newsId, Long userId) {
        String key = "news:" + newsId + ":likes";
        Long result = redisTemplate.opsForSet().add(key, userId);
        if (result != null && result == 1){
            redisTemplate.opsForValue().increment(key + ":count");
            return "success";
        }else {
            return "点赞缓存失败";
        }
    }

    @Override
    public String unlikeNews(Long newsId,Long userId) {
        String key = "news:" + newsId + ":likes";
        Long result = redisTemplate.opsForSet().remove(key, userId);
        if (result != null && result == 1){
            redisTemplate.opsForValue().increment(key + ":count");
            return "success";
        }else {
            return "取消点赞缓存失败";
        }
    }

    @Override
    public Long getNewsLikes(Long newsId) {
        Long count = (Long) redisTemplate.opsForValue().get("news:" + newsId + ":likes:count");
        if (count == null){
            return 0L;
        }
        return count;
    }

    @Override
    public Boolean isLiked(Long newsId,Long userId) {
        Boolean result = redisTemplate.opsForSet().isMember("news:" + newsId + ":likes", userId);
        return result;
    }
}
