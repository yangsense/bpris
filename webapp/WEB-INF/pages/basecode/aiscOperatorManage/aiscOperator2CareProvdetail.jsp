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
    <script type="text/javascript" src="${ctx}/aris/statics/pages/js/basecode/aiscoperator2careprov/aiscoperator2careprovdetail.js"></script>
    <link rel="stylesheet" href="${ctx}/aris/statics/pages/css/multiselect2side/jquery.multiselect2side.css" type="text/css">
    <script type="text/javascript" src="${ctx}/aris/statics/pages/js/basecode/aiscoperator2careprov/jquery.multiselect2side.js"></script>       
</head>
 <body class="body">
 
<div class="wrap100 quickrecharge">
 	<div class="q_r_list">
 		<div class="listTable">
            <form id="aiscOperator2CareProvForm" action="#" dataType="${dataType}">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                	<tr>
                        <th width="15%" class="r">所属机构：</th>
                        <td width="20%">
                           <select id="orgId" name="orgId" class="inputW160">
                            </select> 
                        </td> 
                        <td colspan='4'>
                        <a href="javascript:void(0)" onclick="setInitoperatorlist()"
                           class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-success"><span
                                class="icon-search"></span>查 询</a>
                        </td>
                    </tr>
                    <tr>
            		<td >
            			<div class="ms2side__header" style="width:248px"><span style="color:red;">*</span> 操作人员</div>
            			<select name="searchableoper" id='operatorlist' multiple='multiple' style="width:250px;height:180px" onclick="postloaddata(this.options[this.selectedIndex].value);">
						</select>
            		</td>
            		<td colspan="5">
            			<select name="searchablecare" id='careprovlist' multiple='multiple' >
						</select>
            		</td>
            	</tr>
                </table>
                <input id="flag" type="hidden"/>
            </form>
        </div>
        
		<div class="clearfix form-actions">
			<div class="col-bt-center" align="center">
				<button class="btn btn-info" type="button" id="saveBtn">
		   			<i class="ace-icon fa fa-check bigger-110"></i>
						<span id="showText">保  存</span>
				</button>
				<button class="btn btn-info" type="button" id="closeBtn">
					<i class="ace-icon fa fa-check bigger-110"></i>
					   取 消
			   </button>
		   </div>
		</div>
    </div>
</div>
 </body>
</html>
