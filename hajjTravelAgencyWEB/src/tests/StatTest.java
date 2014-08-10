package tests;

import javax.ejb.EJB;

import org.junit.Test;

import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.pilgrimManagement.PilgrimManLocal;

public class StatTest {
	@EJB
	PilgrimManLocal pml;



	@Test
	public final void test() {
		System.out.println(pml.getPilgrimsByGender("Male").size());
	}

}
