$(function () {
	initDict();
	$("#closeBtn").click(function(){
       closeDiv();
    });
	var type = $("#aiscRoomForm").attr("dataType");
	if(type=="query"){
		com.ai.bdx.util.disableAll("aiscRoomForm");
		$("#roomEnabled").val($("#aiscRoomForm").attr("old_roomEnabled"));
		$("#roomEnabled").select2();
		$("#saveBtn").hide();
	}else if(type=="update"){
		$("#roomId").attr("readonly","true");
		$("#roomEnabled").val($("#aiscRoomForm").attr("old_roomEnabled"));
		$("#roomEnabled").select2();
		$("#showText").text("保 存");
	}else{
		$("#roomId").attr("disabled","disabled");
		$("#roomId").val("系统自动生成");
		$("#roomEnabled").val("1");
		$("#roomEnabled").select2();
		$("#showText").text("保 存");
	}

	$("#aiscRoomForm").validate({
		// 非空校验
		rules : {
			roomDesc : {
				required : true
			},
			locId:{
				isSelect:true
			},
			orgId:{
				isSelect:true
			}

		},
		errorElement:'em',
		messages: {
			
		},
		submitHandler : save
	});
	$("#saveBtn").click(function(){
		$("#aiscRoomForm").submit();
	});

});

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
	        orgSelect.append("<option value='-1'>请选择机构</option>");
	        for (var i = 0; i < sysOrg.length; i++) {
	        	orgSelect.append("<option value='" + sysOrg[i].orgId + "'>" + sysOrg[i].orgName + "</option>");
	        }
	        if(sysOrg.length>0){
	        	orgSelect.val($("#aiscRoomForm").attr("old_org_id")==""?parent.parent.$("#currentOrgId").val():$("#aiscRoomForm").attr("old_org_id"));
	        }
	        orgSelect.select2();  
		}
	}); 
    
    loadLoc();
}

function loadLoc(){
	//科室名称
    $.ajax({
        type: "POST",
        url: GLOBAL.WEBROOT +"/basecode/getAiscLocName.ajax?orgId="+$("#orgId").val(),
        success: function (data) {
            var jsonaiscLocName = eval(data.AiscLocName);
            var loc_nameSelect = $("#locId");
            loc_nameSelect.empty();
            loc_nameSelect.append("<option value='-1'>--请选择--</option>");
            for (var i = 0; i < jsonaiscLocName.length; i++) {
                loc_nameSelect.append("<option value='" + jsonaiscLocName[i].locid + "'>" + jsonaiscLocName[i].locdesc + "</option>");
            }
            if(jsonaiscLocName.length>0){
            	loc_nameSelect.val($("#aiscRoomForm").attr("old_loc_id")==""?"-1":$("#aiscRoomForm").attr("old_loc_id"));	
            }
            loc_nameSelect.select2();
        }
    });
}

function closeDiv(){
	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}

function save() {
		//验证
	
		//提交
	    var data = $("#aiscRoomForm").serializeObject();
	    data = $.toJSON(data);
	    $.ajax({
	        type: "POST",
	        async: true,
	        url: GLOBAL.WEBROOT + "/basecode/saveAiscRoom",
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
	                layer.msg('新增失败', {icon: 1});
	            }
	        }
	    });
}
