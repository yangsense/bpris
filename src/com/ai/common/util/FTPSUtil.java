package com.ai.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.sun.tools.doclets.internal.toolkit.util.DirectoryManager;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPFileFilter;
import org.apache.commons.net.ftp.FTPSClient;


public class FTPSUtil {
	private static Log log = LogFactory.getLog(FTPUtil.class);
	public FTPSClient ftpClient = null; 
	/**
		* 连接ftp服务器
		* @param host 主机
	* @param port 端口
	* @param username 用户名
	* @param password 密码
	* @return void
	* @throws IOException 
	* @throws SocketException 
	* @throws JSchException 
	*/
	public void connect(String host, int port, String username,String password) throws SocketException, IOException{
		String encoding="GBK";
		ftpClient=new FTPSClient(); 
        ftpClient.setDataTimeout(6000000);
        ftpClient.setConnectTimeout(6000000);
		ftpClient.connect(host,port); 
        ftpClient.login(username, password); 
        ftpClient.execPBSZ(0);
		ftpClient.execPROT("P");
        ftpClient.setBufferSize(10240); 
        ftpClient.setControlEncoding(encoding); 
        //设置文件类型（二进制） 
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE); 
        ftpClient.enterLocalPassiveMode();
        log.info("Connected to " + host + ":"+port+" username:"+username);
	}
	/**
	 * 释放资源
	 */
	public void closeChannel() throws Exception {
		if(ftpClient!=null){
			ftpClient.logout();
			ftpClient.disconnect();
		}
    }
	
	public void uploadPdfFile(String path,String fileName,InputStream io) throws IOException {
		File f=new File(fileName);
		this.makeDirectoryNoEnter(path);
		ftpClient.enterLocalPassiveMode();
		System.out.println(f.getName());
        ftpClient.storeFile(path+"/"+new String(f.getName().getBytes("UTF-8"),"iso-8859-1"), io); 
        IOUtils.closeQuietly(io);
	}

	/**
	* 上传文件
	* @param directory 上传的目录
	* @param uploadFile 要上传的文件
	* @param sftp
	 * @throws IOException 
	* @throws SftpException 
	* @throws FileNotFoundException 
	*/
	public void upload(String directory, String uploadFile) throws IOException {
		File f=new File(uploadFile);
		FileInputStream fis = new FileInputStream(f); 
		ftpClient.enterLocalPassiveMode();  //被动模式
		//ftpClient.enterLocalActiveMode(); //主动模式
		System.out.println("--------------------------------:"+directory+":"+fis);
        boolean b = ftpClient.storeFile(directory+"/"+f.getName(), fis); 
        System.out.println("++++++++++++++++++++++++++++++++:"+b);
        IOUtils.closeQuietly(fis);
	}
	/**
	* 上传文件
	* @param directory 上传的目录
	* @param uploadFile 要上传的文件
	* @param sftp
	 * @throws IOException 
	* @throws SftpException 
	* @throws FileNotFoundException 
	*/
	public void upload(String directory, String uploadFile,String remoteFileName) throws IOException {
		File f=new File(uploadFile);
		FileInputStream fis = new FileInputStream(f); 
		ftpClient.enterLocalPassiveMode();  //被动模式
		//ftpClient.enterLocalActiveMode(); //主动模式
		System.out.println("--------------------------------:"+directory+":"+remoteFileName+":"+fis);
        boolean b = ftpClient.storeFile(directory+"/"+remoteFileName, fis); 
        System.out.println("++++++++++++++++++++++++++++++++:"+b);
        IOUtils.closeQuietly(fis);
	}
	/**
	 * 下载文件
	 * @param directory 下载目录
	 * @param downloadFile 下载的文件
	 * @param saveFile 存在本地的路径
	 * @param sftp
	 * @throws IOException
	 * @throws SftpException
	 * @throws FileNotFoundException
	 */
	public void download(String directory, String downloadFile,String saveFile) throws IOException  {
		directory=directory.replaceAll("//", "/");
		saveFile=saveFile.replaceAll("//", "/");
		FileUtils.forceMkdir(new File(saveFile.substring(0, saveFile.lastIndexOf("/"))));
		FileOutputStream fos = new FileOutputStream(saveFile);
		ftpClient.enterLocalPassiveMode();
		ftpClient.retrieveFile(directory+downloadFile, fos);
		IOUtils.closeQuietly(fos);
	}


	/**
	 * 下载文件
	 * @param directory 下载目录
	 * @param downloadFile 下载的文件
	 * @param saveFile 存在本地的路径
	 * @param sftp
	 * @throws IOException
	 * @throws SftpException
	 * @throws FileNotFoundException
	 */
	public void downloadReport(String directory, String downloadFile,String saveFile) throws IOException  {
		directory=directory.replaceAll("//", "/");
		saveFile=saveFile.replaceAll("//", "/");
		FileUtils.forceMkdir(new File(saveFile));
		FileOutputStream fos = new FileOutputStream(saveFile+"/"+downloadFile);
		ftpClient.enterLocalPassiveMode();
		ftpClient.retrieveFile(directory+new String(downloadFile.getBytes("UTF-8"),"iso-8859-1"), fos);
		IOUtils.closeQuietly(fos);
	}

	/**
	* 移动文件
	* @param olddirectoryfile_name 要移动的文件目录和名字
	* @param newdirectoryfile_name 要移动的新文件目录和名字
	 * @throws IOException 
	 * @throws SftpException 
	*/
	public void rename(String olddirectoryfile_name,String newdirectoryfile_name) throws IOException  {
		olddirectoryfile_name=olddirectoryfile_name.replaceAll("//", "/");
		newdirectoryfile_name=newdirectoryfile_name.replaceAll("//", "/");
		this.makeDirectory(newdirectoryfile_name.substring(0, newdirectoryfile_name.lastIndexOf("/")));
		ftpClient.enterLocalPassiveMode();
		ftpClient.rename(olddirectoryfile_name,newdirectoryfile_name);
	}
	/**
	* 移动文件
	* @param olddirectoryfile_name 要移动的文件目录和名字
	* @param newdirectoryfile_name 要移动的新文件目录和名字
	 * @throws IOException 
	 * @throws SftpException 
	*/
	public void renameWithOutDir(String olddirectoryfile_name,String newdirectoryfile_name) throws IOException  {
		ftpClient.dele(newdirectoryfile_name);
		ftpClient.rename(olddirectoryfile_name,newdirectoryfile_name);
	}
	/**
	* 删除文件
	* @param directory 要删除文件所在目录
	* @param deleteFile 要删除的文件
	 * @throws IOException 
	 * @throws SftpException 
	*/
	public void delete(String directory, String deleteFile) throws IOException{
		directory=directory.replaceAll("//", "/");
		ftpClient.enterLocalPassiveMode();
		ftpClient.remoteStore(directory+"/"+deleteFile);
	}
	/**
	* 删除文件
	* @param directory 要删除文件所在目录
	* @param deleteFile 要删除的文件
	 * @throws IOException 
	 * @throws SftpException 
	*/
	public void delete(String pathname) throws IOException{
		ftpClient.dele(pathname);
	}
	public FTPFile[] listFiles(String directory) throws IOException{
		directory=directory.replaceAll("//", "/");
		ftpClient.enterLocalPassiveMode();
		FTPFile[] files = ftpClient.listFiles(directory,filefilter);
		return files;
	}
	FTPFileFilter filefilter = new FTPFileFilter() {
		@Override
		public boolean accept(FTPFile file) {
//			if (file.getName().endsWith(".xml")) {
//                return true;
//            }
//            return false;
			return true;
		}
    };
    public FTPFile[] listFilesPDF(String directory) throws IOException{
		directory=directory.replaceAll("//", "/");
		ftpClient.enterLocalPassiveMode();
		FTPFile[] files = ftpClient.listFiles(directory,filefilterpdf);
		return files;
	}
	FTPFileFilter filefilterpdf = new FTPFileFilter() {
		@Override
		public boolean accept(FTPFile file) {
			if (file.getName().endsWith(".pdf")) {
                return true;
            }
            return false;
		}
    };
    public FTPFile[] listFilesAll(String directory) throws IOException{
		directory=directory.replaceAll("//", "/");
		ftpClient.enterLocalPassiveMode();
		FTPFile[] files = ftpClient.listFiles(directory);
		return files;
	}
	public Long getFileSize(String directoryFile) throws IOException  {
		Long fileSize=0L;
		ftpClient.enterLocalPassiveMode();
		FTPFile[] files = ftpClient.listFiles(directoryFile);
		for (FTPFile file : files){
			fileSize= file.getSize();
		}
		return fileSize;
	}
    public FTPFile[] listFilesBySuffix(String directory,final String suffix) throws IOException{
		directory=directory.replaceAll("//", "/");
		ftpClient.enterLocalPassiveMode();
		FTPFile[] files = ftpClient.listFiles(directory,new FTPFileFilter() {
			@Override
			public boolean accept(FTPFile file) {
				if (file.getName().endsWith("."+suffix) || "".equals(suffix) && !file.getName().contains(".")) {
	                return true;
	            }
	            return false;
			}
	    });
		return files;
	}
	public String getFileTime(String directoryFile) throws IOException {
		String fileTime="";
		ftpClient.enterLocalPassiveMode();
		FTPFile[] files = ftpClient.listFiles(directoryFile);
		SimpleDateFormat from = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (FTPFile file : files){
            fileTime= from.format(file.getTimestamp().getTime());
		}
		return fileTime;
	}
	
	/**
	 * ftp创建文件目录
	 * @param 
	 * @return boolean
	 * @throws
	 */
	public boolean makeDirectory(String dir)throws IOException  {  
	     boolean flag = true;  
	     //检查文件夹是否存在
	     ftpClient.enterLocalPassiveMode();
	     if (!ftpClient.changeWorkingDirectory(dir)){
	    	 flag = ftpClient.makeDirectory(dir);   
	     }
	     return flag;  
	}  
	public void makeDirectoryNoEnter(String dir)throws IOException  {  
		ftpClient.makeDirectory(dir); 
	} 

	public static void main(String[] args){
		String config_org_id,dict_table_id,dict_file_id,file_name,file_chn_name;
		String ftp_type,host, username, password, olddirectory, newdirectory, localPath="";
		int port;
		//etl_log_file 日志字段 month,config_org_id,dict_file_id,local_path,file_name,file_chn_name,file_size,file_uploadtime,table_time,table_num,table_cnt,table_cnt_succ,table_cnt_err,exec_status,error_code,error_msg
		String exec_status,error_code,error_msg,file_size="",file_uploadtime="";
		
//		olddirectory="/smartmedical/shanxi/xianyang/data/fyt/";
		olddirectory="/";
		file_name="zy_brjsmxk_20150311.xml";
		localPath="/home/devweb/apache-tomcat-7.0.55/webapps/etl_smartmedical/WEB-INF/application/";
		
//		host="10.63.24.50";
//		port=21;
//		username="fyt";
//		password="fyt";
		
		host="10.63.83.25";
		port=21;
		username="SmartMedical";
		password="SmartMedical";
		try {
			FTPUtil sf = new FTPUtil(); 
			sf.connect(host, port, username, password);
//			file_size=String.valueOf(sf.getFileSize(olddirectory+file_name));
//			file_uploadtime=sf.getFileTime(olddirectory+file_name);
//			System.out.println(">>>file_size>>"+file_size);
//			System.out.println(">>>file_uploadtime>>"+file_uploadtime);
//			sf.download(olddirectory, file_name, localPath+file_name);
//			FTPFile[] files = sf.listFilesAll(olddirectory);			
//			for (FTPFile file : files){
//				System.out.println(file.getName()+"********"+file.getSize()+"***");
				sf.download(olddirectory,"/00000182861/hisb60301_1_00000182861.xml", "c:\\/00000182861/hisb60301_1_00000182861.xml");
//				sf.rename(olddirectory+"00000182861/hisb60301_1_00000182861.xml", "/bak/20150728/00000182861/hisb60301_1_00000182861.xml");
//			}
//			sf.makeDirectory("");
			
//			FTPClient ftpClient=new FTPClient();
//			ftpClient.connect(host,port);
//	        ftpClient.setBufferSize(1024); 
//	        ftpClient.setControlEncoding("UTF-8"); 
//	        ftpClient.setDefaultTimeout(3000 * 1000);
//	        ftpClient.setConnectTimeout(3000 * 1000);
//	        ftpClient.setDataTimeout(3000 * 1000);
//	        log.info("Connected to " + host + ":"+port+" username:"+username);
//	        int reply = ftpClient.getReplyCode();
//	        if(!FTPReply.isPositiveCompletion(reply)){
//	        	ftpClient.disconnect();
//	        }
//	        if(!ftpClient.login(username, password)){
//	        	ftpClient.logout();
//	        }
	        
//	        ftpClient.changeWorkingDirectory("/");
//	        System.out.println(FTPReply.isPositiveCompletion(reply)+">>reply>>>>"+reply);
//	        FileOutputStream fos = new FileOutputStream(localPath+file_name); 
//	        ftpClient.retrieveFile(olddirectory+file_name, fos); 
//	        IOUtils.closeQuietly(fos);
	        
//	        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
//	        ftpClient.enterLocalPassiveMode();
////	        ftpClient.setControlEncoding("GBK");
//	        String temp_name = new String(file_name.getBytes(), ftpClient.getControlEncoding());
//	        FTPFile[] files = ftpClient.listFiles("/HISB60301.xml");
//	        System.out.println("============="+files.length);
//	        SimpleDateFormat from = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			for (FTPFile file : files){
//				System.out.println(file.getName()+"********"+file.getSize()+"***"+from.format(file.getTimestamp().getTime()));
//			}
//			System.out.println("=============");
//			ftpClient.logout();
//			ftpClient.disconnect();
			sf.closeChannel();
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
}
