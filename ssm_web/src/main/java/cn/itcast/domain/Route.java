package cn.itcast.domain;

/**
 * Created by Administrator on 2019-08-23.
 */
public class Route {
    private int r_id;
    private String type; //类型
    private String url;  //主图上所有画框的坐标
    private String route;  //主图上所有画框的路径
    private int u_id;

    public int getR_id() {
        return r_id;
    }

    public void setR_id(int r_id) {
        this.r_id = r_id;
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

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    @Override
    public String toString() {
        return "Route{" +
                "r_id=" + r_id +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", route='" + route + '\'' +
                ", u_id=" + u_id +
                '}';
    }
}
