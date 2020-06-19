package com.itheima.service;

import com.itheima.domain.Article;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @outhor 孙成玉
 * User:Administrator
 * Date:2020-05-20
 * Time:13:53
 */
public interface IArticleService {

    /**
     * 查询所有物品
     * @return
     * @throws Exception
     */
    List<Article> findAllArticle(int page,int size);

    /**
     * 根据id查询物品信息
     * @param articleId
     * @return
     */
    Article findArticleById(String articleId);

    /**
     * 添加物品
     * @param article
     */
    void saveArticle(Article article);

    /**
     * 根据ID删除物品
     * @param articleId
     */
    void delArticle(String articleId);

    /**
     * 修改物品信息
     * @param article
     */
    void updateArticle(Article article);
}
