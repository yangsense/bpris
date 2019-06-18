//组织机构ID
var orgId = ""; 
$(function () {
	
	orgId = $("#orgId" , parent.document).val(); 	
	selectList();    
     
	$("#closeBtn").click(function(){
	       closeDiv();
	});
	
	$("#saveBtn").click(function(){
		savePatientInfo();
	});
});

function reloadGrid(){
    var data = $("#searchForm").serializeObject() ;   
	$("#patientlist").jqGrid('setGridParam', {postData: data,page:1,pageSize:8}).trigger("reloadGrid");
}

//查询列表
function selectList() {	 
	var currentOrgId = $("#currentOrgId" , parent.document).val();	
	var grid_selector = "#patientlist";
    var pager_selector = "#patientlist_pager";
    var data = $("#searchForm").serializeObject();  
	$(grid_selector).jqGrid({
        url: GLOBAL.WEBROOT+"/patientReg/getPatientList.ajax?patientName="+encodeURI(encodeURI($("#patientName").val())),
        mtype : "post",  
        async: true,
        datatype: "json",
        height: '100%',
        width : '100%',
        postData :data,
        colNames: ['patientId','病人ID','病人姓名','病人姓别','手机','身份证号','住址','patientNamepy','patientDob','patientAge','patientPhone','patientMedicareid','patientCardid','patientRemark'],
        colModel: [
            {name: 'patientGlobalid', index: 'patientGlobalid', width:50,sortable: false,hidden:true},
            {name: 'patientId', index: 'patientId', width:50,sortable: false},
            {name: 'patientName', index: 'patientName', width:50,sortable: false},
            {name: 'patientSex', index: 'patientSex', width:50,sortable: false}, 
            {name: 'patientMobile', index: 'patientMobile', width:80,sortable: false},
            {name: 'patientIdnumber', index: 'patientIdnumber', width:80,sortable: false},
            {name: 'patientAddress', index: 'patientAddress', width:80,sortable: false},
            {name: 'patientNamepy', index: 'patientNamepy', width:50,sortable: false,hidden:true},
            {name: 'patientDob', index: 'patientDob', width:50,sortable: false,hidden:true},
            {name: 'patientAge', index: 'patientAge', width:50,sortable: false,hidden:true}, 
            {name: 'patientPhone', index: 'patientPhone', width:80,sortable: false,hidden:true},
            {name: 'patientMedicareid', index: 'patientMedicareid', width:80,sortable: false,hidden:true},
            {name: 'patientCardid', index: 'patientCardid', width:80,sortable: false,hidden:true},
            {name: 'patientRemark', index: 'patientRemark', width:80,sortable: false,hidden:true}
        ],        
        viewrecords: false,
        rowNum:8,
        rowList:[10,15,20,30],
        multiselect: true,
        multiboxonly: true,
        pager: pager_selector,
        altRows: true,
        loadComplete: function () {
            com.ai.bdx.util.updatePagerIcons(this);
        },
        onSelectAll: function (aRowids, status) {
        }, 
        //实现单选
        beforeSelectRow: function (rowid, e) {
        	$(this).jqGrid("resetSelection");
            return true;
        }, 
        autowidth: true
    });
}
 
  
//浏览
function savePatientInfo(){
	var gr = $("#patientlist").jqGrid("getGridParam","selarrrow");
	if (gr.length == 0) {
		alert("请选择一行！");
		return;
	}
	if (gr.length > 1) {
		alert("不能选择多行！");
		return;
	}
	var rowData = $("#patientlist").jqGrid('getRowData',gr[0]);
	$("#patientId",parent.document).val(rowData.patientId);
    $("#patientGlobalid",parent.document).val(rowData.patientGlobalid);
    $("#patientName",parent.document).val(rowData.patientName);
    //$("#patientSex",parent.document).val(rowData.patientSex);
    var sexV = 1;
    if(rowData.patientSex=='男'){
    	sexV = 1;
    }else{
    	sexV = 2;
    } 
    parent.setSexSelected(sexV);
    
    $("#patientMobile",parent.document).val(rowData.patientMobile);
    $("#patientIdnumber",parent.document).val(rowData.patientIdnumber);
    $("#patientAddress",parent.document).val(rowData.patientAddress);
    //$("#patientNamepy",parent.document).val(rowData.patientNamepy);
    parent.ename();   
    var dob=rowData.patientDob;
    
    if(rowData.patientDob !=null && rowData.patientDob.length>10){
    	dob=rowData.patientDob.substring(0,10);
    }
    $("#patientDob",parent.document).val(dob);
    $("#patientAge",parent.document).val(rowData.patientAge); 
    if(rowData.patientAge.substring(rowData.patientAge.length-2)=="小时"){
    	$("#patientAgeShow",parent.document).val(rowData.patientAge==null?rowData.patientAge:rowData.patientAge.substring(0,rowData.patientAge.length-2)); 
        if(rowData.patientAge!=null){    	
        	parent.setAgeUnitSelected(rowData.patientAge.substring(rowData.patientAge.length-2));
        }
    }else{
    	$("#patientAgeShow",parent.document).val(rowData.patientAge==null?rowData.patientAge:rowData.patientAge.substring(0,rowData.patientAge.length-1)); 
        if(rowData.patientAge!=null){    	
        	parent.setAgeUnitSelected(rowData.patientAge.substring(rowData.patientAge.length-1));
        }
    }
    
    
    $("patientPhone",parent.document).val(rowData.patientPhone);
    $("#patientMedicareid",parent.document).val(rowData.patientMedicareid);
    $("#patientCardid",parent.document).val(rowData.patientCardid);  
    $("#patientRemark",parent.document).val(rowData.patientRemark);
    $("#isNew",parent.document).val("yn");
    
    if(rowData.patientAge!=""){
    	$("#patientAgeShow",parent.document).next(".help-block-new").remove();
    }
    if(rowData.patientSex!="-1"){
    	$("#patientSex",parent.document).next(".help-block-new").remove();
    }
    closeDiv();
}

function closeDiv(){
	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}



 
 
 