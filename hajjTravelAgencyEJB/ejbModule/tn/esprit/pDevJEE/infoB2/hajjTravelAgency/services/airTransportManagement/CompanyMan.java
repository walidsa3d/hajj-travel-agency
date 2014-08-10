package tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.airTransportManagement;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.Company;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.traceManagement.TraceManLocal;

/**
 * Session Bean implementation class CompanyMan
 */
@Stateless
public class CompanyMan implements CompanyManRemote {
	@PersistenceContext
	EntityManager em;
	@EJB
	TraceManLocal traceman;

    /**
     * Default constructor. 
     */
    public CompanyMan() {
    }

	@Override
	public void addCompany(Company company) {
em.persist(company);		
		traceman.traceIt("ADD", company);

	}

	@Override
	public void removeCompany(int idCompany) {
em.remove(em.merge(getCompanyById(idCompany)));		
		traceman.traceIt("DELETE", new Company());

	}

	@Override
	public void updateCompany(Company company) {
em.merge(company);		
		traceman.traceIt("UPDATE", company);

	}

	@Override
	public Company getCompanyById(int idCompany) {
		return em.find(Company.class, idCompany);
	}

	@Override
	public List<Company> getAllCompanies() {
		Query query=em.createQuery("Select c from Company c");
		return query.getResultList();

}
}
