package com.ai.common.util;

import java.io.Serializable;

/**
 * 系统常量
 * @author fangll
 *
 */
public final class Constants implements Serializable{
	public static final String USER_DEFAULT_PWD = "e10adc3949ba59abbe56e057f20f883e";   //新建用户默认密码,123456
	public static final String SESSION_USER = "_USER_INFO_MAP_";            //存session中管理平台用户信息
	public static final String SESSION_USER_OBJ = "_USER_INFO";            //存session中管理平台用户信息 
	public static final String SESSION_MENU = "_USER_MENU";
	public static final String SESSION_BUTTON = "_USER_BUTTON";            //按钮权限
	public static final String SESSION_STATION_BUTTON_RESOURCES = "_STATION_BUTTON_RESOURCES";
	public static final String SESSION_ORG = "_USER_ORG";
	public static final String SESSION_ORG_OBJ = "_ORG_OBJ";
	public static final String SESSION_SYS_ORG = "_SYS_ORG";
	public static final String SESSION_SYS_ORG_OBJ = "_SYS_ORG_OBJ";

	public static final String SESSION_MANAGE_ORG = "_USER_MANAGE_ORG";
	public static final String SESSION_OPERATORS_ORG = "_USER_OPERATORS_ORG";  //同一组织机构以下的操作员
	public static final String SESSION_MANAGE_ORG_ID = "_USER_MANAGE_ORG_ID";
	public static final String SESSION_OPERATOR2ORG = "_OPERATOR2ORG";
	public static final String SESSION_LOGIN_SYS = "USER_LOGIN_SYS_";    //登录的系统
	
	public static final String SESSION_LOGIN_SYS_COM = "0";    //系统默认
	public static final String SESSION_LOGIN_CARD_MANAGER = "1";    //一卡通
	public static final String SESSION_LOGIN_HEALTH_ARCHIVE = "2";    //健康档案
	public static final String SESSION_LOGIN_DATA_PLATFORM = "3";    //数据交换平台
	public static final String SESSION_LOGIN_YBJC = "4";    //数据交换平台
	public static final String SESSION_LOGIN_JYFX = "5";    //基药分析系统
	
	public static final String SESSION_LOGIN_BROWSER = "USER_LOGIN_BROWSER_";    //外系统调阅健康浏览器系统标志
	
	public static final String CLIENT_SESSION_USER = "_USER_INFO_MAP_";     //存session中client用户信息,合并为同一session
	public static final String WEB_CLIENT_SESSION_USER = "_USER_INFO_MAP_"; //存session中web client用户信息,合并为同一session
	public static final String SESSION_USER_AREA = "_USER_AREA_MAP_";  //存session中用户对应的dictarea对象信息
	public static final String PRI_MENU = "_PRI_MENU";                  //按钮权限
	public static final String LOGIN_TIME_OUT = "_LOGIN_TIME_OUT_"; //登录过期标识
	public static final String LOGINPARAM = "_LOGIN_PARAM_";        //存session中登录页面参数
	public static final String VIEWONLY = "_VIEW_ONLY_";			//只有查看权限，没有管理权限
	public static final String LAST_PAGE = "_LAST_PAGE_";           //记录最后一次访问页url

	public static final int pageSize = 30; //列表每页显示的条数
	public static final int ATTACH_BUFFER_SIZE = 16*1024;           //上传、下载文件的缓冲大小
	
	//导出数据默认限制行数
    public static final int EXP_LINE_LIMIT = 20000;
    
    ///导出Excel的参数
    public static final String RESULT_EXP_EXCEL = "EXP_EXCEL";
    public static final String ATTR_EXP_EXCEL = "datas";
    
    //交易相关参数
    public static final String SYS_OPERATOR = "SYS";         //系统自动处理账号
    public static final String OP_TYPE_INCOME = "1";         //收入标识
    public static final String OP_TYPE_SPEND = "2";          //支出标识 
 
    //交易科目
    public static final String TRADE_SUBJECT_0 = "0";          //会员卡激活预存金额
    public static final String TRADE_SUBJECT_1 = "1";          //银行充值
    public static final String TRADE_SUBJECT_2 = "2";          //节假日补贴福利 
    public static final String TRADE_SUBJECT_3 = "3";          //补、退订单货款差价 
    public static final String TRADE_SUBJECT_4 = "4";          //人工调账
    public static final String TRADE_SUBJECT_5 = "5";          //订单扣款

	//员工岗位
	public static final String STATION_CODE_TJ_COUNTY = "ADMIN_PACS_TJ_COUNTY";     //区县级别工号
	public static final String STATION_CODE_TJ_CITY = "ADMIN_PACS_TJ_CITY";          //市级工号
	public static final String SESSION_OPERATOR_STATION = "_OPERATOR_TJ_STATION";//统计岗位信息
	public static final String STATION_CODE_TJ = "ADMIN_PACS_";//统计岗位信息前缀


	//钱箱操作交易类型
	public static final int TRADE_TYPE_1 = 1;          //现金上缴
	public static final int TRADE_TYPE_2 = 2;          //现金上缴入库
	public static final int TRADE_TYPE_3 = 3;          //发放备用金
	public static final int TRADE_TYPE_4 = 4;          //领取备用金
	public static final int TRADE_TYPE_5 = 5;          //现金调出
	public static final int TRADE_TYPE_6 = 6;          //现金调入
    //钱箱操作现金流向
	public static final int CASHFLOW_1 = 1;          //支出
	public static final int CASHFLOW_2 = 2;          //收入
	
	//好视通静态密钥
	public static final String KEY_CODE = "3025495AEE146DA3864AB81BAAF79A3E";

	public static final String STATIONCODE_STAFF_BELONG_ORG ="STAFF_BELONG_ORG";

	public static final String ARIS_SYS_TYPE = "25";

	public static final String OPERATOR_STAFF_ADMIN ="ADMIN";
}