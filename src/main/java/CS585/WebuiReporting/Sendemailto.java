package CS585.WebuiReporting;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.util.*;

import javax.imageio.ImageIO;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

import org.zeroturnaround.zip.ZipUtil;



public class Sendemailto {


	public static void To(String CommitersEmail, String folder){
		
		String path = folder;
		final String fromEmail = "githubuireport@gmail.com"; 
		final String password = "github1234"; 
		final String toEmail = CommitersEmail; 

		System.out.println("Email procedure starts");
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
		props.put("mail.smtp.socketFactory.port", "465"); //SSL Port
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
		props.put("mail.smtp.auth", "true"); //Enabling SMTP Authentication
		props.put("mail.smtp.port", "465"); //SMTP Port

		Authenticator auth = new Authenticator() {
			//override the getPasswordAuthentication method
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};

		Session session = Session.getDefaultInstance(props, auth);
		
		try{
			MimeMessage msg = new MimeMessage(session);
			
			msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
			msg.addHeader("format", "flowed");
			msg.addHeader("Content-Transfer-Encoding", "8bit");

			msg.setFrom(new InternetAddress("githubuireport@gmail.com", "darshan jethwa"));

			msg.setReplyTo(InternetAddress.parse("dbjethwa2810@gmail.com", false));

			msg.setSubject("Tests Results", "UTF-8");

			msg.setSentDate(new Date());

			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));

			// Create the message body part
			BodyPart messageBodyPart = new MimeBodyPart();
			
			
			messageBodyPart.setText("Hey! Tested you changes. Please find the attachment below. ");

			// Create a multipart message for attachment
			Multipart multipart = new MimeMultipart();

			// Set text message part
			multipart.addBodyPart(messageBodyPart);

			// Second part is the ZIP attachment of the tests results
			messageBodyPart = new MimeBodyPart();
			DataSource source = new FileDataSource(folder);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName("Tests image");
			
			messageBodyPart.setHeader("Content-ID", "<image>");
			multipart.addBodyPart(messageBodyPart);

			//Set the multipart message to the email message
			msg.setContent(multipart);

			// Send message
			Transport.send(msg);
			
			System.out.println("Email Sent Successfully");
			
		}catch (MessagingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

}

