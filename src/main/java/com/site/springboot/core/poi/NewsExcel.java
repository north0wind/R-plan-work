package com.site.springboot.core.poi;

import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.alibaba.excel.annotation.ExcelProperty;

import java.util.Date;

/**
 * @author xiaolong
 * @date 2024/5/28
 */
@Data
@EqualsAndHashCode
@ContentRowHeight(20)
@HeadRowHeight(20)
@ColumnWidth(20)
public class NewsExcel {

    /**
     * 新闻标题
     */
    @ExcelProperty(value = "新闻标题", index = 0)
    private String newsTitle;

    @ExcelProperty(value = "新闻分类", index = 1)
    private String newsCategory;

    @ExcelProperty(value = "新闻封面", index = 2)
    private String newsCoverImage;

    @ExcelProperty(value = "新闻内容", index = 3)
    private String newsContent;

    @ExcelProperty(value = "新闻状态", index = 4)
    private String newsStatus;

    @ExcelProperty(value = "新闻浏览量", index = 5)
    private Long newsViews;
    @ExcelProperty(value = "新闻创建时间", index = 6)
    private Date createTime;

}
