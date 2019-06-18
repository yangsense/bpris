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
    <script type="text/javascript" src="${ctx}/aris/statics/pages/js/basecode/aiscordcat2loc/aiscordcat2locdetail.js"></script>
    <link type="text/css" rel="stylesheet" href="${ctx}/aris/statics/common/js/date/skin/WdatePicker.css"/>
    <script type="text/javascript" src="${ctx}/aris/statics/common/js/date/WdatePicker.js"></script>

    <link rel="stylesheet" href="${ctx}/aris/statics/common/plugins/ztree/css/zTreeStyle/zTreeStyle.css"
          type="text/css">
    <link rel="stylesheet" href="${ctx}/aris/statics/common/plugins/ztree/css/selectTree.css" type="text/css">
    <script type="text/javascript" src="${ctx}/aris/statics/common/plugins/ztree/js/jquery.ztree.core-3.5.js"></script>
    <script type="text/javascript"
            src="${ctx}/aris/statics/common/plugins/ztree/js/jquery.ztree.excheck-3.5.js"></script>
    <script type="text/javascript"
            src="${ctx}/aris/statics/common/plugins/ztree/js/jquery.ztree.exedit-3.5.js"></script>

             <!-- 盖掉bootstrap样式 -->
    <style type="text/css">
    label.help-block-new {
	    display: block;
	    margin-top: -30px;
	    margin-left: 180px;
	    margin-bottom: 10px;
	    color: #ff7777
    }
    .main-content h1{
    background:#B9F1DE;
    border-top-left-radius:10px;
    border-top-right-radius:10px;
    height:34px;
    color:#09245F;
    font-size:16px;
    text-indent:10px;
    line-height:34px;}

    /*库存数量详情 表单 begin*/
	.main-content .fM_listTable2{ margin:auto; background:#fff; width:100%; border-collapse: separate; border-left: 1px solid #eee; border-top: 1px solid #eee; overflow-x: auto; clear:both; font-size:14px;margin-bottom:20px; }
	.main-content .fM_listTable2 th{border-bottom: 1px solid #eee; border-right: 1px solid #eee;height: 18px;padding: 10px 10px;text-align: center; background:#f8f8f8; color:#777; font-weight:700; border-bottom: 1px solid #eee; }
	.main-content .fM_listTable2 td{border-bottom: 1px solid #eee; border-right: 1px solid #eee; line-height: 22px; padding: 10px 3px; /*text-align:center;*/ vertical-align: top; color:#444;}

	.main-content .col-bt-right{text-align:right;margin-right:20px;}
	.col-xs-2{width:9%;}
	.col-xs-3{width:24%;}
	.row{margin-right:0px;margin-left:0px;}
    </style>

</head>
 <body class="body">
 
<div class="main-content">
    <div class="page-content min_style">
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-12">
                <form class="form-horizontal" role="form" id="aiscOrdCat2LocForm" dataType="${dataType}" old_org_id="${aiscOrdCat2LocBean.orgId}" old_loc_id="${locBean.locId}" old_ordcatId="${aiscOrdCat2LocBean.ordcatId}">
       <div class="fM_listTable2">
              <table id="configTab" width="100%" border="0" cellspacing="0" cellpadding="0">
                <tbody>
                 <tr>
                    <th width="16%" class="c">组织机构</th>
		            <th width="10%" class="c">科室</th>
		            <th width="16%" class="c">检查大类</th>
		          </tr>
		          <tr tag="ordcat2locItem">
		           <td width="10%" align = "center">
                        <select id="orgId" name="orgId" class="inputW160" onchange="loadLoc()">
                            <option value="-1">请选择机构</option>
                        </select>
                     </td>
		            <td width="10%" align = "center">
		                <select id="locId" name="locId" class="inputW160">
                             <option value="-1">--请选择--</option>
                        </select>
		            </td>
		             <td width="10%" align = "center">
		                <select id="ordcatId" name="ordcatId" class="inputW160">
                             <option value="-1">--请选择检查大类--</option>
                        </select>
		            </td>
		          </tr>
              </tbody>
              </table>
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
<input type="hidden" name="ordcat2locId" id="ordcat2locId" value="${aiscOrdCat2LocBean.ordcat2locId}"/>
                </form>
			</div>
		</div>
	</div>
</div>
 </body>
</html>
