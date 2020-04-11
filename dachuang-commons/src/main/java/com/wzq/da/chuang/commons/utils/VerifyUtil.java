package com.wzq.da.chuang.commons.utils;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Title：生成验证码图片
 * Description：
 * @author WZQ
 * @version 1.0.0
 * @date 2020/3/19
 */
public class VerifyUtil {
    public static final String RANDOMCODEKEY= "RANDOMREDISKEY";//放到session中的key
    //private String randString = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";//随机产生数字与字母组合的字符串
    private String randString = "0123456789abcdefghijklmnopqrstuvwxyz";//随机产生数字与字母组合的字符串
//    private int width = 95;// 图片宽
//    private int height = 25;// 图片高
//    private int lineSize = 40;// 干扰线数量
//    private int stringNum = 4;// 随机产生字符数量
    private int width = 130;// 图片宽
    private int height = 53;// 图片高
    private int lineSize = 110;// 干扰线数量
    private int stringNum = 4;// 随机产生字符数量

    private Random random = new Random();

    /**
     * 获得字体
     */
    private Font getFont() {
        return new Font("Fixedsys", Font.CENTER_BASELINE, 30);
    }

    /**
     * 获得颜色
     */
    private Color getRandColor(int fc, int bc) {
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc - 16);
        int g = fc + random.nextInt(bc - fc - 14);
        int b = fc + random.nextInt(bc - fc - 18);
        return new Color(r, g, b);
    }

    /**
     * 生成随机图片
     */
    public String getRandcode(/*HttpServletRequest request,*/ HttpServletResponse response) {
        //HttpSession session = request.getSession();
        // BufferedImage类是具有缓冲区的Image类,Image类是用于描述图像信息的类
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        Graphics g = image.getGraphics();// 产生Image对象的Graphics对象,改对象可以在图像上进行各种绘制操作
        g.fillRect(0, 0, width, height);//图片大小
        g.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 30));//字体大小
        g.setColor(getRandColor(110, 133));//字体颜色
        // 绘制干扰线
        for (int i = 0; i <= lineSize; i++) {
            drowLine(g);
        }
        // 绘制随机字符
        String randomString = "";
        for (int i = 1; i <= stringNum; i++) {
            randomString = drowString(g, randomString, i);
        }
//        //将生成的随机字符串保存到session中
//        session.removeAttribute(RANDOMCODEKEY);
//        session.setAttribute(RANDOMCODEKEY, randomString);
//        //设置失效时间1分钟
//        session.setMaxInactiveInterval(60);
        g.dispose();
        try {
            // 将内存中的图片通过流动形式输出到客户端
            ImageIO.write(image, "JPEG", response.getOutputStream());
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        return randomString;
    }

    /**
     * 绘制字符串
     */
    private String drowString(Graphics g, String randomString, int i) {
        g.setFont(getFont());
        g.setColor(new Color(random.nextInt(101), random.nextInt(111), random
                .nextInt(121)));
        String rand = String.valueOf(getRandomString(random.nextInt(randString
                .length())));
        randomString += rand;
        g.translate(random.nextInt(8), random.nextInt(8));
        g.drawString(rand, 20 * i, 25);
        return randomString;
    }

    /**
     * 绘制干扰线
     */
    private void drowLine(Graphics g) {
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        int xl = random.nextInt(13);
        int yl = random.nextInt(15);
        g.drawLine(x, y, x + xl, y + yl);
    }

    /**
     * 获取随机的字符
     */
    public String getRandomString(int num) {
        return String.valueOf(randString.charAt(num));
    }

}