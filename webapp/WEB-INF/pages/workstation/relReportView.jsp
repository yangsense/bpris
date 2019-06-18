<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns=" http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/common/meta.jsp" %>
    <title>工作列表</title>

    
    <link type="text/css" rel="stylesheet" href="${ctx}/aris/statics/common/js/jquery-easyui/themes/default/easyui.css"/>
    <script type="text/javascript" src="${ctx}/aris/statics/common/js/jquery-easyui/jquery.easyui.min.js"></script>
    <link type="text/css" rel="stylesheet" href="${ctx}/aris/statics/common/js/date/skin/WdatePicker.css"/>
	<link href="${ctx}/aris/statics/pages/css/index/css/public.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/aris/statics/common/css/css.css" rel="stylesheet" type="text/css"/> 
    <link href="${ctx}/aris/statics/pages/css/index/css/detail.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/aris/statics/pages/css/index/css/icon.css" rel="stylesheet" type="text/css"/>
    <link href="${ctx}/aris/statics/pages/css/index/css/inner.css" rel="stylesheet" type="text/css"/> 
         
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/jqGrid/jquery.jqGrid.min.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/ace/plugins/jqGrid/grid.locale-cn.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/pages/js/workstation/relReportView.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/common/js/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
     	
    <script type="text/javascript" src="${ctx}/aris/statics/common/js/ckeditor/ckeditor.js"></script>
    <script type="text/javascript" src="${ctx}/aris/statics/common/js/ckeditor/config.js"></script>
    <style type="text/css">
     .table td{
     word-wrap:break-word;
     word-break:break-all; 
     }
    </style>
</head>
<body> 
<div class="thickdiv" style="display: none; height: 100%;"></div> 
<div class="wrap100 quickrecharge ">
      
        <form class="form-horizontal" role="form" id="reportForm">
         
        <div class="r_list" style="margin-left:24px;margin-right:12px;"> 
            
            <div class="row report m-t-20">               
                <div class="col-lg-9 report-center">
 
                    <div class="report-right-content">
                     
                        <h6><span style="margin-left:20%;text-align:center;"> 
                             ${regInfoBean.patientName }  &nbsp;&nbsp;&nbsp;&nbsp; 
                             ${regInfoBean.patientSex }   &nbsp;&nbsp;&nbsp;&nbsp;  
                             ${regInfoBean.patientAge}    &nbsp;&nbsp;&nbsp;&nbsp;  
                             ${regInfoBean.studydesc}	  &nbsp;&nbsp;&nbsp;&nbsp;  
                             ${regInfoBean.orgName}       &nbsp;&nbsp;&nbsp;&nbsp;  
                             ${regInfoBean.applocname}</span></h6>
                        <div class="report-show" >
                            <h6>检查所见</h6> 
                             <textarea id="reportExam" cols="5" rows="18" isRich="true" name="reportExam" class="ckeditor" readonly="readonly" disabled="disabled">
                              ${reportBean.reportExam }
                             </textarea> 
                        </div>
                        <div class="report-show" >
                            <h6>诊断意见</h6> 
                            <textarea id="reportResult" cols="5" rows="5" isRich="true" name="reportResult" class="ckeditor" readonly="readonly" disabled="disabled"> 
                             ${reportBean.reportResult }                           
                            </textarea>
                        </div>
                    </div>
                    <div class="fM_listTable3">
                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr>			                    
			                    <th colspan="" class="c n_b_b"> 
                                     
			                       <a href="javascript:void(0);" id="quoteReportBtn"  class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary">
			                       		<span class=""></span>引用报告
			                       </a>
			                       <a href="javascript:void(0);" id="closeBtn"  class="fm-button ui-state-default ui-corner-all fm-button-icon-right ui-reset btn btn-sm btn-primary">
			                       		<span class=""></span>关        闭 
			                       </a> 
			                                            
			                    </th>   
                                
                            </tr>
                        </table>
                    </div>
                </div>
            </div>

    <!--右侧内容 end-->
</div>
</form>
</div> 
</body>
</html>
