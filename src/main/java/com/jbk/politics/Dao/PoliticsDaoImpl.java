package com.jbk.politics.Dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jbk.politics.Entity.Admin;
import com.jbk.politics.Entity.PoliticiansDetails;


@Repository
public class PoliticsDaoImpl implements PoliticDao {

	Logger logger = LoggerFactory.getLogger(PoliticsDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	public boolean getAdminDetails(Admin admin) {

		Session session = sessionFactory.openSession();

		boolean isCorrect = false;

		try {
			Admin admin2 = session.get(Admin.class, admin.getUsername());

			if (admin2.getUsername().equals(admin.getUsername()) && admin2.getPassword().equals(admin.getPassword())) {

				isCorrect = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return isCorrect;

	}

	@Override
	public boolean savaPoliticianData(PoliticiansDetails politiciansDetails) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		boolean isSaved = false;
		try {
			PoliticiansDetails politiciansDetails2 = session.get(PoliticiansDetails.class,
					politiciansDetails.getPoliticianName());
			if (politiciansDetails2 == null) {
				session.save(politiciansDetails);
				transaction.commit();
				isSaved = true;
				logger.info(" Dao Method => Politician saved Successfully" + " savaPoliticianData");
			} else {
				logger.error("Dao Method => Politician Save Failed " + " savaPoliticianData");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return isSaved;
	}

}
