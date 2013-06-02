package tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.messageService;


import java.util.Date;

import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


/**
 * Session Bean implementation class MService
 */
@Stateless

public class MService implements MServiceRemote, MServiceLocal {
	@Resource(mappedName="java:jboss/mail/Default")
    private Session mySession;


    /**
     * Default constructor. 
     */
    public MService() {
    }

	@Override
	public void sendMail(String name, String email, String username, String password) {
		try
        {
              Message message = new MimeMessage(mySession);
   
              message.setFrom(new InternetAddress("walid.sa3d@gmail.com")); //<= IGNORE THIS, use default from user
              message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
              message.setSubject("Registration for hajj");
              String msg="Hello"+" "+name+". You have completed your registration for hajj. Congratulations!" +
              		"<p><b>Username :  </b>"+username+"</p>" +
            		"<p><b>Password :  </b>"+password+"</p>";
              Multipart mp = new MimeMultipart();

              MimeBodyPart htmlPart = new MimeBodyPart();
              htmlPart.setContent(msg, "text/html");
              mp.addBodyPart(htmlPart);

              message.setContent(mp);
              		
   
              Transport.send(message);
   
        }
        catch(Exception e)
        {
              e.printStackTrace();
        }
		
       
	}
	  @Schedule(dayOfWeek="Wed", hour = "5") //TODO scheduled emails
	  
	       public void backgroundProcessing()
	  
	          {
	  
	             System.out.println("\n\n\t AutomaticSchedulerBean's backgroundProcessing() called....at: "+new Date());
	  
	          }


}
