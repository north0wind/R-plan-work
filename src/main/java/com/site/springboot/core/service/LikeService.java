package com.site.springboot.core.service;

/**
 * @author xiaolong
 * @date 2024/5/30
 */
public interface LikeService {

    /**
     * 点赞
     * @param newsId
     * @return
     */
    String likeNews(Long newsId,Long userId);

    /**
     * 取消点赞
     * @param newsId
     * @return
     */
    String unlikeNews(Long newsId,Long userId);

    /**
     * 获取新闻点赞数
     * @param newsId
     * @return
     */
    Long getNewsLikes(Long newsId);

    /**
     * 判断是否点赞
     * @param newsId
     * @return
     */
    Boolean isLiked(Long newsId,Long userId);
}
