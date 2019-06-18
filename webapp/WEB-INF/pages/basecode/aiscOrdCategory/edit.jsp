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
    <script type="text/javascript" src="${ctx}/aris/statics/pages/js/basecode/aiscordcategory/edit.js"></script>
    <link type="text/css" rel="stylesheet" href="${ctx}/aris/statics/common/js/date/skin/WdatePicker.css"/>
    <script type="text/javascript" src="${ctx}/aris/statics/common/js/date/WdatePicker.js"></script> 
</head>
 <body class="body">
 <div class="wrap100 quickrecharge">
     <!--导航end-->
     <div class="q_r_list">
         <!--查询条件 begin-->
         <div class="listTable">
             <form id="editForm" action="#" opType="${opType}" >
                 <input type="hidden" id="ordcategoryId" name="ordcategoryId" value="${id}"/>
                 <table width="100%" border="0" cellspacing="0" cellpadding="0">
                     <tr align="left">
                     	 <th class="r" width="11%"><span style="color:red;">*</span> 机构名称 ：</th>
                         <td width="18%">
                            <select id="orgId" name="orgId" class="inputW160">
                                <option value="-1">请选择机构</option>
                            </select> 
                         </td> 
                         <th class="r" width="13%"><span style="color:red;">*</span> 大类代码：</th>
                         <td width="18%">
                             <input name="ordcategoryCode" id="ordcategoryCode" class="inputW160"/>
                         </td>
                     </tr>
                     <tr>
                      	<th class="r" width="13%"><span style="color:red;">*</span> 检查大类名称：</th>
                         <td width="18%">
                             <input name="ordcategoryDesc" id="ordcategoryDesc" class="inputW160"/>
                         </td>
                         <th width="13%" class="r">英文描述：</th>
                         <td width="18%">
                             <input name="ordcategoryEndesc" id="ordcategoryEndesc" class="inputW160"/>
                         </td>
                         <td></td>
                     </tr>
                 </table>
             </form>


         </div>
     </div>
 </div>
 <div class="main-content">
     <div class="page-content min_style">
         <div class="col-bt-center">
             <button class="btn btn-info" type="button" id="saveBtn">
                 <i class="ace-icon fa fa-check bigger-110"></i>
                 <span id="showText">保 存</span>
             </button>
             <button class="btn btn-info" type="button" id="closeBtn">
                 <i class="ace-icon fa fa-check bigger-110"></i>
                 取 消
             </button>
         </div>
     </div>
 </div>
 </body>
 
</html>
