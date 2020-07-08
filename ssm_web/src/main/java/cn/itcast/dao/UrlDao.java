package cn.itcast.dao;

import cn.itcast.domain.Url;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2020/6/30.
 */
public class UrlDao {

    /**
     * 查询所有主图信息
     * @return
     */
    public List<Url> findAllUrl(Connection con)throws Exception{
        Url resultUrl = null;
        List<Url> urlList = new ArrayList<>();
        String sql = "select * from p_url";
        PreparedStatement psmt = con.prepareStatement(sql);
        ResultSet rs = psmt.executeQuery();
        while (rs.next()){
            resultUrl = new Url();
            resultUrl.setId(rs.getInt("id"));
            resultUrl.setIphoto(rs.getString("iphoto"));
            resultUrl.setIphotoUrl(rs.getString("iphotoUrl"));
            resultUrl.setO_id(rs.getInt("o_id"));
            resultUrl.setIhtml(rs.getString("ihtml"));
            resultUrl.setAnnotation(rs.getString("annotation"));
            urlList.add(resultUrl);
        }
        return urlList;
    }
    /**
     * 根据ID查询主图信息
     * @return
     */
    public List<Url> findUrlById(Connection con, int id)throws Exception{
        Url resultUrl = null;
        List<Url> urlList = new ArrayList<>();
        String sql = "select * from p_url where id=?";
        PreparedStatement psmt = con.prepareStatement(sql);
        psmt.setInt(1,id);
        ResultSet rs = psmt.executeQuery();
        while (rs.next()){
            resultUrl = new Url();
            resultUrl.setId(rs.getInt("id"));
            resultUrl.setIphoto(rs.getString("iphoto"));
            resultUrl.setIphotoUrl(rs.getString("iphotoUrl"));
            resultUrl.setO_id(rs.getInt("o_id"));
            resultUrl.setIhtml(rs.getString("ihtml"));
            resultUrl.setAnnotation(rs.getString("annotation"));
            urlList.add(resultUrl);
        }
        return urlList;
    }
    /**
     * 根据Name查询主图信息
     * @return
     */
    public List<Url> findUrlByName(Connection con, String urlName)throws Exception{
        Url resultUrl = null;
        List<Url> urlList = new ArrayList<>();
        String sql = "select * from p_url where iphoto=?";
        PreparedStatement psmt = con.prepareStatement(sql);
        psmt.setString(1,urlName);
        ResultSet rs = psmt.executeQuery();
        while (rs.next()){
            resultUrl = new Url();
            resultUrl.setId(rs.getInt("id"));
            resultUrl.setIphoto(rs.getString("iphoto"));
            resultUrl.setIphotoUrl(rs.getString("iphotoUrl"));
            resultUrl.setO_id(rs.getInt("o_id"));
            resultUrl.setIhtml(rs.getString("ihtml"));
            resultUrl.setAnnotation(rs.getString("annotation"));
            urlList.add(resultUrl);
        }
        return urlList;
    }
    /**
     * 添加主图信息
     * @param url
     */
    public int saveUrl(Connection con, Url url)throws Exception{
        String sql = "insert into p_url values(null,?,?,?,?,?)";
        PreparedStatement psmt = con.prepareStatement(sql);
        psmt.setString(1,url.getIphoto());
        psmt.setString(2,url.getIphotoUrl());
        psmt.setInt(3,url.getO_id());
        psmt.setString(4,url.getIhtml());
        psmt.setString(5,url.getAnnotation());
        return psmt.executeUpdate();
    }


}
