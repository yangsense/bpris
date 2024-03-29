package com.ai.aris.web.servlet;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: chengzj
 * Date: 15-3-18
 * Time: 下午4:43
 * To change this template use File | Settings | File Templates.
 */
public class SCaptcha {
    // 图片的宽度。
    private int width = 120;
    // 图片的高度。
    private int height = 40;
    // 验证码字符个数
    private int codeCount = 4;
    // 验证码干扰线数
    private int lineCount = 50;
    // 验证码
    private String code = null;
    // 验证码图片Buffer
    private BufferedImage buffImg = null;

    private char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
            'I', 'J', 'K', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
            'X', 'Y', 'Z', '2', '3', '4', '5', '6', '7', '8', '9' };

    // 生成随机数
    private Random random = new Random();

    public SCaptcha() {
        this.createCode();
    }

    /**
     *
     * @param width
     *            图片宽
     * @param height
     *            图片高
     */
    public SCaptcha(int width, int height) {
        this.width = width;
        this.height = height;
        this.createCode();
    }

    /**
     *
     * @param width
     *            图片宽
     * @param height
     *            图片高
     * @param codeCount
     *            字符个数
     * @param lineCount
     *            干扰线条数
     */
    public SCaptcha(int width, int height, int codeCount, int lineCount) {
        this.width = width;
        this.height = height;
        this.codeCount = codeCount;
        this.lineCount = lineCount;
        this.createCode();
    }

    public void createCode() {
        int codeX = 0;
        int fontHeight = 0;
        fontHeight = height - 5;// 字体的高度
        codeX = width / (codeCount+3);// 每个字符的宽度

        // 图像buffer
        buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = buffImg.createGraphics();

        // 将图像填充为白色
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);

        // 创建字体
        ImgFontByte imgFont = new ImgFontByte();
        Font font = imgFont.getFont(fontHeight);
        g.setFont(font);

        // 绘制干扰线
        for (int i = 0; i < lineCount; i++) {
            int xs = getRandomNumber(width);
            int ys = getRandomNumber(height);
            int xe = xs + getRandomNumber(width / 8);
            int ye = ys + getRandomNumber(height / 8);
            g.setColor(getRandomColor());
            g.drawLine(xs, ys, xe, ye);
        }

        StringBuffer randomCode = new StringBuffer();
        // 随机产生验证码字符
        for (int i = 0; i < codeCount; i++) {
            String strRand = String.valueOf(codeSequence[random
                    .nextInt(codeSequence.length)]);
            // 设置字体颜色
            g.setColor(getRandomColor());
            // 设置字体位置
            g.drawString(strRand, (i + 1) * codeX,
                    getRandomNumber(height / 2) + 20);
            randomCode.append(strRand);
        }
        code = randomCode.toString();
    }

    /** 获取随机颜色 */
    private Color getRandomColor() {
        int r = getRandomNumber(255);
        int g = getRandomNumber(255);
        int b = getRandomNumber(255);
        return new Color(r, g, b);
    }

    /** 获取随机数 */
    private int getRandomNumber(int number) {
        return random.nextInt(number);
    }

    public void write(String path) throws IOException {
        OutputStream sos = new FileOutputStream(path);
        this.write(sos);
    }

    public void write(OutputStream sos) throws IOException {
        ImageIO.write(buffImg, "png", sos);
        sos.close();
    }

    public BufferedImage getBuffImg() {
        return buffImg;
    }

    public String getCode() {
        return code;
    }

    /** 字体样式类 */
    class ImgFontByte {
        public Font getFont(int fontHeight) {
            try {
                Font baseFont = Font.createFont(Font.TRUETYPE_FONT,
                        new ByteArrayInputStream(hex2byte(getFontByteStr())));
                return baseFont.deriveFont(Font.PLAIN, fontHeight);
            } catch (Exception e) {
                return new Font("Arial", Font.PLAIN, fontHeight);
            }
        }

        private byte[] hex2byte(String str) {
            if (str == null)
                return null;
            str = str.trim();
            int len = str.length();
            if (len == 0 || len % 2 == 1)
                return null;

            byte[] b = new byte[len / 2];
            try {
                for (int i = 0; i < str.length(); i += 2) {
                    b[i / 2] = (byte) Integer.decode(
                            "0x" + str.substring(i, i + 2)).intValue();
                }
                return b;
            } catch (Exception e) {
                return null;
            }
        }

        // 字体文件的十六进制字符串
        private String getFontByteStr() {
            return "6004700480049004a004b004c004d004e004f0050005100520053005400550056005700580059005a005b005c005d005e005f00600061006200630010006400650066006700680069006a0000000000010000001a0001000200060001000600320025ffd90032003cff9900000000001000000070090805000707030605050505030303020503050203050506050506050505050203030503050506050505050607060506060607060505050505060706070706060303030505030605050505060706050606060706050505050506070607070606030303050802030303030606070000000a0805000808030606060606030303020603060304060606050606060606050303030603050607060606060607070607070708070606060505070707080806060304030606030706060606060707060707070807060606050507070708080606030403060802030303030606080000000b0906000909030706060606040303030604060304070607060607060606060304030603060608060606070708080707070809070606060606080808090807070304030606040806060607070808070707080907060606060608080809080707030403060903040404040707080000000c0a06000a0a04080707070704040303070407030507070806070707070706030404070306070807070707080908070808090a080707070606080908090908080404030707040807070707080908070808090a08070707060608090809090808040503070a03040404040808090000000d0b07000b0b04080808080804040403070408030508070807080807080707030404080407080908080808080a09080909090b090707070707090a090a0a08080405040808040908080808080a09080909090b090707070707090a090a0a0808040504080b030404040408080a0000000e0c07000c0c04090808080805040403080508040508080907080908080807040504080407080a08080808090a0a0909090a0b0908080807070a0a0a0b0b09090405040808050a08080808090a0a0909090a0b0908080807070a0a0a0b0b0909040504080c030505050509090b0000000f0d08000c0c050a0909090905040404090509040609080908090908090808040504090408090a090909090a0b0b090a0a0b0c0a09090808080a0b0a0c0b0a0a0406040909050a090909090a0b0b090a0a0b0c0a09090808080a0b0a0c0b0a0a040604090d04050505050a0a0c000000100d08000d0d050a0909090905050504090509040609090a08090a09090908040505090508090b090909090a0c0b0a0b0b0b0d0b09090908080b0c0b0d0c0a0a0506050909060b090909090a0c0b0a0b0b0b0d0b09090908080b0c0b0d0c0a0a050605090d04060505060a0a0c000000110e09000e0e050b0a0a0a0a060505040a060a05060a090b090a0a0a0a0a090506050a05090a0c0a0a0a0a0b0c0c0a0b0b0c0e0b0a0a0909090c0d0c0d0d0b0b0506050a0a060c0a0a0a0a0b0c0c0a0b0b0c0e0b0a0a0909090c0d0c0d0d0b0b0506050a0e04060606060b0b0d000000120f09000f0f050b0a0a0a0a060505040a060a05070b0a0b090a0b0a0b0a090506050a05090a0c0b0a0b0b0c0d0d0b0c0c0d0f0c0a0a0a09090c0d0c0e0e0c0b0507050a0a060c0b0a0b0b0c0d0d0b0c0c0d0f0c0a0a0a09090c0d0c0e0e0c0b0507050a0f04060606060b0b0e00000013100a001010060c0b0b0b0b060605050b060b05070b0b0c0a0b0c0b0b0b0a0506060b050a0b0d0b0b0b0b0c0e0d0c0c0c0e100d0b0b0b0a0a0d0e0d0f0e0c0c0607050b0b070d0b0b0b0b0c0e0d0c0c0c0e100d0b0b0b0a0a0d0e0d0f0e0c0c0607050b1005070606070c0c0f00000014110a001111060d0c0c0c0c070606050b070c05080c0b0d0a0c0c0b0c0b0a0507060c060a0c0e0c0c0c0c0d0f0e0c0d0d0e100d0b0b0b0a0a0e0f0e100f0d0d0607060c0c070e0c0c0c0c0d0f0e0c0d0d0e100d0b0b0b0a0a0e0f0e100f0d0d0608060c1105070707070d0d0f00000015120b001111060d0c0c0c0c070606050c070c06080c0c0d0b0c0d0c0c0c0b0607060c060b0c0f0c0c0c0c0d0f0f0d0e0e0f110e0c0c0c0b0b0e100e10100e0d0608060c0c070f0c0c0c0c0d0f0f0d0e0e0f110e0c0c0c0b0b0e100e10100e0d0608060c1205070707070d0d1000000016120b001212070e0d0d0d0d070706050d070d06080d0c0e0b0d0e0c0d0c0b0607070d060b0d0f0d0d0d0d0e10100d0e0e10120f0d0c0c0b0b0f100f11110e0e0708060d0d080f0d0d0d0d0e10100d0e0e10120f0d0c0c0b0b0f100f11110e0e0708060d1205080707080e0e1100000017130c001313070f0d0d0d0d080706060d080d06090e0d0e0c0d0e0d0d0d0c0608070d060c0d100d0d0e0e0f11100e0f0f10130f0d0d0d0c0c10111012110f0f0709060d0d08100d0d0e0e0f11100e0f0f10130f0d0d0d0c0c10111012110f0f0709060d1306080808080f0f1200000018140c001414070f0e0e0e0e080707060e080e06090e0d0f0c0e0f0e0e0e0d0608070e070c0e110e0e0e0e0f12110f10101114100e0e0d0d0d11121013120f0f0709070e0e08110e0e0e0e0f12110f10101114100e0e0d0d0d11121013120f0f0709070e1406080808080f0f120000000001029c01900005000002bc028a0000008f02bc028a000001c5003201030000000004000000000000000000000300000000000000000000000046726f670040002020260355ff5c0000035500a4000000010000000000000001000080000000033d02f10000600002dd0275416374696f6e204a61636b7320202020ffffffff37fffffe4143545230300000000000000001000000010000fae7a6eb5f0f3cf5000003e800000000b2050dda00000000b20549240000ff5c03280356000000030002000100000000000100000356ff38000003430000000d032800010000000000000000000000000000006b00010000006b0227001d01030009000200080040000a00000097013000030003\";";
        }
    }
}
