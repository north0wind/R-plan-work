package com.site.springboot.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.site.springboot.core.dao.NewsCommentMapper;
import com.site.springboot.core.entity.News;
import com.site.springboot.core.entity.NewsComment;
import com.site.springboot.core.service.CommentService;
import com.site.springboot.core.util.PageQueryUtil;
import com.site.springboot.core.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class CommentServiceImpl extends ServiceImpl<NewsCommentMapper, NewsComment> implements CommentService {
    @Autowired
    private NewsCommentMapper newsCommentMapper;

    @Override
    public Boolean addComment(NewsComment newsComment) {
        return this.save(newsComment);
        // return newsCommentMapper.insertSelective(newsComment) > 0;
    }

    @Override
    public PageResult getCommentsPage(PageQueryUtil pageUtil) {
        List<NewsComment> comments = this.findNewsCommentList(pageUtil);
        int total = this.getTotalNewsComments(pageUtil);
        PageResult pageResult = new PageResult(comments, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }
    public List<NewsComment> findNewsCommentList(Map<String, Object> params) {
        QueryWrapper<NewsComment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted", 0).orderByDesc("comment_id");

        // 如果存在start和limit参数，模拟分页查询，然后取所有页的数据
        int start = (int) params.getOrDefault("start", 0);
        int limit = (int) params.getOrDefault("limit", Integer.MAX_VALUE);
        queryWrapper.last("limit " + start + ", " + limit);

        return list(queryWrapper);
    }
    public int getTotalNewsComments(Map<String, Object> params) {
        QueryWrapper<NewsComment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted", 0);

        return (int)count(queryWrapper);
    }


    @Override
    public Boolean checkDone(Integer[] ids) {
        // List<Integer> ids = Arrays.asList(commentIds);
        QueryWrapper<NewsComment> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("comment_id", ids).eq("comment_status", 0);

        List<NewsComment> comments = list(queryWrapper);
        List<NewsComment> commentsToUpdate = new ArrayList<>();

        for (NewsComment comment : comments) {
            comment.setCommentStatus((byte) 1);
            commentsToUpdate.add(comment);
        }
        return updateBatchById(commentsToUpdate);
        // return newsCommentMapper.checkDone(ids) > 0;
    }

    @Override
    public Boolean deleteBatch(Integer[] ids) {
        return this.removeByIds(Arrays.asList(ids));
        // return newsCommentMapper.deleteBatch(ids) > 0;
    }

    @Override
    public List<NewsComment> getCommentsByNewsId(Long newsId) {
        QueryWrapper<NewsComment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("news_id", newsId)
                .eq("is_deleted",0);
        List<NewsComment> result = this.list(queryWrapper);
        return result;
    }

    @Override
    public PageResult getCommentsLatest(PageQueryUtil pageUtil,Long newsId) {
        List<NewsComment> comments = this.findNewsCommentLatest(pageUtil,newsId);
        int total = this.getTotalNewsComments(pageUtil);
        PageResult pageResult = new PageResult(comments, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }
    //根据新闻id查找最新的十条评论
    public List<NewsComment> findNewsCommentLatest(Map<String, Object> params,Long newsId) {
        QueryWrapper<NewsComment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("news_id", newsId)
                .eq("is_deleted", 0)
                .eq("comment_status", 1)
                .orderByDesc("create_time")
                .last("limit 10");

        // 如果存在start和limit参数，模拟分页查询，然后取所有页的数据
        int start = (int) params.getOrDefault("start", 0);
        int limit = (int) params.getOrDefault("limit", Integer.MAX_VALUE);
        queryWrapper.last("limit " + start + ", " + limit);

        return list(queryWrapper);
    }
}
