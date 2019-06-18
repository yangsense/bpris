<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns=" http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/common/meta.jsp" %>
    <link rel="stylesheet" href="${ctx}/aris/statics/ace/plugins/validate/jquery.validate.css" type="text/css">
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/validate/jquery.validate.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/validate/additional-methods.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/validate/messages_cn.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/pages/js/basecode/aiscboardroom/boardroomdetail.js"></script>
	<script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/jquery/validate.expand.js"></script>
</head>
 <body class="body">
<div class="main-content">
    <div class="page-content min_style">
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-12">
                <form class="form-horizontal" role="form" id="aiscboardroomForm" dataType="${dataType}" old_enablechairpwd="${boardroomBean.enablechairpwd}" old_verifymode="${boardroomBean.verifymode}">
                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right" style="width: 15%"> 会议室ID： </label>
                        <div class="col-xs-3"  style="width: 35%"  >
                            <input  class="inputW160" type="text" id="boardroomId" name="boardroomId" value="${boardroomBean.boardroomId}" />
                        </div>
                        <label class="col-xs-2 control-label no-padding-right" style="width: 15%"><span style="color:red;">*</span> 会议室名称： </label>
                        <div class="col-xs-3"  style="width: 35%">
                           <input  class="inputW160" type="text" id="boardroomName" name="boardroomName" value="${boardroomBean.boardroomName}"/>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right" style="width: 15%"><span style="color:red;">*</span> 登录校验模型： </label>
                        <div class="col-xs-3"  style="width: 35%">
                       		 <select id="verifymode" name="verifymode" class="inputW160" onchange="changeType(this)">
                                <option value="-1">全部</option>
                                <option value="1">用户密码验证</option>
                                <option value="2">会议室密码验证</option>
                                <option value="3">匿名登录</option>
                             </select>
                        </div>
                        <label class="col-xs-2 control-label no-padding-right" style="width: 15%"><span id="title1" style="color:red;display: none;" >*</span> 会议室密码： </label>
                        <div class="col-xs-3"  style="width: 35%">
                           <input  class="inputW160" type="text" id="password" name="password" value="${boardroomBean.password}"/>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right" style="width: 15%"><span style="color:red;">*</span> 允许最大用户数： </label>
                        <div class="col-xs-3"  style="width: 35%">
                        	<input  class="inputW160" type="text" id="maxusercount" name="maxusercount" value="${boardroomBean.maxusercount}"/>
                        </div>
                        <label class="col-xs-2 control-label no-padding-right" style="width: 15%"><span style="color:red;">*</span> 是否允许主席密码： </label>
                        <div class="col-xs-3"  style="width: 35%">
                            <select id="enablechairpwd" name="enablechairpwd" class="inputW160" onchange="changeType1(this)">
                                <option value="0">不启用</option>
                                <option value="1">启用</option>
                             </select>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right" style="width: 15%"><span id="title2" style="color:red;display: none;" >*</span> 主席密码： </label>
                        <div class="col-xs-3"  style="width: 35%">
                           <input  class="inputW160" type="text" id="chairpassword" name="chairpassword" value="${boardroomBean.chairpassword}"/>
                        </div>
                    </div>
                    
                    <div class="clearfix form-actions">
                        <div class="col-bt-center">
                            <button class="btn btn-info" type="button" id="saveBtn">
                                <i class="ace-icon fa fa-check bigger-110"></i>
                                	<span id="showText">新 增</span>
                            </button>
                            <button class="btn btn-info" type="button" id="closeBtn">
                                <i class="ace-icon fa fa-check bigger-110"></i>
                             	   取 消
                            </button>
                        </div>
                    </div>
                </form>
			</div>
		</div>	
	</div>
</div>
 </body>
</html>
