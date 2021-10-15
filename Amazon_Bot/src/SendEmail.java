import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;



public class SendEmail {
	
public SendEmail(String emailToSend) throws Exception
{		
		
		
	 	//final String username = "ap-nbv@nbvresorts.com";
	    //final String password = "Villagelife2020!+";
	
	    final String username = "buenavistajunry@gmail.com";
        final String password = "Killingmesoftly2";

	    Properties props = new Properties();
	    props.put("mail.smtp.ssl.protocols", "TLSv1.2");
	    props.put("mail.smtp.auth", true);
	    props.put("mail.smtp.starttls.enable", true);
	    props.put("mail.smtp.host", "smtp.gmail.com");
	    props.put("mail.smtp.port", "587");

	    Session session = Session.getInstance(props,
	            new javax.mail.Authenticator() {
	                protected PasswordAuthentication getPasswordAuthentication() {
	                    return new PasswordAuthentication(username, password);
	                }
	            });
	  
	    try {
	        Message message = new MimeMessage(session);
	        message.setFrom(new InternetAddress("buenavistajunry@gmail.com"));
	        message.setRecipients(Message.RecipientType.TO,
	        		InternetAddress.parse("junrybuenavista@yahoo.com"));
	        message.setSubject("Testing Subject");
	        
	        URL url = new URL(emailToSend);
	        InputStream is = url.openStream();
	        int ptr = 0;
	        StringBuffer buffer = new StringBuffer();
	        while ((ptr = is.read()) != -1) {
	            buffer.append((char)ptr);
	        }
	      
	        
	        message.setContent(buffer.toString(),"text/html" );
	        
	        Transport.send(message);
	        
	        System.out.println("Email sent!");
	  
		    } 
		    catch (MessagingException e) {
		        e.printStackTrace();
		        System.out.println("Sending Mail waiting:");
		        Thread.sleep(1000);
		    }
	    
	}
public void getFileList() {
	
}

public static void main(String[] args)throws Exception {
	new SendEmail("");
			
  }
}