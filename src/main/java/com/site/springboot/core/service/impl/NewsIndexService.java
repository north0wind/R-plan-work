package com.site.springboot.core.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.site.springboot.core.dao.NewsMapper;
import com.site.springboot.core.entity.News;
import com.site.springboot.core.entity.NewsIndex;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaolong
 * @date 2024/5/30
 */
@Service
public class NewsIndexService {

    private static final Logger log = LoggerFactory.getLogger(NewsIndexService.class);

    @Resource
    private NewsMapper newsMapper;

    @Resource
    private ElasticsearchTemplate template;

    /**
     * 初始化索引结构和数据
     */
    public void initIndex(){
        //处理索引结构
        IndexOperations indexOps = template.indexOps(NewsIndex.class);
        if (indexOps.exists()){
            boolean delFlag = indexOps.delete();
            log.info("删除索引结构{}",delFlag);
            indexOps.createMapping(NewsIndex.class);
        }else {
            log.info("索引结构不存在");
            indexOps.createMapping(NewsIndex.class);
        }
        //同步数据库表记录
        List<News> newsList = newsMapper.selectList(new QueryWrapper<>());
        if (newsList.size() > 0){
            List<NewsIndex> newsIndexList = new ArrayList<>();
            newsIndexList.forEach(news -> {
                NewsIndex newsIndex = new NewsIndex();
                BeanUtils.copyProperties(news, newsIndex);
                newsIndexList.add(newsIndex);
            });
            template.save(newsIndexList);
        }
        //ID查询
        NewsIndex newsIndex = template.get("10", NewsIndex.class);
        log.info("查询结果{}",newsIndex);
    }
}
