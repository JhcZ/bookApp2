package cdu.jhc.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

//前后台：生成验证码
@WebServlet(urlPatterns = {"/admin/validCode", "/customer/validCode"})
public class ValidCodeServlet extends HttpServlet{
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //利用随机数生成四位随机字符验证码
        String chars = "abcdefghijkmnpqrstuvwxyzABCDEFGHJKLMNPQRSTUVWXYZ23456789";
        Random random = new Random();
        char[] validCode = new char[4];
        for (int i = 0; i < 4; i++) {
            validCode[i] = chars.charAt(random.nextInt(chars.length()));
        }
        //在会话范围内保存验证码
        req.getSession().setAttribute("validCode", new String(validCode));
        //绘制验证码
        int width = 100;
        int height = 24;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        Graphics g = image.getGraphics();
        g.setColor(Color.lightGray);
        g.fillRect(0, 0, width, height);
        g.setColor(Color.red);
        g.setFont(new Font("arial", Font.BOLD, 18));
        g.drawChars(validCode, 0, validCode.length, 0, 18);
        //以图像形式返回验证码
        ImageIO.write(image, "png", resp.getOutputStream());
    }
}
