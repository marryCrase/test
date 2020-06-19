package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.dao.IArticleDao;
import com.itheima.domain.Article;
import com.itheima.service.IArticleService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @outhor 孙成玉
 * User:Administrator
 * Date:2020-05-20
 * Time:13:54
 * 物品Service
 */
@Service("articleService")
public class ArticleServiceImpl implements IArticleService {

    @Autowired
    private IArticleDao articleDao;

    /**
     * 查询所有物品
     * @return
     * @throws Exception
     */
    @Override
    public List<Article> findAllArticle(int page,int size){
        PageHelper.startPage(page,size);
        return articleDao.findAllArticle();
    }

    /**
     * 根据id查询物品信息
     * @param articleId
     * @return
     */
    @Override
    public Article findArticleById(String articleId) {
        return articleDao.findArticleById(articleId);
    }

    /**
     * 添加物品
     * @param article
     */
    @Override
    public void saveArticle(Article article) {
        articleDao.saveArticle(article);
    }

    /**
     * 根据ID删除物品
     * @param articleId
     */
    @Override
    public void delArticle(String articleId) {
        articleDao.delArticle(articleId);
    }

    /**
     * 修改物品信息
     * @param article
     */
    @Override
    public void updateArticle(Article article) {
        articleDao.updateArticle(article);
    }

}
