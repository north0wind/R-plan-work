package com.comment.springboot.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.comment.springboot.core.entity.NewsComment;
import org.springframework.stereotype.Component;

@Component
public interface NewsCommentMapper extends BaseMapper<NewsComment> {
    // int insert(NewsComment record);
    //
    // int insertSelective(NewsComment record);
    //
    // NewsComment selectByPrimaryKey(Long commentId);
    //
    // int updateByPrimaryKeySelective(NewsComment record);
    //
    // int updateByPrimaryKey(NewsComment record);
    //
    // List<NewsComment> findNewsCommentList(Map map);
    //
    // int getTotalNewsComments(Map map);
    //
    // int checkDone(Integer[] ids);
    //
    // int deleteBatch(Integer[] ids);
}