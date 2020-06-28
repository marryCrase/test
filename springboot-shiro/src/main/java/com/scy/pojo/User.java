package com.scy.pojo;

import com.alibaba.druid.sql.visitor.functions.Char;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by Administrator on 2020/6/28.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable{

    private int id;
    private String name;
    private String pwd;
    private String perms;

    public String getPwd() {
        return pwd;
    }

    public String getPerms() {
        return perms;
    }
}
