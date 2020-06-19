package cn.itcast.dao;

import cn.itcast.domain.Video;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @outhor 孙成玉
 * User:Administrator
 * Date:2020-05-29
 * Time:12:26
 */
@Repository("videoDao")
public interface IVideoDao {
    /**
     * 查询所有视频信息
     * @return
     */
    @Select("select * from p_video")
    List<Video> findAllVideo();

    /**
     * 根据主图ID查询视频信息
     * @return
     */
    @Select("select * from p_video where u_id=#{u_id}")
    List<Video> findVideoByUid(int u_id);

    /**
     * 添加视频信息
     * @param video
     */
    @Insert("insert into p_video values(null,#{videoUrl},#{video},#{u_id})")
    void saveVideo(Video video);

}
