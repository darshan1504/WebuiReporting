package CS585.WebuiReporting;

import java.awt.Desktop;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.imageio.ImageIO;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRemoteException;
import org.eclipse.jgit.api.errors.TransportException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import CS585.WebuiReporting.WebhookResponse.Committer;
import CS585.WebuiReporting.WebhookResponse.Head_commit;
import CS585.WebuiReporting.WebhookResponse.Payload;
import CS585.WebuiReporting.WebhookResponse.Repository;
import CS585.WebuiReporting.WebhookResponse.WebHookRequest;

@Controller
@RequestMapping("/payload")
public class WebhookControl {

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public WebHookRequest getPayload(@RequestBody Payload payload)
			throws Exception {
		System.setProperty("java.awt.headless", "false");
		String ScreenShotFolderPath = "C:\\Screenshots\\";

		Repository repo = (Repository) payload.getRepository();
		String cloneURL = repo.getURL();

		Head_commit commit = (Head_commit) payload.getHead_commit();
		Committer committer = (Committer) commit.getCommitter();
		String email = committer.getEmail();

		File localPath = File.createTempFile("TestGitRepository", "");
		if (!localPath.delete()) {
			throw new IOException("Could not delete temporary file "
					+ localPath);
		}

		System.out.println("Cloning from " + cloneURL + " to " + localPath);
		try (Git result = Git.cloneRepository().setURI(cloneURL)
				.setDirectory(localPath).call()) {

			Runtime runtime = Runtime.getRuntime();
			runtime.exec("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome -new-window "
					+ localPath + "\\index.html");

			Thread.sleep(3000);

			SimpleDateFormat formatter = new SimpleDateFormat(
					"yyyyMMdd hh mm ss a");

			Calendar now = Calendar.getInstance();
			Robot robot = new Robot();
			BufferedImage screenShot = new Robot()
					.createScreenCapture(new Rectangle(Toolkit
							.getDefaultToolkit().getScreenSize()));
			ImageIO.write(screenShot, "JPG", new File(ScreenShotFolderPath
					+ formatter.format(now.getTime()) + ".jpg"));
			String img = ScreenShotFolderPath + formatter.format(now.getTime())
					+ ".jpg";
			System.out.println(formatter.format(now.getTime()));
			

			Sendemailto.To(email, img);

			System.out.println("Having repository: "
					+ result.getRepository().getDirectory());
			result.close();

		}
		return new WebHookRequest();
	}

}