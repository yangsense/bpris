package com.ai.common.util;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: chengzj
 * Date: 15-2-6
 * Time: 下午5:10
 * To change this template use File | Settings | File Templates.
 */
public abstract class DESUtil {

	private static Log logger = LogFactory.getLog(DESUtil.class);
	/** 加密算法,可用 DES,DESede,Blowfish. */
	private final static String ALGORITHM = "DES";

	private final static String KEY = "fjaskj2489#$5";

	public static String encrypt(String data) throws Exception {
		return encrypt(data, KEY);
	}

	/**
	 * 用指定的key对数据进行DES加密.
	 * 
	 * @param data
	 *            待加密的数据
	 * @param key
	 *            DES加密的key, 必须要不少于8位
	 * @return 返回DES加密后的数据
	 * @throws Exception
	 */
	public static String encrypt(String data, String key) throws Exception {
		byte[] dt = data.getBytes("UTF-8");
		byte[] ky = key.getBytes("UTF-8");
		byte[] res = encrypt(dt, ky);
		BASE64Encoder base64encoder = new BASE64Encoder();
		String encode = base64encoder.encode(res);
		return encode;
	}

	/**
	 * 用指定的key对数据进行DES解密.
	 * 
	 * @param data
	 *            待解密的数据
	 * @param key
	 *            DES解密的key, 必须要不少于8位
	 * @return 返回DES解密后的数据
	 * @throws Exception
	 */
	public static String decrypt(String data, String key) throws Exception {
		BASE64Decoder base64decoder = new BASE64Decoder();
		byte[] encodeByte = base64decoder.decodeBuffer(data);
		byte[] ky = key.getBytes("UTF-8");
		byte[] res = decrypt(encodeByte, ky);
		return new String(res, "UTF-8");
	}

	public static String decrypt(String data) throws Exception {
		if (StringUtils.isBlank(data)) {
			return null;
		}
		return decrypt(data, KEY);
	}

	/**
	 * 用指定的key对数据进行DES加密.
	 * 
	 * @param data
	 *            待加密的数据
	 * @param key
	 *            DES加密的key
	 * @return 返回DES加密后的数据
	 * @throws Exception
	 */
	private static byte[] encrypt(byte[] data, byte[] key) throws Exception {
		// DES算法要求有一个可信任的随机数源
		SecureRandom sr = new SecureRandom();
		// 从原始密匙数据创建DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(key);
		// 创建一个密匙工厂，然后用它把DESKeySpec转换成
		// 一个SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
		SecretKey securekey = keyFactory.generateSecret(dks);
		// Cipher对象实际完成加密操作
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
		// 现在，获取数据并加密
		// 正式执行加密操作
		return cipher.doFinal(data);
	}

	/** */
	/**
	 * 用指定的key对数据进行DES解密.
	 * 
	 * @param data
	 *            待解密的数据
	 * @param key
	 *            DES解密的key
	 * @return 返回DES解密后的数据
	 * @throws Exception
	 */
	private static byte[] decrypt(byte[] data, byte[] key) throws Exception {
		// DES算法要求有一个可信任的随机数源
		SecureRandom sr = new SecureRandom();
		// 从原始密匙数据创建一个DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(key);
		// 创建一个密匙工厂，然后用它把DESKeySpec对象转换成
		// 一个SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
		SecretKey securekey = keyFactory.generateSecret(dks);
		// Cipher对象实际完成解密操作
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
		// 现在，获取数据并解密
		// 正式执行解密操作
		return cipher.doFinal(data);
	}

	@SuppressWarnings("static-access")
	public static void main(String[] args) throws Exception {
        System.out.println(new Date(1374047155656L));

        long currentTime = System.currentTimeMillis();
		//String s = DESUtil.encrypt("apptoken=hello&institute=1&distributeCenter=2&warehouse=3&firstWorkPeriodBeginTime=10:00&firstWorkPeriodEndTime=13:00&secondWorkPeriodBeginTime=10:00&secondWorkPeriodEndTime=11:00&totalOrder=1000&nowtime=7:55&timeSection=10:30;08:00-11:30,11:00;08:00-12:00&totalOrderPoint=10:30;300,11:30;200&undoneOrder=500&undoneOrderPoint=10:30;300,11:30;200&finishedOrder=500&finishedOrderPoint=10:30;300,11:30;200&requestTimeDSP=" + currentTime, "esdF0pE3");
		//String s = DESUtil.encrypt("435861379||长武县相公中心卫生院||2018-05-31 11:35:24||2018-05-31 11:36:24||2018-06-31 11:37:24||''||2018-05-31 11:35:24||d4-25-8b-67-d8-38", "*slD5a1o");
		//String s = DESUtil.encrypt("00:0C:29:AD:98:E4", "*slD5a1o");
		String s = DESUtil.encrypt("610428435861248||长武县人民医院||2018-05-31 11:35:24||2018-05-31 11:36:24||2018-06-31 11:37:24||''||2018-05-31 11:35:24||d4-25-8b-67-d8-38", "*slD5a1o");
		System.out.println(s);
        String urlEncodeStr = URLEncoder.encode(s, "utf-8");
        System.out.println();
        System.out.println(urlEncodeStr);
        System.out.println();
        String rst = DESUtil.decrypt(s, "*slD5a1o");
		System.out.println(rst);
	}

}
