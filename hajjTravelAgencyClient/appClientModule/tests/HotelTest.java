package tests;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.Hotel;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.hotelManagement.HotelManRemote;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgencyClient.util.ServiceLocator;

public class HotelTest {
	private static HotelManRemote remote;

	@BeforeClass
	   public static void getProxy() {
				remote = (HotelManRemote) ServiceLocator.getInstance()
						.getRemoteInterface(
								"ejb:/tn.esprit.pDevJEE.4infoB2.hajjTravelAgencyEJB/HotelMan!"
										+ HotelManRemote.class.getCanonicalName());
		}

		@Test
		public final void testAddCompany() {
			int b=remote.getAllHotels().size();
			Hotel pr=new Hotel();	
			pr.setAddressHotel("Sousse");
			pr.setLocationHotel("Mecca");
			pr.setNameHotel("Movenpick");
			pr.setPhoneHotel(888);
			remote.addHotel(pr);			
			int a=remote.getAllHotels().size();	
			assertEquals(b+1,a);


}

	@Test
	public final void test() {
	}

}
