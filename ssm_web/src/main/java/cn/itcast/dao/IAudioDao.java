package cn.itcast.dao;

import cn.itcast.domain.Audio;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @outhor 孙成玉
 * User:Administrator
 * Date:2020-05-29
 * Time:12:25
 */
@Repository("audioDao")
public interface IAudioDao {

    /**
     * 查询所有音频信息
     * @return
     */
    @Select("select * from p_audio")
    List<Audio> findAllAudio();

    /**
     * 根据主图ID查询音频信息
     * @return
     */
    @Select("select * from p_audio where u_id=#{u_id}")
    List<Audio> findAudioByUid(int u_id);

    /**
     * 添加音频信息
     * @param audio
     */
    @Insert("insert into p_audio values(null,#{audioUrl},#{audio},#{u_id})")
    void saveAudio(Audio audio);



}
