package com.itheima.dao;

import com.itheima.domain.Article;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @outhor 孙成玉
 * User:Administrator
 * Date:2020-05-20
 * Time:13:51
 * 物品Dao
 */
@Repository
public interface IArticleDao {

    /**
     * 查询所有物品
     * @return
     * @throws Exception
     */
    @Select("select * from article")
    List<Article> findAllArticle();

    /**
     * 根据id查询物品信息
     * @param articleId
     * @return
     */
    @Select("select * from article where id = #{articleId}")
    Article findArticleById(String articleId);

    /**
     * 添加物品
     * @param article
     */
    @Insert("insert into article(id,name,startDate,endDate,details,users,status,delStatus) values(#{id},#{name},#{startDate},#{endDate},#{details},#{users},#{status},#{delStatus})")
    void saveArticle(Article article);

    /**
     * 根据ID删除物品
     * @param articleId
     */
    @Delete("delete from article where id = #{articleId}")
    void delArticle(String articleId);

    /**
     * 修改物品信息
     * @param article
     */
    @Update("update article set name=#{name},startDate=#{startDate},endDate=#{endDate},details=#{details},users=#{users},status=#{status},delStatus=#{delStatus} where id =#{id}")
    void updateArticle(Article article);
}
