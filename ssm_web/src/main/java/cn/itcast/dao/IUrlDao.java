package cn.itcast.dao;

import cn.itcast.domain.Audio;
import cn.itcast.domain.Url;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @outhor 孙成玉
 * User:Administrator
 * Date:2020-05-29
 * Time:12:25
 */
@Repository("urlDao")
public interface IUrlDao {

    /**
     * 查询所有主图信息
     * @return
     */
    @Select("select * from p_url")
//    @Options(useGeneratedKeys=true, keyProperty="userId", keyColumn="id")
//    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    List<Url> findAllUrl();

    /**
     * 根据ID查询主图信息
     * @return
     */
    @Select("select * from p_url where id=#{id}")
    List<Url> findUrlById(int id);

    /**
     * 根据Name查询主图信息
     * @return
     */
    @Select("select * from p_url where iphoto=#{urlName}")
    List<Url> findUrlByName(String urlName);

    /**
     * 添加主图信息
     * @param url
     */
    @Insert("insert into p_url values(null,#{iphoto},#{iphotoUrl},#{o_id},#{ihtml},#{annotation})")
    void saveUrl(Url url);
}
