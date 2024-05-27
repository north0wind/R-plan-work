package com.site.springboot.core.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.site.springboot.core.entity.NewsCategory;
import com.site.springboot.core.util.PageQueryUtil;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface NewsCategoryMapper extends BaseMapper<NewsCategory> {

    List<NewsCategory> findCategoryList(PageQueryUtil pageUtil);

    int getTotalCategories(PageQueryUtil pageUtil);

    int deleteByPrimaryKey(Long categoryId);

    int insert(NewsCategory record);

    int insertSelective(NewsCategory record);

    NewsCategory selectByPrimaryKey(Long categoryId);

    NewsCategory selectByCategoryName(String categoryName);

    int updateByPrimaryKeySelective(NewsCategory record);

    int updateByPrimaryKey(NewsCategory record);

    int deleteBatch(Integer[] ids);
}