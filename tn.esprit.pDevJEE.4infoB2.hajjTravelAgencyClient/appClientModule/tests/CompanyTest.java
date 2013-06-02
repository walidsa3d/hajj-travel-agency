package tests;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.Company;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.airTransportManagement.CompanyManRemote;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgencyClient.util.ServiceLocator;

public class CompanyTest {

	private static CompanyManRemote remote;

	@BeforeClass
	   public static void getProxy() {
				remote = (CompanyManRemote) ServiceLocator.getInstance()
						.getRemoteInterface(
								"ejb:/tn.esprit.pDevJEE.4infoB2.hajjTravelAgencyEJB/CompanyMan!"
										+ CompanyManRemote.class.getCanonicalName());
		}

		@Test
		public final void testAddCompany() {
			int b=remote.getAllCompanies().size();
			Company pr=new Company();	
			pr.setAddressCompany("Tunis");
			pr.setEmailCompany("conatct@company.org");
			pr.setNameCompany("Air France");
			pr.setPhone("333");
			remote.addCompany(pr);
			int a=remote.getAllCompanies().size();	
			assertEquals(b+1,a);


}
}
