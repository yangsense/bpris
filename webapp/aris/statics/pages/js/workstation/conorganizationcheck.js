var orgId=''; 
var locId='';
var rowData='';
$(function () {
	//初始化查询进间
    initDate();
    
	orgId = $("#orgId" , parent.document).val();  

	//书写报告DIV初始化
    
    //检过条件初始化
    initDict(); 
    
    //所有select嵌套css样式
    $("#studyStatus").select2();
    $("#mainDoc").select2();
	$("#locInfo").select2();
	setInterval(function(){
		setTime();
	},1000);
});



//初始化查询进间
function initDate(){
	var n=new Date();
    n.setDate(n.getDate()-1);
    $("#startTime").val(n.getFullYear()+"-"+ (n.getMonth()+1)+"-"+ n.getDate());
    n.setDate(n.getDate()+1);
    $("#endTime").val(n.getFullYear()+"-"+ (n.getMonth()+1)+"-"+ n.getDate());
    
}
function Appendzero (obj) {
	if (obj < 10) return "0" + obj; else return obj;
}

function setTime(){
	var date = new Date();
	var year = date.getFullYear();
	var month = date.getMonth() + 1;
	var day = date.getDate();
	var hour = date.getHours();
	var minute = date.getMinutes();
	var second = date.getSeconds();
    $("#startHzTime").val(year + '-' + (month<10?"0"+month:month)  + '-' + (day<10?"0"+day:day)  + ' ' +(hour<10?"0"+hour:hour)+":"+(minute<10?"0"+minute:minute)+":"+(second<10?"0"+second:second));
}
  
//initial query dict
function initDict() {

	var currentLocId = $("#currentLocId").val();
	//科室下拉列表
	$.ajax({
		type: "POST",
		async: false,
		url: GLOBAL.WEBROOT+"/conorgCheck/getLocInfo.ajax?orgId="+orgId,
		success: function (data) {
			var jsonStatus = eval(data.LOC_INFO);
			var statusSelect = $("#locInfo");
			statusSelect.empty();
//			statusSelect.append("<option value='-1' >请选择</option>");
			var currFlag = false;
			if(jsonStatus.length>0){
	            for (var i = 0; i < jsonStatus.length; i++) {
	                statusSelect.append("<option value='" + jsonStatus[i].locid + "'>" + jsonStatus[i].locdesc + "</option>");
	                if(jsonStatus[i].locid == $("#currentLocId").val()){
	                	currFlag = true;
	                }
	            }
	            if($("#currentLocId").val()=="" || $("#currentLocId").val()==undefined){
	            	$("#locInfo option:first").prop("selected", 'selected');
	                $("#locInfo").select2();
	            }else{
	            	if(currFlag){
	            		$("#locInfo").val($("#currentLocId").val());
	                	$("#locInfo").select2();
	            	}else{
	            		$("#locInfo option:first").prop("selected", 'selected');
	                	$("#locInfo").select2();
	            	}            	
	            } 
	            setLocCookie();
          }else{
        	layer.msg('操作员未关联会诊科室！', {title:'提示',time:9000000000,btn:['前去配置'],shade: [0.5, '#f5f5f5'],icon: 2},function(){window.location.href = GLOBAL.WEBROOT+'/basecode/aiscLoginLocInit'; });
          }   
//			for (var i = 0; i < jsonStatus.length; i++) {
//				statusSelect.append("<option value='" + jsonStatus[i].locid + "'>" + jsonStatus[i].locdesc + "</option>");
//			}
//			if($("#currentLocId").val()==""){
//				$("#locInfo option:first").prop("selected", 'selected');
//				$("#locInfo").select2();
//			}else{
//				$("#locInfo").val($("#currentLocId").val());
//				$("#locInfo").select2();
//			}

		}
	});
    
    //检查状态下拉列表
    $.ajax({
        type: "POST",
        async: true,
        url: GLOBAL.WEBROOT+"/conorgCheck/getStudyStatus.ajax",
        success: function (data) { 
            var jsonStatus = eval(data.STUDY_STATUS);
            var statusSelect = $("#studyStatus");
            statusSelect.empty();
            statusSelect.append("<option value='-1'>请选择</option>");
            for (var i = 0; i < jsonStatus.length; i++) {
            	if(jsonStatus[i].itemno == "APPConsult" || jsonStatus[i].itemno =="Consulting" || jsonStatus[i].itemno =="Consulted"|| jsonStatus[i].itemno =="AppVerify"|| jsonStatus[i].itemno =="RejectApp"){
            		statusSelect.append("<option value='" + jsonStatus[i].itemno + "'>" + jsonStatus[i].itemname + "</option>");
            	}                
            }
//            statusSelect.select2("val","APPConsult");
            selectList();
        }
    });
    

}


