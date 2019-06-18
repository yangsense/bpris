$(function () {
	selectList();
});
//查询列表
function selectList() {
	var grid_selector = "#reportlist";
    var pager_selector = "#report_pager";
    var data = $("#searchForm").serializeObject();
    data.patientId=$("#patientId").val();
    data.patientOutpatientid=$("#patientOutpatientid").val();
    data.patientInpatientid=$("#patientInpatientid").val();
    data.orgId=$("#orgId").val();
	$(grid_selector).jqGrid({
        url: GLOBAL.WEBROOT +"/reportBrowse/queryReportList.ajax",
        mtype : "post",  
        datatype: "json",
        height: '100%',
        width : '100%',
        postData :data,
        colNames: ['申请单号','影像','影像获取','patientId','检查号','检查名称','检查时间','机构名称','检查所见','诊断意见','备注','报告医生','审核医生','报告日期'],
        colModel: [
            {name: 'studyinfoId', index: 'studyinfoId', width:60,sortable: false},
            {name: 'studyHaveimage', index: 'study_Haveimage', width:50,sortable: true,
            	formatter: function (param1, param2, recode) {
            	if(param1==1){
            		return '<img src="/Aris/aris/statics/pages/css/index/images/icon_3.png" onclick="pacsView(2,this)"></img>';
            	}else{
            		return '';
            	} 
        	}},      
        	{name: 'getImage', index: "get_image",width:60,  sortable: false
            	,formatter: function (param1, param2, recode) {
	            	if(recode.studyHaveimage==0){
	            		return '<div class="action-buttons">' +
	                    '<a class="blue" href="javascript:void(0)" onclick="getImage(\'' + recode.patientId + '\',\'' + recode.studyAccnumber + '\',\'' + recode.orgId + '\')" title="影像获取"><i class="icon-search bigger-120"></i></a>' +
	                    "</div>";
	            	}else{
	            		return '';
	            	}
                }
            },
        	{name: 'patientId', index: 'patient_Id', width:70,sortable: true,hidden:true},
            {name: 'studyAccnumber', index: 'studyAccnumber',width:90,  sortable: false},
            {name: 'studyItemdesc', index: 'study_Itemdesc', width:80,sortable: true},
            {name: 'cStudyStarttime', index: 'cStudyStarttime', width:125,sortable: true},
            {name: 'orgName', index: 'org_Name', width:80,sortable: true},
            {name: 'reportExam', index: 'reportExam', width:80,sortable: true,hidden:true},
            {name: 'reportResult', index: 'reportResult', width:80,sortable: true,hidden:true},
            {name: 'reportRemark', index: 'reportRemark', width:80,sortable: true,hidden:true},
            {name: 'reportDoctorname', index: 'reportDoctorname', width:80,sortable: true,hidden:true},
            {name: 'reportVerifydoctorname', index: 'reportVerifydoctorname', width:80,sortable: true,hidden:true},
            {name: 'reportDatetime', index: 'reportDatetime', width:80,sortable: true,hidden:true}
        ],
        viewrecords: false,
        rowNum:15,
        rowList:[10,15,20,30],
        multiselect: false,
        multiboxonly: true,
        pager: pager_selector,
        altRows: true,
        loadComplete: function () {
            com.ai.bdx.util.updatePagerIcons(this);
            var re_records = $("#reportlist").getGridParam('records');
        	if(re_records < 15 )
        	{
        		for(var i=0,j=15-re_records;i<=j;i++){
        			//(re_records+1) 这个是加行号用的，没这个选中记录的行号都会重复
        			$("#reportlist").jqGrid("addRowData",(re_records+1),{ '申请单号':"",'影像':"",'影像获取':"",'检查号':"",'检查名称':"",'检查时间':"",'机构名称':"",'检查所见':"",'诊断意见':"",'备注':"",'报告医生':"",'审核医生':"",'报告日期':""},"");
        		}
        	}
        	if(re_records >0){
        		$("#reportlist").setSelection(1);
        	}
        },
        onSelectAll: function (aRowids, status) {
        },
        onSelectRow: function (rowid, status) {
        	var rowData = $("#reportlist").jqGrid('getRowData',rowid);
        	CKEDITOR.instances.reportExam.setData(rowData.reportExam);
        	CKEDITOR.instances.reportResult.setData(rowData.reportResult);
        	$("#reportRemark").val(rowData.reportRemark);
        	$("#orgName").text(rowData.orgName);
        	$("#reportDoctorname").text(rowData.reportDoctorname);
        	$("#reportVerifydoctorname").text(rowData.reportVerifydoctorname);
        	$("#reportDatetime").text(rowData.reportDatetime);
        },
        autowidth: true
    });
}

function getImage(patientId,studyAccnumber,orgId){
	$.ajax({
        type: "POST",
        async: false,
        url: GLOBAL.WEBROOT + '/reportBrowse/retrivePatientImage.ajax?patientId='+ patientId+'&studyAccnumber='+studyAccnumber+'&orgId='+orgId,
        dataType: 'json',
        // data :setdata,
        success: function (data) {
            if (data.success) {
                if(data.result=="0"){
                	layer.msg("影像获取成功");
                }else{
                	layer.msg("影像获取失败");
                }
            }
        }
    });
}

//影像调阅
function pacsView(type,obj){
	var gr = $("#reportlist").jqGrid("getGridParam","selarrrow");
	 
//	if (gr.length > 1 || gr.length == 0) {
//		layer.alert('请选择一条记录进行操作！', {icon: 5,shadeClose: true});
//		return;
//	}
	var rowData ;
	if(type==1){
		rowData = $("#reportlist").jqGrid('getRowData',gr[0]); 
		if (rowData.studystatusCode=="取消预约") {
			layer.alert('报告已取消，不允许调阅影像！', {icon: 5,shadeClose: true});
			return;
		}
	}else{
		var rowid = $(obj).closest('tr').attr("id");
		rowData = $("#reportlist").jqGrid('getRowData',rowid);
		if (rowData.studystatusCode=="取消预约") {
			layer.alert('报告已取消，不允许调阅影像！', {icon: 5,shadeClose: true});
			return;
		}
	}
	
	var patientid = rowData.patientId;   //"06009580";
    var accessnumber = rowData.studyAccnumber; //"345959";
    
	 var filespec = "C:\\Windows\\AIPACS\\AIPACS.Desktop.ImageViewerManager.exe";
     var fso = new ActiveXObject("Scripting.FileSystemObject");
     if (fso.FileExists(filespec))
     {
         var commandline = accessnumber+"&"+patientid+"&&&&I&&&";
         filespec = filespec + " " + commandline;
         //alert(filespec);

         var wsh = new ActiveXObject('WSCript.shell');
         wsh.RegWrite("HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Internet Settings\\Zones\\2\\1001","0","REG_DWORD");  
     	 wsh.RegWrite("HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Internet Settings\\Zones\\2\\1004","0","REG_DWORD");  
     	 wsh.RegWrite("HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Internet Settings\\Zones\\2\\1200","0","REG_DWORD");  
     	 wsh.RegWrite("HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Internet Settings\\Zones\\2\\1201","0","REG_DWORD");  
     	 wsh.RegWrite("HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Internet Settings\\Zones\\2\\1405","0","REG_DWORD");  
     	 wsh.RegWrite("HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Internet Settings\\Zones\\2\\2201","0","REG_DWORD");  
         wsh.run(filespec);

     }
     else
     {
         location.href = GLOBAL.WEBROOT+"/aris/statics/files/setup.exe";
     }	 	 
}