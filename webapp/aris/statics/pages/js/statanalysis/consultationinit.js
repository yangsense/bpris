var chart01='';  
$(function () {
	//默认时间
	initDate();
	
    //检过条件初始化
    initDict();
    $("#studyConsultorg").select2();
    $("#studyConsultloc").select2();
    
    selectList();
});

var currentLocId = $("#currentLocId" , parent.document).val();
var orgId = $("#currentOrgId" , parent.document).val();
//初始化查询进间
function initDate(){
	var n=new Date();
    n.setDate(n.getDate()-30); 
    $("#studyStarttime").val(n.getFullYear()+"-"+ Appendzero(n.getMonth()+1)+"-"+ Appendzero(n.getDate()));
    var u=new Date();
    u.setDate(u.getDate()); 
    $("#studyEndtime").val(u.getFullYear()+"-"+ Appendzero(u.getMonth()+1)+"-"+ Appendzero(u.getDate()));
}
function Appendzero (obj) {
	if (obj < 10) return "0" + obj; else return obj;
}

//initial query dict
function initDict() {
	//会诊申请机构列表加载
    $.ajax({
		type: "POST",
		async: false,
		url:GLOBAL.WEBROOT +  '/bpris/getOrgs.ajax?operatorCode='+parent.user.operatorCode+"&sysType=25",
		dataType:'json',
		success:function(data){
			var sysOrg = eval(data);
	        var orgSelect = $("#orgId");
	        orgSelect.empty(); 
	        //orgSelect.append("<option value='-1'>请选择机构</option>");
	        for (var i = 0; i < sysOrg.length; i++) {
	        	orgSelect.append("<option value='" + sysOrg[i].orgId + "'>" + sysOrg[i].orgName + "</option>");
	        }
	        orgSelect.val(parent.$("#currentOrgId").val()==""?"-1":parent.$("#currentOrgId").val());
	        orgSelect.select2();  
	        getsqLoc($("#orgId").val());
		}
	}); 
    
}

function getsqLoc(orgId){
	//申请科室查询
    $.ajax({
		type: "POST",
		async: false,
		url:GLOBAL.WEBROOT +  '/consultationCount/getsqLoc.ajax?orgId='+orgId,
		dataType:'json',
		success:function(data){
			var locSq = eval(data.locBeans);
	        var locSelect = $("#locId");
	        locSelect.empty(); 
	        locSelect.append("<option value='-1'>请选择</option>");
	        for (var i = 0; i < locSq.length; i++) {
	        	locSelect.append("<option value='" + locSq[i].locId + "'>" + locSq[i].locName + "</option>");
	        }
	        locSelect.select2();  
		}
	}); 
}

function getHzOrg(obj){
	//机构列表加载
    $.ajax({
		type: "POST",
		async: false,
		url:GLOBAL.WEBROOT +  '/consultationCount/getHzOrg.ajax?orgId='+orgId+"&locId="+$(obj).val(),
		dataType:'json',
		success:function(data){
			var sysOrg = eval(data.orgBeans);
	        var orgSelect = $("#studyConsultorg");
	        orgSelect.empty(); 
	        orgSelect.append("<option value='-1'>请选择机构</option>");
	        for (var i = 0; i < sysOrg.length; i++) {
        		orgSelect.append("<option value='" + sysOrg[i].conorgId + "'>" + sysOrg[i].orgName + "</option>");
 	        }
	        orgSelect.select2();  
		}
	}); 
}

function getHzLoc(orgId,locId,conorgId){
	//机构列表加载
    $.ajax({
		type: "POST",
		async: false,
		url:GLOBAL.WEBROOT +  '/consultationCount/getHzOrg.ajax?orgId='+orgId+"&locId="+locId+"&conorgId="+conorgId,
		dataType:'json',
		success:function(data){
			var sysOrg = eval(data.orgBeans);
	        var orgSelect = $("#studyConsultloc");
	        orgSelect.empty(); 
	        orgSelect.append("<option value='-1'>请选择机构</option>");
	        for (var i = 0; i < sysOrg.length; i++) {
        		orgSelect.append("<option value='" + sysOrg[i].conlocId + "'>" + sysOrg[i].locName + "</option>");
 	        }
	        orgSelect.select2();  
		}
	}); 
}

