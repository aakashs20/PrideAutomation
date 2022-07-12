package com.project.utilities;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.project.testbase.TestBase;



public class SendMailResults extends TestBase {
	static String email_username, email_password, set_Subject, message_Body_Part1;
	String[] listofmails;
	static SimpleDateFormat formatter = new SimpleDateFormat("_yyyy_MM_dd");
	static Date sysdate = new Date();
	String hostname = "Unknown";
	
	public String[] dataSetup()
	{ 
		try
		{
		    InetAddress addr;
		    addr = InetAddress.getLocalHost();
		    hostname = addr.getHostName();
		}
		catch (UnknownHostException ex)
		{
		    System.out.println("Hostname can not be resolved");
		}
		
		email_username = emailIDFrom;
		email_password = emailIDPassword;
		set_Subject = "SC Automation Execution Report " + formatter.format(sysdate);
		message_Body_Part1 = "<table style=\"border:3px solid black;width:70%;border-collapse: collapse;font-family:'Trebuchet MS', Arial, Helvetica, sans-serif\">\n" + 
				"	<tr>\n" + 
				"		<th colspan=\"2\" style=\"text-align:center;background-color:#ffff00;color: black;font-style: italic; padding-top: 10px;padding-bottom: 10px\">Automation Run on Machine - "+ hostname +"</th>\n" + 
				"  	</tr>\n" + 
				"	<tr>\n" + 
				"		<th colspan=\"2\" style=\"text-align:center;background-color:#9bc2e6;color: black;border:2px solid black;padding-top: 10px;padding-bottom: 10px\">Execution Details</th></tr>\n" + 
				" 	<tr>\n" + 
				"    	<td style=\"text-align:left;background-color:white;color: black;border: 1px solid #ddd;padding: 8px\">Execution Status</td>\n" + 
				"    	<td style=\"text-align:left;background-color:white;color: black;border: 1px solid #ddd;padding: 8px\">Done</td>\n" + 
				"  	</tr>\n" + 
				"  	<tr>\n" + 
				"    	<td style=\"text-align:left;background-color:white;color: black;border: 1px solid #ddd;padding: 8px\">Application Url</td>\n" + 
				"    	<td style=\"text-align:left;background-color:white;color: black;border: 1px solid #ddd;padding: 8px\">"+ applicationUrl +"</td>\n" + 
				"  	</tr>\n" + 
				"  	<tr>\n" + 
				"    	<td style=\"text-align:left;background-color:white;color: black;border: 1px solid #ddd;padding: 8px\">Username</td>\n" + 
				"    	<td style=\"text-align:left;background-color:white;color: black;border: 1px solid #ddd;padding: 8px\">"+ adminUsername +"</td>\n" + 
				"  	</tr>\n" + 
				"  	<tr>\n" + 
				"    	<td colspan=\"2\" style=\"text-align:center;background-color:white;color: black;font-style: italic;border: 1px solid #ddd;padding: 8px\">Please find attached report for more details</td>\n" + 
				"  	</tr>\n" + 
				"</table>";

		if(emailIDTo != null || emailIDTo != "") {
			listofmails = emailIDTo.split(",");
			System.out.println("Email to be sent to - ");
		      for(String email : listofmails) {
		         System.out.println(email);
		      }
		}
		
		return listofmails;

	}

	public  void mailconfig(String path, String fileName)
	{
		// Create object of Property file
		Properties props = new Properties();

		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", mailServer);
		props.put("mail.smtp.port", "587");

		listofmails =dataSetup();

		// This will handle the complete authentication
		Session session = Session.getInstance(props);

		try {

			// Create object of MimeMessage class
			Message message = new MimeMessage(session);

			// Set the from address
			message.setFrom(new InternetAddress(email_username));

			InternetAddress[] address = new InternetAddress[listofmails.length];
			for(int count = 0; count<listofmails.length;count++)
			{
				address[count]= new InternetAddress(listofmails[count]);

			}
			// Set the recipient address
			message.setRecipients(Message.RecipientType.TO,address);

			// Add the subject link
			message.setSubject(set_Subject);

			// Create object to add multimedia type content
			BodyPart messageBodyPart1 = new MimeBodyPart();

			// Set the body of email
			messageBodyPart1.setContent(message_Body_Part1, "text/html");
			
			// Create another object to add another content
			MimeBodyPart messageBodyPart2 = new MimeBodyPart();

			String filePath=path+fileName;
			// Create data source and pass the filename
			DataSource source = new FileDataSource(filePath);

			// set the handler
			messageBodyPart2.setDataHandler(new DataHandler(source));

			// set the file
			messageBodyPart2.setFileName(fileName);

			// Create object of MimeMultipart class
			Multipart multipart = new MimeMultipart();

			// add body part 1
			multipart.addBodyPart(messageBodyPart2);

			// add body part 2
			multipart.addBodyPart(messageBodyPart1);

			// set the content
			message.setContent(multipart);

			// finally send the email
			Transport transport = session.getTransport("smtp");
	        transport.connect("smtp.gmail.com", emailIDFrom, email_password);
			Transport.send(message, emailIDUsername, email_password);

			logInfo("=====Email Sent=====");

		    
		} catch (MessagingException e) {

			logError("Failed to Send email : " + e.getMessage());
		}

	}
}


