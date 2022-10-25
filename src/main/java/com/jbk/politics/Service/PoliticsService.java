package com.jbk.politics.Service;

import java.util.HashMap;

import com.jbk.politics.Entity.Admin;
import com.jbk.politics.Entity.PoliticiansDetails;

public interface PoliticsService {
	
	public HashMap<String,String> forAdminLoginOTPGeneration(Admin admin);
	
	public boolean savaPoliticianData(PoliticiansDetails politiciansDetails);
}
