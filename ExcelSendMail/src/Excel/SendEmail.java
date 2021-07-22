/**
 * @Author		: Hasan Abdul Muhaimeen
 * @Date		: 17/07/21
 * @E-Mail		: abdulmuhaimeen2000@gmail.com
 * @FileName	: SendEmail.java
 * @Description	: This file will validate, Get UserName from E-mail and Send E-Mail with Attachment
 * @Dependancies: This file Requires SendExcelAttachmentByMail.java to execute the program
 */

package Excel;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.net.ssl.HttpsURLConnection;
import java.io.OutputStream;

import org.apache.commons.validator.routines.EmailValidator;

public class SendEmail {

	public static void sendFromGmail(String fromMail, String password, String toMail, String subject, String textBody,
			String attachmentFileName) {

		System.out.println("------------ Sending G-Mail -----------");
		System.out.println("Sending From : " + fromMail);
		System.out.println("Sending To : " + toMail);
		System.out.println("Attached FileName : " + attachmentFileName);
		// Get the properties
		Properties props = new Properties();
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
	

		// Get the Session object with Authentication.
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromMail, password);
			}
		});

		try {

			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(fromMail));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(toMail));

			// Set Subject: header field
			message.setSubject(subject);

			// create MimeBodyPart object and set your message text (Text Body)
			BodyPart messageTextBody = new MimeBodyPart();
			messageTextBody.setText(textBody);

			// create new MimeBodyPart object and set DataHandler object to this object
			// For Sending Attachment
			MimeBodyPart messageAttachment = new MimeBodyPart();
			DataSource source = new FileDataSource(attachmentFileName);
			messageAttachment.setDataHandler(new DataHandler(source));
			messageAttachment.setFileName(attachmentFileName);

			// Create Multipart object and add MimeBodyPart objects to this object
			// For Sending all data together
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageTextBody);
			multipart.addBodyPart(messageAttachment);

			// set the multiplart object to the message object
			message.setContent(multipart);

//			// Now set the actual message
//			message.setText(textBody);

			// Sending E-Mail
			Transport.send(message);

			System.out.println("Successfully Sent the Mail with an Attachment");

		} catch (MessagingException msgExcp) {
			msgExcp.printStackTrace();
		}
	}

	// Validate E-Mails and return the status as true if valid, otherwise False
	public static boolean isValidEmailAddress(String email) {
		// create the EmailValidator instance
		EmailValidator validator = EmailValidator.getInstance();
		// check for valid email addresses using isValid method
		return validator.isValid(email);
	}

	// Extract and Get the User-Name from the E-Mail
	public static String getUserName(String eMail) {
		int index = eMail.indexOf('@');
		eMail = eMail.substring(0, index);
		return eMail;
	}
}
