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
    <script type="text/javascript" src="${ctx}/aris/statics/pages/js/basecode/aiscmedium/aiscmediumdetail.js"></script>
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
                <form class="form-horizontal" role="form" id="aiscMediumForm" dataType="${dataType}" old_medium_type="${aiscMediumInfoBean.mediumType}"
                old_mediumEnabled="${aiscMediumInfoBean.mediumEnabled}" old_mediumIsfull="${aiscMediumInfoBean.mediumIsfull}" 
                old_mediumIsonline="${aiscMediumInfoBean.mediumIsonline}"  old_server="${aiscMediumInfoBean.serverId}" >
                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right"  style="width:15%"> 介质ID： </label>
                        <div class="col-xs-3"   style="width:35%">
                            <input  class="inputW120" type="text" id="mediumId" name="mediumId" value="${aiscMediumInfoBean.mediumId}" />
                        </div>
                        <label class="col-xs-2 control-label no-padding-right"  style="width:15%"><span style="color:red;">*</span> 介质名称： </label>
                        <div class="col-xs-3"   style="width:35%">
                        	<input  class="inputW120" type="text" id="mediumName" name="mediumName" value="${aiscMediumInfoBean.mediumName}" />
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right"  style="width:15%"><span style="color:red;">*</span> 服务器名称： </label>
                        <div class="col-xs-3"   style="width:35%">
                           <select id="serverId" name="serverId" class="inputW120">
                                <option value="-1">请选择服务器</option>
                            </select>
                        </div>
                         <label class="col-xs-2 control-label no-padding-right"  style="width:15%"><span style="color:red;">*</span> 介质路径： </label>
                        <div class="col-xs-3"   style="width:35%">
                        	<input  class="inputW120" type="text" id="mediumPath" name="mediumPath" value="${aiscMediumInfoBean.mediumPath}"/>
                        </div>
                    </div>

					<div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right"  style="width:15%"> 是否已满： </label>
                        <div class="col-xs-3"   style="width:35%">
                        	<select id="mediumIsfull" name="mediumIsfull" class="inputW120">
                                <option value="1">已满</option>
                                <option value="0">未满</option>
                            </select>
                        </div>
                        <label class="col-xs-2 control-label no-padding-right"  style="width:15%"> 是否在线： </label>
                        <div class="col-xs-3"   style="width:35%">
                           <select id="mediumIsonline" name="mediumIsonline" class="inputW120">
                                <option value="1">在线</option>
                                <option value="0">离线</option>
                            </select>
                        </div>
                    </div>

					<div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right"  style="width:15%"><span style="color:red;">*</span> 初始大小： </label>
                        <div class="col-xs-3"   style="width:35%">
                           <input  class="inputW120" type="text" id="mediumSize" name="mediumSize" value="${aiscMediumInfoBean.mediumSize}"/>
                        </div>
                         <label class="col-xs-2 control-label no-padding-right"  style="width:15%"><span style="color:red;">*</span> 剩余大小： </label>
                        <div class="col-xs-3"   style="width:35%">
                        	<input  class="inputW120" type="text" id="mediumReminesize" name="mediumReminesize" value="${aiscMediumInfoBean.mediumReminesize}"/>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right"  style="width:15%"><span style="color:red;">*</span> 介质类型： </label>
                        <div class="col-xs-3"   style="width:35%">
                        	<select id="mediumType" name="mediumType" class="inputW120">
                                <option value="-1">请选择类型</option>
                            </select>
                        </div>
                        <label class="col-xs-2 control-label no-padding-right"  style="width:15%"> 是否可用： </label>
                        <div class="col-xs-3"   style="width:35%">
                           <select id="mediumEnabled" name="mediumEnabled" class="inputW120">
                                <option value="1">是</option>
                                <option value="0">否</option>
                            </select>
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
