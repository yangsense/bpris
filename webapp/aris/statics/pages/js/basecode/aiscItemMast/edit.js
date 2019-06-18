
$(function () {
    initDict();
    var id = $("#itemmastId").val()==''?0:$("#itemmastId").val();
    var opType = $("#editForm").attr("opType");
    init(id);
    if(opType=="view"){
        com.ai.bdx.util.disableAll("editForm");
        $("#saveBtn").hide();
    }else if(opType=="update"){
    	$("#showText").text("保 存");
    }else{
    	 //检查大类下拉
        initCate("");
        
     }
	
    $("#closeBtn").click(closeLayer);
    //$("#saveBtn").click(save);
 	$("select").select2();
    $("#editForm").validate({
        // 非空校验
        rules : {
    		ordcategoryId:{
                isSelect:true
            },
            ordsubcategoryId:{
                isSelect:true
            },
            itemmastWeight:{
            	number:true
            },
            itemmastCode:{
                required:true
            },
            itemmastDesc:{
                required:true
            },
            itemmastSevice:{
            	publicSelectVal:true
            },
            itemmastIsenhanced:{
            	publicSelectVal:true
            }
        },
        errorElement:'em',
        messages: {
        	itemmastWeight:{
        	    required : '必填',
             	max:'不能大于1',
             	number:'非数字'
             },
            itemmastCode:{
                required:'必填',
                remote:'编码已存在'
            }
        },
        submitHandler : save
    });

    $("#saveBtn").click(function(){
//        //校验数据是否重复
//        if (!checkAdd()){
            $("#editForm").submit();
//        }else {
//            alert("医嘱代码，请重新填写！");
//        }
//
    });
});

function getpym(){
	$.ajax({
        type: "POST",
        async: false,
        url:GLOBAL.WEBROOT +  '/aiscItemMast/getPym.ajax?itemmastName='+encodeURI(encodeURI($("#itemmastDesc").val())),
        dataType:'json',
        success:function(data){
            $("#pyInitial").val(data.pymName);
        }
    });
}


function checkAdd() {
    //校验新增数据是否重复
    var  flag= false ;
    var itemmastCode = $("#itemmastCode").val();
    var orgId = $("#orgId").val();
    var itemmastId = $("#itemmastId").val();
    if($("#editForm").attr("opType")=="add"){
    	itemmastId = 0;
	}
    $.ajax({
        type: "POST",
        async: false,
        url:GLOBAL.WEBROOT +  '/aiscItemMast/checkAiscItemMast.ajax?itemmastCode='+itemmastCode+"&orgId="+orgId+"&itemmastId="+itemmastId,
        dataType:'json',
        success:function(data){
            if(data.result=="true"){
                flag=true ;
            }
        }
    });
    return flag ;
}
//initial query dict
function initDict() {
	//机构列表加载
    $.ajax({
		type: "POST",
		async: false,
		url:GLOBAL.WEBROOT +  '/bpris/getOrgs.ajax?operatorCode='+parent.parent.user.operatorCode+"&sysType=25",
		dataType:'json',
		success:function(data){
			var sysOrg = eval(data);
	        var orgSelect = $("#orgId");
	        orgSelect.empty(); 
	        //orgSelect.append("<option value='-1'>请选择机构</option>");
	        for (var i = 0; i < sysOrg.length; i++) {
	        	orgSelect.append("<option value='" + sysOrg[i].orgId + "'>" + sysOrg[i].orgName + "</option>");
	        }
	        if($("#editForm").attr("old_org_Id")!=""){
	        	orgSelect.val($("#editForm").attr("old_org_Id")==""?"-1":$("#editForm").attr("old_org_Id"));
	        }else{
	        	orgSelect.val(parent.parent.$("#currentOrgId").val());	
	        }
	        orgSelect.select2();
		}
	}); 
	
    //初始化字典值
    $.ajax({
        type: "POST",
        async:false,
        url: GLOBAL.WEBROOT + "/aiscItemMast/getQueryCondition",
        success: function (data) {
            var jsonService = eval(data.service);
            var serviceSelect = $("#itemmastSevice");
            serviceSelect.empty();
            serviceSelect.append("<option value='-1'>请选择</option>");
            for (var i = 0; i < jsonService.length; i++) {
                serviceSelect.append("<option value='" + jsonService[i].itemno + "'>" + jsonService[i].itemname + "</option>");
            }
            serviceSelect.val(($("#editForm").attr("old_itemmast_service")=="0"||$("#editForm").attr("old_itemmast_service")=="")?"-1":$("#editForm").attr("old_itemmast_service"));
			serviceSelect.select2();
            var jsonIsEnhanced = eval(data.isEnhanced);
            var isenhancedSelect = $("#itemmastIsenhanced");
            isenhancedSelect.empty();
            isenhancedSelect.append("<option value='-1'>请选择</option>");
            for (var i = 0; i < jsonIsEnhanced.length; i++) {
                isenhancedSelect.append("<option value='" + jsonIsEnhanced[i].itemno + "'>" + jsonIsEnhanced[i].itemname + "</option>");
            }
            isenhancedSelect.val($("#editForm").attr("old_itemmast_Isenhanced")==0?"-1":$("#editForm").attr("old_itemmast_Isenhanced"));
           	isenhancedSelect.select2();
        }
    });
    
  
}

