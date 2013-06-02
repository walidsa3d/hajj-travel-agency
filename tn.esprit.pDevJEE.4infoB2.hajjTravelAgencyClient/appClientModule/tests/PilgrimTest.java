package tests;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.Pilgrim;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.pilgrimManagement.PilgrimManRemote;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgencyClient.util.ServiceLocator;

public class PilgrimTest {
	static PilgrimManRemote remote;

	@BeforeClass
	
   public static void getProxy() {
			remote = (PilgrimManRemote) ServiceLocator.getInstance()
					.getRemoteInterface(
							"ejb:/tn.esprit.pDevJEE.4infoB2.hajjTravelAgencyEJB/PilgrimMan!"
									+ PilgrimManRemote.class.getCanonicalName());
	}

	@Test
	public final void testCreatePilgrim() {
		int b = remote.getAllPilgrims().size();
		Pilgrim p=new Pilgrim();
		p.setPilgrimFirstName("walid");
		p.setPilgrimLastName("Saad");
		p.setPilgrimGender("male");
		p.setPilgrimPhone(999);
		p.setPilgrimAddress("tunis");
		p.setPilgrimBirthDate(null);
		p.setPilgrimCin(33890905);
		p.setPilgrimPassport(23377890);
		p.setPilgrimEmail("walid.sa3d@gmail.com");
		remote.createPilgrim(p);
		int a=remote.getAllPilgrims().size();
		assertEquals(b+1,a);		
		
	}
	@Test
public final void testDeletePilgrim() {
	int b = remote.getAllPilgrims().size();
	Pilgrim p=new Pilgrim();
	p.setPilgrimCin(33890903);
	remote.deletePilgrim(p.getPilgrimCin());
	int a=remote.getAllPilgrims().size();
	assertEquals(b-1,a);
	
}


}
