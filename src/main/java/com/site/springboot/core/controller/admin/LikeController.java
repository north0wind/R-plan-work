package com.site.springboot.core.controller.admin;

import com.site.springboot.core.service.LikeService;
import com.site.springboot.core.util.Result;
import com.site.springboot.core.util.ResultGenerator;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/like")
    public Result likeNews(@RequestParam("newsId") Long newsId, HttpServletRequest request ) {
        Long userId;
        try {
            Object loginUserIdObj = request.getSession().getAttribute("loginUserId");

            if (loginUserIdObj != null){
                userId = Long.valueOf(loginUserIdObj.toString());
            }else {
                throw new RuntimeException("用户未登录");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        String result = likeService.likeNews(newsId, userId);
        if ("success".equals(result)){
            return ResultGenerator.genSuccessResult("点赞成功");
        }else{
            return ResultGenerator.genFailResult(result);
        }
    }

    @PostMapping("/unlike")
    public Result unlikeNews(@RequestParam("newsId") Long newsId, HttpServletRequest request) {
        Long userId;
        try {
            Object loginUserIdObj = request.getSession().getAttribute("loginUserId");

            if (loginUserIdObj != null){
                userId = Long.valueOf(loginUserIdObj.toString());
            }else {
                throw new RuntimeException("用户未登录");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        String result = likeService.unlikeNews(newsId, userId);
        if ("success".equals(result)){
            return ResultGenerator.genSuccessResult("取消点赞成功");
        }else{
            return ResultGenerator.genFailResult(result);
        }
    }

    @GetMapping("/isLiked")
    public Result isLiked(@RequestParam("newsId") Long newsId, HttpServletRequest request) {
        Long userId;
        try {
            Object loginUserIdObj = request.getSession().getAttribute("loginUserId");
            if (loginUserIdObj != null){
                userId = Long.valueOf(loginUserIdObj.toString());
            }else {
                throw new RuntimeException("用户未登录");
            }
            return ResultGenerator.genSuccessResult( likeService.isLiked(newsId, userId));
        } catch (Exception e) {
            return ResultGenerator.genFailResult("获取点赞状态失败");
        }
    }

    @GetMapping("/likes")
    public Result getLikes(@PathVariable("newsId") Long newsId) {
        try {
            return ResultGenerator.genSuccessResult(likeService.getNewsLikes(newsId));
        } catch (Exception e) {
            return ResultGenerator.genFailResult("获取点赞数失败");
        }
    }

}
