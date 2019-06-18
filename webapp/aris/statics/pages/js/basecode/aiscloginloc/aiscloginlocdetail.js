$(function () {
	initDict();
	
	$("#closeBtn").click(function(){
       closeDiv();
    });
	
	var type = $("#aiscLoginLocForm").attr("dataType");
	if(type=="query"){
		com.ai.bdx.util.disableAll("aiscLoginLocForm");
		$("#saveBtn").hide();
	}else if(type=="update"){
		$("#showText").text("修 改");
	}else{
		com.ai.bdx.util.reset("aiscLoginLocForm");
		$("#showText").text("保 存");
	}

	$("#saveBtn").click(function(){
		save();
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
	        var orgIdLocSelect = $("#orgIdLoc");
	        orgIdLocSelect.empty(); 
	        orgSelect.empty(); 
	        for (var i = 0; i < sysOrg.length; i++) {
	        	orgSelect.append("<option value='" + sysOrg[i].orgId + "'>" + sysOrg[i].orgName + "</option>");
	        	orgIdLocSelect.append("<option value='" + sysOrg[i].orgId + "'>" + sysOrg[i].orgName + "</option>");
	        }
	        orgSelect.val(parent.parent.$("#currentOrgId").val()==""?"-1":parent.parent.$("#currentOrgId").val());
	        orgSelect.select2();  
	        
	        orgIdLocSelect.val(parent.parent.$("#currentOrgId").val()==""?"-1":parent.parent.$("#currentOrgId").val());
	        orgIdLocSelect.select2();  
//	        orgIdInit = $("#orgId").val();
	        selectDataset();
		}
	});  
}
function selectDataset(){
//	var id = $("#orgId").val()==null?orgIdInit:$("#orgId").val();
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
                operSelect.append("<option value='" + jsonOperatorlist[i].operatorId+"||"+$("#orgIdLoc").val() + "'>" +jsonOperatorlist[i].operatorId+"_" +jsonOperatorlist[i].operatorCode +"_"+jsonOperatorlist[i].operatorName+"</option>");
            }
        }
    });
    var data = $("#aiscLoginLocForm").serializeObject();
    data.orgId = $("#orgIdLoc").val();
    $.ajax({
        type: "POST",
        async: true,
        url: GLOBAL.WEBROOT + "/basecode/selectDataset",
        dataType: 'json',
        data:data,
        success: function (data) {
    		//科室列表
            var jsonLoclist = eval(data.AiscLoc);
            var locSelect = $("#loclist");
            locSelect.multiselect2side('destroy');
            locSelect.empty();
            for (var k = 0; k < jsonLoclist.length; k++) {
                locSelect.append("<option value='" + jsonLoclist[k].locid + "'>" + jsonLoclist[k].locid+"_"+ jsonLoclist[k].locdesc + "</option>");
            }
            $('#loclist').multiselect2side({
			   //search: "<img src='img/search.gif' />" //Search: 
            	
			});
        }
    });
}

function closeDiv(){
	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}

function postloaddata(operId){
	var userId = operId.substr(0,operId.indexOf("||"));
	var orgLocId = operId.substr(operId.indexOf("||")+2);
	$.ajax({
        type: "POST",
        url: GLOBAL.WEBROOT +"/basecode/getLocList.ajax?operator_id="+userId+"&orgId="+orgLocId,
        success: function (data) {
            //重新加载科室列表
			var notJoinLoc = eval(data.NotJoinLoc);
			var oldlocSelect = $("#loclist");
            oldlocSelect.multiselect2side('destroy');
            oldlocSelect.empty();
            for (var i = 0; i < notJoinLoc.length; i++) {
                oldlocSelect.append("<option value='" + notJoinLoc[i].locId + "'>" + notJoinLoc[i].locId+"_"+ notJoinLoc[i].locDesc + "</option>");
            }
            $('#loclist').multiselect2side({
			   //search: "<img src='img/search.gif' />" //Search: 
			});
			//加载已加入科室列表
			var jsonData = eval(data.NewLocList);
			var side__dx = $("#searchablems2side__dx");
            side__dx.multiselect2side('destroy');
            side__dx.empty();
            for (var k = 0; k < jsonData.length; k++) {
                side__dx.append("<option value='" + jsonData[k].locId + "'>" + jsonData[k].locId+"_"+ jsonData[k].locDesc + "</option>");
            }
        }
    });
}

function save() {
	var selectOption = $("#searchablems2side__dx option").map(function(){
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
	}else if(selectOption==""||array.length<1){
		layer.alert('请关联科室！', {
	    skin: 'layui-layer-molv', //样式类名
	    closeBtn: 0,
	    icon: 5
		}); 
	}else{
		var orgId = $("#orgIdLoc").val();
		$.ajax({
	        type: "POST",
	        url: GLOBAL.WEBROOT +"/basecode/saveAiscLoginLoc?operatorId="+str+"&orgId="+orgId+"&selectOption="+selectOption,
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
