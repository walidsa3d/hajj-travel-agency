package login;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.primefaces.context.RequestContext;

import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.Pilgrim;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.pilgrimManagement.PilgrimManLocal;


@ManagedBean
@RequestScoped

public class PilgrimRegBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -800242783454146168L;

	@EJB
	private PilgrimManLocal pml;
	
	private Pilgrim pilgrim=new Pilgrim();

	public PilgrimRegBean() {
	}

	public Pilgrim getPilgrim() {
		return pilgrim;
	}

	public void setPilgrim(Pilgrim pilgrim) {
		this.pilgrim = pilgrim;
	}
	public String registerPilgrim() {
		String navigateTo=null;
		pml.createPilgrim(pilgrim);
		navigateTo="/pages/login.jsf";
		return navigateTo;
	}
	public void doCancel() {
        RequestContext.getCurrentInstance().reset(":form0");  
	}
	

}
