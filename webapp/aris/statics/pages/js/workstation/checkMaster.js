var orgId=''; 
var locId='';
var rowData='';
$(function () {
	//初始化查询进间
    initDate();
    
	orgId = $("#orgId" , parent.document).val();  
	//工作列表记录查询
	selectList();

	//书写报告DIV初始化
    
    //检过条件初始化
    initDict(); 
    
    //所有select嵌套css样式
    $("#equipmentId").select2();
    $("#studyStatus").select2();
    $("#mainDoc").select2();
    $("#otherDoc").select2();
	$("#locInfo").select2();
	$("#modalityId").select2();
	//初始化设备下拉值
    initEquipment();

});

function initEquipment(){
	var Equipment=getCookies("C_Equipment");
	if(Equipment!=''&&Equipment!=null&&Equipment!=undefined){
		$("#equipmentId").select2('val',Equipment);
	}
}

//初始化查询进间
function initDate(){
	var n=new Date();
    n.setDate(n.getDate()-1);
    $("#startTime").val(n.getFullYear()+"-"+ (n.getMonth()+1)+"-"+ n.getDate());
    n.setDate(n.getDate()+1);
    $("#endTime").val(n.getFullYear()+"-"+ (n.getMonth()+1)+"-"+ n.getDate());
}

  
//initial query dict
function initDict() {

	var currentOrgId = $("#currentOrgId" , parent.document).val();
	//科室下拉列表
    $.ajax({
        type: "POST",
        async: false,
        url: GLOBAL.WEBROOT+"/workList/getLocInfo.ajax?orgId="+orgId,
        success: function (data) {
            var jsonStatus = eval(data.LOC_INFO);
            var statusSelect = $("#locInfo");
            statusSelect.empty(); 
            //statusSelect.append("<option value='-1'>请选择科室</option>");
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
	        }else{
	        	layer.msg('操作员与登录科室未关联！', {title:'提示',time:9000000000,btn:['去配置'],shade: [0.5, '#f5f5f5'],icon: 2},function(){window.location.href = GLOBAL.WEBROOT+'/basecode/aiscLoginLocInit'; });
	        }  
          //根据科室获取检查设备类型
            $.ajax({
                type: "POST",
                async: false,
                url: GLOBAL.WEBROOT+"/workList/getEquimentByLocId.ajax?orgId="+orgId+"&locId="+$("#locInfo").val(),
                success: function (data) {
                    var jsonEqimentType = eval(data.MODALITY_TYPE);
                    var modalitySelect = $("#modalityId");
                    modalitySelect.empty(); 
                    modalitySelect.append("<option value='-1'>全部</option>");
                    var currFlag = false;
                    for (var i = 0; i < jsonEqimentType.length; i++) {
                    	modalitySelect.append("<option value='" + jsonEqimentType[i].itemno + "'>" + jsonEqimentType[i].itemname + "</option>");
                    }
                }
            });
            
          //检查状态下拉列表
            $.ajax({
                type: "POST",
                async: true,
                url: GLOBAL.WEBROOT+"/workList/getStudyStatus.ajax?orgId="+orgId+"&locId="+$("#locInfo").val(),
                success: function (data) { 
                    var jsonStatus = eval(data.STUDY_STATUS);
                    var statusSelect = $("#studyStatus");
                    statusSelect.empty();
                    statusSelect.append("<option value='-1'>请选择</option>");
                    for (var i = 0; i < jsonStatus.length; i++) {
                    	if(jsonStatus[i].studystatus == "ARRIVE" || jsonStatus[i].studystatus =="StartStudy" || jsonStatus[i].statusname =="STUDYED"){
                    		statusSelect.append("<option value='" + jsonStatus[i].studystatus + "'>" + jsonStatus[i].statusname + "</option>");
                    	}                
                    }
                }
            });
        }
    });
}


