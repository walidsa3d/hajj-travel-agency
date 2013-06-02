package tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.roleManagement;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.Privilege;

@Remote
public interface PrivilegeManRemote {
	public void createPrivilege(Privilege privilege);

	public void updatePrivilege(Privilege privilege);

	public void deletePrivilege(Privilege privilege);

	public Privilege getPrivilegeById(String idPrivilege);

	public List<Privilege> getAllPrivileges();

}
