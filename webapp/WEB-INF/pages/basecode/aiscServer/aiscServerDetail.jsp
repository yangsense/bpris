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
    <script type="text/javascript" src="${ctx}/aris/statics/pages/js/basecode/aiscserver/aiscserverdetail.js"></script>
    <link rel="stylesheet" href="${ctx}/aris/statics/common/plugins/ztree/css/zTreeStyle/zTreeStyle.css"
          type="text/css">
    <link rel="stylesheet" href="${ctx}/aris/statics/common/plugins/ztree/css/selectTree.css" type="text/css">
    <script type="text/javascript" src="${ctx}/aris/statics/common/plugins/ztree/js/jquery.ztree.core-3.5.js"></script>
    <script type="text/javascript"
            src="${ctx}/aris/statics/common/plugins/ztree/js/jquery.ztree.excheck-3.5.js"></script>
    <script type="text/javascript"
            src="${ctx}/aris/statics/common/plugins/ztree/js/jquery.ztree.exedit-3.5.js"></script>
</head>
 <body class="body">
<div class="main-content">
    <div class="page-content min_style">
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-12">
                <form class="form-horizontal" role="form" id="aiscServerForm" dataType="${dataType}" old_server_type="${aiscServerInfoBean.serverType}"
                old_serverEnabled="${aiscServerInfoBean.serverEnabled}">
                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right"  style="width: 15%"> 服务器ID： </label>
                        <div class="col-xs-3" style="width: 35%">
                            <input  class="inputW120" type="text" id="serverId" name="serverId" value="${aiscServerInfoBean.serverId}" />
                        </div>
                        <label class="col-xs-2 control-label no-padding-right"  style="width: 15%"><span style="color:red;">*</span> 服务器名称： </label>
                        <div class="col-xs-3" style="width: 35%">
                        	<input  class="inputW120" type="text" id="serverName" name="serverName" value="${aiscServerInfoBean.serverName}" />
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right"  style="width: 15%"><span style="color:red;">*</span> 服务器IP： </label>
                        <div class="col-xs-3" style="width: 35%">
                           <input  class="inputW120" type="text" id="serverIp" name="serverIp" value="${aiscServerInfoBean.serverIp}"/>
                        </div>
                         <label class="col-xs-2 control-label no-padding-right"  style="width: 15%"><span style="color:red;">*</span> 服务器端口： </label>
                        <div class="col-xs-3" style="width: 35%">
                        	<input  class="inputW120" type="text" id="serverPort" name="serverPort" value="${aiscServerInfoBean.serverPort}"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right"  style="width: 15%"><span style="color:red;">*</span> 服务器类型： </label>
                        <div class="col-xs-3" style="width: 35%">
                        	<select id="serverType" name="serverType" class="inputW120">
                                <option value="-1">请选择类型</option>
                            </select>
                        </div>
                        <label class="col-xs-2 control-label no-padding-right"  style="width: 15%"> 是否可用： </label>
                        <div class="col-xs-3" style="width: 35%">
                           <select id="serverEnabled" name="serverEnabled" class="inputW120">
                                <option value="1">是</option>
                                <option value="0">否</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right"  style="width: 15%"> 登陆用户： </label>
                        <div class="col-xs-3" style="width: 35%">
                           <input  class="inputW120" type="text" id="serverUser" name="serverUser" value="${aiscServerInfoBean.serverUser}"/>
                        </div>
                         <label class="col-xs-2 control-label no-padding-right"  style="width: 15%"> 登陆密码： </label>
                        <div class="col-xs-3" style="width: 35%">
                        	<input  class="inputW120" type="text" id="serverPassword" name="serverPassword" value="${aiscServerInfoBean.serverPassword}"/>
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
