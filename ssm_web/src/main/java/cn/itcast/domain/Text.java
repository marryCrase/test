package cn.itcast.domain;

/**
 * Created by Administrator on 2019-08-23.
 */
public class Text {
    private int t_id;
    private String textListUrl;//画框文字坐标
    private String textUrl;  //画框文字链接
    private String text;     //画框文字内容
    private int u_id;   //主图id

    public int getT_id() {
        return t_id;
    }

    public void setT_id(int t_id) {
        this.t_id = t_id;
    }

    public String getTextListUrl() {
        return textListUrl;
    }

    public void setTextListUrl(String textListUrl) {
        this.textListUrl = textListUrl;
    }

    public String getTextUrl() {
        return textUrl;
    }

    public void setTextUrl(String textUrl) {
        this.textUrl = textUrl;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    @Override
    public String toString() {
        return "Text{" +
                "t_id=" + t_id +
                ", textListUrl='" + textListUrl + '\'' +
                ", textUrl='" + textUrl + '\'' +
                ", text='" + text + '\'' +
                ", u_id=" + u_id +
                '}';
    }
}
