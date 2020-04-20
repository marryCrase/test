package com.itheima.domain;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * @outhor 孙成玉
 * User:Administrator
 * Date:2020-03-30
 * Time:13:40
 */
public class Account implements Serializable{

    private Integer id;
    private String name;
    private Float money;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getMoney() {
        return money;
    }

    public void setMoney(Float money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", money='" + money + '\'' +
                '}';
    }
}
