package tests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.messageService.MServiceRemote;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgencyClient.util.ServiceLocator;

public class MailTest {

	private static MServiceRemote remote;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
			remote = (MServiceRemote) ServiceLocator.getInstance()
					.getRemoteInterface(
							"ejb:/tn.esprit.pDevJEE.4infoB2.hajjTravelAgencyEJB/MService!"
									+ MServiceRemote.class.getCanonicalName());
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	
	}

	@Test
	public final void testSendMail() {
		remote.sendMail("walid", "walid.sa3d@gmail.com", "walda", "saad");
}

}
