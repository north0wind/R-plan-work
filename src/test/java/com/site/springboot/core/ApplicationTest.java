package com.site.springboot.core;

import com.site.springboot.core.entity.News;
import com.site.springboot.core.entity.NewsIndex;
import com.site.springboot.core.repository.NewsIndexRepository;
import com.site.springboot.core.service.NewsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaolong
 * @date 2024/5/29
 */
@SpringBootTest
public class ApplicationTest {

    @Autowired
    private NewsService newsService;
    @Autowired
    private NewsIndexRepository newsIndexRepository;

    @Test
    public void testQueryNewsById(){
        News news = newsService.queryNewsById(21L);
        System.out.println(news);
        News news1 = newsService.queryNewsById(4L);
        System.out.println(news1);
    }

    @Test
    public void testQuery(){
        List<NewsIndex> byNewsContentLike = newsIndexRepository.findByNewsContentLike("测试");
        List<News> newsList = new ArrayList<>();
        for (NewsIndex newsIndex : byNewsContentLike){
            News news = new News();
            BeanUtils.copyProperties(newsIndex, news);
            newsList.add(news);
        }
        newsList.forEach(System.out::println);
    }

}
