package com.site.springboot.core.repository;


import com.site.springboot.core.entity.NewsIndex;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


import java.util.List;

/**
 * @author xiaolong
 * @date 2024/5/30
 */
public interface NewsIndexRepository extends ElasticsearchRepository<NewsIndex, Long> {

    List<NewsIndex> findByNewsContentLike(String keyword);
}
