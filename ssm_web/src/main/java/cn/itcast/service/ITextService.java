package cn.itcast.service;

import cn.itcast.domain.Text;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @outhor 孙成玉
 * User:Administrator
 * Date:2020-05-29
 * Time:16:13
 */
public interface ITextService {
    /**
     * 添加文字
     * @param text
     * @return
     */
    void saveText(Text text);

    /**
     * 根据主图ID查询文字信息
     * @return List
     * @throws Exception
     */
    List<Text> findTextByUid(int u_id);

}
