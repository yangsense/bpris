package com.ai.aris.server.webservice.bean;

import java.io.Serializable;

/**
 * Created by Walter on 2017/8/7.
 */
public class UserSessionAdd implements Serializable {
    private String add;
    private String sessid;
    private String username;



    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username=username;
    }

    public String getIp() {
        return add;
    }

    public void setAdd(String add) {
        this.add = add;
    }

    public String getAdd(){
        return this.add;
    }

    public String getSessid() {
        return sessid;
    }

    public void setSessid(String sessid) {
        this.sessid = sessid;
    }
}
