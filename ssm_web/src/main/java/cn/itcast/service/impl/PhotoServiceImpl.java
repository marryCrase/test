package cn.itcast.service.impl;

import cn.itcast.dao.IPhotoDao;
import cn.itcast.domain.Photo;
import cn.itcast.service.IPhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @outhor 孙成玉
 * User:Administrator
 * Date:2020-05-29
 * Time:15:53
 */
@Service("photoService")
public class PhotoServiceImpl implements IPhotoService {

    @Autowired
    private IPhotoDao photoDao;

    /**
     * 根据主图ID查询图片信息
     * @return
     */
    @Override
    public List<Photo> findPhotoByUid(int u_id) {
        return photoDao.findPhotoByUid(u_id);
    }

    /**
     * 添加图片
     * @param photo
     * @return
     */
    @Override
    public void savePhoto(Photo photo) {
        photoDao.savePhoto(photo);
    }
}
