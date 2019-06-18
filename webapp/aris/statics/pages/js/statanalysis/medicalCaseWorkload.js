//组织机构ID
var orgId = ""; 
$(function () {
	orgId = $("#orgId" , parent.document).val(); 
	//工作列表记录查询
	selectList();
    initDate();
    //检过条件初始化
    initDict();
    
    //所有select嵌套css样式
    $("#reportDoctorid").select2();
    $("#reportIspositive").select2();
    $("#reportVerifydoctorid").select2();
    
});

//初始化查询进间
function initDate(){
	var n=new Date();
    n.setDate(n.getDate()-30); 
    $("#startTime").val(n.getFullYear()+"-"+ (n.getMonth()+1)+"-"+ n.getDate());
    var u=new Date();
    u.setDate(u.getDate()); 
    $("#endTime").val(u.getFullYear()+"-"+ (u.getMonth()+1)+"-"+ u.getDate());
}

//重置
function reset(){
	$("#startTime").val("");
	$("#endTime").val("");
	$("#reportDoctorid").select2("val","-1"); 
	$("#categories").val(""); 
	$("#reportIspositive").select2("val","-1"); 
	$("#reportVerifydoctorid").select2("val","-1"); 
	$("#startAge").val("");
	$("#endAge").val("");
}

function exportMedicalCase(){
	var data = $("#searchForm").serialize();
	var url=GLOBAL.WEBROOT + '/medicalCaseWorkload/exportMedicalCase.ajax?'+data;
    window.open(url);
}
  
//initial query dict
function initDict() {
	
	var orgId = $("#currentOrgId" , parent.document).val();
	//医生下拉列表
    $.ajax({
        type: "POST",
        url: GLOBAL.WEBROOT+"/patientReg/getCareProv.ajax?orgId="+orgId,
        success: function (data) {
            var careProvBeans = eval(data.careProvBeans);
            var statusSelect = $("#reportDoctorid");
            statusSelect.empty();
            statusSelect.append("<option value='-1'>请选择医生</option>");
            for (var i = 0; i < careProvBeans.length; i++) {
            	statusSelect.append("<option value='" + careProvBeans[i].careprovId + "'>" + careProvBeans[i].careprovName + "</option>");
            }
            $("#reportVerifydoctorid").html(statusSelect.html());
        }
    });
    
    //阴阳性
    $.ajax({
        type: "POST",
        url: GLOBAL.WEBROOT+"/medicalCaseWorkload/getIspositive.ajax",
        success: function (data) {
            var isPositive = eval(data.ISPOSITIVE);
            var statusSelect = $("#reportIspositive");
            statusSelect.empty();
            statusSelect.append("<option value='-1'>请选择</option>");
            for (var i = 0; i < isPositive.length; i++) {
            	statusSelect.append("<option value='" + isPositive[i].itemno + "'>" + isPositive[i].itemname + "</option>");
            }
        }
    });
}

function reloadGrid(){
    var data = $("#searchForm").serializeObject()
    data.page = 1;
    data.pageSize = 10;
	$("#caselist").jqGrid('setGridParam', {postData: data,page:1,pageSize:10}).trigger("reloadGrid");
}

