package login;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.Pilgrim;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.pilgrimManagement.PilgrimManLocal;


@ManagedBean
@ViewScoped
public class PilgrimBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5121633567423479246L;

	@EJB
	private PilgrimManLocal pml;
	
	private Pilgrim pilgrim=new Pilgrim();
	private List<Pilgrim> pilgrims;
	private boolean formDisplayed = false;
//	private int selectedPilgrimId = -1;

	
	@PostConstruct
	public void init(){
		pilgrims=pml.getAllPilgrims();
		}
	
	public PilgrimBean() {
	}



	public List<Pilgrim> getPilgrims() {
		return pilgrims;
	}



	public void setPilgrims(List<Pilgrim> pilgrims) {
		this.pilgrims = pilgrims;
	}

	public Pilgrim getPilgrim() {
		return pilgrim;
	}

	public void setPilgrim(Pilgrim pilgrim) {
		this.pilgrim = pilgrim;
	}

	public boolean isFormDisplayed() {
		return formDisplayed;
	}

	public void setFormDisplayed(boolean formDisplayed) {
		this.formDisplayed = formDisplayed;
	}


//	public int getSelectedPilgrimId() {
//		return selectedPilgrimId;
//	}
//
//	public void setSelectedPilgrimId(int selectedPilgrimId) {
//		this.selectedPilgrimId = selectedPilgrimId;
//	}
	public void doNew(){
		pilgrim=new Pilgrim();
		formDisplayed = true;
	}
	public void doCancel(){
		pilgrim=new Pilgrim();
		formDisplayed = true;
	}
	public void doDelete(){
		pml.deletePilgrim(pilgrim.getPilgrimCin());
		pilgrims=pml.getAllPilgrims();
		formDisplayed = false;
	}
	public void doSaveOrUpdate(){
		pml.updatePilgrim(pilgrim);
		pilgrims=pml.getAllPilgrims();
		formDisplayed = false;
	}
	public void onRowSelect(){
		formDisplayed = true;
	}

}
