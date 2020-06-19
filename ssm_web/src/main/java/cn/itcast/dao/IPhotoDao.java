package cn.itcast.dao;

import cn.itcast.domain.Photo;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @outhor 孙成玉
 * User:Administrator
 * Date:2020-05-29
 * Time:12:25
 */
@Repository("photoDao")
public interface IPhotoDao {

    /**
     * 根据主图ID查询图片信息
     * @return
     */
    @Select("select * from p_photo where u_id=#{u_id}")
    List<Photo> findPhotoByUid(int u_id);
    /**
     * 添加图片
     * @param photo
     * @return
     */
    @Select("insert into p_photo values(null,#{photoListUrl},#{photoUrl},#{photo},#{u_id})")
    void savePhoto(Photo photo);


}
