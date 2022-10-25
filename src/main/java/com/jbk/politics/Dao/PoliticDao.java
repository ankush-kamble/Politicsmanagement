package com.jbk.politics.Dao;

import com.jbk.politics.Entity.Admin;
import com.jbk.politics.Entity.PoliticiansDetails;

public interface PoliticDao {
	
	public boolean getAdminDetails(Admin admin) ;
	
	public boolean savaPoliticianData(PoliticiansDetails politiciansDetails);
}
