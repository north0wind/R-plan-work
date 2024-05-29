package com.site.springboot.core;

import com.site.springboot.core.entity.News;
import com.site.springboot.core.service.NewsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author xiaolong
 * @date 2024/5/29
 */
@SpringBootTest
public class ApplicationTest {

    @Autowired
    private NewsService newsService;

    @Test
    public void testQueryNewsById(){
        News news = newsService.queryNewsById(20L);
        System.out.println(news);
        News news1 = newsService.queryNewsById(3L);
        System.out.println(news1);

    }
}
