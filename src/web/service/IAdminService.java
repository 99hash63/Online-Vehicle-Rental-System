package web.service;

import web.model.Admin;
import java.util.ArrayList;
import java.util.logging.Logger;

import web.service.IAdminService;


public interface IAdminService {

	public static final Logger adminLog = Logger.getLogger(IAdminService.class.getName());
	
	public void addAdmin(Admin admin);

	public Admin getAdminById(String adminID);
		
	public ArrayList<Admin> getAdmins();
		
	public Admin updateAdmin(String adminID, Admin admin);

	public void removeAdmin(String adminID);

	
}

