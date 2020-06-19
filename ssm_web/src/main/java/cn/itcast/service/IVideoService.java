package cn.itcast.service;

import cn.itcast.domain.Video;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @outhor 孙成玉
 * User:Administrator
 * Date:2020-05-29
 * Time:12:26
 */
public interface IVideoService {
    /**
     * 查询所有视频信息
     * @return
     */
    List<Video> findAllVideo();

    /**
     * 根据主图ID查询视频信息
     * @return
     */
    List<Video> findVideoByUid(int u_id);

    /**
     * 添加视频信息
     * @param video
     */
    void saveVideo(Video video);

}
