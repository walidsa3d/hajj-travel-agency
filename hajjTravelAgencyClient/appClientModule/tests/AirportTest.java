package tests;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.Airport;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.airTransportManagement.AirportManRemote;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgencyClient.util.ServiceLocator;

public class AirportTest {
	private static AirportManRemote remote;

	@BeforeClass
	   public static void getProxy() {
				remote = (AirportManRemote) ServiceLocator.getInstance()
						.getRemoteInterface(
								"ejb:/tn.esprit.pDevJEE.4infoB2.hajjTravelAgencyEJB/AirportMan!"
										+ AirportManRemote.class.getCanonicalName());
		}

		@Test
		public final void testCreatePrivilege() {
			int b=remote.getAllAirports().size();
			Airport pr=new Airport();
			pr.setAddressAirport("Tunis");
			pr.setNameAirport("Bourguiba");
			pr.setPhone("999");
			remote.addAirport(pr);
			int a=remote.getAllAirports().size();
			assertEquals(b+1,a);
		}

	@Test
	public final void test() {
		
	}
	

}
