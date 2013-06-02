package tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.airTransportManagement;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.Company;

@Remote
public interface CompanyManRemote {
	public void addCompany(Company company);	
	public void removeCompany(int idCompany);
	public void updateCompany(Company company);
	public Company getCompanyById(int idCompany);
	public List<Company> getAllCompanies();

}
