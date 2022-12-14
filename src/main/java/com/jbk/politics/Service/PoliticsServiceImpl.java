package com.jbk.politics.Service;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jbk.politics.Controller.PoliticsContoller;
import com.jbk.politics.Dao.PoliticsDaoImpl;
import com.jbk.politics.Entity.Admin;
import com.jbk.politics.Entity.PoliticiansDetails;

@Service
public class PoliticsServiceImpl implements PoliticsService{
	
	Logger logger = LoggerFactory.getLogger(PoliticsServiceImpl.class);
	
	@Autowired
	private PoliticsDaoImpl dao;
	
	private String OTP1;
	
	public HashMap<String,String> forAdminLoginOTPGeneration(Admin admin){
		boolean adminDetails = dao.getAdminDetails(admin);
		
		HashMap<String,String> hm= new HashMap<String,String>();
		if(adminDetails) {
			logger.info("Service Method => OTP Generated");
			String OTP1 = new DecimalFormat("000000").format(new Random().nextInt(999999));
			hm.put(" OTP Generated :", OTP1);
			this.OTP1=OTP1;//VERY VERY IMPORTANT
		}else {
			logger.warn("Service Method => OTP Generation Failed");
		}
		return hm;
	}
	
	public String forAdminLoginEnterOTP(String OTP) {
		String login=null;
		try {
			if(OTP.equals(OTP1)) {
				logger.info("Service Method => Loginned Successfully");
				login = "Loginned Successfully";
				}else {
					logger.error("Service Method =>Loginned Failed");
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
		this.OTP1 = null;
		}
		return login;
		
	}

	@Override
	public boolean savaPoliticianData(PoliticiansDetails politiciansDetails) {
		
		return dao.savaPoliticianData(politiciansDetails);
	}
}
