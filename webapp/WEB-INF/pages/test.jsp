<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.ai.common.util.FTPSUtil" %>
<%
FTPSUtil ftpUtil = new FTPSUtil();
try {
	ftpUtil.connect("10.63.90.226", 21, "ftp123", "Ftp_123*456AB");
	
	System.out.println("+++11111111111111111+++++++++++++++++++++++++++++:");
	ftpUtil.upload("/", "/home/devweb/apache-tomcat-7.0.55_8200/webapps/Aris/PACS_FILE/bbb.txt");
	System.out.println("+++22222222222222222+++++++++++++++++++++++++++++");
} catch (Exception e) {
	e.printStackTrace();
} 
%>