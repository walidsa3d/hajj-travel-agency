package tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.packageManagement;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.Pakage;

@Remote
public interface PackageManRemote {
	public void addPackage(Pakage pack);
	public void updatePackage(Pakage pack);
	public void removePackage(Pakage pack);
	public Pakage findPackageById(int idPakage);
	public List<Pakage> getAllPackages();
	

}
