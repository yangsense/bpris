package com.ai.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 
 * @Description: 文件共用类
 * 
 * @date 2015-5-9 上午11:47:59 
 *
 */
public class FileUtil {
	
	/**
	 * 创建目录
	 * 
	 * @return
	 * @throws
	 */
	public static boolean makeDirs(String path){
		boolean flag = true;
		try {
			File file = new File(path);
			if(!file.exists()){
				flag = file.mkdirs();
			}
		} catch (Exception e) {
			
		}
		return flag;
	}
	
	/**
	 * 判断文件是否存在
	 * 
	 * @return
	 * @throws
	 */
	public static boolean checkFileExist(String path){
		File file = new File(path);
		return file.exists();
	}
	
	/**
	 * 复制文件
	 * @param 
	 * @return boolean
	 * @throws
	 */
	public static boolean copyFile(String newFile, String oldFile){
		boolean flag = false;
		File oFile = new File(oldFile);
		if(!oFile.exists()){
			flag = true;
		}
		File nFile = new File(newFile);
		if (nFile.exists()) {
			 new File(newFile).delete();
		}
		int byteread = 0; // 读取的字节数
		InputStream in = null;
		OutputStream out = null;  
		try {  
			in = new FileInputStream(oldFile);  
			out = new FileOutputStream(newFile);  
			byte[] buffer = new byte[1024];  
			while ((byteread = in.read(buffer)) != -1) {  
			   out.write(buffer, 0, byteread);  
			}
			flag =  true;
		} catch (Exception e) { 
		} finally { 
			if(out != null){
				try {
					out.close();
				} catch (IOException e) {
				}
			}
			if(in != null){
				try {
					in.close();
				} catch (IOException e) {
				}  
			}
		} 
		return flag;
	}
	
	/**
	 * 删除文件
	 * @param path
	 * @return void
	 * @throws
	 */
	public static void deleteFile(String path){
		File file = new File(path);  
		if (file.isFile() && file.exists()) {
			file.delete();
		} 
	}
	
	public static void main(String[] args) {
		copyFile("E:/新建文件夹.zip", "D:/新建文件夹.zip");
	}
}
