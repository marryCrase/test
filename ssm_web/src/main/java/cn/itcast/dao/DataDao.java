package cn.itcast.dao;

import cn.itcast.domain.Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2020/7/2.
 */
public class DataDao {

    /**
     * 根据主图ID查询信息
     * @return
     */
    public List<Data> findDataByUid(Connection con, int uid)throws Exception {
        Data data = null;
        List<Data> lists = new ArrayList<>();
        String sql = "select * from p_data where uid=?";
        PreparedStatement psmt = con.prepareStatement(sql);
        psmt.setInt(1, uid);
        ResultSet rs = psmt.executeQuery();
        while (rs.next()) {
            data = new Data();
            data.setId(rs.getInt("id"));
            data.setType(rs.getString("type"));
            data.setUrl(rs.getString("url"));
            data.setCoord(rs.getString("coord"));
            data.setText(rs.getString("text"));
            data.setPath(rs.getString("path"));
            data.setUid(rs.getInt("uid"));
            lists.add(data);
        }
        return lists;
    }

    /**
     * 添加资源
     * @param data
     * @return
     */
    public int saveData(Connection con, Data data)throws Exception{
        String sql = "insert into p_data values(null,?,?,?,?,?,?)";
        PreparedStatement psmt = con.prepareStatement(sql);
        psmt.setString(1,data.getType());
        psmt.setString(2,data.getUrl());
        psmt.setString(3,data.getCoord());
        psmt.setString(4,data.getText());
        psmt.setString(5,data.getPath());
        psmt.setInt(6,data.getUid());
        return psmt.executeUpdate();
    }

}