//科室变更记录到cookie中，下次登录时，默认选中
function setLocCookie(){
	reloadGrid();
	var locId = $("#locInfo option:selected").val();
	$.ajax({
		type: "POST",
		async: true,
		url: GLOBAL.WEBROOT+"/workList/setLocCookie.ajax?locId="+locId,
		success: function (data) {

		}
	});
}
//会诊医生查询
function setCareProv(orgId,studyConsultloc){
	//会诊医生下拉列表
	$.ajax({
	    type: "POST",
	    async: true,
	    url: GLOBAL.WEBROOT+"/conorgCheck/getCareProv.ajax?orgId="+orgId+"&locId="+studyConsultloc+"&doctype=1",
	    success: function (data) {
	        var careProvBeans = eval(data.careProvBeans);
	        var statusSelect = $("#mainDoc");
	        statusSelect.empty();        
	        statusSelect.append("<option value='-1'>请选择</option>");
	        $("#mainDoc").select2("val", "-1");
	        for (var i = 0; i < careProvBeans.length; i++) {
	            statusSelect.append("<option value='" + careProvBeans[i].careprovId + "'>" + careProvBeans[i].careprovName + "</option>");
	        }
			var maindoc=$("#mainDoc").select2();

			if(rowData!=''){
				if(rowData.studyDoctorid!=''&&rowData.studyDoctorid!=null&&rowData.studyDoctorid!=undefined){
					$(maindoc).val(rowData.studyDoctorid).trigger("change");
				}

			}
	    }
	});
}

function reloadGrid(){
    var data = $("#searchForm").serializeObject()
//    data.page = 1;
//    data.pageSize = 6;
	$("#worklist").jqGrid('setGridParam', {postData: data,page:1,pageSize:5}).trigger("reloadGrid");
}

//查询列表
function selectList() {
	var grid_selector = "#worklist";
    var pager_selector = "#worklist_pager";
    var data = $("#searchForm").serializeObject();
	$(grid_selector).jqGrid({
        url: GLOBAL.WEBROOT+"/conorgCheck/conorganizationCheckList.ajax?orgId="+orgId,
        mtype : "post",  
        datatype: "json",
        height: '100%',
        width : '100%',
        postData :data,
        colNames: ['病人ID','studyinfoId','病人姓名','性别','年龄','检查项目','studylevelId','studyAccnumber','检查状态','studyConsultloc','申请机构','申请科室','会诊申请时间','期望执行时间','会诊开始时间','会诊结束时间','statusCode'],
        colModel: [
            {name: 'patientId', index: 'patientId',width:50, sortable: false},
            {name: 'studyinfoId', index: 'studyinfoId',width:50, sortable: false,hidden:true},
            {name: 'patientName', index: 'patientName',width:50, sortable: false},
            {name: 'patientSex', index: 'patientSex',width:50, sortable: false},
            {name: 'patientAge', index: 'patientAge',width:50, sortable: false},
            {name: 'studyItemdesc', index: 'studyItemdesc',width:50, sortable: false},
            {name: 'studylevelId', index: 'studylevelId',width:50, sortable: false,hidden:true},
            {name: 'studyAccnumber', index: 'studyAccnumber',width:50, sortable: false,hidden:true},
            {name: 'studystatusCode', index: 'studystatusCode',width:50, sortable: false},
            {name: 'studyConsultloc', index: 'studyConsultloc',width:50, sortable: false,hidden:true},
            {name: 'orgName', index: 'orgName',width:50, sortable: false},
			{name: 'locName', index: 'locName',width:50, sortable: false},
			{name: 'studyAppdate', index: 'studyAppdate',width:50, sortable: false},
            {name: 'studyDatetime', index: 'studyDatetime',width:50, sortable: false},
			{name: 'studyConsultstarttime', index: 'studyConsultstarttime',width:50, sortable: false},
			{name: 'studyConsultendtime', index: 'studyConsultendtime',width:50, sortable: false},
			{name: 'statusCode', index: 'statusCode',width:50, sortable: false,hidden:true},
        ],
        viewrecords: false,
        rowNum:5,
        rowList:[10,15,20,30],
        multiselect: false,
        multiboxonly: true,
        pager: pager_selector,
        altRows: true,
        loadComplete: function () {
            com.ai.bdx.util.updatePagerIcons(this);
            var re_records = $("#worklist").getGridParam('records');
        	if(re_records < 5 )
        	{
        		for(var i=0,j=4-re_records;i<=j;i++){
        			//(re_records+1) 这个是加行号用的，没这个选中记录的行号都会重复
        			$("#worklist").jqGrid("addRowData",(re_records+1),{ '病人ID':"",'病人姓名':"",'性别':"",'年龄':"",'患者类型':"",'studylevelId':"",'检查优先级':"",'流水号':"",'设备':"",'房间':"",'临床诊断':"",'检查id':"",'检查科室':"",'检查状态':"",'状态编码':""},"");
        		}
        	}
        },
        onSelectAll: function (aRowids, status) {
        },
        onSelectRow: function (rowid, status) {
        	onselect(rowid);
        },
        autowidth: true
    });
}

