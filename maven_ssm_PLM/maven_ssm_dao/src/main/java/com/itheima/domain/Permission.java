package com.itheima.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @outhor 孙成玉
 * UserInfo:Administrator
 * Date:2020-05-19
 * Time:11:15
 */
public class Permission implements Serializable {

    private String id;
    private String permissionName;
    private String url;
    private List<Role> roles;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
