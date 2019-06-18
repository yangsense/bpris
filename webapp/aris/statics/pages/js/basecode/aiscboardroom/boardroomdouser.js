$(function () {
	selectDataset();
	postloaddata("-1");
	$("#closeBtn").click(function(){
       closeDiv();
    });

	$("#saveBtn").click(function(){
		save();
	});


});

function selectDataset(){
    $.ajax({
        type: "POST",
        async: true,
        url: GLOBAL.WEBROOT + "/basecode/getBoardroomList",
        dataType: 'json',
        success: function (data) {
        	var jsonroomlist = eval(data.roomlist);
        	var roomSelect = $("#roomlist");
            roomSelect.empty();
            for (var k = 0; k < jsonroomlist.length; k++) {
            	roomSelect.append("<option value='" + jsonroomlist[k].boardroomid + "'>" + jsonroomlist[k].boardroomid+"_"+ jsonroomlist[k].boardroomname + "</option>");
            }
        }
    });
    var data = $("#searchFormSe").serializeObject();
    $.ajax({
        type: "POST",
        async: true,
        url: GLOBAL.WEBROOT + "/basecode/getBoardroomList",
        dataType: 'json',
        data:data,
        success: function (data) {
        	//操作人员
	 		var jsonOperatorlist = eval(data);
            var operSelect = $("#operatorlist");
            operSelect.multiselect2side('destroy');
            operSelect.empty();
            for (var i = 0; i < jsonOperatorlist.length; i++) {
                operSelect.append("<option value='" + jsonOperatorlist[i].operatorId+"||"+$("#orgIdLoc").val() + "'>" +jsonOperatorlist[i].operatorId+"_" +jsonOperatorlist[i].operatorCode +"_"+jsonOperatorlist[i].operatorName+"</option>");
            }
            $('#operatorlist').multiselect2side({
 			   //search: "<img src='img/search.gif' />" //Search: 
             	
 			});
        }
    });
}

function closeDiv(){
	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}

function postloaddata(roomId){
	//查询会议室已授权人员列表
	$.ajax({
        type: "POST",
        url: GLOBAL.WEBROOT +"/basecode/getInBoardroom.ajax?roomId="+roomId,
        success: function (data) {
            //加载已授权列表
			var userlist = eval(data.userlist);
			var notuserlist = eval(data.notuserlist);
			var oldUserSelect = $("#operatorlist");
            oldUserSelect.multiselect2side('destroy');
            oldUserSelect.empty();
            if(notuserlist){
	            for (var i = 0; i < notuserlist.length; i++) {
	            	oldUserSelect.append("<option value='" + notuserlist[i].userid + "||" + notuserlist[i].usercode + "||" + notuserlist[i].username + "'>" + notuserlist[i].usercode+"_"+ notuserlist[i].username + "</option>");
	            }
	            $('#operatorlist').multiselect2side({
				   //search: "<img src='img/search.gif' />" //Search: 
				});
            }
			//加载已授权列表
			var jsonData = eval(data.userlist);
			var side__dx = $("#searchablems2side__dx");
            side__dx.multiselect2side('destroy');
            side__dx.empty();
            if(jsonData){
	            for (var k = 0; k < jsonData.length; k++) {
	                side__dx.append("<option value='" + jsonData[k].userid +"||"+jsonData[k].usercode + "||"+jsonData[k].username+"'>"+jsonData[k].usercode +"_"+ jsonData[k].username + "</option>");
	            }
            }
        }
    });
}

function save() {
	var selectOption = $("#searchablems2side__dx option").map(function(){
		return $(this).val();
		}).get().join(",");
	var array = selectOption.split(",");
	var str = $("#roomlist").val()==null?"":$("#roomlist").val().toString();
	var arraystr = str.split(",");
	if(str==""){
			layer.alert('请选择会议室！', {
		    skin: 'layui-layer-molv', //样式类名
		    closeBtn: 0,
		    icon: 5
			}); 
	}else if(arraystr.length>1){
			layer.alert('会议室不允许多选！', {
		    skin: 'layui-layer-molv', //样式类名
		    closeBtn: 0,
		    icon: 5
			}); 
	}else if(selectOption==""||array.length<1){
		layer.confirm('是否清空该会议室下的所有人员授权？', {
            btn: ['确认', '取消'],
		}, function(){
            saveHstInfo(str,selectOption);
		});
		/*layer.alert('请授权会议人员！', {
		    skin: 'layui-layer-molv', //样式类名
		    closeBtn: 0,
		    icon: 5
			});*/
	}else{
        saveHstInfo(str,selectOption);
	}
}


function saveHstInfo(str,selectOption){
    $('#saveBtn').addClass('disabled');
    $.ajax({
        type: "POST",
        url: GLOBAL.WEBROOT +"/basecode/saveDouser?roomId="+str+"&selectOption="+selectOption,
        dataType: 'json',
        success: function (data) {
            if (data.ERRCODE == "0") {
                layer.msg('操作成功', {icon: 1});
                $('#saveBtn').removeClass('disabled');
                postloaddata(str);
            }
            else {
                layer.msg('操作失败,请联系管理员。错误信息:'+data.ERRMSG, {icon: 2});
                $('#saveBtn').removeClass('disabled');
                postloaddata(str);
            }
        }
    });
}
