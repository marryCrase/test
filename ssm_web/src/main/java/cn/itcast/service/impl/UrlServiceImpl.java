package cn.itcast.service.impl;

import cn.itcast.dao.IUrlDao;
import cn.itcast.domain.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @outhor 孙成玉
 * User:Administrator
 * Date:2020-05-29
 * Time:16:23
 */
@Service("urlService")
public class UrlServiceImpl implements IUrlDao {

    @Autowired
    private IUrlDao urlDao;
    /**
     * 查询所有主图信息
     * @return
     */
    @Override
    public List<Url> findAllUrl() {
        return urlDao.findAllUrl();
    }

    /**
     * 根据ID查询主图信息
     * @return
     */
    @Override
    public List<Url> findUrlById(int id) {
        return urlDao.findUrlById(id);
    }

    /**
     * 根据Name查询主图信息
     * @return
     */
    @Override
    public List<Url> findUrlByName(String urlName) {
        return urlDao.findUrlByName(urlName);
    }

    /**
     * 添加主图信息
     * @param url
     */
    @Override
    public void saveUrl(Url url) {
        urlDao.saveUrl(url);
    }
}
