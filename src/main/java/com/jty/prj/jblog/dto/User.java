package com.jty.prj.jblog.dto;

/**
 * @author jinit
 * @project Jblog
 * @file User
 * @date 20-1-2
 * @todo TODO
 */
public class User {
    /**
     * 主键
     */
    private Integer uid;
    private String user_number;
    private String user_name;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUser_number() {
        return user_number;
    }

    public void setUser_number(String user_number) {
        this.user_number = user_number;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }
}
