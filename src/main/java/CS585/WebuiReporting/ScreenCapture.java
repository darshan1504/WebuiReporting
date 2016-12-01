/*package CS585.WebuiReporting;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.imageio.ImageIO;

import org.zeroturnaround.zip.ZipUtil;

public class ScreenCapture {

	SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd hh mm ss a");

	public void robo(String folderPath) throws AWTException, IOException {
		
		System.setProperty("java.awt.headless", "false");
		Calendar now = Calendar.getInstance();
		Robot robot = new Robot();
		BufferedImage screenShot = new Robot()
				.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit()
						.getScreenSize()));
		ImageIO.write(screenShot, "JPG", new File(folderPath
				+ formatter.format(now.getTime()) + ".jpg"));
		System.out.println(formatter.format(now.getTime()));
		


}
}
*/