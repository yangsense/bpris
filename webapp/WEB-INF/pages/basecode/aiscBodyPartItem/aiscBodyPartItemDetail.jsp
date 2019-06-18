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
    <script type="text/javascript" src="${ctx}/aris/statics/pages/js/basecode/aiscbodypartitem/aiscbodypartitemdetail.js"></script>

    <link rel="stylesheet" href="${ctx}/aris/statics/pages/css/multiselect2side/jquery.multiselect2sidebodypart.css" type="text/css">
    <script type="text/javascript" src="${ctx}/aris/statics/pages/js/basecode/aiscbodypartitem/jquery.multiselect2bodypart.js"></script>
    </head>
    
 <body class="body">
<div class="wrap100 quickrecharge">
 	<div class="q_r_list">
 		<div class="listTable">
            <form id="searchForm" action="#" old_orgId="${aiscBodyPartItemBean.orgId}">
                  <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr  align="left">
                        <th class="r" width="10%">机构名称：</th>
                        <td>
                           	<select id="orgId" name="orgId" class="inputW160">
                               <option value="-1">请选择机构</option>
                            </select>
                        </td>  
                        <th class="r" width="10%">医嘱大类 ：</th>
                        <td>
                        	 	<select id="ordcategoryId" name="ordcategoryId" onchange="initSubCate()" class="inputW160">
                               <option value="-1">请选择大类</option>
                            </select>
                        </td>                         
                        <th  class="r" width="10%">医嘱子类：</th>
                        <td >
                            <select id="ordsubcategoryId" name="ordsubcategoryId" class="inputW160">
                               <option value="-1">请选择子类</option>
                            </select>           
                        </td>
                    </tr>
                   <tr>
                   		<th  class="r" width="10%">部位大类：</th>
                        <td>
                        	<select id="bodypartPid" name="bodypartPid" class="inputW160">
                        		<option value="-1">--请选择--</option>
                            </select>
                        </td> 
                        <td colspan="4" align="center">
                        	<a href="javascript:void(0)" onclick="postloaddata('selectItem')"
                           class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-success">
                           <span class="icon-search"></span>查 询</a>
                        </td>
                   </tr>
                    <tr  >
	            		<td>
	            			<div class="ms2side__header" style="width:228px;"><span style="color:red;">*</span> 医嘱列表</div>
	            			<select name="searchableYZ" id='itemMastList' multiple='multiple' style="width:230px;height:380px" onclick="postloadBodypart(this.options[this.selectedIndex].value);">
							</select>
	            		</td>
	            		<!-- <td colspan="4" >
	            			<select name="searchableBigBody" id='bigbodyList' multiple='multiple' style="width:150px;height:180px;">
							</select>
	            		</td> -->
	            		<td colspan="5">
	            			<select name="searchableJoin" id='bodyPartList' multiple='multiple' >
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
