package com.ai.aris.server.common.util;

import java.awt.image.BufferedImage;
import java.io.FileOutputStream;

import org.jbarcode.JBarcode;
import org.jbarcode.encode.Code39Encoder;
import org.jbarcode.encode.EAN13Encoder;
import org.jbarcode.paint.BaseLineTextPainter;
import org.jbarcode.paint.EAN13TextPainter;
import org.jbarcode.paint.WideRatioCodedPainter;
import org.jbarcode.paint.WidthCodedPainter;
import org.jbarcode.util.ImageUtil;

/**
 * java生成条形码工具类
 *
 * @author yong
 */
public class BarCodeUtil {

    public static void main(String[] paramArrayOfString) {
        try {
            JBarcode localJBarcode = new JBarcode(EAN13Encoder.getInstance(), WidthCodedPainter.getInstance(), EAN13TextPainter.getInstance());
            //生成. 欧洲商品条码(=European Article Number)
            //这里我们用作图书条码
            String str = "788515004012";
            BufferedImage localBufferedImage = localJBarcode.createBarcode(str);
            saveToGIF(localBufferedImage, "EAN13.gif");
            localJBarcode.setEncoder(Code39Encoder.getInstance());
            localJBarcode.setPainter(WideRatioCodedPainter.getInstance());
            localJBarcode.setTextPainter(BaseLineTextPainter.getInstance());
            localJBarcode.setShowCheckDigit(false);
            //xx
            str = "JBARCODE-39";
            localBufferedImage = localJBarcode.createBarcode(str);
            saveToPNG(localBufferedImage, "Code39.png");

        } catch (Exception localException) {
            localException.printStackTrace();
        }
    }

    static void saveToJPEG(BufferedImage paramBufferedImage, String paramString) {
        saveToFile(paramBufferedImage, paramString, "jpeg");
    }

    static void saveToPNG(BufferedImage paramBufferedImage, String paramString) {
        saveToFile(paramBufferedImage, paramString, "png");
    }

    static void saveToGIF(BufferedImage paramBufferedImage, String paramString) {
        saveToFile(paramBufferedImage, paramString, "gif");
    }

    public static void saveToFile(BufferedImage paramBufferedImage, String paramString1, String paramString2) {
        try {
            FileOutputStream localFileOutputStream = new FileOutputStream("c://tmp//" + paramString1);
            ImageUtil.encodeAndWrite(paramBufferedImage, paramString2, localFileOutputStream, 96, 96);
            localFileOutputStream.close();
        } catch (Exception localException) {
            localException.printStackTrace();
        }
    }


}
