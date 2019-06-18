package com.ai.aris.web.controller.common;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbarcode.JBarcode;
import org.jbarcode.encode.Code128Encoder;
import org.jbarcode.encode.EAN13Encoder;
import org.jbarcode.paint.BaseLineTextPainter;
import org.jbarcode.paint.EAN13TextPainter;
import org.jbarcode.paint.WideRatioCodedPainter;
import org.jbarcode.paint.WidthCodedPainter;
import org.jbarcode.util.ImageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ai.aris.web.servlet.SCaptcha;
import com.ai.common.util.AjaxUtil;

/**
 * Created with IntelliJ IDEA.
 * User: chengzj
 * Date: 15-3-18
 * Time: 下午4:58
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("/aris/common/checkCode")
public class CheckCodeController {

    private Log logger = LogFactory.getLog(CheckCodeController.class);

    @RequestMapping("/code")
    public void getCode(HttpServletResponse response,String page,HttpSession httpSession)
            throws IOException {
        // 设置响应的类型格式为图片格式
        response.setContentType("image/jpeg");
        // 禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        SCaptcha instance = new SCaptcha();
        instance.write(response.getOutputStream());
        httpSession.setAttribute("sessionCheckCode_" + page , instance.getCode());
        logger.info("sessionCheckCode_" + page + ":" + instance.getCode());
    }

    @RequestMapping("/validate.ajax")
    @ResponseBody
    public JSONObject validate(String checkCode,String page,HttpSession httpSession){
        String sessionCheckCode = (String) httpSession.getAttribute("sessionCheckCode_" + page);
        logger.info("sessionCheckCode:" + sessionCheckCode);
        logger.info("checkCode:" + checkCode);
        if(sessionCheckCode != null && sessionCheckCode.toLowerCase().equals(checkCode.toLowerCase())){
            return AjaxUtil.success("");
        }
        return AjaxUtil.failure("");
    }

    @RequestMapping("/barCode")
    public void getBarCode(HttpServletResponse response,String page,HttpSession httpSession,String ids) throws IOException {

        // 设置响应的类型格式为图片格式
        response.setContentType("image/gif");
        // 禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        //SCaptcha instance = new SCaptcha();
        //instance.write(response.getOutputStream());
        try {

            JBarcode localJBarcode = new JBarcode(Code128Encoder.getInstance(), WidthCodedPainter.getInstance(), EAN13TextPainter.getInstance());
            //生成. 欧洲商品条码(=European Article Number)
            //这里我们用作图书条码
            //String str = "788515004012";
            if(StringUtils.isNotBlank(ids)&&ids.length()<12&&ids.length()>0){
                int l=12-ids.length();
                for(int i=0;i<l;i++){
                    ids="0"+ids;
                }
            }
            BufferedImage localBufferedImage = localJBarcode.createBarcode(ids);
            localJBarcode.setEncoder(Code128Encoder.getInstance());
            localJBarcode.setPainter(WideRatioCodedPainter.getInstance());
            localJBarcode.setTextPainter(BaseLineTextPainter.getInstance());
            localJBarcode.setShowCheckDigit(false);
            
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ImageIO.write(localBufferedImage,"gif", out);
//            ImageUtil.encodeAndWrite(localBufferedImage, "gif", , 96, 96);
            byte[] b = out.toByteArray();
            response.getOutputStream().write(b);
            

        } catch (Exception localException) {
        	logger.info("getBarCode exception:"  + localException.toString());
            localException.printStackTrace();
        }

    }

}