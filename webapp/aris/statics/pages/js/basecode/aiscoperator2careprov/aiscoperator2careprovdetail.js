
$(function () {
	initDict();
	
	//关闭按钮
	$("#closeBtn").click(function(){
	       closeDiv();
	    });
	
	var type = $("#aiscOperator2CareProvForm").attr("dataType");
	if(type=="query"){
		com.ai.bdx.util.disableAll("aiscOperator2CareProvForm");
		$("#saveBtn").hide();
	}else if(type=="update"){
//		$("#user2careprovId").attr("readonly","true");
		//$("#showText").text("修 改");
	}else{
		$("#showText").text("保 存");
	}

	$("#saveBtn").click(function(){
		save();
	});
});

function closeDiv(){
	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}
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
	        for (var i = 0; i < sysOrg.length; i++) {
	        	orgSelect.append("<option value='" + sysOrg[i].orgId + "'>" + sysOrg[i].orgName + "</option>");
	        }
	        orgSelect.val(parent.parent.$("#currentOrgId").val()==""?"-1":parent.parent.$("#currentOrgId").val());
	        orgSelect.select2();  
	        setInitoperatorlist();
		}
	});  
}

function setInitoperatorlist(){
 	$.ajax({
        type: "POST",
        async: true,
        url: GLOBAL.WEBROOT + "/bpris/getOperatorBeansByOp.ajax?orgId="+$("#orgId").val(),
        dataType: 'json',
        success: function (data) {
    		//操作人员
	 		var jsonOperatorlist = eval(data);
            var operSelect = $("#operatorlist");
            operSelect.empty();
            for (var i = 0; i < jsonOperatorlist.length; i++) {
                operSelect.append("<option value='" + jsonOperatorlist[i].operatorId+"||"+jsonOperatorlist[i].operatorCode + "'>" +jsonOperatorlist[i].operatorId+"_" +jsonOperatorlist[i].operatorCode +"_"+ jsonOperatorlist[i].operatorName + "</option>");
            }
        }
    });
	
 	var data = $("#aiscOperator2CareProvForm").serializeObject();
    data.orgId = $("#orgId").val();
	//医护人员
 	$.ajax({
        type: "POST",
        async: true,
        url: GLOBAL.WEBROOT + "/basecode/getMedicalStaff.ajax",
        dataType: 'json',
        data:data,
        success: function (data) {
    		var jsonCarelist = eval(data.CareprovBean);
            var careSelect = $("#careprovlist");
            careSelect.multiselect2side('destroy');
            careSelect.empty();
            for (var j = 0; j < jsonCarelist.length; j++) {
                careSelect.append("<option value='" + jsonCarelist[j].careprovid + "'>" + jsonCarelist[j].careprovid +"_"+ jsonCarelist[j].careprovname + "</option>");
            }
            $('#careprovlist').multiselect2side({
//			   search: "<img src='img/search.gif' />" //Search: 
            	
			});
        }
    });
            
}

function postloaddata(operId){
	operId = operId.substr(0,operId.indexOf("||"));
	$.ajax({
        type: "POST",
        url: GLOBAL.WEBROOT +"/basecode/getCareProvList.ajax?operator_id="+operId+"&orgId="+$("#orgId").val(),
        success: function (data) {
            //重新加载医护人员列表
			var notJoinCare = eval(data.NotJoinCare);
			var careSelect = $("#careprovlist");
            careSelect.multiselect2side('destroy');
            careSelect.empty();
            for (var i = 0; i < notJoinCare.length; i++) {
                careSelect.append("<option value='" + notJoinCare[i].careprovId + "'>" + notJoinCare[i].careprovId+"_"+ notJoinCare[i].careprovName + "</option>");
            }
            $('#careprovlist').multiselect2side({
			   //search: "<img src='img/search.gif' />" //Search: 
			});
			//加载已加入科室列表
			var jsonData = eval(data.NewCareList);
			var side__dx = $("#searchablecarems2side__dx");
            side__dx.multiselect2side('destroy');
            side__dx.empty();
            for (var k = 0; k < jsonData.length; k++) {
                side__dx.append("<option value='" + jsonData[k].careprovId + "'>" + jsonData[k].careprovId+"_"+ jsonData[k].careprovName + "</option>");
            }
        }
    });
}


function save() {
	var selectOption = $("#searchablecarems2side__dx option").map(function(){
		return $(this).val();
		}).get().join(",");
	
	var array = selectOption.split(",");
	var str = $("#operatorlist").val()==null?"":$("#operatorlist").val().toString();
	var arraystr = str.split(",");
	if(str==""){
			layer.alert('请选择操作人员！', {
		    skin: 'layui-layer-molv', //样式类名
		    closeBtn: 0,
		    icon: 5
			}); 
	}else if(arraystr.length>1){
			layer.alert('操作人员不允许多选！', {
		    skin: 'layui-layer-molv', //样式类名
		    closeBtn: 0,
		    icon: 5
			}); 
	}else if(array==""||array.length<1){
		layer.alert('请关联医护人员！', {
	    skin: 'layui-layer-molv', //样式类名
	    closeBtn: 0,
	    icon: 5
		}); 
	}else{
		$.ajax({
	        type: "POST",
	        url: GLOBAL.WEBROOT +"/basecode/saveAiscOperator2CareProv.ajax?operatorId="+str+"&careprovId="+selectOption,
			dataType: 'json',
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
		
}
