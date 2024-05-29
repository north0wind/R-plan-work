package com.site.springboot.core.vo;

import com.site.springboot.core.entity.News;
import com.site.springboot.core.entity.NewsComment;
import lombok.Data;

import java.util.List;

/**
 * @author xiaolong
 * @date 2024/5/29
 */
@Data
public class NewsVO extends News {
    /**
     * 评论
     */
    List<NewsComment> comments;
}
