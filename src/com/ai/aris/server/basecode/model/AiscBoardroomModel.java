package com.ai.aris.server.basecode.model;
import java.sql.Timestamp;
import com.ai.common.domain.DictItem;

public class AiscBoardroomModel {

    private  long maxusercount;
    private  String verifymode;
    private  String keycode;
    private  String boardroomName;
    private  long boardroomId;
    private  String password;
    private  String enablechairpwd;
    private  String chairpassword;

//自定义字典

    public long getMaxusercount(){
        return maxusercount;
    }
    public String getVerifymode(){
        return verifymode;
    }
    public String getKeycode(){
        return keycode;
    }
    public String getBoardroomName(){
        return boardroomName;
    }
    public long getBoardroomId(){
        return boardroomId;
    }
    public String getPassword(){
        return password;
    }
    public String getEnablechairpwd(){
        return enablechairpwd;
    }
    public String getChairpassword(){
        return chairpassword;
    }

    public  void setMaxusercount(long maxusercount){
        this.maxusercount = maxusercount;
    }
    public  void setVerifymode(String verifymode){
        this.verifymode = verifymode;
    }
    public  void setKeycode(String keycode){
        this.keycode = keycode;
    }
    public  void setBoardroomName(String boardroomName){
        this.boardroomName = boardroomName;
    }
    public  void setBoardroomId(long boardroomId){
        this.boardroomId = boardroomId;
    }
    public  void setPassword(String password){
        this.password = password;
    }
    public  void setEnablechairpwd(String enablechairpwd){
        this.enablechairpwd = enablechairpwd;
    }
    public  void setChairpassword(String chairpassword){
        this.chairpassword = chairpassword;
    }

//自定义字典
}
