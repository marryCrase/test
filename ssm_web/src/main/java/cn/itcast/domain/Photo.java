package cn.itcast.domain;

/**
 * Created by Administrator on 2019-08-23.
 */
public class Photo {
    private int p_id;
    private String photoListUrl;//画框图片坐标
    private String photoUrl;//画框图片链接
    private String photo;   //画框图片坐标
    private  int u_id;      //主图id

    public int getP_id() {
        return p_id;
    }

    public void setP_id(int p_id) {
        this.p_id = p_id;
    }

    public String getPhotoListUrl() {
        return photoListUrl;
    }

    public void setPhotoListUrl(String photoListUrl) {
        this.photoListUrl = photoListUrl;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "p_id=" + p_id +
                ", photoListUrl='" + photoListUrl + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                ", photo='" + photo + '\'' +
                ", u_id=" + u_id +
                '}';
    }
}
