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
    <script type="text/javascript" src="${ctx}/aris/statics/pages/js/basecode/aiscequip/aiscequipdetail.js"></script>
    <link type="text/css" rel="stylesheet" href="${ctx}/aris/statics/common/js/date/skin/WdatePicker.css"/>
    <script type="text/javascript" src="${ctx}/aris/statics/common/js/date/WdatePicker.js"></script> 
</head>
 <body class="body">
<div class="main-content">
    <div class="page-content min_style">
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-12">
                <form class="form-horizontal" role="form" id="aiscEquipForm" dataType="${dataType}" old_sb_type="${aiscEquipBean.modalityId}" 
                param1="${aiscEquipBean.modalityEnabled}" param2="${aiscEquipBean.modalityWorklist}" param3="${aiscEquipBean.modalitySupportchinese}" 
                param4="${aiscEquipBean.modalitySupportsexage}" param5="${aiscEquipBean.modalitySupportid}" old_loc_id="${aiscEquipBean.locId}" old_org_id="${aiscEquipBean.orgId}" old_room_id="${aiscEquipBean.roomId}" old_sub_cate="${aiscEquipBean.ordsubcategoryId}">
                    <div class="form-group">
                    	<label class="col-xs-2 control-label no-padding-right" style="width: 20%"> 设备ID： </label>
                        <div class="col-xs-3"  style="width: 30%"  >
                            <input  class="inputW160" type="text" id="equipmentId" name="equipmentId" value="${aiscEquipBean.equipmentId}" />
                        </div>
                    	<label class="col-xs-2 control-label no-padding-right" style="width: 20%"> 所属机构： </label>
                        <div class="col-xs-3"  style="width: 30%">
                        	<select id="orgId" name="orgId" class="inputW160" onchange="loadLoc()">
                                <option value="-1">请选择机构</option>
                            </select> 
                        </div>
                    </div>
                    
                    <div class="form-group">
                    	<label class="col-xs-2 control-label no-padding-right" style="width: 20%"><span style="color:red;">*</span> 科室名称： </label>
                        <div class="col-xs-3"  style="width: 30%">
                            <select id="locId" name="locId" class="inputW160" onchange="loadRoom()">
                                <option value="-1">--请选择--</option>
                            </select>
                        </div>
                        <label class="col-xs-2 control-label no-padding-right" style="width: 20%"><span style="color:red;">*</span> 所属房间： </label>
                        <div class="col-xs-3"  style="width: 30%">
                           <select id="roomId" name="roomId" class="inputW160">
                                <option value="-1">请选择房间</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right" style="width: 20%"><span style="color:red;">*</span> 设备类型： </label>
                        <div class="col-xs-3"  style="width: 30%">
                        	<select id="modalityId" name="modalityId" class="inputW160">
                                <option value="-1">请选择类型</option>
                            </select>
                        </div>
                        <label class="col-xs-2 control-label no-padding-right" style="width: 20%"><span style="color:red;">*</span> 设备代码： </label>
                        <div class="col-xs-3"  style="width: 30%">
                           <input  class="inputW160" type="text" id="equipmentCode" name="equipmentCode" value="${aiscEquipBean.equipmentCode}"/>
                        </div>
                    </div>
                    
                    <div class="form-group">
                    	<label class="col-xs-2 control-label no-padding-right" style="width: 20%"><span style="color:red;">*</span> 设备描述： </label>
                        <div class="col-xs-3"  style="width: 30%">
                           <input  class="inputW160" type="text" id="equipmentDesc" name="equipmentDesc" value="${aiscEquipBean.equipmentDesc}"/>
                        </div>
                        <label class="col-xs-2 control-label no-padding-right" style="width: 20%"> 设备描述(英文)： </label>
                        <div class="col-xs-3"  style="width: 30%">
                       		<input  class="inputW160" type="text" id="equipmentEndesc" name="equipmentEndesc" value="${aiscEquipBean.equipmentEndesc}"/>
                        </div>
                    </div>
                    
                    <div class="form-group">
                    	<label class="col-xs-2 control-label no-padding-right" style="width: 20%"> 设备Dicom AE： </label>
                        <div class="col-xs-3"  style="width: 30%">
                           <input  class="inputW160" type="text" id="equipmentAe" name="equipmentAe" value="${aiscEquipBean.equipmentAe}"/>
                        </div>
                        <label class="col-xs-2 control-label no-padding-right" style="width: 20%">IP地址： </label>
                        <div class="col-xs-3"  style="width: 30%">
                        	<input  class="inputW160" type="text" id="equipmentIp" name="equipmentIp" value="${aiscEquipBean.equipmentIp}"/>
                        </div>
                    </div>
                    
                    <div class="form-group">
                    	<label class="col-xs-2 control-label no-padding-right" style="width: 20%"> 监听端口： </label>
                        <div class="col-xs-3"  style="width: 30%">
                           <input  class="inputW160" type="text" id="equipmentPort" name="equipmentPort" value="${aiscEquipBean.equipmentPort}"/>
                        </div>
                        <label class="col-xs-2 control-label no-padding-right" style="width: 20%"><span style="color:red;">*</span> 设备开始使用日期： </label>
                        <div class="col-xs-3"  style="width: 30%">
                        	<input id="equipmentStartdate" name="equipmentStartdate" class="inputW160" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" value="<fmt:formatDate value="${aiscEquipBean.equipmentStartdate}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
                        </div>
                    </div>
                    
                    <div class="form-group">
                    	<label class="col-xs-2 control-label no-padding-right" style="width: 20%"> 设备厂商： </label>
                        <div class="col-xs-3"  style="width: 30%">
                           <input  class="inputW160" type="text" id="equipmentManufacturer" name="equipmentManufacturer" value="${aiscEquipBean.equipmentManufacturer}"/>
                        </div>
                        <label class="col-xs-2 control-label no-padding-right" style="width: 20%"> 设备厂商电话： </label>
                        <div class="col-xs-3"  style="width: 30%">
                        	<input  class="inputW160" type="text" id="equipmentPhone" name="equipmentPhone" value="${aiscEquipBean.equipmentPhone}"/>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right" style="width: 20%"> 设备是否可用： </label>
                        <div class="col-xs-3"  style="width: 30%">
                        	<select id="modalityEnabled" name="modalityEnabled" class="inputW160">
                                <option value="1">是</option>
                                <option value="0">否</option>
                            </select>
                        </div>
                        <label class="col-xs-2 control-label no-padding-right" style="width: 20%"> 是否调用worklist： </label>
                        <div class="col-xs-3"  style="width: 30%">
                           <select id="modalityWorklist" name="modalityWorklist" class="inputW160">
                                <option value="1">是</option>
                                <option value="0">否</option>
                            </select>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="col-xs-2 control-label no-padding-right" style="width: 20%"> 是否支持中文： </label>
                        <div class="col-xs-3"  style="width: 30%">
                        	<select id="modalitySupportchinese" name="modalitySupportchinese" class="inputW160">
                                <option value="1">是</option>
                                <option value="0">否</option>
                            </select>
                        </div>
                        <label class="col-xs-2 control-label no-padding-right" style="width: 20%"> 是否支持姓名中带有性别和年龄： </label>
                        <div class="col-xs-3"  style="width: 30%">
                           <select id="modalitySupportsexage" name="modalitySupportsexage" class="inputW160">
                                <option value="1">是</option>
                                <option value="0">否</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                    	<label class="col-xs-2 control-label no-padding-right" style="width: 20%"> 是否支持姓名中带有体检号： </label>
                        <div class="col-xs-3"  style="width: 30%">
                        	<select id="modalitySupportid" name="modalitySupportid" class="inputW160">
                                <option value="1">是</option>
                                <option value="0">否</option>
                            </select>
                        </div>
                    	<label class="col-xs-2 control-label no-padding-right" style="width: 20%"><span style="color:red;">*</span> 医嘱子类ID： </label>
                    	<div class="col-xs-3"  style="width: 30%">
                   		<select id="ordsubcategoryId" name="ordsubcategoryId" class="inputW160">
                              <option value="-1">请选择子类</option>
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
