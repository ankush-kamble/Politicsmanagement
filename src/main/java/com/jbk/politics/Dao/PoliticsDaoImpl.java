package com.jbk.politics.Dao;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jbk.politics.Entity.Admin;
import com.jbk.politics.Entity.PoliticiansDetails;

@Repository
public class PoliticsDaoImpl implements PoliticDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	public boolean getAdminDetails(Admin admin) {
		
		Session session = sessionFactory.openSession();
		
		boolean isCorrect =false;
		
		try {
			Admin admin2 = session.get(Admin.class, admin.getUsername());
			if(admin2.getUsername().equals(admin.getUsername()) && admin2.getPassword().equals(admin.getPassword())) {
				isCorrect=true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isCorrect;
		
	}


	@Override
	public boolean savaPoliticianData(PoliticiansDetails politiciansDetails) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		boolean isSaved=false;
		
		PoliticiansDetails politiciansDetails2 = session.get(PoliticiansDetails.class, politiciansDetails.getPoliticianName());
		if (politiciansDetails2 == null) {
			session.save(politiciansDetails);
			transaction.commit();
			isSaved=true;
		}
		return isSaved;
	}
	
	
}
