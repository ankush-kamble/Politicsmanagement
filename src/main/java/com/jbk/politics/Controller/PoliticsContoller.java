package com.jbk.politics.Controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jbk.politics.Entity.Admin;
import com.jbk.politics.Entity.PoliticiansDetails;
import com.jbk.politics.Exception.AdminDetailNotFoundException;
import com.jbk.politics.Exception.OTPWrongEnteredException;
import com.jbk.politics.Exception.PoliticiansDetailNotFoundException;
import com.jbk.politics.Service.PoliticsServiceImpl;

@RestController
public class PoliticsContoller {

	@Autowired
	private PoliticsServiceImpl service;

	private boolean isLogined;

	@GetMapping(value = "/forAdminloginOTPGeneration")
	public ResponseEntity<HashMap<String, String>> forAdminLoginOTPGeneration(@RequestBody Admin admin) {
		HashMap<String, String> forAdminLoginOTPGeneration = service.forAdminLoginOTPGeneration(admin);
		if (forAdminLoginOTPGeneration != null) {
			return new ResponseEntity<HashMap<String, String>>(forAdminLoginOTPGeneration, HttpStatus.OK);
		} else {
			throw new AdminDetailNotFoundException(
					"Admin Details are Wrong please check once again :" + admin.getUsername());
		}

	}

	@GetMapping(value = "/forAdminLoginEnterOTP/{OTP}")
	public ResponseEntity<String> forAdminLoginEnterOTP(@PathVariable String OTP) {
		String forAdminLoginEnterOTP = service.forAdminLoginEnterOTP(OTP);
		if (forAdminLoginEnterOTP != null) {
			boolean isLogined = true; //VERY VERY IMPORTANT
			this.isLogined = isLogined;
			return new ResponseEntity<String>(forAdminLoginEnterOTP, HttpStatus.OK);
		} else {
			throw new OTPWrongEnteredException("OTP entered is wrong please regenerate it ");
		}
	}

//	@PostMapping(value="/savaPoliticianData")
//	public ResponseEntity<Boolean> savaPoliticianData(@RequestBody PoliticiansDetails politiciansDetails) {
//		boolean savaPoliticianData=false;
//		if(isLogined) {
//			savaPoliticianData = service.savaPoliticianData(politiciansDetails);
//			
//		}else {
//			throw new AdminDetailNotFoundException("Admin must login first for saving the data");
//		}
//		if (savaPoliticianData) {
//			return new ResponseEntity<Boolean>(savaPoliticianData,HttpStatus.OK);
//		} else {
//			throw new PoliticiansDetailNotFoundException("Politician Already Exists");
//		}
//			
//	}

	@PostMapping(value = "/savaPoliticianData")
	public ResponseEntity<Boolean> savaPoliticianData(@RequestBody PoliticiansDetails politiciansDetails) {
		boolean savaPoliticianData = false;
		if (isLogined) {
			savaPoliticianData = service.savaPoliticianData(politiciansDetails);
			if (savaPoliticianData) {
				return new ResponseEntity<Boolean>(savaPoliticianData, HttpStatus.OK);
				} else {
				throw new PoliticiansDetailNotFoundException("Politician Already Exists");
			}
		} else {
			throw new AdminDetailNotFoundException("Admin must login first for saving the data");
		}
	}
}
