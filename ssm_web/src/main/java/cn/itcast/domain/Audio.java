package cn.itcast.domain;

/**
 * Created by Administrator on 2019-08-23.
 */
public class Audio {
    private int a_id;
    private String audioUrl;//画框音频坐标
    private String audio;   //画框音频坐标
    private int u_id;//主图id

    public int getA_id() {
        return a_id;
    }

    public void setA_id(int a_id) {
        this.a_id = a_id;
    }

    public String getAudioUrl() {
        return audioUrl;
    }

    public void setAudioUrl(String audioUrl) {
        this.audioUrl = audioUrl;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    @Override
    public String toString() {
        return "Audio{" +
                "a_id=" + a_id +
                ", audioUrl='" + audioUrl + '\'' +
                ", audio='" + audio + '\'' +
                ", u_id=" + u_id +
                '}';
    }
}
