package tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.roleManagement;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.Role;

@Remote
public interface RoleManRemote {
	public void createRole(Role role);

	public void updateRole(Role role);

	public void deleteRole(String id);

	public Role getRoleById(String nameRole);

	public List<Role> getAllRoles();

}