//新增
function init(id) {
    if (id){
        edit(id);
    }
}

function edit(id) {
    var data = "id=" + id;
    $.ajax({
        type: "GET",
        async: false,
        url: GLOBAL.WEBROOT + "/aiscItemMast/getAiscItemMast",
        dataType: 'json',
        data: data,
        success: function (data) {
                setForm(data);
        }
    });
}

function setForm(data) {
    
	var ordcategoryid = data.ordcategoryId;
	initCate(ordcategoryid);
	var ordsubid = data.ordsubcategoryId;
	setTimeout("initSubCate("+ordsubid+")",500);
    $("#itemmastCode").val(data.itemmastCode);
    $("#itemmastDesc").val(data.itemmastDesc);
    $("#itemmastEndesc").val(data.itemmastEndesc);
    $("#itemmastPrice").val(data.itemmastPrice);
    $("#itemmastWeight").val(data.itemmastWeight);
    $("#itemmastExposurecount").val(data.itemmastExposurecount);
    $("#pyInitial").val(data.pyInitial);
//    $("#itemmastSevice").val(data.itemmastSevice);
//    $("#itemmastIsenhanced").val(data.itemmastIsenhanced);
//    alert($("#editForm").attr("old_itemmast_Isenhanced"));
//    $("#itemmastSevice option").each(function () {
//        if ($(this).val() == data.itemmastSevice) {
//            $(this).attr("selected", true);
//        }
//    });
//    $("#itemmastIsenhanced option").each(function () {
//        if ($(this).val() == data.itemmastIsenhanced) {
//            $(this).attr("selected", true);
//        }
//    });

}

function save() {
	if (checkAdd()){
		layer.msg('机构与医嘱代码必须唯一，请重新选择！', {icon: 2});
		return ;
	}
	
    var data = $("#editForm").serializeObject()
    data = $.toJSON(data);
    $.ajax({
        type: "POST",
        async: true,
        url: GLOBAL.WEBROOT + "/aiscItemMast/saveAiscItemMast",
        dataType: 'json',
        contentType: 'application/json',
        data: data,
        success: function (data) {
            if (data.ERRCODE == "0") {
            	layer.msg('操作成功', {icon: 1}); 
            	saveSuccess();
            }
            else {
                info('温馨提示',data.ERRINFO);
            }
        }
    });
}


function initCate(ordcategoryid) {
    //检查大类下拉
    $.ajax({
        type: "POST",
        url: GLOBAL.WEBROOT +"/aiscOrdCate/getAiscOrdCategory.ajax?orgId="+$("#orgId").val() ,
        success: function (data) {
            var jsonOrdCategory = eval(data.AiscOrdCategoryName);
            var ordCategorySelect = $("#ordcategoryId");
            ordCategorySelect.empty();
            ordCategorySelect.append("<option value='-1'>请选择大类</option>");
            for (var i = 0; i < jsonOrdCategory.length; i++) {
            	ordCategorySelect.append("<option value='" + jsonOrdCategory[i].ordcategoryid + "'>" + jsonOrdCategory[i].ordcategorydesc + "</option>");
            }
            ordCategorySelect.val(ordcategoryid==""?"-1":ordcategoryid);
            ordCategorySelect.select2();
        }
    });
    
}

function initSubCate(ordsubcategoryid) {
	//检查子类下拉
    var ordcategoryId = $("#ordcategoryId").val() ;
    $.ajax({
        type: "POST",
        url: GLOBAL.WEBROOT +"/aiscOrdSubCate/getAiscOrdSubCategory.ajax?ordcategoryId="+ordcategoryId+"&orgId="+$("#orgId").val() ,
        success: function (data) {
            var jsonOrdSubCategory = eval(data.AiscOrdSubCategory);
            var ordSubCategorySelect = $("#ordsubcategoryId");
            ordSubCategorySelect.empty();
            ordSubCategorySelect.append("<option value='-1'>请选择子类</option>");
            for (var i = 0; i < jsonOrdSubCategory.length; i++) {
            	ordSubCategorySelect.append("<option value='" + jsonOrdSubCategory[i].ordsubcategoryid + "'>" + jsonOrdSubCategory[i].ordsubcategorydesc + "</option>");
            }
           ordSubCategorySelect.val(ordsubcategoryid==""?"-1":ordsubcategoryid);
           ordSubCategorySelect.select2();
        }
    });
}





function saveSuccess(){
    parent.reloadGrid();
    setTimeout(closeLayer,1500);
}
function closeLayer(){
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}