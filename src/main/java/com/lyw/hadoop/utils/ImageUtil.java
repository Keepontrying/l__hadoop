package com.lyw.hadoop.utils;

import org.xhtmlrenderer.context.AWTFontResolver;
import org.xhtmlrenderer.css.constants.IdentValue;
import org.xhtmlrenderer.css.value.FontSpecification;
import org.xhtmlrenderer.swing.Java2DRenderer;
import org.xhtmlrenderer.util.FSImageWriter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import static org.xhtmlrenderer.test.DocumentDiffTest.height;

/**
 * Created by wangxiaowu on 2018/11/2.
 */
public class ImageUtil {

    public static void main(String[] args) throws Exception{
        String html="/Users/wangxiaowu/Downloads/551385082.html";
        String img="/Users/wangxiaowu/Downloads/551385082.png";

        Java2DRenderer renderer = new Java2DRenderer(new File(html),900);
        AWTFontResolver fontResolver = new AWTFontResolver();
        String[] s ={"Microsoft Sans Serif","Hiragino Sans"};
        fontResolver.resolveFont(renderer.getSharedContext(),s,12f, IdentValue.NORMAL,
                IdentValue.NORMAL,IdentValue.NORMAL);
        renderer.getSharedContext().setFontResolver(fontResolver);

        BufferedImage image = renderer.getImage();

        FSImageWriter fsImageWriter = new FSImageWriter();
        fsImageWriter.setWriteCompressionQuality(0.9f);

        fsImageWriter.write(image,img);

    }

    public static void main2(String[] args) throws MalformedURLException,
    IOException,URISyntaxException,AWTException {

        String html = "/Users/wangxiaowu/Downloads/551385082.html";
        String img = "/Users/wangxiaowu/Downloads/551385082.png";

        //此方法仅适用于JdK1.6及以上版本  
        Desktop.getDesktop().browse(new URL("https://blog.csdn.net/cping1982/article/details/5353049").toURI());
//        Desktop.getDesktop().edit(new File(html));
        Robot robot = new Robot();
        robot.delay(10000);
        Dimension d = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
        int width = (int) d.getWidth();
        int height = (int) d.getHeight();
        //最大化浏览器
        robot.keyRelease(KeyEvent.VK_F11);
        robot.delay(2000);
        Image image = robot.createScreenCapture(new Rectangle(0, 0, width, height));
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = bufferedImage.getGraphics();
        g.drawImage(image, 0, 0, width, height, null);

        ImageIO.write(bufferedImage, "png", new File(img));

    }


/*    Robot robot = new Robot();  
    robot.delay(10000);  
    Dimension d = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());  
    int width = (int) d.getWidth();  
    int height = (int) d.getHeight();  
    //最大化浏览器  
    robot.keyRelease(KeyEvent.VK_F11);  
    robot.delay(2000);  
    Image image = robot.createScreenCapture(new Rectangle(0, 0, width,  
                            height));  
    BufferedImage bi = new BufferedImage(width, height,  
                            BufferedImage.TYPE_INT_RGB);  
    Graphics g = bi.createGraphics();  
    g.drawImage(image, 0, 0, width, height, null);  
    //保存图片  
    ImageIO.write(bi, "jpg", new File("google.jpg"));  
    }  */

}
