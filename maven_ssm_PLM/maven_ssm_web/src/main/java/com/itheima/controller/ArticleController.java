package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Article;
import com.itheima.service.IArticleService;
import com.itheima.utils.UtilsUUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @outhor 孙成玉
 * User:Administrator
 * Date:2020-05-20
 * Time:13:56
 * 物品Controller
 */
@Controller
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private IArticleService articleService;

    /**
     * 查询所有物品
     * @return
     * @throws Exception
     */
    @RequestMapping("/findAllArticle.do")
    public ModelAndView findAllArticle(@RequestParam(name = "page",defaultValue = "1",required = true) int page,@RequestParam(name = "size",defaultValue = "5",required = true) int size) {
        ModelAndView mv = new ModelAndView();
        List<Article> articles = articleService.findAllArticle(page,size);
        PageInfo pageInfo = new PageInfo(articles);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("DocList");
        return mv;
    }

    @RequestMapping("/findArticleById/{articleId}")
    public ModelAndView findArticleById(@PathVariable String articleId){
        ModelAndView mv = new ModelAndView();
        Article article = articleService.findArticleById(articleId);
        mv.addObject("article",article);
        mv.setViewName("updateDoc");
        return mv;
    }

    /**
     * 添加物品
     * @param article
     */
    @RequestMapping("/saveArticle.do")
    public void saveArticle(Article article, HttpServletRequest request, HttpServletResponse response) throws IOException {
        UtilsUUID utilsUUID = new UtilsUUID();
        String uuid= utilsUUID.getUUID()+new Date().toString();
        article.setId(uuid);
        articleService.saveArticle(article);
        response.sendRedirect(request.getContextPath()+"/article/findAllArticle.do");
        return;
    }

    /**
     * 根据ID删除物品
     * @param articleId
     */
    @RequestMapping("/delArticle/{articleId}")
    public String delArticle(@PathVariable String articleId){
        articleService.delArticle(articleId);
        return "forward:/article/findAllArticle.do";
    }

    @RequestMapping("/delArticles")
    public String delArticles(@RequestParam(defaultValue = "",required = true) String checkedId){
        String[] ids = checkedId.split(",");
        for(int i=0;i<ids.length;i++) {
            articleService.delArticle(ids[i]);
        }
        return "forward:/article/findAllArticle.do";
    }

    /**
     * 根据ID修改物品信息
     * @param article
     * @return
     */
    @RequestMapping("/updateArticle")
    public String updateArticle(Article article){
        articleService.updateArticle(article);
        return "forward:/article/findAllArticle.do";
    }

    @RequestMapping("/page/{page}")
    public String page(@PathVariable String page){
        return page;
    }


}
