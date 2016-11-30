package CS585.WebuiReporting;

import java.io.File;
import java.io.IOException;

public class ServerNgrok {

	public static void run() throws IOException {
		String[] command = { "CMD", "/C", "start", "ngrok", "http", "8080" };
		ProcessBuilder probuilder = new ProcessBuilder(command);

		/**
		 * Download ngrok and unzip the folder into C: drive, so its in location
		 * c:\\ngrok OR Set the path below to where you have unzipped ngrok on
		 * your machine, it should contain ngrok.exe
		 */
		probuilder.directory(new File("c:\\ngrok"));

		// run the command
		Process process = probuilder.start();

		try {
			// wait for it to exit
			int exitValue = process.waitFor();
			System.out.println("\n\nExit Value is " + exitValue);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

}
