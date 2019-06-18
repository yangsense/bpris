var chart01='';  
$(function () {
	//默认时间
	initDate();
	
    //检过条件初始化
    initDict();
    $("#studyConsultorg").select2();
    $("#studyConsultloc").select2();
    
    selectList();
    selectdetailList();
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
	//城市加载
	$.ajax({
		type: "POST",
		async: false,
		url:GLOBAL.WEBROOT +  '/basecode/getCitys.ajax',
		dataType:'json',
		success:function(data){
			 var jsonCitys = eval(data.cityCode);
	         var source_cityCode = $("#cityCode");
	         source_cityCode.empty();
	         source_cityCode.append("<option value='-1'>请选择城市</option>");
	         for (var i = 0; i < jsonCitys.length; i++) {
	        	 source_cityCode.append("<option value='" + jsonCitys[i].citycode + "'>" + jsonCitys[i].citydesc + "</option>");
	         }
	         source_cityCode.select2();
		}
	}); 
	getCountySelect();
	getOrgSelects();
    
}

function getCountySelect(){
	//区县加载
	var cityCode = $("#cityCode").val();
	$.ajax({
		type: "POST",
		async: false,
		url:GLOBAL.WEBROOT +  '/basecode/getCountys.ajax?cityCode='+cityCode,
		dataType:'json',
		success:function(data){
			 var jsonCountys = eval(data.countyMsg);
	         var source_countyCode = $("#countyCode");
	         source_countyCode.empty();
	         source_countyCode.append("<option value='-1'>请选择区县</option>");
	         for (var i = 0; i < jsonCountys.length; i++) {
	        	 source_countyCode.append("<option value='" + jsonCountys[i].countycode + "'>" + jsonCountys[i].countydesc + "</option>");
	         }
	         source_countyCode.select2();
		}
	}); 
}
function getOrgSelects(){
	var cityCode = $("#cityCode").val();
	var countyCode = $("#countyCode").val();
	//机构列表加载
	$.ajax({
		type: "POST",
		async: false,
		url:GLOBAL.WEBROOT +  '/basecode/getOrgSelects.ajax?cityCode='+cityCode+"&countyCode="+countyCode,
		dataType:'json',
		success:function(data){
			var jsonOrgMsg = eval(data.orgMsg);
	        var source_orgMsg = $("#orgId");
	        source_orgMsg.empty();
	        source_orgMsg.append("<option value='-1'>请选择机构</option>");
	        for (var i = 0; i < jsonOrgMsg.length; i++) {
	        	source_orgMsg.append("<option value='" + jsonOrgMsg[i].orgid + "'>" + jsonOrgMsg[i].orgname + "</option>");
	        }
	        source_orgMsg.select2();
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
	$("#startAge").val("");
	$("#endAge").val("");
	$("#categories").val("");
	$("#orgId").select2("val","-1"); 
	$("#cityCode").select2("val","-1");
	$("#countyCode").select2("val","-1");
}

function exportMedicalCount(){
	var data = $("#searchForm").serialize();
	var url=GLOBAL.WEBROOT + '/meidcalCount/exportMedicalCount.ajax?'+data+"&exportMark="+$("#exportMark").val();;
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
        url: GLOBAL.WEBROOT+"/meidcalCount/queryMedicalCountList.ajax",
        mtype : "post",  
        datatype: "json",
        height: '100%',
        width : '100%',
        postData :data,
        colNames: ['区县','开始日期','结束日期','数量'],
        colModel: [
            {name: 'countyDesc', index: 'countyDesc',width:50, sortable: false,formatter: function (param1, param2, recode) {
            	if(param1==undefined){
            		return '';
	        	}else{
	        		var studyStarttime = $("#studyStarttime").val();
	        		var studyEndtime = $("#studyEndtime").val();
	        		return '<div class="action-buttons">' +
	                '<a class="blue" href="javascript:void(0)" onclick="viewLocDetail(\'' + studyStarttime + '\',\'' + studyEndtime + '\',\'' + recode.countyCode + '\')" title="'+param1+'">'+param1+'</a>' +
	               "</div>";
	        	}
            }},             
            {name: '', index: '',width:50, sortable: false,formatter: function (param1, param2, recode) {
            	   var desc = recode.countyDesc;
            	   if(desc==undefined){
            		   return "";
            	   }else{
            		   return $("#studyStarttime").val();   
            	   }
            		
            }},            
            {name: '', index: '', width:50,sortable: false,formatter: function (param1, param2, recode) {
            		var desc = recode.countyDesc;
            		if(desc==undefined){
             		   return "";
             	   }else{
             		   return $("#studyEndtime").val();   
             	   }
            }}, 
            {name: 'hzsl', index: 'hzsl',width:50, sortable: false}, 
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
        			$("#conlist").jqGrid("addRowData",(re_records+1),{ '区县':'','开始日期':'','结束日期':'','数量':''},"");
        		}
        	}
        	$("#divTotalInfo").html("数量合计："+$("#conlist").getCol("hzsl", true, "sum"));
        },
        onSelectAll: function (aRowids, status) {
        },
        onSelectRow: function (rowid, status) {
        },
        autowidth: true
    });
}

