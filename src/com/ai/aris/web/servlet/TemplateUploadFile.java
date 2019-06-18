package com.ai.aris.web.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.ai.appframe2.service.ServiceFactory;
import com.ai.aris.server.workstation.model.TemplateContent;
import com.ai.aris.server.workstation.service.interfaces.IStudyReportSV;

/**
 * 模板上传
 */
@SuppressWarnings("serial")
public class TemplateUploadFile extends HttpServlet {
	/**
	 * 文件上传到的目录
	 */
	File uploadDirectoryFile;
	/**
	 * 临时文件目录
	 */
	File tempDirectoryFile;

	private long requestSizeRequestSize=1024*3000;
	private int maxMemorySize=1024*10;
	String locId = "";
	String importType = "";
	String orgId = "";
	String operatorId = "0";
	
	 private static IStudyReportSV sv = (IStudyReportSV) ServiceFactory.getService(IStudyReportSV.class);
	
	public void init() throws ServletException {
		String nativeWebAppFoldPath=this.getServletContext().getRealPath("/");
		String tempDirectory=this.getInitParameter("TempDirectory");
		tempDirectoryFile=new File(nativeWebAppFoldPath+tempDirectory);
		String uploadDirectory=this.getInitParameter("UploadDirectory");
		uploadDirectoryFile=new File(nativeWebAppFoldPath+uploadDirectory);		
		//文件夹不存在就自动创建
		if(!tempDirectoryFile.exists())tempDirectoryFile.mkdirs();
		if(!uploadDirectoryFile.exists())uploadDirectoryFile.mkdirs();
		try {
			requestSizeRequestSize=Long.parseLong(this.getInitParameter("RequestSizeRequestSize"));
			maxMemorySize = Integer.parseInt(this.getInitParameter("MaxMemorySize"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
//		System.out.println(request.getParameter("importType"));
		File uploadedFile = processFileUpload(request);
		//以便setattrubit可以设置和读取
		//request.getRequestDispatcher("Commons_FileUpload/resultFile.jsp").forward(request,response);
		//response.sendRedirect("newInfo.jsp");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String str="文件内容：";
		if(uploadedFile.exists()) 
	    { 
			try {
				str+=readExcel(uploadedFile,importType,locId,orgId,operatorId);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			uploadedFile.delete();
	        out.println("<script>parent.callback('success');</script>"); 
	    }else 
	    { 
//	        out.println("<script>try{parent.callback('upload file error');}catch(e){};</script>"); 
	    } 
		out.flush();
		out.close();
	}

	public static String readExcel(File file,String importType,String locId,String orgId,String operatorId) throws Exception {
		StringBuffer buf=new StringBuffer("");
		try {
			Workbook book = Workbook.getWorkbook(file);
			Sheet sheet = book.getSheet(0);
			int rownum = sheet.getRows(); 
//			int colnum = sheet.getColumns();
			long templatedir_flag = importType.equals("public")?1:2;
			if (rownum != 0) {
				for (int j = 1; j < rownum; j++) {
					Cell [] cellrow = sheet.getRow(j);
					System.out.println(cellrow[0].getContents());
					System.out.println(cellrow[1].getContents());
					System.out.println(cellrow[2].getContents());
					
					int count = sv.isHaveDir(cellrow[0].getContents(),templatedir_flag,orgId,0);
					
//					int numContent = isHaveContent(cellrow[2].getContents());
					//插入目录
					try {
						long id = sv.getNodeIndex();
						if(importType.equals("public")){
							//父目录+++
							if(count<1){
								sv.saveNode(id, -1, cellrow[0].getContents(), Long.parseLong(locId), Long.parseLong(orgId), 1,0,1,cellrow[6].getContents());
							}else{
								id = sv.getNodeId(cellrow[0].getContents(),templatedir_flag,orgId);
							}
							int num = sv.isHaveDir(cellrow[1].getContents(),templatedir_flag,orgId,id);
							//子目录+++
							long idNext = sv.getNodeIndex();
							if(num<1){
								sv.saveNode(idNext,id, cellrow[1].getContents(), Long.parseLong(locId), Long.parseLong(orgId), 1,0,1,cellrow[6].getContents());
							}else{
								idNext = sv.getNodeId(cellrow[1].getContents(),templatedir_flag,orgId);
							}
							//子节点+++
							long idNextNext = sv.getNodeIndex();
							sv.saveNode(idNextNext,idNext, cellrow[2].getContents(), Long.parseLong(locId), Long.parseLong(orgId), 0,0,1,cellrow[6].getContents());
							//模板内容+++
							TemplateContent content = new TemplateContent();
							content.setTemplatecontentId(null);
							content.setTemplatedirId(idNextNext);
							content.setTemplateName(cellrow[2].getContents());
							content.setTemplateMethod(cellrow[3].getContents());
							content.setTemplateExam(cellrow[4].getContents());
							content.setTemplateResult(cellrow[5].getContents());
							content.setTemplateFq(Long.parseLong("0"));
							sv.saveTemplateDetail(content);
							
						}else{
							//父目录+++
							if(count<1){
								sv.saveNode(id, -2, cellrow[0].getContents(), Long.parseLong(locId), Long.parseLong(orgId), 1,Long.parseLong(operatorId),2,cellrow[6].getContents());
							}else{
								id = sv.getNodeId(cellrow[0].getContents(),templatedir_flag,orgId);
							}
							int num = sv.isHaveDir(cellrow[1].getContents(),templatedir_flag,orgId,id);
							//子目录+++
							long idNext = sv.getNodeIndex();
							if(num<1){
								sv.saveNode(idNext,id, cellrow[1].getContents(), Long.parseLong(locId), Long.parseLong(orgId), 1,Long.parseLong(operatorId),2,cellrow[6].getContents());
							}else{
								idNext = sv.getNodeId(cellrow[1].getContents(),templatedir_flag,orgId);
							}
							//子节点+++
							long idNextNext = sv.getNodeIndex();
							sv.saveNode(idNextNext,idNext, cellrow[2].getContents(), Long.parseLong(locId), Long.parseLong(orgId), 0,Long.parseLong(operatorId),2,cellrow[6].getContents());
							//模板内容+++
							TemplateContent content = new TemplateContent();
							content.setTemplatecontentId(null);
							content.setTemplatedirId(idNextNext);
							content.setTemplateName(cellrow[2].getContents());
							content.setTemplateMethod(cellrow[3].getContents());
							content.setTemplateExam(cellrow[4].getContents());
							content.setTemplateResult(cellrow[5].getContents());
							content.setTemplateFq(Long.parseLong("0"));
							sv.saveTemplateDetail(content);
						}
						
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
//					 (cellrow[0].getContents());
					
					
//					for (int i = 0; i < colnum; i++){
//						Cell cell = sheet.getCell(i, j);
//						String content = cell.getContents();
//						buf.append(content+",");
//						System.out.println("(" + i + "," +j + ")="
//								+ content);
//					}	
//					buf.append("\\r\\n");
				}
			}
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IndexOutOfBoundsException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return buf.toString();
	}
	

	public File processFileUpload(HttpServletRequest request){
		File uploadedFile = null;
		//Check that we have a file upload request
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if(isMultipart){
			//1.-------------------------------------------------------
			//Create a factory for disk-based file items
			DiskFileItemFactory factory = new DiskFileItemFactory();
			//Set factory constraints
			factory.setSizeThreshold(maxMemorySize);
			factory.setRepository(this.tempDirectoryFile);	
			//Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);
			//Set overall request size constraint
			upload.setSizeMax(requestSizeRequestSize);
			
			//2.-------------------------------------------------------
			//Parse the request
			try {
				List items = upload.parseRequest(request);
				Iterator iter = items.iterator();
				while(iter.hasNext()){
					FileItem item = (FileItem) iter.next();
					if (!item.isFormField()) {
						//了解上传的文件
						String fileName = item.getName();
						fileName=new File(fileName).getName();
						System.out.println("recevied file,name:"+fileName);
						
						//将上传的文件保留下来【注意放病毒程序可能会在一下载完程序的时候就检查病毒并删除掉疑为病毒的文件从而导致问题，那样的话需要
						//将文件保存的目录设置为不自动检查病毒的】    
						uploadedFile = new File(this.uploadDirectoryFile,fileName);
						try {
							item.write(uploadedFile);
						} catch (Exception e) {
							e.printStackTrace();
						}
						break;
					}else {
			              try {
			                  if("importType".equals(item.getFieldName())) {
			                	  importType = item.getString("gb2312").trim();
			                  }
			                  if("locId".equals(item.getFieldName())) {
			                	  locId = item.getString("gb2312").trim();
			                  }
			                  if("orgId".equals(item.getFieldName())) {
			                	  orgId = item.getString("gb2312").trim();
			                  }
			                  if("operatorId".equals(item.getFieldName())) {
			                	  operatorId = item.getString("gb2312").trim();
			                  }
			              }catch (NumberFormatException e) {
			                  e.printStackTrace();
			              } catch (UnsupportedEncodingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			           }
				}
			} catch (FileUploadException e) {
				e.printStackTrace();
			}
		}
		
		return uploadedFile;
	}


}
