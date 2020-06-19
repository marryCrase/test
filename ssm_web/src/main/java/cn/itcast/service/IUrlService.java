package cn.itcast.service;

import cn.itcast.domain.Url;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @outhor 孙成玉
 * User:Administrator
 * Date:2020-05-29
 * Time:12:25
 */
public interface IUrlService {

    /**
     * 查询所有主图信息
     * @return
     */
    List<Url> findAllUrl();

    /**
     * 根据ID查询主图信息
     * @return
     */
    List<Url> findUrlByUid(int id);

    /**
     * 根据Name查询主图信息
     * @return
     */
    List<Url> findUrlByName(String urlName);

    /**
     * 添加主图信息
     * @param url
     */
    void saveUrl(Url url);
}
