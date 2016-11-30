package CS585.WebuiReporting;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebHook {

	public static void main(String[] args) throws IOException {
		//Starting Ngrok
		ServerNgrok.run();
		//Starting Spring
		SpringApplication.run(WebHook.class, args);
	}

}
