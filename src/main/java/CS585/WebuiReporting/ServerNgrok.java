package CS585.WebuiReporting;

import java.io.File;
import java.io.IOException;

public class ServerNgrok {

	public static void run() throws IOException {
		String[] command = { "CMD", "/C", "start", "ngrok", "http", "8080" };
		ProcessBuilder probuilder = new ProcessBuilder(command);

		probuilder.directory(new File("c:\\ngrok"));

		Process process = probuilder.start();

		try {

			int exitValue = process.waitFor();
			System.out.println("\n\nExit Value is " + exitValue);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

}
