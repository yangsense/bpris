package com.ai.aris.server.webservice.bean;

/**
 * Created by lenovo on 2016/12/29.
 */
public class AiscServerInfoData {
      private String  ServerPassword  ;
      private long  ServerPort  ;
      private String  ServerUser  ;
      private long  ServerId  ;
      private long  ServerEnabled  ;
      private long  ServerType  ;
      private String  ServerName ;
      private String  ServerIp  ;

    public long getServerEnabled() {
        return ServerEnabled;
    }

    public void setServerEnabled(long serverEnabled) {
        ServerEnabled = serverEnabled;
    }

    public long getServerId() {
        return ServerId;
    }

    public void setServerId(long serverId) {
        ServerId = serverId;
    }

    public String getServerIp() {
        return ServerIp;
    }

    public void setServerIp(String serverIp) {
        ServerIp = serverIp;
    }

    public String getServerName() {
        return ServerName;
    }

    public void setServerName(String serverName) {
        ServerName = serverName;
    }

    public String getServerPassword() {
        return ServerPassword;
    }

    public void setServerPassword(String serverPassword) {
        ServerPassword = serverPassword;
    }

    public long getServerPort() {
        return ServerPort;
    }

    public void setServerPort(long serverPort) {
        ServerPort = serverPort;
    }

    public long getServerType() {
        return ServerType;
    }

    public void setServerType(long serverType) {
        ServerType = serverType;
    }

    public String getServerUser() {
        return ServerUser;
    }

    public void setServerUser(String serverUser) {
        ServerUser = serverUser;
    }
}
