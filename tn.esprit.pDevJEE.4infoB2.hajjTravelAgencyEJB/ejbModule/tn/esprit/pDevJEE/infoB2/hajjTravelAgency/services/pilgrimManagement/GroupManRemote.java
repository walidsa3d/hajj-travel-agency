package tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.pilgrimManagement;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.PGroup;

@Remote
public interface GroupManRemote {
	public void addGroup(PGroup group);
	public void removeGroup(PGroup group);
	public void updateGroup(PGroup group);
	public PGroup findGroupById(int idGroup);
	public List<PGroup> getAllGroups();	

}
