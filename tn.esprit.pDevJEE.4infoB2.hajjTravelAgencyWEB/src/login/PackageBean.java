package login;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.Pakage;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.packageManagement.PackageManLocal;

@ManagedBean
@SessionScoped
public class PackageBean {
	@EJB
	private PackageManLocal pml;
	private List<Pakage> packages;
	private Pakage pakage;
	
@PostConstruct
public void init() {
	packages=pml.getAllPackages();
}
	public PackageBean() {
	}
	public List<Pakage> getPackages() {
		return packages;
	}
	public void setPackages(List<Pakage> packages) {
		this.packages = packages;
	}
	public Pakage getPakage() {
		return pakage;
	}
	public void setPakage(Pakage pakage) {
		this.pakage = pakage;
	}
	

}
