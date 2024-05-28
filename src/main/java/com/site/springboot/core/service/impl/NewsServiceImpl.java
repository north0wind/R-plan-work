package com.site.springboot.core.service.impl;

import com.alibaba.excel.util.ListUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.site.springboot.core.dao.NewsMapper;
import com.site.springboot.core.entity.News;
import com.site.springboot.core.poi.NewsExcel;
import com.site.springboot.core.service.CategoryService;
import com.site.springboot.core.service.NewsService;
import com.site.springboot.core.util.PageQueryUtil;
import com.site.springboot.core.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class NewsServiceImpl extends ServiceImpl<NewsMapper, News> implements NewsService {

    @Autowired
    private NewsMapper newsMapper;
    @Autowired
    private CategoryService categoryService;

    @Override
    public String saveNews(News news) {
        return this.save(news) ? "success" : "保存失败";
    }

    @Override
    public PageResult getNewsPage(PageQueryUtil pageUtil) {
        List<News> newsList = this.findNewsList(pageUtil);
        int total = this.getTotalNews(pageUtil);
        PageResult pageResult = new PageResult(newsList, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }
    public List<News> findNewsList(Map<String, Object> params) {
        QueryWrapper<News> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted", 0).orderByDesc("news_id");

        // 如果存在start和limit参数，模拟分页查询，然后取所有页的数据
        int start = (int) params.getOrDefault("start", 0);
        int limit = (int) params.getOrDefault("limit", Integer.MAX_VALUE);
        queryWrapper.last("limit " + start + ", " + limit);

        return list(queryWrapper);
    }
    public int getTotalNews(Map<String, Object> params) {
        QueryWrapper<News> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted", 0);

        return (int)count(queryWrapper);
    }

    @Override
    public Boolean deleteBatch(Integer[] ids) {
        return this.removeByIds(Arrays.asList(ids));
    }

    @Override
    public News queryNewsById(Long newsId) {
        return this.getById(newsId);
        // return newsMapper.selectByPrimaryKey(newsId);
    }

    @Override
    public String updateNews(News news) {
        News newsForUpdate = this.getById(news.getNewsId());
        if (newsForUpdate == null) {
            return "数据不存在";
        }
        news.setNewsCategoryId(news.getNewsCategoryId());
        news.setNewsContent(news.getNewsContent());
        news.setNewsCoverImage(news.getNewsCoverImage());
        news.setNewsStatus(news.getNewsStatus());
        news.setNewsTitle(news.getNewsTitle());
        news.setUpdateTime(new Date());
        return this.updateById(news) ? "success" : "修改失败";
        // if (newsMapper.updateByPrimaryKeySelective(news) > 0) {
        //     return "success";
        // }
        // return "修改失败";
    }

    @Override
    public List<NewsExcel> getData() {
        List<News> newsList = this.list();

        List<NewsExcel> list = ListUtils.newArrayList();
        for (int i = 0; i < newsList.size(); i++) {
            News news = newsList.get(i);
            NewsExcel data = new NewsExcel();
            data.setNewsTitle(news.getNewsTitle());
            data.setNewsCategory(categoryService.getCategoryById(news.getNewsCategoryId()).getCategoryName());
            data.setNewsCoverImage(news.getNewsCoverImage());
            data.setNewsContent(news.getNewsContent());
            if (news.getNewsStatus() == 1){
                data.setNewsStatus("已发布");
            }else {
                data.setNewsStatus("草稿");
            }
            data.setNewsViews(news.getNewsViews());
            data.setCreateTime(news.getCreateTime());
            list.add(data);
        }
        return list;
    }

}
