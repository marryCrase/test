package cn.itcast.service;

import cn.itcast.domain.Photo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @outhor 孙成玉
 * User:Administrator
 * Date:2020-05-29
 * Time:12:25
 */
public interface IPhotoService {

    /**
     * 根据主图ID查询图片信息
     * @return
     */
    List<Photo> findPhotoByUid(int u_id);
    /**
     * 添加图片
     * @param photo
     * @return
     */
    void savePhoto(Photo photo);


}
