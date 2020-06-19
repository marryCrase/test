package com.itheima.utils;

import java.util.UUID;

/**
 * @outhor 孙成玉
 * User:Administrator
 * Date:2020-05-21
 * Time:13:24
 * UUID
 */
public class UtilsUUID {

    public String getUUID(){
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replace("-","");
        return uuid;
    }

}
