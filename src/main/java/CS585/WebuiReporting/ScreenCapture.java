package CS585.WebuiReporting;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.imageio.ImageIO;

public class ScreenCapture {
	
	SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd hh mm ss a");

	public void robo() throws AWTException, IOException {
		Calendar now = Calendar.getInstance();
        /*Robot robot = new Robot();*/
        /*BufferedImage screenShot = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
        ImageIO.write(screenShot, "JPG", new File("C:\\Screenshots\\"+formatter.format(now.getTime())+".jpg"));
        System.out.println(formatter.format(now.getTime()));*/
        
        Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        BufferedImage capture = new Robot().createScreenCapture(screenRect);
        ImageIO.write(capture,"JPG", new File("C:\\Screenshots\\"+formatter.format(now.getTime())+".jpg"));
	}

	
}
