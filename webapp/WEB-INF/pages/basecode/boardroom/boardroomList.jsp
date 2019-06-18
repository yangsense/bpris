<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns=" http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="/common/meta.jsp" %>
</head>
<script>

$(function () {
	$("#closeBtn").click(function(){
       closeDiv();
    }); 
});
 
function closeDiv(){
	var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}

function forwardBoardroom(roomId,userCode,userPwd,url){
	window.open("http://"+url+"/launch/toEnterMeeting.do?roomID="+roomId+"&userName="+userCode+"&userPwd="+userPwd);
}
</script>
 <body class="body">
<div class="main-content">
    <div class="page-content min_style">
        <div class="row">
            <div class="col-xs-12 col-sm-12 col-md-12">
                <form class="form-horizontal" role="form" >
                	<input name="userCode" id="userCode" type="hidden" value="${userCode}"/>
                    <table width="100%" border="1" cellspacing="0" cellpadding="0">
                    <tr>
						<th>会议室ID</th>
						<th>会议室名称</th>
						<th>最大参会人数</th>
						<th>当前用户数</th>
						<th>会议室类型</th>                    
                    </tr>
					<c:forEach items="${rooms}" var="room">
						<tr>
							<td>${room.roomId }</td>
							<td><a href="javascript:void(0)" onclick="forwardBoardroom(${room.roomId },'${userCode}','${userPwd}','${Url}')">${room.roomName }</a></td>
							<td>${room.maxUserCount }</td>
							<td>${room.curUserCount }</td>
							<td><c:if test="${room.roomType==1}">固定会议室</c:if><c:if test="${room.roomType==2}">预约会议室</c:if>
								<c:if test="${room.roomType==4}">周例会议室</c:if>
							</td>
						</tr>
					</c:forEach>
                    </table>              
                    <div class="clearfix form-actions">
                        <div class="col-bt-center">                            
                            <button class="btn btn-info" type="button" id="closeBtn">
                                <i class="ace-icon fa fa-check bigger-110"></i>
                             	   关  闭
                            </button>
                        </div>
                    </div>
                </form>
			</div>
		</div>	
	</div>
</div>
 </body>
</html>
