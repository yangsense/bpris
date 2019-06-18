package com.ai.common.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * User: chengzj
 * Date: 14-3-25
 * Time: 下午2:41
 * To change this template use File | Settings | File Templates.
 */
public class HttpClientUtil {
    private static Log log = LogFactory.getLog(HttpClientUtil.class);

    /**
     * 发送HTTP_POST请求
     * 该方法会自动关闭连接,释放资源
     * 当isEncoder=true时,其会自动对sendData中的[中文][|][ ]等特殊字符进行URLEncoder.encode(string,encodeCharset)
     *
     * @param reqURL        请求地址
     * @param sendData      请求参数,若有多个参数则应拼接成param11=value11¶m22=value22¶m33=value33的形式后,传入该参数中
     * @param isEncoder     请求数据是否需要encodeCharset编码,true为需要
     * @param encodeCharset 编码字符集,编码请求数据时用之,其为null时默认采用UTF-8解码
     * @param decodeCharset 解码字符集,解析响应数据时用之,其为null时默认采用UTF-8解码
     * @return 远程主机响应正文
     */

    public static String sendPostRequest(String reqURL, String sendData, boolean isEncoder, String encodeCharset, String decodeCharset) throws Exception{
        String responseContent = null;
        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(reqURL);
        //httpPost.setHeader(HTTP.CONTENT_TYPE, "application/x-www-form-urlencoded; charset=UTF-8");
        httpPost.setHeader(HTTP.CONTENT_TYPE, "application/x-www-form-urlencoded");
        try {
            if (isEncoder) {
                List<NameValuePair> formParams = new ArrayList<NameValuePair>();
                for (String str : sendData.split("&")) {
                    formParams.add(new BasicNameValuePair(str.substring(0, str.indexOf("=")), str.substring(str.indexOf("=") + 1)));
                }
                httpPost.setEntity(new StringEntity(URLEncodedUtils.format(formParams, encodeCharset == null ? "UTF-8" : encodeCharset)));
            } else {
                httpPost.setEntity(new StringEntity(sendData));
            }
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            if (null != entity) {
                responseContent = EntityUtils.toString(entity, decodeCharset == null ? "UTF-8" : decodeCharset);
                EntityUtils.consume(entity);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage(),e);
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
        return responseContent;
    }

    public static String sendGetRequest(String url,String decodeCharset,Integer connectionTimeout) throws Exception{
        HttpClient client = new DefaultHttpClient();
        try {
            client.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, connectionTimeout == null ? 15000 : connectionTimeout);
            HttpGet get = new HttpGet(url);
            HttpResponse response = client.execute(get);
            HttpEntity entity = response.getEntity();
            StatusLine statusLine = response.getStatusLine();
            int code = statusLine.getStatusCode();
            if (HttpStatus.SC_OK == code) {
                return  EntityUtils.toString(entity, decodeCharset == null ? "UTF-8" : decodeCharset);
            } else {
                log.error("访问url:" + url + "异常.");
                return null;
            }
        } finally {
            client.getConnectionManager().shutdown();
        }
    }



}
