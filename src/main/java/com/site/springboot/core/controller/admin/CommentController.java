package com.site.springboot.core.controller.admin;

import com.site.springboot.core.service.CommentProvider;
import com.site.springboot.core.service.CommentService;
import com.site.springboot.core.util.PageQueryUtil;
import com.site.springboot.core.util.Result;
import com.site.springboot.core.util.ResultGenerator;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;
import java.util.Map;


@Controller
@RequestMapping("/admin")
public class CommentController {
    @Resource
    private CommentProvider commentProvider;

    @Resource
    private CommentService commentService;

    @GetMapping("/comments")
    public String list(HttpServletRequest request) {
        return commentProvider.list(request);
        // request.setAttribute("path", "comments");
        // return "admin/comment";
    }

    @GetMapping("/comments/list")
    @ResponseBody
    public Result list(@RequestParam Map<String, Object> params) {
        return commentProvider.list(params);
        // if (ObjectUtils.isEmpty(params.get("page")) || ObjectUtils.isEmpty(params.get("limit"))) {
        //     return ResultGenerator.genFailResult("参数异常！");
        // }
        // PageQueryUtil pageUtil = new PageQueryUtil(params);
        // return ResultGenerator.genSuccessResult(commentService.getCommentsPage(pageUtil));
    }

    @PostMapping("/comments/checkDone")
    @ResponseBody
    public Result checkDone(@RequestBody Integer[] ids) {
        return commentProvider.checkDone(ids);
        // if (ids.length < 1) {
        //     return ResultGenerator.genFailResult("参数异常！");
        // }
        // if (commentService.checkDone(ids)) {
        //     return ResultGenerator.genSuccessResult();
        // } else {
        //     return ResultGenerator.genFailResult("审核失败");
        // }
    }

    @PostMapping("/comments/delete")
    @ResponseBody
    public Result delete(@RequestBody Integer[] ids) {
        return commentProvider.deleteBatch(ids);
        // if (ids.length < 1) {
        //     return ResultGenerator.genFailResult("参数异常！");
        // }
        // if (commentService.deleteBatch(ids)) {
        //     return ResultGenerator.genSuccessResult();
        // } else {
        //     return ResultGenerator.genFailResult("刪除失败");
        // }
    }

    /**
     * 后台获取最新评论
     *
     * @param params
     * @return
     */
    @GetMapping("/comments/list-latest")
    @ResponseBody
    public Result listLatest(@RequestParam Map<String, Object> params,@RequestParam Long newsId) {

        if (ObjectUtils.isEmpty(params.get("page")) || ObjectUtils.isEmpty(params.get("limit"))) {
            return ResultGenerator.genFailResult("参数异常！");
        }
        PageQueryUtil pageUtil = new PageQueryUtil(params);
        return ResultGenerator.genSuccessResult(commentService.getCommentsLatest(pageUtil, newsId));
    }


}
