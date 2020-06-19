package cn.itcast.service.impl;

import cn.itcast.dao.IVideoDao;
import cn.itcast.domain.Video;
import cn.itcast.service.IVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @outhor 孙成玉
 * User:Administrator
 * Date:2020-05-29
 * Time:16:25
 */
@Service("videoService")
public class VideoServiceImpl implements IVideoService {

    @Autowired
    private IVideoDao videoDao;

    /**
     * 查询所有视频信息
     * @return
     */
    @Override
    public List<Video> findAllVideo() {
        return videoDao.findAllVideo();
    }

    /**
     * 根据主图ID查询视频信息
     * @return
     */
    @Override
    public List<Video> findVideoByUid(int u_id) {
        return videoDao.findVideoByUid(u_id);
    }

    /**
     * 添加视频信息
     * @param video
     */
    @Override
    public void saveVideo(Video video) {
        videoDao.saveVideo(video);
    }
}
