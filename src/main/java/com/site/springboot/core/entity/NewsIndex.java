package com.site.springboot.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;

/**
 * @author xiaolong
 * @date 2024/5/30
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(indexName = "news_index", createIndex = false)
public class NewsIndex implements Serializable {

    private static final long serialVersionUID=1L;

    @Id
    @Field(type= FieldType.Long)
    private Long newsId;

    @Field(type= FieldType.Text,analyzer = "ik_smart")
    private String newsTitle;

    @Field(type= FieldType.Long)
    private Long newsCategoryId;

    @Field(type= FieldType.Text)
    private String newsCoverImage;

    @Field(type= FieldType.Text,analyzer = "ik_smart")
    private String newsContent;

    @Field(type= FieldType.Byte)
    private Byte newsStatus;

    @Field(type= FieldType.Long)
    private Long newsViews;

    @Field(type= FieldType.Byte)
    private Byte isDeleted;

    @Field(type= FieldType.Date,format = DateFormat.date_hour_minute_second)
    private Date createTime;
}
