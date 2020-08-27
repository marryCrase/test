package cn.itcast.dao;

import cn.itcast.domain.Qrcode;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2020/7/16.
 */
public class QrcodeDao {

    /**
     * 查询所有二维码信息
     * @return
     */
    public List<Qrcode> findAllQrcode(Connection con)throws Exception{
        Qrcode resultUrl = null;
        List<Qrcode> urlList = new ArrayList<>();
        String sql = "select * from p_QRcode";
        PreparedStatement psmt = con.prepareStatement(sql);
        ResultSet rs = psmt.executeQuery();
        while (rs.next()){
            resultUrl = new Qrcode();
            resultUrl.setId(rs.getInt("id"));
            resultUrl.setName(rs.getString("name"));
            resultUrl.setUid(rs.getInt("uid"));
            urlList.add(resultUrl);
        }
        return urlList;
    }
    /**
     * 根据ID查询主图信息
     * @return
     */
    public List<Qrcode> findQrcodeById(Connection con, int id)throws Exception{
        Qrcode resultUrl = null;
        List<Qrcode> urlList = new ArrayList<>();
        String sql = "select * from p_QRcode where id=?";
        PreparedStatement psmt = con.prepareStatement(sql);
        psmt.setInt(1,id);
        ResultSet rs = psmt.executeQuery();
        while (rs.next()){
            resultUrl = new Qrcode();
            resultUrl.setId(rs.getInt("id"));
            resultUrl.setName(rs.getString("name"));
            resultUrl.setUid(rs.getInt("uid"));
            urlList.add(resultUrl);
        }
        return urlList;
    }
    /**
     * 根据Name查询主图信息
     * @return
     */
    public List<Qrcode> findQrcodeByName(Connection con, String urlName)throws Exception{
        Qrcode resultUrl = null;
        List<Qrcode> urlList = new ArrayList<>();
        String sql = "select * from p_QRcode where name like '%"+urlName+"%'";
        PreparedStatement psmt = con.prepareStatement(sql);
//        psmt.setString(1,"%"+urlName+"%");
        ResultSet rs = psmt.executeQuery();
        while (rs.next()){
            resultUrl = new Qrcode();
            resultUrl.setId(rs.getInt("id"));
            resultUrl.setName(rs.getString("name"));
            resultUrl.setUid(rs.getInt("uid"));
            urlList.add(resultUrl);
        }
        return urlList;
    }
    /**
     * 添加主图信息
     * @param qrcode
     */
    public int saveQrcode(Connection con, Qrcode qrcode)throws Exception{
        String sql = "insert into p_QRcode values(null,?,?)";
        PreparedStatement psmt = con.prepareStatement(sql);
        psmt.setString(1,qrcode.getName());
        psmt.setInt(2,qrcode.getUid());
        return psmt.executeUpdate();
    }
    /**
     * 根据UID删除数据
     */
    public int delQRcode(Connection con, int uid)throws Exception{
        String sql = "delete from p_qrcode where uid = ?";
        PreparedStatement psmt = con.prepareStatement(sql);
        psmt.setInt(1,uid);
        return psmt.executeUpdate();
    }
}