//科室变更记录到cookie中，下次登录时，默认选中
function setLocCookie(){
	locId = "";
	locId = $("#locInfo option:selected").val();
	$.ajax({
        type: "POST",
        async: true,
        url: GLOBAL.WEBROOT+"/workList/setLocCookie.ajax?locId="+locId,
        success: function (data) { 
            
        }
    });
}
//申请医生查询
function setCareProv(orgId,locId){
	//操作技师申请医生下拉列表
	$.ajax({
	    type: "POST",
	    async: true,
	    url: GLOBAL.WEBROOT+"/patientReg/getCareProv.ajax?orgId="+orgId+"&locId="+locId+"&doctype=3",
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
	
	//辅助技师医生下拉列表
	$.ajax({
	    type: "POST",
	    async: true,
	    url: GLOBAL.WEBROOT+"/checkMaster/getCareProv.ajax?orgId="+orgId+"&locId="+locId+"&doctype=4",
	    success: function (data) {
	        var careProvBeans = eval(data.careProvBeans);
	        var statusSelect = $("#otherDoc");
	        statusSelect.empty();	        
	        statusSelect.append("<option value='-1'>请选择</option>");
	        $("#otherDoc").select2("val", "-1");
	        for (var i = 0; i < careProvBeans.length; i++) {
	            statusSelect.append("<option value='" + careProvBeans[i].careprovId + "'>" + careProvBeans[i].careprovName + "</option>");
	        }

			var otherDoc=$("#otherDoc").select2();
			if(rowData!=''){
				if(rowData.aidDoctorid!=''&&rowData.aidDoctorid!=null&&rowData.aidDoctorid!=undefined){
					$(otherDoc).val(rowData.aidDoctorid).trigger("change");
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
        url: GLOBAL.WEBROOT+"/checkMaster/queryCheckList.ajax?orgId="+orgId,
        mtype : "post",  
        datatype: "json",
        height: '100%',
        width : '100%',
        postData :data,
        colNames: ['病人ID','病人姓名','性别','年龄','患者类型','studylevelId','检查优先级','流水号','设备','房间','临床诊断','检查号','检查科室','检查状态','状态编码','检查医师','辅助医师'],
        colModel: [
            {name: 'patientId', index: 'patientId',width:50, sortable: false},
            {name: 'patientName', index: 'patientName',width:50, sortable: false},
            {name: 'patientSex', index: 'patientSex',width:50, sortable: false},
            {name: 'patientAge', index: 'patientAge',width:50, sortable: false},
            {name: 'patienttypeCode', index: 'patienttypeCode',width:50, sortable: false},
            {name: 'studylevelId', index: 'studylevelId',width:50, sortable: false,hidden:true},
            {name: 'studylevelIdDesc', index: 'studylevelIdDesc',width:50, sortable: false},
            {name: 'studyLocseqno', index: 'studyLocseqno',width:50, sortable: false},            
            {name: 'equipmentDesc', index: 'equipmentDesc',width:50, sortable: false},
            {name: 'roomDesc', index: 'roomDesc',width:50, sortable: false},
            {name: 'studyClinic', index: 'studyClinic',width:50, sortable: false,hidden:true},
            {name: 'studyinfoId', index: 'studyinfoId',width:50, sortable: false},
            {name: 'locName', index: 'locName',width:50, sortable: false},
            {name: 'studystatusCode', index: 'studystatusCode',width:50, sortable: false},
            {name: 'statusCode', index: 'statusCode',width:50, sortable: false,hidden:true},
			{name: 'studyDoctorid', index: 'studyDoctorid',width:50, sortable: false,hidden:true},
			{name: 'aidDoctorid', index: 'aidDoctorid',width:50, sortable: false,hidden:true},
            
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
        		for(var i=0,j=5-re_records;i<=j;i++){
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
	//alert(111);
	rowData= $("#worklist").jqGrid('getRowData',rowid);
	//初始化下拉技师列表
	 setCareProv(orgId,rowData.locId);

	 $.ajax({
	        type: "GET",
	        async: true,
	        url: GLOBAL.WEBROOT+"/checkMaster/queryStudyItem.ajax",
	        dataType: 'json',
	        data: {studyinfoId:rowData.studyinfoId},
	        success: function (data) {
	        	if(data.itemInfo!=''){
	        		var item=$.parseJSON(data.itemInfo);
	        		$("#studyitem_desc").val(item.studyitemDesc);
	        		$("#studyitem_bodyinfo").val(item.studyitemBodyinfo);
	        	}

	        	$("#study_clinic").val(rowData.studyClinic);
	        	$("#studyinfoId").val(rowData.studyinfoId);
	        	$("#statusCode").val(rowData.statusCode);
	        }
	    });
	
	 
	
}


//把object转成字符串
function getObjToString(_obj) {
    if ((_obj != 0 && !_obj) || _obj == 'null') {
        _obj = '';
    }
    return _obj;
}

function updateCheck(type){
	var data={};
	if($("#studyinfoId").val()==''){ 
		layer.alert('请选择一条检查记录进行操作！', {icon: 5,shadeClose: true});
		return  false;
	}
	//状态判定
	if(type=='1' && $("#statusCode").val()!='ARRIVE' && $("#statusCode").val()!='APP'){
		 
//		layer.alert('当前状态不能开始检查!', {
//		    skin: 'layui-layer-molv', //样式类名
//		    closeBtn: 0, //不显示关闭按钮
//		    btn: 0, //不显示确定按钮
//		    shadeClose: true, //开启遮罩关闭
//		    icon: 5
//		});  
		
		layer.alert('当前状态不能开始检查!', {icon: 5,shadeClose: true});
		return  false;
	}
	//状态判定
	if(type=='2'&&($("#statusCode").val()!='StartStudy'&&$("#statusCode").val()!=='ARRIVE')){
		layer.msg('当前状态不能结束检查！', {icon: 5});
		 
		return  false;
	}
	
	//状态判定
	if($("#mainDoc").val()=='-1'){
//		layer.alert('请选择操作技师！', {
//		    skin: 'layui-layer-molv', //样式类名
//		    closeBtn: 0, //不显示关闭按钮
//		    btn: 0, //不显示确定按钮
//		    shadeClose: true, //开启遮罩关闭
//		    icon: 5
//	    });  
		layer.alert('请选择操作技师！', {icon: 5,shadeClose: true});
		return  false;
	}
	/*//状态判定
	if($("#otherDoc").val()=='-1'){ 
		layer.alert('请选择操作辅助技师！', {icon: 5,shadeClose: true});
		return  false;
	}*/
	
	if(type=='1'||type=='2'){
		 data={actionType:type,studyinfoId:$("#studyinfoId").val(),studyDoctorid:$("#mainDoc").val(),aidDoctorid:$("#otherDoc").val(),studyFilmcount:$("#studyFilmcount").val()==''?0:$("#studyFilmcount").val(),studyExposurecount:$("#studyExposurecount").val()==''?0:$("#studyExposurecount").val()}
		 $.ajax({
		        type: "POST",
		        async: true,
		        url: GLOBAL.WEBROOT+"/checkMaster/updateCheck.ajax",
		        dataType: 'json',
		        data:data,
		        success: function (data) {
		        	if(data.success!=''){
		        		if(data.result=='success'){
		        			layer.msg('保存成功！');
							reloadGrid();
		        		}else{
		        			layer.msg('保存失败！'); 
		        		};
		        	}
		        	
		        }
		   });
		
	}
	if(type=='3'){
		
	}
}; 

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

//打印
function regPrint(){
	var studyInfoId = $("#studyinfoId").val();
	if (studyInfoId != null && studyInfoId != "") {
		layer.open({
			type: 2,
			title:"检查信息打印",
			area: ['800px', '500px'],
			fix: false, //不固定
			maxmin: true,
			content: GLOBAL.WEBROOT + "/patientReg/regPrint.html?studyinfoId="+studyInfoId
		});
	}else{
		layer.msg('请先选择一条检查记录！', {icon: 5});
	}
}


//重置设备房间
function setRoomId(obj){
	if($("#equipmentId").val()!=null&&$("#equipmentId").val()!='-1'){
		//alert($("#equipmentId").val());
		delCookies("C_Equipment");
		setCookies("C_Equipment",$("#equipmentId").val(),240);
	}

	var roomId = $("#equipmentId option:selected").attr("roomId")
	$("#roomId").val(roomId);
}