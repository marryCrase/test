package cn.itcast.domain;

import java.io.Serializable;

/**
 * Created by Administrator on 2020/7/2.
 */
public class Data implements Serializable{

    private Integer id;
    private String type;//类型
    private String url;//链接
    private String coord;//坐标
    private String text;//文字
    private String path;//路径
    private Integer uid;//对应主图id

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCoord() {
        return coord;
    }

    public void setCoord(String coord) {
        this.coord = coord;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "Data{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", coord='" + coord + '\'' +
                ", text='" + text + '\'' +
                ", path='" + path + '\'' +
                ", uid=" + uid +
                '}';
    }
}
