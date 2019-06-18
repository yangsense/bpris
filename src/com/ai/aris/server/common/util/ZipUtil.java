package com.ai.aris.server.common.util;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;

import sun.misc.BASE64Decoder;

public class ZipUtil {
    
    /**
     * Base64解码
     * @param source
     * @return
     */
    private static byte[] decodeBase64(String source) {
            return Base64.decodeBase64(source);
    }

    /**
     * base64编码
     * @param source
     * @return
     */
    private static byte[] encodeBase64(byte[] source) {
            return Base64.encodeBase64(source);
    }

    /***
     * 压缩Zip
     * @param data
     * @return
     * @throws IOException
     */
    private static byte[] zip(byte[] bContent) {

            byte[] b = null;
            try {
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    ZipOutputStream zip = new ZipOutputStream(bos);
                    ZipEntry entry = new ZipEntry("zip");
                    entry.setSize(bContent.length);
                    zip.putNextEntry(entry);
                    zip.write(bContent);
                    zip.closeEntry();
                    zip.close();
                    b = bos.toByteArray();
                    bos.close();
            } catch (Exception ex) {
                    ex.printStackTrace();
            }
            return b;
    }

    /***
     * 解压Zip
     * @param data
     * @return
     * @throws IOException
     */
    private static byte[] unZip(byte[] bContent) {
            byte[] b = null;
            try {
                    ByteArrayInputStream bis = new ByteArrayInputStream(bContent);
                    ZipInputStream zip = new ZipInputStream(bis);
                    while (zip.getNextEntry() != null) {
                            byte[] buf = new byte[1024];
                            int num = -1;
                            ByteArrayOutputStream baos = new ByteArrayOutputStream();
                            while ((num = zip.read(buf, 0, buf.length)) != -1) {
                                    baos.write(buf, 0, num);
                            }
                            b = baos.toByteArray();
                            baos.flush();
                            baos.close();
                    }
                    zip.close();
                    bis.close();
            } catch (Exception ex) {
                    ex.printStackTrace();
            }
            return b;
    }

    /**
     * zip压缩后进行Base64编码
     * @param source
     * @return
     */
    public static String zipAndEncode(String source) {
            return new String(encodeBase64(zip(StringUtils.getBytesUtf8(source))));
    }

    /**
     * Base64解码后进行Zip解压
     * @param source
     * @return
     */
    public static String decodeAndUnzip(String source) {
            return StringUtils.newStringUtf8(decodeBase64(source));
    }
    
    public static String encodeBase64File(String path) throws Exception {
       File file = new File(path);
	   FileInputStream inputFile = new FileInputStream(file);
	   byte[] buffer = new byte[(int) file.length()];
	   inputFile.read(buffer);
	   inputFile.close();
	   return Base64Encoder.encode(buffer);
	}

    public static void decoderBase64File(String base64Code, String targetPath)
    		   throws Exception {
    		  byte[] buffer = new BASE64Decoder().decodeBuffer(base64Code);
    		  FileOutputStream out = new FileOutputStream(targetPath);
    		  out.write(buffer);
    		  out.close();
    		 }
    
    public static void main(String[] args) {
            String compString;
			try {
				compString = encodeBase64File("C:\\Users\\Administrator\\Desktop\\1.zip");
				decoderBase64File(compString, "C:/Users/Administrator/Desktop/11/1.zip");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

    }

}