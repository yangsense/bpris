$(function () {
	$("#closeBtn").click(function(){
       closeDiv();
    });
    
	var type = $("#aiscPeriodForm").attr("dataType");
	if(type=="query"){
		com.ai.bdx.util.disableAll("aiscReportForm");
		$("#saveBtn").hide();
	}else if(type=="update"){
		$("#showText").text("保 存");
	}else{
		$("#showText").text("保 存");
	}

	$("#aiscPeriodForm").validate({

		// 非空校验
		rules : {
			orgId : {
				isSelect:true
			},
			sourceUrl:{
				required:true
			},
			sourceUser:{
				required:true
			},
			sourcePassword:{
				required:true
			}

		},
		errorElement:'em',
		messages: {
		/*	locCode: '非空',
			locDesc:'非空'*/
		},
		submitHandler : save
	});

	$("#saveBtn").click(function(){
		$("#aiscPeriodForm").submit();
	});
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
	        orgSelect.append("<option value='-1'>请选择机构</option>");
	        for (var i = 0; i < sysOrg.length; i++) {
	        	orgSelect.append("<option value='" + sysOrg[i].orgId + "'>" + sysOrg[i].orgName + "</option>");
	        }
        	var orgIds = sysOrg[0].orgId;
	        if(orgIds.length>0){
	        	orgSelect.val(orgIds);
	        }else{
	        	orgSelect.val("-1");
	        }
	        orgSelect.val(parent.parent.$("#currentOrgId").val()==""?"-1":parent.parent.$("#currentOrgId").val());
	        orgSelect.select2({ width: 'resolve' });  
		}
	}); 
    
  //类型
    $.ajax({
        type: "POST",
        url: GLOBAL.WEBROOT +"/basecode/getAiscSourceType.ajax",
        success: function (data) {
            var jsonAiscSourceType = eval(data.AiscSourceType);
            var source_TypeSelect = $("#patientTypecode");
            source_TypeSelect.empty();
            source_TypeSelect.append("<option value='-1'>请选择类型</option>");
            for (var i = 0; i < jsonAiscSourceType.length; i++) {
            	source_TypeSelect.append("<option value='" + jsonAiscSourceType[i].itemno + "'>" + jsonAiscSourceType[i].itemname + "</option>");
            }
            if($("#typecodeHidden").val()==""){
            	source_TypeSelect.val("-1");
	        }else{
	        	source_TypeSelect.val($("#typecodeHidden").val());
	        }
            source_TypeSelect.select2();
        }
    });
});

function closeDiv(){
	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}

function save() {
	    var data = $("#aiscPeriodForm").serializeObject();
	    data.orgName=$("#s2id_orgId").find("span.select2-chosen").html();
	    data = $.toJSON(data);
	    $.ajax({
	        type: "POST",
	        async: true,
	        url: GLOBAL.WEBROOT + "/basecode/saveAiscHisDatasource.ajax",
	        dataType: 'json',
	        contentType: 'application/json',
	        data: data,
	        success: function (data) {
	            if (data.ERRCODE == "0") {
	            	layer.msg('操作成功', {icon: 1});
	                parent.reloadGrid();
	                setTimeout(closeDiv,1500);
	            }
	            else {
	                layer.msg('新增失败'+data.ERRINFO, {icon: 1});
	            }
	        }
	    });
}