//查会诊科室
function setLocItem(obj){ 
	var conorgId = $(obj).val();
	if(conorgId !="" && conorgId != '-1'){
		$.ajax({
		    type: "POST",
		    url: GLOBAL.WEBROOT+"/patientReg/getConsultLoc.ajax?orgId="+conorgId+"&locType=C&locId="+$("#locId").val(),
		    success: function (data) {
		        var locItemBeans = eval(data.locBeans);
		        var statusSelect = $("#studyConsultloc");
		        statusSelect.empty();
		        statusSelect.append("<option value='-1'>请选择科室</option>");
		        for (var i = 0; i < locItemBeans.length; i++) {
		            statusSelect.append("<option value='" + locItemBeans[i].conlocId + "' >" + locItemBeans[i].locName + "</option>");
		            $("#studyConsultloc").select2();
		        }
		    }
		});    
	}	
}

//重置
function reset(){
	$("#studyStarttime").val("");
	$("#studyEndtime").val("");
	$("#studyConsultorg").select2("val","-1"); 
	$("#studyConsultloc").select2("val","-1");
	$("#locId").select2("val","-1"); 
}

function exportHzInit(){
	var data = $("#searchForm").serialize();
	var url=GLOBAL.WEBROOT + '/consultationCount/exportHzInit.ajax?'+data;
    window.open(url);
}

function reloadGrid(){
    var data = $("#searchForm").serializeObject()
    data.page = 1;
    data.pageSize = 10;
	$("#conlist").jqGrid('setGridParam', {postData: data,page:1,pageSize:10}).trigger("reloadGrid");
}

//查询列表
function selectList() {
	var grid_selector = "#conlist";
    var pager_selector = "#conlist_pager";
    var data = $("#searchForm").serializeObject();
	$(grid_selector).jqGrid({
        url: GLOBAL.WEBROOT+"/consultationCount/queryHzRecordList.ajax",
        mtype : "post",  
        datatype: "json",
        height: '100%',
        width : '100%',
        postData :data,
        colNames: ['姓名','性别','年龄','机构名称','申请科室','申请医生','申请时间','会诊机构','会诊科室','会诊医生','会诊时间（报告时间）'],
        colModel: [
            {name: 'patientName', index: 'patientName',width:50, sortable: false},             
            {name: 'patientSex', index: 'patientSex',width:50, sortable: false},             
            {name: 'patientAge', index: 'patientAge', width:50,sortable: false},
            {name: 'orgName', index: 'orgName',width:50, sortable: false},
            {name: 'locDesc', index: 'locDesc',width:50,sortable: false},
            {name: 'operatorName', index: 'operatorName',width:50,sortable: false},
            {name: 'studyDatetime', index: 'studyDatetime',width:50,sortable: false},
            {name: 'studyConsultorgname', index: 'studyConsultorgname',width:50,sortable: false},
            {name: 'studyConsultlocname', index: 'studyConsultlocname',width:50,sortable: false},
            {name: 'careprovName', index: 'careprovName',width:50,sortable: false},
            {name: 'reportDatetime', index: 'reportDatetime',width:50,sortable: false},
        ],
        viewrecords: false,
        rowNum:10,
        rowList:[10,15,20,30],
        multiselect: false,
        multiboxonly: true,
        pager: pager_selector,
        altRows: true,
        loadComplete: function (result) {
            com.ai.bdx.util.updatePagerIcons(this);
    		//空列填充
    		var re_records = $("#conlist").getGridParam('records');
        	if(re_records < 10 )
        	{
        		for(var i=0,j=10-re_records;i<=j;i++){
        			//(re_records+1) 这个是加行号用的，没这个选中记录的行号都会重复
        			$("#conlist").jqGrid("addRowData",(re_records+1),{ '姓名':'','性别':'','年龄':'','机构名称':'','申请科室':'','申请医生':'','申请时间':'','会诊机构':'','会诊科室':'','会诊医生':'','会诊时间（报告时间）':''},"");
        		}
        	}
        	$("#divTotalInfo").html("会诊量合计："+re_records);
        },
        onSelectAll: function (aRowids, status) {
        },
        onSelectRow: function (rowid, status) {
        },
        autowidth: true
    });
}

//把object转成字符串
function getObjToString(_obj) {
    if ((_obj != 0 && !_obj) || _obj == 'null') {
        _obj = '';
    }
    return _obj;
}
function view(date,obj){
	var rowid = $(obj).closest('tr').attr("id");
	var rowData = $("#equilist").jqGrid('getRowData',rowid);
	var data = $("#searchForm").serialize();
	layer.open({
        type: 2,
        title:"检查记录",
        area: ['1000px', '450px'],
        fix: false, //不固定
        maxmin: true,
        content: GLOBAL.WEBROOT + "/equiWorkload/studyInfoList.html?orgId="+orgId+"&"+data+"&studyDatetime="+date+"&nowequipmentId="+rowData.modalityId
    });
}
 