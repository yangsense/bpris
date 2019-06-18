<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns=" http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/common/meta.jsp" %>
    <title>检查工作站</title>
    <link href="${ctx}/aris/statics/common/css/css.css" rel="stylesheet" type="text/css"/> 
    <link type="text/css" rel="stylesheet" href="${ctx}/aris/statics/common/js/jquery-easyui/themes/default/easyui.css"/>
    <script type="text/javascript" src="${ctx}/aris/statics/common/js/jquery-easyui/jquery.easyui.min.js"></script>
    
    <link type="text/css" rel="stylesheet" href="${ctx}/aris/statics/common/js/date/skin/WdatePicker.css"/>
    <script type="text/javascript" src="${ctx}/aris/statics/common/js/date/WdatePicker.js"></script>
	<script type="text/javascript" src="${ctx}/aris/statics/pages/js/common/common.js"></script>

    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/jqGrid/jquery.jqGrid.min.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/jqGrid/grid.locale-cn.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/pages/js/workstation/checkMaster.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/common/js/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
     <style type="text/css">
      .ui-jqgrid-btable .ui-widget-content.ui-state-highlight {
       background-color: #c4efc9
      }
    </style>
    
</head>
<body>
<div class="wrap100 quickrecharge">
    <!--导航start-->
<%--	<div class="breadcrumbs" id="breadcrumbs">--%>
<%--        <script type="text/javascript">--%>
<%--            try {--%>
<%--                ace.settings.check('breadcrumbs', 'fixed')--%>
<%--            } catch (e) {--%>
<%--            }--%>
<%--        </script>--%>
<%----%>
<%--        <ul class="breadcrumb" style="margin-top: 8px;">--%>
<%--            <li>--%>
<%--                <i class="icon-home home-icon"></i>--%>
<%--                <a href="javaScript:void(0);">RIS工作站</a>--%>
<%--            </li>--%>
<%--            <li class="active">检查技师</li>--%>
<%--        </ul>--%>
<%--    </div>--%>
	<!--导航end-->
	
	<div class="q_r_list">
	
	<!--查询条件 begin-->
    <div class="listTable">
            <form id="searchForm" action="#">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <%-- <tr>
						<input type="hidden" id="currentLocId" value="${cookie_locId }"/>
						<th class="r" width="13%">科室名称：</th>
						<td width="13%">
							<select id="locInfo" name="locId" style="width:160px;"   onchange="setLocCookie()" >
								<option value="-1">请选择</option>
							</select>
						</td>

                        <th class="r" width="13%">检查状态 ：</th>
                        <td width="15%">
                            <select id="studyStatus" name="studystatusCode" style="width:160px;">
                                <option value="-1">请选择</option>
                            </select> 
                        </td>
                        <th class="r" >设备类型 ：</th>
                        <td >
                            <select id="modalityId" name="modalityId" style="width:160px;">
                                 <option value="-1">全部</option>         
                            </select> 
                        </td>                 
                    </tr>
                    <tr>                         
                        <th width="13%" class="r">开始日期：</th>
                        <td width="13%">
                            <input id="startTime" name="startTime" class="inputW160" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
                            
                        </td> 
                        <th width="13%" class="r">结束日期：</th>
                        <td >
                           <input id="endTime" name="endTime" class="inputW160" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
                        </td>

						<th width="13%" class="r">病人ID：</th>
						<td width="13%">
							<input name="patientId" id="patientId" class="inputW160"/>
						</td>
					</tr>
                    <tr>
						<td  class="r" colspan="5" align="center">
						<a href="javascript:void(0)" class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary" style="width:80px;">呼叫</a>&nbsp;&nbsp;
						<a href="javascript:void(0)" class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary" style="width:80px;">过号</a>&nbsp;&nbsp;
						<a href="javascript:void(0)" class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary" style="width:80px;">到达</a>
						</td>
						<td  class="r"  >
							<a href="javascript:void(0)" onclick="reloadGrid();return false;" class="btn btn-sm btn-success"><span class="icon-search"></span>查询</a>
							<a href="javascript:void(0)" onclick="com.ai.bdx.util.reset('searchForm');" class="btn btn-sm btn-gray"><span class="icon-retweet"></span>重 置</a>
						</td>
					</tr> --%>
					
					<tr>
						<td>
							科室名称1 : 
							<select id="locInfo" name="locId" style="width:160px;"   onchange="setLocCookie()" >
								<option value="-1">请选择</option>
							</select>
						</td>
						<td>
							检查状态 : 
							<select id="studyStatus" name="studystatusCode" style="width:160px;">
                                <option value="-1">请选择</option>
                            </select> 
						</td>
						<td>
							设备类型: 
							<select id="modalityId" name="modalityId" style="width:160px;">
                                 <option value="-1">全部</option>         
                            </select>
						</td>
					</tr>
					<tr>
						<td>
							开始日期 : 
							<input id="startTime" name="startTime" class="inputW160" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
						</td>
						<td>
							结束日期 : 
							<input id="endTime" name="endTime" class="inputW160" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
						</td>
						<td>
							病&nbsp;人&nbsp;ID&nbsp; : 
							<input name="patientId" id="patientId" class="inputW160"/>
						</td>
					</tr>
                </table>
                <div>
					<span>
						<a href="javascript:void(0)" class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary" style="width:80px;">呼叫</a>&nbsp;&nbsp;
						<a href="javascript:void(0)" class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary" style="width:80px;">过号</a>&nbsp;&nbsp;
						<a href="javascript:void(0)" class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary" style="width:80px;">到达</a>
					</span>
					<span style="float:right;">
						<a href="javascript:void(0)" onclick="reloadGrid();return false;" class="btn btn-sm btn-success"><span class="icon-search"></span>查询</a>
						<a href="javascript:void(0)" onclick="com.ai.bdx.util.reset('searchForm');" class="btn btn-sm btn-gray"><span class="icon-retweet"></span>重 置</a>
					</span>
				</div>
            </form>
           
        </div> 
        <!--查询条件 end-->
			<div>
            	<table id="worklist" width="100%" border="0" cellspacing="0" cellpadding="0">
               
            	</table>
            	<div id="worklist_pager"></div>
        	</div>
        	 
        	<div class="listTable">
				<table border="0" cellspacing="0" cellpadding="0">
					<tr>
						<th class="r" style="color:#777">检查项目</th>
						<td width="33%"><textarea rows="4" cols="45" id="studyitem_desc"  readonly="readonly"></textarea></td>

						<th class="r" style="color:#777">检查部位</th>
						<td width="33%"><textarea rows="4" cols="45" id="studyitem_bodyinfo" readonly="readonly"></textarea></td>
						<th class="r" style="color:#777">临床诊断</th>
						<td><textarea rows="4" cols="45" id="study_clinic" readonly="readonly"></textarea></td>
					</tr>
				</table>
			
			 
				<table style="height:58px;">
					<!-- <tr>
					    
						<th width="12%" class="r" >操作技师：</th>
						<td width="10%"><select id="mainDoc" name="mainDoc" style="width:160px;" > <option value="-1">请选择</option> </select></td>
						
						<th width="12%" class="r">辅助技师：</th>
						<td width="10%"><select id="otherDoc" name="otherDoc" style="width:160px;" > <option value="-1">请选择</option> </select></td>
								
						<th width="12%" class="r">曝光次数：</th>
						<td width="5%"><input id="studyExposurecount" name="studyExposurecount" class="inputW80"/></td>
						
						<th width="12%" class="r">胶片数量：</th>
						<td width="5%"><input id="studyFilmcount" name="studyFilmcount" class="inputW80"/></td>
							
					</tr> -->
					<tr>
						<td width="10%">
							操作技师:
							<select id="mainDoc" name="mainDoc" style="width:120px;" > <option value="-1">请选择</option> </select>
						</td>
						<td width="10%">
							辅助技师:
							<select id="otherDoc" name="otherDoc" style="width:120px;"> <option value="-1">请选择</option> </select>
						</td>
						<td width="10%">
							曝光次数:
							<input id="studyExposurecount" name="studyExposurecount" class="inputW80"  style="width:120px;" />
						</td>
						<td width="10%">
							胶片数量:
							<input id="studyFilmcount" name="studyFilmcount" class="inputW80"  style="width:120px;"/>
						</td>
					
					</tr>
				</table>
				</div> 
			<input type="hidden" id="studyinfoId" name ="studyinfoId" value=""/>
			<input type="hidden" id="statusCode" name ="statusCode" value=""/>
			 
			<div style="text-align:right;margin-top:10px;" class="class="r n_b_b"> 
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
	                <tr>
	                    <th class="r n_b_b">&nbsp;</th>
	                    <td class="n_b_b">&nbsp;</td>
	                    <th class="r n_b_b">&nbsp;</th>
	                    <td colspan="3">
 	                       <a href="javascript:void(0)" onclick="updateCheck('1');" class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary"><span class=""></span>开始检查</a>
	                       <a href="javascript:void(0)" onclick="updateCheck('2');" class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary"><span class=""></span>检查结束</a>
	                       <a href="javascript:void(0)" onclick="regPrint();" class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary"><span class=""></span>打印凭证</a>
 	                       
	                   </td>
	                </tr>
	            </table>
            
            
			</div>
        </div>
</div>

<!-- 书写报告 begin -->
 
<!-- 书写报告 end -->
 

</body>
</html>
