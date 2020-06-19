package cn.itcast.service;

import cn.itcast.domain.Audio;

import java.util.List;

/**
 * @outhor 孙成玉
 * User:Administrator
 * Date:2020-05-29
 * Time:12:25
 */
public interface IAudioService {

    /**
     * 查询所有账户
     * @return
     */
    List<Audio> findAllAudio();
    /**
     * 添加音频
     * @param audio
     */
    void saveAudio(Audio audio);

    /**
     * 根据主图ID查询音频信息
     * @return
     */
    public List<Audio> findAudioByUid(int u_id);

}
