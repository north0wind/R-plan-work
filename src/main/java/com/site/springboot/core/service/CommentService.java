package com.site.springboot.core.service;

import com.site.springboot.core.entity.NewsComment;
import com.site.springboot.core.util.PageQueryUtil;
import com.site.springboot.core.util.PageResult;

import java.util.List;

public interface CommentService {
    /**
     * 添加评论
     *
     * @param newsComment
     * @return
     */
    Boolean addComment(NewsComment newsComment);

    /**
     * 后台管理系统中评论分页功能
     *
     * @param pageUtil
     * @return
     */
    PageResult getCommentsPage(PageQueryUtil pageUtil);

    /**
     * 批量审核
     *
     * @param ids
     * @return
     */
    Boolean checkDone(Integer[] ids);

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    Boolean deleteBatch(Integer[] ids);

    /**
     * 根据newsId查询评论
     *
     * @param newsId
     * @return
     */
    List<NewsComment> getCommentsByNewsId(Long newsId);

    /**
     * 根据newsId查询最新10条评论
     *
     * @param pageUtil
     * @return
     */
    PageResult getCommentsLatest(PageQueryUtil pageUtil,Long newsId);
}
