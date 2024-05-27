package com.site.springboot.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.site.springboot.core.dao.NewsCategoryMapper;
import com.site.springboot.core.entity.NewsCategory;
import com.site.springboot.core.entity.NewsComment;
import com.site.springboot.core.service.CategoryService;
import com.site.springboot.core.util.PageQueryUtil;
import com.site.springboot.core.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl extends ServiceImpl<NewsCategoryMapper, NewsCategory> implements CategoryService {

    @Autowired
    private NewsCategoryMapper newsCategoryMapper;

    @Override
    public List<NewsCategory> getAllCategories() {
        QueryWrapper<NewsCategory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted",0).orderByDesc("category_id");
        return list(queryWrapper);
    }


    @Override
    public NewsCategory queryById(Long id) {
        return this.getById(id);
    }

    @Override
    public PageResult getCategoryPage(PageQueryUtil pageUtil) {
        List<NewsCategory> categoryList = this.findCategoryList(pageUtil);
        int total = this.getTotalCategories(pageUtil);
        PageResult pageResult = new PageResult(categoryList, total, pageUtil.getLimit(), pageUtil.getPage());
        return pageResult;
    }
    public List<NewsCategory> findCategoryList(Map<String,Object> params){
        QueryWrapper<NewsCategory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted",0).orderByDesc("category_id");
        int start = (int) params.getOrDefault("start",0);
        int limit = (int) params.getOrDefault("limit",Integer.MAX_VALUE);
        queryWrapper.last("limit "+start+","+limit);
        return list(queryWrapper);
    }
    public int getTotalCategories(Map<String, Object> params) {
        QueryWrapper<NewsCategory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted", 0);

        return (int)count(queryWrapper);
    }

    @Override
    public Boolean saveCategory(String categoryName) {
        /**
         * 查询是否已存在
         */
        QueryWrapper<NewsCategory> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_name", categoryName).eq("is_deleted", 0);
        NewsCategory temp = this.getOne(queryWrapper);
        if (temp == null) {
            NewsCategory newsCategory = new NewsCategory();
            newsCategory.setCategoryName(categoryName);
            return this.save(newsCategory);
        }
        return false;
    }

    @Override
    public Boolean updateCategory(Long categoryId, String categoryName) {
        // NewsCategory newsCategory = newsCategoryMapper.selectByPrimaryKey(categoryId);
        NewsCategory newsCategory = this.getById(categoryId);
        if (newsCategory != null) {
            newsCategory.setCategoryName(categoryName);
            return this.saveOrUpdate(newsCategory);
        }
        return false;
    }

    @Override
    public Boolean deleteBatchByIds(Integer[] ids) {
        if (ids.length < 1) {
            return false;
        }
        //删除分类数据
        return this.deleteBatchByIds(ids);
    }

}