//影像调阅
function pacsView(type,obj){
	var gr = $("#caselist").jqGrid("getGridParam","selarrrow");
	 
//	if (gr.length > 1 || gr.length == 0) {
//		layer.alert('请选择一条记录进行操作！', {icon: 5,shadeClose: true});
//		return;
//	}
	var rowData ;
	if(type==1){
		rowData = $("#caselist").jqGrid('getRowData',gr[0]); 
//		if (rowData.studystatusCode=="取消预约") {
//			layer.alert('报告已取消，不允许调阅影像！', {icon: 5,shadeClose: true});
//			return;
//		}
	}else{
		var rowid = $(obj).closest('tr').attr("id");
		rowData = $("#caselist").jqGrid('getRowData',rowid);
//		if (rowData.studystatusCode=="取消预约") {
//			layer.alert('报告已取消，不允许调阅影像！', {icon: 5,shadeClose: true});
//			return;
//		}
	}
	
	var patientid = rowData.patientId;   //"06009580";
    var accessnumber = rowData.studyAccnumber; //"345959";

    var filespec = "C:\\Windows\\AIPACS\\AIPACS.Desktop.ImageViewerManager.exe";
     var fso = new ActiveXObject("Scripting.FileSystemObject");
     if (fso.FileExists(filespec))
     {
         var commandline = accessnumber+"&"+patientid+"&&&&I&&&";
         filespec = filespec + " " + commandline;
        

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

//报告阅览
function reportView(obj){
	var rowid = $(obj).closest('tr').attr("id");
	var rowData = $("#caselist").jqGrid('getRowData',rowid);
	//var locId = $("#locInfo").val();
	window.location.href = GLOBAL.WEBROOT + "/studyReport/init.html?studyinfoId="+rowData.studyinfoId+"&patientGlobalid="+rowData.patientGlobalid+"&rtype=0"+"&locId="+rowData.locId+"&isReadonly=y";
	 
}

//查询列表
function selectList() {
	var grid_selector = "#caselist";
    var pager_selector = "#caselist_pager";
    var data = $("#searchForm").serializeObject();
    data.orgId = orgId;
	$(grid_selector).jqGrid({
        url: GLOBAL.WEBROOT+"/medicalCaseWorkload/queryMedicalCaseList.ajax",
        mtype : "post",  
        datatype: "json",
        height: '100%',
        width : '100%',
        postData :data,
        colNames: ['studyinfoId','reportId','reportFinaldoctorid','patientGlobalid','studyAccnumber','patientId','locId','姓名','性别','年龄','检查项目','检查结果','诊断意见','报告医生','报告日期','审核医生','有图像','有报告'], 
        colModel: [
        	{name: 'studyinfoId', index: 'studyinfoId', width:60,sortable: false,hidden:true},  
        	{name: 'reportId', index: 'reportId', width:60,sortable: false,hidden:true}, 
        	{name: 'reportFinaldoctorid', index: 'reportFinaldoctorid', width:60,sortable: false,hidden:true}, 
        	{name: 'patientGlobalid', index: 'patientGlobalid', width:60,sortable: false,hidden:true},             
        	{name: 'studyAccnumber', index: 'studyAccnumber', width:60,sortable: false,hidden:true},             
        	{name: 'patientId', index: 'patientId', width:60,sortable: false,hidden:true},             
        	{name: 'locId', index: 'locId', width:60,sortable: false,hidden:true},             
            {name: 'patientName', index: 'patientName', width:60,sortable: false},             
            {name: 'patientSex', index: 'patientSex',width:60, sortable: false},
            {name: 'patientAge', index: 'patientAge',width:60, sortable: false},
            {name: 'studyItemdesc', index: 'studyItemdesc', width:300,sortable: false},
            {name: 'reportIspositive', index: 'reportIspositive', width:50,sortable: true,
            	formatter: function (param1, param2, recode) {
            	if(param1==0){
            		return '阳性';
            	}else if(param1==1){
            		return '阴性';
            	}else if(param1==2){
            		return '未知';
            	}else{
            		return '';
            	}
        	}}, 
        	{name: 'reportResult', index: 'reportResult', width:300,sortable: false},
            {name: 'reportDoctorname', index: 'reportDoctorname',width:60, sortable: false},             
            {name: 'reportDatetime', index: 'reportDatetime', width:120,sortable: false},
            {name: 'verifydoctorName', index: 'verifydoctorName',width:60, sortable: false},
            {name: 'studyHaveimage', index: 'study_Haveimage', width:50,sortable: true,
            	formatter: function (param1, param2, recode) {
            	if(param1==1){
            		return '<img src="/Aris/aris/statics/pages/css/index/images/icon_3.png" onclick="pacsView(2,this)"></img>';
            	}else{
            		return '';
            	} 
        	}},            
            {name: 'studyHavereport', index: 'study_Havereport', width:50,sortable: true,
            	formatter: function (param1, param2, recode) {
            	if(param1==1){
            		return '<img src="/Aris/aris/statics/pages/css/index/images/icon_2.png" onclick="reportshow(this)"></img>';
            	}else{
            		return '';
            	} 
        	}},
        ],
        viewrecords: false,
        rowNum:10,
        rowList:[10,15,20,30],
        multiselect: false,
        multiboxonly: true,
        pager: pager_selector,
        altRows: true,
        loadComplete: function () {
            com.ai.bdx.util.updatePagerIcons(this);
            
            //空列填充
    		var re_records = $("#caselist").getGridParam('records');

            //$("#divTotalInfo").html("报告数量合计："+re_records+"	阳性率：");
    		getTotalInfo();

            if(re_records < 10 )
        	{
        		for(var i=0,j=10-re_records;i<=j;i++){
        			//(re_records+1) 这个是加行号用的，没这个选中记录的行号都会重复
        			$("#caselist").jqGrid("addRowData",(re_records+1),{ '检查日期':'','设备名称':'','检查数量':'','总费用':''},"");
        		}
        	}
        },
        onSelectAll: function (aRowids, status) {
        },
        onSelectRow: function (rowid, status) {
        },
        autowidth: true
    });
}

function getTotalInfo(){
	var data = $("#searchForm").serializeObject();
	data.orgId= orgId;
	$.ajax({
		type : "POST",
		async : true,
		url : GLOBAL.WEBROOT + '/medicalCaseWorkload/getTotalInfo.ajax',
		data:data,
		success : function(data) {
			if (data.ERRCODE == "0") {
				$("#divTotalInfo").html("报告数量合计："+data.total+"&nbsp;&nbsp;阳性："+data.reportIspositive+""+"&nbsp;&nbsp;阳性率："+data.studyItemdesc+"");
			} else {
				alert("操作失败！" + data.ERRINFO);
			}
		}
	});
}

function reportshow(obj){
	var rowid = $(obj).closest('tr').attr("id");
	var rowData = $("#caselist").jqGrid('getRowData',rowid);
	var w=screen.availWidth;
	var h=screen.availHeight; 
	window.open(GLOBAL.WEBROOT + "/studyReport/reportPrint.html?reportId="+rowData.reportId+"&finalDocId="+rowData.reportFinaldoctorid,'newwindow','width='+w+',height='+h+',top=0,left=0,status=no')
}

//把object转成字符串
function getObjToString(_obj) {
    if ((_obj != 0 && !_obj) || _obj == 'null') {
        _obj = '';
    }
    return _obj;
}
 
 