package cn.itcast.domain;

/**
 * Created by Administrator on 2019-08-23.
 */
public class Video {
    private int v_id;
    private String videoUrl;//画框视频坐标
    private String video;   //画框视频坐标
    private int u_id;

    public int getV_id() {
        return v_id;
    }

    public void setV_id(int v_id) {
        this.v_id = v_id;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    @Override
    public String toString() {
        return "Video{" +
                "v_id=" + v_id +
                ", videoUrl='" + videoUrl + '\'' +
                ", video='" + video + '\'' +
                ", u_id=" + u_id +
                '}';
    }
}
