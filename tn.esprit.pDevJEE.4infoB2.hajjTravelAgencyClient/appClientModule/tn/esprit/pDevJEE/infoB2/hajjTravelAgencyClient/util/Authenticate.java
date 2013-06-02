package tn.esprit.pDevJEE.infoB2.hajjTravelAgencyClient.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.Role;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.persistence.User;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.roleManagement.RoleManRemote;
import tn.esprit.pDevJEE.infoB2.hajjTravelAgency.services.userManagement.UserManRemote;

public class Authenticate {
	private UserManRemote remote;
	private RoleManRemote remote2;

	public Authenticate() {		
		
	}
	public Role auth(String username, String password){
		remote = (UserManRemote) ServiceLocator.getInstance()
				.getRemoteInterface(
						"ejb:/tn.esprit.pDevJEE.4infoB2.hajjTravelAgencyEJB/UserMan!"
								+ UserManRemote.class.getCanonicalName());
		List<User> users=remote.getAllUsers();
		for(User user:users){
			if(user.getUserName().equals(username)&&user.getUserPassword().equals(password))
			{
				createSession(username, user.getUserRole().toString());
				return user.getUserRole();

			}
				
		}
		return null;
		
	}
	public void createSession(String username, String userRole) {
    	Properties prop = new Properties();
		
	  	try {
	  		
    		prop.setProperty("user", username);
    		prop.setProperty("role", userRole); 
    		prop.store(new FileOutputStream("sessions.properties"), null);
 
    	} catch (IOException ex) {
    		ex.printStackTrace();
        }

	}
	public String loadSession() {
		Properties prop = new Properties();
		 
    	try {
    		prop.load(new FileInputStream("sessions.properties")); 
            String username=prop.getProperty("user");
            return username;
            
 
    	} catch (IOException ex) {
    		ex.printStackTrace();
    		return null;
        }
		
	}
	
}
