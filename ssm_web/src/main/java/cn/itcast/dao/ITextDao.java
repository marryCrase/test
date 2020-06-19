package cn.itcast.dao;

import cn.itcast.domain.Text;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @outhor 孙成玉
 * User:Administrator
 * Date:2020-05-29
 * Time:16:13
 */
@Repository("textDao")
public interface ITextDao {
    /**
     * 添加文字
     * @param text
     * @return
     */
    @Insert("insert into p_text values(null,#{textListUrl},#{textUrl},#{text},#{u_id})")
    void saveText(Text text);

    /**
     * 根据主图ID查询文字信息
     * @return List
     * @throws Exception
     */
    @Select("select * from p_text where u_id=#{u_id}")
    List<Text> findTextByUid(int u_id);

}
