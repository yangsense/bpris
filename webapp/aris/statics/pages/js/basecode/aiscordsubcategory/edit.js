$(function () {
    var id = $("#ordsubcategoryId").val()==''?0:$("#ordsubcategoryId").val();
    var opType = $("#editForm").attr("opType");
    init(id);

    if(opType=="view"){
        com.ai.bdx.util.disableAll("editForm");
        $("#saveBtn").hide();
    }else if(opType=="update"){
//    	$("#ordsubcategoryCode").attr("readonly","true");
    	$("#showText").text("保 存");
    }else{
    	}


    $("select").select2();
    $("#closeBtn").click(closeLayer);
    //$("#saveBtn").click(save);
    $("#editForm").validate({
        // 非空校验
        rules : {
//            ordcategoryId : {
//                required : true,
//                number:true
//            },
            ordcategoryId:{
            	isSelect:true
            },
            ordsubcategoryDesc:{
                required:true
            },
            ordsubcategoryCode:{
                required:true,
                remote:GLOBAL.WEBROOT + '/aiscOrdSubCate/checkSubOrdcategoryCode.ajax?ordsubcategoryId='+id
            }
        },
        errorElement:'em',
        messages: {
           /* ordsubcategoryDesc: '必须填写',
            ordsubcategoryCode:'必须填写'*/
        	 ordsubcategoryCode:{
                 required:'必填',
                 remote:'编码已存在'
             }
        },
        submitHandler : save
    });

    $("#saveBtn").click(function(){
        $("#editForm").submit();
    });
    
    
});

function initCate(id) {
    //检查大类下拉
	//var orgId = $("#orgIdValue").val();
	var orgId = orgIdFromJs = $("#orgId" , parent.document).val(); 
    $.ajax({
        type: "POST",
        url: GLOBAL.WEBROOT +"/aiscOrdCate/getAiscOrdCategory.ajax?orgId="+orgId,
        success: function (data) {
            var jsonOrdCategory = eval(data.AiscOrdCategoryName);
            var ordCategorySelect = $("#ordcategoryId");
            ordCategorySelect.empty();
            ordCategorySelect.append("<option value='-1'>请选择大类</option>");
            for (var i = 0; i < jsonOrdCategory.length; i++) {
            	ordCategorySelect.append("<option value='" + jsonOrdCategory[i].ordcategoryid + "'>" + jsonOrdCategory[i].ordcategorydesc + "</option>");
            }
            ordCategorySelect.val(id==""?"-1":id);
            ordCategorySelect.select2();
        }
    });
}


//新增
function init(id) {
	initCate("");
    if (id){
        edit(id);
    }
}

function edit(id) {
    var data = "id=" + id;
    $.ajax({
        type: "GET",
        async: false,
        url: GLOBAL.WEBROOT + "/aiscOrdSubCate/getAiscOrdSubCate",
        dataType: 'json',
        data: data,
        success: function (data) {
                setForm(data);
        }
    });
}

function setForm(data) {
	initCate(data.ordcategoryId) ; //初始化检查大类
    $("#ordsubcategoryDesc").val(data.ordsubcategoryDesc);
    $("#ordsubcategoryEndesc").val(data.ordsubcategoryEndesc);
    $("#ordsubcategoryNote").val(data.ordsubcategoryNote);
    $("#ordsubcategoryCode").val(data.ordsubcategoryCode);
}

function save() {
    var data = $("#editForm").serializeObject()
    data = $.toJSON(data);
    $.ajax({
        type: "POST",
        async: true,
        url: GLOBAL.WEBROOT + "/aiscOrdSubCate/saveAiscOrdSubCate",
        dataType: 'json',
        contentType: 'application/json',
        data: data,
        success: function (data) {
            if (data.ERRCODE == "0") {
                infoCall('温馨提示',"保存成功！",saveSuccess);
            }
            else {
                info('温馨提示',data.ERRINFO);
            }
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