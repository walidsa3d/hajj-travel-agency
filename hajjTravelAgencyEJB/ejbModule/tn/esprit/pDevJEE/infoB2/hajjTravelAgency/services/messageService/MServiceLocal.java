package tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.messageService;

import javax.ejb.Local;

@Local
public interface MServiceLocal {
	public void sendMail(String name, String email, String username, String password);

}
