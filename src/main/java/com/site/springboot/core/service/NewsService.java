package com.site.springboot.core.service;

import com.site.springboot.core.entity.News;
import com.site.springboot.core.poi.NewsExcel;
import com.site.springboot.core.util.PageQueryUtil;
import com.site.springboot.core.util.PageResult;
import com.site.springboot.core.vo.NewsDetail;

import java.util.List;

public interface NewsService {
    News saveNews(News news);

    PageResult getNewsPage(PageQueryUtil pageUtil);

    Boolean deleteBatch(Integer[] ids);

    /**
     * 根据id获取详情
     *
     * @param newsId
     * @return
     */
    News queryNewsById(Long newsId);

    /**
     * 后台修改
     *
     * @param news
     * @return
     */
    String updateNews(News news);

    List<NewsExcel> getData();

    /**
     * 首页最新新闻
     *
     * @return
     */
    PageResult getLatestNews(PageQueryUtil pageUtil);

    /**
     * 根据关键字模糊查询
     *
     * @param keyword
     * @return
     */
    List<News> findNewsByKeyword(String keyword);

    /**
     * 根据id获取详情
     *
     * @param newsId
     * @return
     */
    NewsDetail getNewsAndComments(Long newsId);
}
