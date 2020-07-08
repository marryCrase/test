package cn.itcast.domain;

/**
 * Created by Administrator on 2019-08-22.
 */
public class Url {
    private int id;
    private String iphoto; //主图的名字
    private String iphotoUrl;  //主图的坐标
    private int o_id;  //父图的ID
    private String ihtml;//主网页名称
    private String annotation;//网页注释

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIphoto() {
        return iphoto;
    }

    public void setIphoto(String iphoto) {
        this.iphoto = iphoto;
    }

    public String getIphotoUrl() {
        return iphotoUrl;
    }

    public void setIphotoUrl(String iphotoUrl) {
        this.iphotoUrl = iphotoUrl;
    }

    public int getO_id() {
        return o_id;
    }

    public void setO_id(int o_id) {
        this.o_id = o_id;
    }

    public String getIhtml() {
        return ihtml;
    }

    public void setIhtml(String ihtml) {
        this.ihtml = ihtml;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }

    @Override
    public String toString() {
        return "Url{" +
                "id=" + id +
                ", iphoto='" + iphoto + '\'' +
                ", iphotoUrl='" + iphotoUrl + '\'' +
                ", o_id=" + o_id +
                ", ihtml='" + ihtml + '\'' +
                ", annotation='" + annotation + '\'' +
                '}';
    }
}