function onselect(rowid){
	rowData= $("#worklist").jqGrid('getRowData',rowid);
	//初始化下拉技师列表
	setCareProv(orgId,rowData.studyConsultloc);
	$("#studyinfoId").val(rowData.studyinfoId);
	$("#patientId").val(rowData.patientId);
	$("#studyAccnumber").val(rowData.studyAccnumber);
	$("#statusCode").val(rowData.statusCode);
}

function refuseApply(){
	if($("#studyinfoId").val()==''){ 
		layer.alert('请选择一条检查记录进行操作！', {icon: 5,shadeClose: true});
		return  false;
	}
	if($("#statusCode").val()=="APPConsult"||$("#statusCode").val()=="STUDYED"){
		layer.open({
	        type: 2,
	        title:"退回申请",
	        area: ['500px', '300px'],
	        fix: false, //不固定
	        maxmin: true,
	        content: GLOBAL.WEBROOT + "/conorgCheck/conorgCheckrefuse.html?studyinfoId="+$("#studyinfoId").val(),
	    });
	}else{
		layer.alert('当前状态不能退回申请!', {icon: 5,shadeClose: true});
		return  false;
	}

}

function pdfView(){
	if($("#studyinfoId").val()==''){ 
		layer.alert('请选择一条检查记录进行操作！', {icon: 5,shadeClose: true});
		return  false;
	}
	var url = "/studyReport/medicalRecordView.html?studyinfoId="+$("#studyinfoId").val();
    var title = "资料浏览";
    var index = layer.open({
        type: 2,
        title: title,
        area: ['800px', '300px'],
        fix: false, //不固定
        maxmin: true,
        content: GLOBAL.WEBROOT + url
    });
}

//影像调阅
function pacsView(){
	if($("#studyinfoId").val()==''){ 
		layer.alert('请选择一条检查记录进行操作！', {icon: 5,shadeClose: true});
		return  false;
	}
	
	var patientid =  $("#patientId").val();   //"06009580";
    var accessnumber = $("#studyAccnumber").val(); //"345959";
    
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



//把object转成字符串
function getObjToString(_obj) {
    if ((_obj != 0 && !_obj) || _obj == 'null') {
        _obj = '';
    }
    return _obj;
}

function hzApply(){
	var data={};
	if($("#studyinfoId").val()==''){ 
		layer.alert('请选择一条检查记录进行操作！', {icon: 5,shadeClose: true});
		return  false;
	}
	//状态判定
	if($("#statusCode").val()=="APPConsult"||$("#statusCode").val()=="STUDYED"){
//		if($("#mainDoc").val()=='-1'){
//			layer.alert('请选择会诊医生！', {icon: 5,shadeClose: true});
//			return  false;
//		}
//		if($("#startHzTime").val()==""){
//			layer.alert('请选择会诊开始时间！', {icon: 5,shadeClose: true});
//			return  false;
//		}
		
		 data={studyinfoId:$("#studyinfoId").val(),studyConsultdoctorid:$("#mainDoc").val(),studyConsultstarttime:$("#startHzTime").val()}
		 data = $.toJSON(data);
		 $.ajax({
		        type: "POST",
		        async: true,
		        url: GLOBAL.WEBROOT+"/conorgCheck/hzApply.ajax",
		        dataType: 'json',
		        contentType: 'application/json',
		        data:data,
		        success: function (data) {
		        	if(data.success!=''){
		        		if(data.result=='success'){
		        			layer.msg('审核成功！');
							reloadGrid();
		        		}else{
		        			layer.msg('审核失败！'); 
		        		};
		        	}
		        	 
		        }
		   });
	}else{
		layer.alert('当前状态不能审核!', {icon: 5,shadeClose: true});
		return  false;
	}
	
	
		
	}

function setForm(data) {
    openView();
    $("#cashboxnoView").text(data.cashboxno);
    $("#orgnameView").text(data.orgname);
    $("#cashboxownernameView").text(data.cashboxownername);
}

 
function openEdit() {
    $('#divEdit').dialog('open');
}

function openView() {
    $('#divView').dialog('open');
}

