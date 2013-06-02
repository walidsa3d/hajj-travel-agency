package login;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.event.SelectEvent;

import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.PGroup;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.pilgrimManagement.GroupManLocal;




@ManagedBean
@ViewScoped
public class GroupBean {
	@EJB
	private GroupManLocal gml;
	
	private PGroup group=new PGroup();
	private List<PGroup> groups;
	private boolean formDisplayed = false;


	
	@PostConstruct
	public void init(){
		setGroups(gml.getAllGroups());
		}

	public GroupBean() {
	}

	public PGroup getGroup() {
		return group;
	}

	public void setGroup(PGroup group) {
		this.group = group;
	}

	public List<PGroup> getGroups() {
		return groups;
	}

	public void setGroups(List<PGroup> groups) {
		this.groups = groups;
	}

	public boolean isFormDisplayed() {
		return formDisplayed;
	}

	public void setFormDisplayed(boolean formDisplayed) {
		this.formDisplayed = formDisplayed;
	}
	public void doSaveOrUpdate(){
		gml.updateGroup(group);
		groups = gml.getAllGroups();
		formDisplayed = false;
	}
	
	public void doNew(){
		formDisplayed = true;
	}
	
	public void doCancel(){
		group = new PGroup();
		formDisplayed = false;
	}
	
	public void doDelete(){
		gml.removeGroup(group);
		groups = gml.getAllGroups();
		formDisplayed = false;
	}
	public void onRowSelect(SelectEvent event) {
		formDisplayed=true;
		
	}

}
