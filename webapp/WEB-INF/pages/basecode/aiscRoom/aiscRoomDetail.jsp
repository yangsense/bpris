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
    <script type="text/javascript" src="${ctx}/aris/statics/pages/js/basecode/aiscroom/aiscroomdetail.js"></script>
</head>
 <body class="body">
<div class="main-content">
    <div class="page-content min_style">
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-12">
                <form class="form-horizontal" role="form" id="aiscRoomForm" dataType="${dataType}" 
                old_roomEnabled="${aiscRoomBean.roomEnabled}" old_loc_id="${aiscRoomBean.locId}" old_org_id="${aiscRoomBean.orgId}">
                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right"  style="width: 15%"> 诊间ID： </label>
                        <div class="col-xs-3" style="width: 35%" >
                            <input  class="inputW160" type="text" id="roomId" name="roomId" value="${aiscRoomBean.roomId}" />
                        </div>
                        <label class="col-xs-2 control-label no-padding-right"  style="width: 15%"><span style="color:red;">*</span> 诊间名称： </label>
                        <div class="col-xs-3" style="width: 35%" >
                        	<input  class="inputW160" type="text" id="roomDesc" name="roomDesc" value="${aiscRoomBean.roomDesc}"/>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right"  style="width: 15%"> 机构编号： </label>
                        <div class="col-xs-3" style="width: 35%">
                        	<select id="orgId" name="orgId" class="inputW160" onchange="loadLoc()">
                                <option value="-1">请选择机构</option>
                            </select>
                        </div>
                        <label class="col-xs-2 control-label no-padding-right"  style="width: 15%"><span style="color:red;">*</span> 所属科室： </label>
                        <div class="col-xs-3" style="width: 35%" >
                        	<select id="locId" name="locId" class="inputW160">
                                <option value="-1">--请选择--</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right"  style="width: 15%"> 诊间英文名称： </label>
                        <div class="col-xs-3" style="width: 35%" >
                        	<input  class="inputW160" type="text" id="roomEndesc" name="roomEndesc" value="${aiscRoomBean.roomEndesc}"/>
                        </div>
                        <label class="col-xs-2 control-label no-padding-right"  style="width: 15%"> 是否可用： </label>
                        <div class="col-xs-3" style="width: 35%" >
                           <select id="roomEnabled" name="roomEnabled" class="inputW160">
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
