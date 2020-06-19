package cn.itcast.service.impl;

import cn.itcast.dao.IAudioDao;
import cn.itcast.domain.Audio;
import cn.itcast.service.IAudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @outhor 孙成玉
 * User:Administrator
 * Date:2020-05-29
 * Time:14:43
 */
@Service("audioService")
public class AudioServiceImpl implements IAudioService{

    @Autowired
    private IAudioDao audioDao;

    /**
     * 查询所有音频
     * @return
     */
    @Override
    public List<Audio> findAllAudio() {
        return audioDao.findAllAudio();
    }
    /**
     * 添加音频
     * @param audio
     */
    @Override
    public void saveAudio(Audio audio) {
        audioDao.saveAudio(audio);
    }
    /**
     * 根据主图ID查询音频信息
     * @return
     */
    @Override
    public List<Audio> findAudioByUid(int u_id) {
        return audioDao.findAudioByUid(u_id);
    }
}
