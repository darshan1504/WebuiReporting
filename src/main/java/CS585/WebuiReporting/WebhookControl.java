package CS585.WebuiReporting;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

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

		// Get the repositry URL to clone the repository
		Repository repo = (Repository) payload.getRepository();
		String cloneURL = repo.getURL();

		// Get the email of the committer to send test results
		Head_commit commit = (Head_commit) payload.getHead_commit();
		Committer committer = (Committer) commit.getCommitter();
		String email = committer.getEmail();

		// Clone the Github Repository
		/* CloneRepo.run(cloneURL,email); */
		File localPath = File.createTempFile("TestGitRepository", "");
		if (!localPath.delete()) {
			throw new IOException("Could not delete temporary file "
					+ localPath);
		}

		// then clone
		System.out.println("Cloning from " + cloneURL + " to " + localPath);
		try (Git result = Git.cloneRepository().setURI(cloneURL)
				.setDirectory(localPath).call()) {

			Runtime runtime = Runtime.getRuntime();
			runtime.exec("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome -new-window "
					+ localPath + "\\index.html");
			ScreenCapture screenGrab = new ScreenCapture();
			screenGrab.robo();
			// Note: the call() returns an opened repository already which needs
			// to be closed to avoid file handle leaks!
			System.out.println("Having repository: "
					+ result.getRepository().getDirectory());
			result.close();

		}
		return new WebHookRequest();
	}
}