function viewLocDetail(studyStarttime,studyEndtime,counutyCode) {
    $("#firstDiv").hide();
    $("#detail_div").show();
    $("#exportMark").val(2);
    $("#excelCounty").val(counutyCode);
    reloadMultipleGrid(studyStarttime,studyEndtime,counutyCode);
}

function showlast(){
	$("#exportMark").val(1);
	$("#firstDiv").show();
    $("#detail_div").hide();
}

function reloadMultipleGrid(studyStarttime,studyEndtime,counutyCode){
    var data = $("#searchForm").serializeObject();
    data.studyStarttime = studyStarttime;
    data.studyEndtime = studyEndtime;
    data.countyCode = counutyCode;
    $("#detaillist").jqGrid('setGridParam', {postData: data,page:1,pageSize:10}).trigger("reloadGrid");
}

//查询列表
function selectdetailList() {
	var grid_selector = "#detaillist";
    var pager_selector = "#detail_pager";
    var data = $("#searchForm").serializeObject();
	$(grid_selector).jqGrid({
        url: GLOBAL.WEBROOT+"/meidcalCount/queryMedicalCountDetailList.ajax",
        mtype : "post",  
        datatype: "json",
        height: '100%',
        width : '100%',
        postData :data,
        colNames: ['区县','机构名称','日期','病人姓名','登记号','年龄','性别','检查号','诊断意见','诊断结果'],
        colModel: [
            {name: 'countyDesc', index: 'countyDesc',width:50, sortable: false},        
            {name: 'orgName', index: 'orgName',width:50, sortable: false},          
            {name: 'sdatetime', index: 'sdatetime',width:50, sortable: false},            
            {name: 'patientName', index: 'patientName', width:50,sortable: false},
            {name: 'studyinfoId', index: 'studyinfoId',width:50, sortable: false},
            {name: 'patientAge', index: 'patientAge',width:50, sortable: false},
            {name: 'patientSex', index: 'patientSex',width:50, sortable: false},
            {name: 'studyAccnumber', index: 'studyAccnumber',width:50, sortable: false},
            {name: 'reportExam', index: 'reportExam',width:50, sortable: false},
            {name: 'reportResult', index: 'reportResult',width:50, sortable: false},
            
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
    		var re_records = $("#detaillist").getGridParam('records');
        	if(re_records < 10 )
        	{
        		for(var i=0,j=10-re_records;i<=j;i++){
        			//(re_records+1) 这个是加行号用的，没这个选中记录的行号都会重复
        			$("#detaillist").jqGrid("addRowData",(re_records+1),{ '区县':'','机构名称':'','日期':'','病人姓名':'','登记号':'','年龄':'','性别':'','检查号':'','诊断意见':'','诊断结果':''},"");
        		}
        	}
        	//getTotalInfo();
        	
        	$("#retLastBtn").html("<a onclick='showlast()'>返回上一级</a>");
        },
        onSelectAll: function (aRowids, status) {
        },
        onSelectRow: function (rowid, status) {
        },
        autowidth: true
    });
}


//function getTotalInfo(){
//	var data = $("#searchForm").serializeObject();
//	data.orgId= orgId;
//	$.ajax({
//		type : "POST",
//		async : true,
//		url : GLOBAL.WEBROOT + '/consultationCount/getTotalInfo.ajax',
//		data:data,
//		success : function(data) {
//			if (data.ERRCODE == "0") {
//				$("#divTotalInfo").html("会诊申请数量合计："+data.hzsqsl+"  已会诊数量合计："+data.hzsl+"");
//			} else {
//				alert("操作失败！" + data.ERRINFO);
//			}
//		}
//	});
//}

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
 