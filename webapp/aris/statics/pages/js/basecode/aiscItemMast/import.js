var orgId = "";
$(function () {
	orgId = $("#orgId" , parent.parent.document).val();
	$("select").select2();
	initLoc();
	initCate("");
	$("#editForm").validate({
        // 非空校验
        rules : {
        	ordcategoryId:{
                isSelect:true
            },
            ordsubcategoryId:{
                isSelect:true
            },
            locCode:{
                isSelect:true
            }
        },
        errorElement:'em',
        submitHandler : save
    });
    $("#saveBtn").click(function(){
        $("#editForm").submit();
    });
    $("#closeBtn").click(function(){
    	closeLayer();
    });
    $("#exportItem").click(function(){
    	exportItem();
    });
});
function initLoc(){
	//科室下拉列表
    $.ajax({
        type: "POST",
        async: false,
        url: GLOBAL.WEBROOT+"/workList/getLocInfo.ajax?orgId="+orgId,
        success: function (data) {
            var jsonStatus = eval(data.LOC_INFO);
            var statusSelect = $("#locInfo");
            statusSelect.empty(); 
            statusSelect.append("<option value='-1'>请选择科室</option>");
            var currFlag = false;
            for (var i = 0; i < jsonStatus.length; i++) {
                statusSelect.append("<option value='" + jsonStatus[i].loccode + "'>" + jsonStatus[i].locdesc + "</option>");
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
        }
    });
}
function save() {
    var data = $("#editForm").serializeObject()
    $.ajax({
        type: "POST",
        async: true,
        url: GLOBAL.WEBROOT + "/aiscItemMast/importItem.ajax",
        dataType: 'json',
        data: data,
        success: function (data) {
            if (data.ERRCODE == "0") {
                infoCall('温馨提示',"导入成功！",saveSuccess);
            }else if(data.ERRCODE == "-2"){
				info('导入失败',"没有查询到机构与所选科室对应的his检查项目");
			}
            else {
            	info('导入失败',"请检查数据源配置信息或重新登录");
            }
        },
		beforeSend:function(){
			$("#showText").attr("disabled", true);
			$("#showText").html("正在导入...");
		},
		complete : function(responseText, statusText) {
			$("#showText").attr("disabled", false);
			$("#showText").html("导 入");
		}
    });
}
function initCate(ordcategoryid) {
    //检查大类下拉
    $.ajax({
        type: "POST",
        url: GLOBAL.WEBROOT +"/aiscOrdCate/getAiscOrdCategory.ajax?orgId="+orgId ,
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
        url: GLOBAL.WEBROOT +"/aiscOrdSubCate/getAiscOrdSubCategory.ajax?ordcategoryId="+ordcategoryId+"&orgId="+orgId ,
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
    closeLayer();
}
function closeLayer(){
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}

function exportItem(){
	if(orgId == null || orgId==""){
		alert("请选择机构");
		return;
	}
	if($("#ordcategoryId").val()=="-1"){
		alert("请选择医嘱大类");
		return;
	}
	if($("#ordsubcategoryId").val()=="-1"){
		alert("请选择医嘱子类");
		return;
	}
	if($("#locInfo").val()=="-1"){
		alert("请选择科室");
		return;
	}
	var data1 = $("#editForm").serialize();
	
	$.ajax({
		type : "POST",
		async : true,
		url : GLOBAL.WEBROOT + '/aiscItemMast/exportCheckMask.ajax',
		data : data1,
		success : function(data) {
			if (data.ERRCODE == "0") {
				alert(data1);
				var url=GLOBAL.WEBROOT + '/aiscItemMast/exportItem.ajax?'+data1;
			    window.open(url);
			} else {
				info('导出失败',"请检查数据源配置信息或重新登录");
			}
		}
	});
	
    
}