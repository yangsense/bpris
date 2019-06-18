package com.ai.aris.server.sysmanage.service.interfaces;


/**
 * 操作员日志相关服务
 *
 * @author Administrator
 */
public interface IOperatorLogSV {
	//以下是操作类型常量定义
	public static final String optTypeAddUser = "adduser";            //添加操作员   
	public static final String optTypeUpdateUser = "updateuser";      //修改操作员   
	public static final String optTypeUpdateUserRole = "updateuserrole";      //用户角色复权
	public static final String optTypeDeleteUser = "deleteuser";      //删除操作员
	public static final String optTypeStopUser = "stopuser"; //停用操作员
	public static final String optTypeUpdatePSW = "updatepsw";        //修改密码     
	public static final String optTypeUserLogin = "login";                //登陆系统     
	public static final String optTypeUserLogOut = "logout";                //注销系统     
	public static final String optTypeAddPhone = "addphone";          //添加终端     
	public static final String optTypeAddFence = "addFence";          //添加围栏  
	public static final String optTypeUpdateFence = "updateFence";          //添加围栏  
	public static final String optTypeDeleteFences = "deleteFences";          //删除围栏  
	public static final String optTypeUpdatePhone = "updatephone";    //修改终端     
	public static final String optTypeDelPhone = "delphone";          //删除终端     
	public static final String optTypeForceSignOut = "forcesignout";  //强制签出     
	public static final String optTypeForceSignIn = "forcesignin";    //强制签入     
	public static final String optTypeAddWhiteAPP = "addwhiteapp";    //添加APP白名单
	public static final String optTypeDelWhiteAPP = "delwhiteapp";    //删除APP白名单
	public static final String optTypeAddWhitePhone = "addwhitephone";//添加可拨打的电话
	public static final String optTypeUpdateWhitePhone = "updatewhitephone"; //修改可拨打的号码
	public static final String optTypeDelWhitePhone = "delwhitephone"; //删除可拨打的号码
	public static final String optTypeUpdateShortNum = "updateshortnum"; //修改手机短号
	public static final String optTypeDeletePhoneCutNet = "deletePhoneCutNet";
	public static final String optTypeSavePhoneCutNet = "savePhoneCutNet";
	public static final String optTypeSavePhoneGatherTime = "savePhoneGatherTime";

	public static final String optTypeAddPoliceCallGroup = "addpolicecallgroup"; //添加群组信息
	public static final String optTypeUpdatePoliceCallGroup = "updatepolicecallgroup";//修改群组信息
	public static final String optTypeDelPoliceCallGroup = "delpolicecallgroup";//删除群组信息
	public static final String optTypeAddCallGroup2ShortNum = "addcallgroup2shortnum";//短号加入群组
	public static final String optTypeDelCallGroup2ShortNum = "delcallgroup2shortnum";//短号退出群组 

	public static final String savePolicePhoneInfoSepState = "savePolicePhoneInfoSepState";//保存手机分离状态

	public static final String savePolicePhoneInfoMonState = "savePolicePhoneInfoMonState";//保存手机监控状态

	public static final String optTypeDelCell = "delCell";//删除基站配置信息
	public static final String optTypeAddCell = "addCell";//添加基站 配置信息
	public static final String optTypeUpdateCell = "updateCell";//修改基站 配置信息
	public static final String optTypeAddRfBook = "addRfBook";//添加刷卡器配置信息
	public static final String optTypeDelRfBook = "delRfBook";//删除刷卡器配置信息
	public static final String optTypeUpdateRfBook = "updateRfBook";//修改刷卡器配置信息 
	public static final String optTypeAddAppConfig = "addAppConfig";//添加APP应用配置信息
	public static final String optTypeUpdateAppConfig = "updateAppConfig";//修改APP应用配置信息
	public static final String optTypeDelAppConfig = "delAppConfig";//删除APP应用配置信息

	public static final String optTypeDelCallRecording = "delCallRecording";//删除通话录音
	public static final String optTypeUpdateClientVersion = "updateClientVersion";//修改客户端版本

	public static final String optTypeStartGroupCallMeeting = "startGroupCallMeeting";//发起群组会议
	public static final String optTypeHangupGroupCallMeeting = "hangupGroupCallMeeting";//挂断群组会议
	public static final String optTypeInviteGroupCallMember = "inviteGroupCallMember";//邀请成员通话
	public static final String optTypeHangupGroupCallMember = "hangupGroupCallMember";//挂断通话成员

	public static final String optTypeAddSensitiveWord = "addSensitiveWord";//添加敏感词
	public static final String optTypeDelSensitiveWord = "delSensitiveWord";//删除敏感词

	public static final String optTypeAddDoc = "addDoc";//添加文档
	public static final String optTypeUpdateDoc = "updateDoc";//修改文档
	public static final String optTypeDelDoc = "delDoc";//删除文档

	public static final String optTypeAddDocTag = "addDocTag";//添加文档标签
	public static final String optTypeUpdateDocTag = "updateDocTag";//修改文档标签
	public static final String optTypeDelDocTag = "delDocTag";//删除文档标签

	public static final String optTypeAddUsefulExp = "addUsefulExp";//添加常用语
	public static final String optTypeUpdateUsefulExp = "updateUsefulExp";//修改常用语
	public static final String optTypeDelUsefulExp = "delUsefulExp";//删除常用语

	public static final String optTypeAddOrg = "optTypeAddOrg";//添加组织
	public static final String optTypeUpdateOrg = "optTypeUpdateOrg";//修改组织
	public static final String optTypeDelOrg = "optTypeDelOrg";//删除组织

	public static final String optTypeAddOrgCutnet = "optTypeAddOrgCutnet";//添加组织监控时间段
	public static final String optTypeDelOrgCutnet = "optTypeDelOrgCutnet";//删除组织监控时间段
	
	public static final String optTypeAddFenceRule = "optTypeAddFenceRule";//添加电子围栏规则
	public static final String optTypeUpdateFenceRule = "optTypeUpdateFenceRule";//添加电子围栏规则
	public static final String optTypeDelFenceRule = "optTypeDelFenceRule";//删除电子围栏规则
	public static final String optTypeStartFenceRule = "optTypeStartFenceRule";//启用电子围栏规则
	public static final String optTypeStopFenceRule = "optTypeStopFenceRule";//停止电子围栏规则
	
	public static final String optTypeDelFenceAlarm = "optTypeDelFenceAlarm";//删除围栏告警
	
	public static final String optTypeClearFenceAlarm = "optTypeClearFenceAlarm";//清除围栏告警
	
	public static final String optTypeRoleTypeAdd = "optTypeRoleTypeAdd";//新增角色
	
	public static final String optTypeRoleTypeUpdate = "optTypeRoleTypeUpdate";//修改角色
	
	public static final String optTypeRoleTypeDel = "optTypeRoleTypeDel";//删除角色
	
	public static final String optTypeMenuTypeAdd = "optTypeMenuTypeAdd";//新增菜单
	
	public static final String optTypeMenuTypeUpdate = "optTypeMenuTypeUpdate";//修改菜单
	
	public static final String optTypeMenuTypeDel = "optTypeMenuTypeDel";//删除菜单


	/**
	 * 保存操作员日志
	 *
	 * @param operatorCode 操作员工号
	 * @param operatorType 操作类型
	 * @param operatorDesc 操作描述
	 * @throws Exception
	 */
	public void saveOperatorLog(String operatorCode, String operatorType, String operatorDesc) throws Exception;


}
