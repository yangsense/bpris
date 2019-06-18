$(function () {
	initDict();
	
	$("#closeBtn").click(function(){
       closeDiv();
    });
	
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
	        selectDataset();
		}
	});  
}
function selectDataset(){
    $.ajax({
        type: "POST",
        async: true,
        url: GLOBAL.WEBROOT + "/basecode/getAiscLocName.ajax?orgId="+$("#orgIdLoc").val(),
        dataType: 'json',
        success: function (data) {
        	 var jsonaiscLocName = eval(data.AiscLocName);
             var loc_nameSelect = $("#loclist");
             loc_nameSelect.empty();
             for (var i = 0; i < jsonaiscLocName.length; i++) {
                 loc_nameSelect.append("<option value='" + jsonaiscLocName[i].locid + "||"+$("#orgIdLoc").val()+"'>" + jsonaiscLocName[i].locdesc + "</option>");
             }
        }
    });
    $.ajax({
        type: "POST",
        async: true,
        url: GLOBAL.WEBROOT + "/basecode/getStudyStatus.ajax",
        dataType: 'json',
        success: function (data) {
    		//科室列表
            var jsonstatuslist = eval(data.STUDY_STATUS);
            var statusSelect = $("#statuslist");
            statusSelect.multiselect2side('destroy');
            statusSelect.empty();
            for (var k = 0; k < jsonstatuslist.length; k++) {
            	statusSelect.append("<option value='" + jsonstatuslist[k].itemno+"||"+jsonstatuslist[k].itemname+ "'>" + jsonstatuslist[k].itemno+"_"+ jsonstatuslist[k].itemname + "</option>");
            }
            $('#statuslist').multiselect2side({
			   //search: "<img src='img/search.gif' />" //Search: 
            	
			});
        }
    });
}

function closeDiv(){
	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}

function postloaddata(locId){
	var newlocId = locId.substr(0,locId.indexOf("||"));
	var orgd = locId.substr(locId.indexOf("||")+2);
	$.ajax({
        type: "POST",
        url: GLOBAL.WEBROOT +"/basecode/getLocStatusList.ajax?locId="+newlocId+"&orgId="+orgd,
        success: function (data) {
            //重新加载检查状态列表
        	var notJoinStatus = eval(data.NotJoinStatus);
			var oldStatusSelect = $("#statuslist");
            oldStatusSelect.multiselect2side('destroy');
            oldStatusSelect.empty();
            for (var i = 0; i < notJoinStatus.length; i++) {
                oldStatusSelect.append("<option value='"+ notJoinStatus[i].studyStatus+"||"+notJoinStatus[i].statusName+"'>" + notJoinStatus[i].studyStatus+"_"+ notJoinStatus[i].statusName + "</option>");
            }
            $('#statuslist').multiselect2side({
			   //search: "<img src='img/search.gif' />" //Search: 
			});
			//加载已加入科室列表
			var jsonData = eval(data.newStatusList);
			var side__dx = $("#searchablems2side__dx");
            side__dx.multiselect2side('destroy');
            side__dx.empty();
            for (var k = 0; k < jsonData.length; k++) {
                side__dx.append("<option value='"+ jsonData[k].studyStatus+"||"+jsonData[k].statusName+"'>" + jsonData[k].studyStatus+"_"+ jsonData[k].statusName + "</option>");
            }
        }
    });
}

function save() {
	var selectOption = $("#searchablems2side__dx option").map(function(){
		return $(this).val();
		}).get().join(",");
	var array = selectOption.split(",");
	var str = $("#loclist").val()==null?"":$("#loclist").val().toString();
	var arraystr = str.split(",");
	if(str==""){
			layer.alert('请选择科室！', {
		    skin: 'layui-layer-molv', //样式类名
		    closeBtn: 0,
		    icon: 5
			}); 
	}else if(arraystr.length>1){
			layer.alert('科室不允许多选！', {
		    skin: 'layui-layer-molv', //样式类名
		    closeBtn: 0,
		    icon: 5
			}); 
	}else if(selectOption==""||array.length<1){
		layer.alert('请关联检查状态！', {
	    skin: 'layui-layer-molv', //样式类名
	    closeBtn: 0,
	    icon: 5
		}); 
	}else{
		var orgId = $("#orgIdLoc").val();
		$.ajax({
	        type: "POST",
	        url: GLOBAL.WEBROOT +"/basecode/saveAiscStatusLoc?locId="+str+"&orgId="+orgId+"&selectOption="+encodeURI(encodeURI(selectOption)),
			dataType: 'json',
	        success: function (data) {
	             if (data.ERRCODE == "0") {
	            	layer.msg('操作成功', {icon: 1});
	                parent.reloadGrid();
	                setTimeout(closeDiv,1500);
	             }
	             else {
	                layer.msg('操作失败', {icon: 1});
	             }
	        }
	    });
		
	}
}
