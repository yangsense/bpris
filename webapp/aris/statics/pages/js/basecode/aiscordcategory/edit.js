$(function () {
	//机构下拉
    getOrgSelects();
    var id = $("#ordcategoryId").val()==''?0:$("#ordcategoryId").val();
    var opType = $("#editForm").attr("opType");
    init(id);
    if(opType=="view"){
        com.ai.bdx.util.disableAll("editForm");
        $("#saveBtn").hide();
    }else if(opType=="update"){
    	$("#showText").text("保 存");
    }else{
    }
    $("select").select2();
    $("#closeBtn").click(closeLayer);
   // $("#saveBtn").click(save);
	
    $("#editForm").validate({
        // 非空校验
        rules : {
	    	orgId : {
				isSelect:true
			},
            ordcategoryDesc : {
                required : true
            },

            ordcategoryCode:{
            	required:true,
            	remote:GLOBAL.WEBROOT + '/aiscOrdCate/chekcAiscOrdCateCode.ajax?ordcategoryId='+id+"&orgId="+$("#orgId").val()
            }
        },
        errorElement:'em',
        messages: {
        	ordcategoryCode:{
        		required:'必填字段',
        		remote:'已存在'
        	}
        },
        submitHandler : save
    });

    $("#saveBtn").click(function(){
        $("#editForm").submit();
    });
});

/*function getOrgSelects(){
	//机构列表加载
	$.ajax({
		type: "POST",
		async: false,
		url:GLOBAL.WEBROOT +  '/basecode/getOrgSelects2.ajax',
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
}*/
//机构列表加载
function getOrgSelects(){
	$.ajax({
		type: "POST",
		async: false,
		url:GLOBAL.WEBROOT +  '/bpris/getOrgs.ajax?operatorCode='+parent.parent.user.operatorCode+"&sysType=25",
		dataType:'json',
		success:function(data){
			var sysOrg = eval(data);
	        var orgSelect = $("#orgId");
	        orgSelect.empty(); 
	        orgSelect.append("<option value='-1'>请选择机构</option>");
	        for (var i = 0; i < sysOrg.length; i++) {
	        	orgSelect.append("<option value='" + sysOrg[i].orgId + "'>" + sysOrg[i].orgName + "</option>");
	        }
	        orgSelect.val(parent.$("#orgId").val()==""?"-1":parent.$("#orgId").val());
	        orgSelect.select2();
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
        url: GLOBAL.WEBROOT + "/aiscOrdCate/getAiscOrdCate",
        dataType: 'json',
        data: data,
        success: function (data) {
            setForm(data);
        }
    });
}

function setForm(data) {
    $("#ordcategoryDesc").val(data.ordcategoryDesc);
    $("#ordcategoryEndesc").val(data.ordcategoryEndesc);
    $("#ordcategoryCode").val(data.ordcategoryCode);
    $("#orgId").select2("val",data.orgId);
}



function save() {
    var data = $("#editForm").serializeObject()
    data = $.toJSON(data);
    $.ajax({
        type: "POST",
        async: true,
        url: GLOBAL.WEBROOT + "/aiscOrdCate/saveAiscOrdCate",
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