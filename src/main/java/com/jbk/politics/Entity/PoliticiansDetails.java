package com.jbk.politics.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PoliticiansDetails {

	@Id
	private String politicianName;
	
	private String politicianLocation;
	public PoliticiansDetails() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "PoliticiansDetails [politicianName=" + politicianName + ", politicianLocation=" + politicianLocation
				+ "]";
	}

	public PoliticiansDetails(String politicianName, String politicianLocation) {
		super();
		this.politicianName = politicianName;
		this.politicianLocation = politicianLocation;
	}

	public String getPoliticianName() {
		return politicianName;
	}

	public void setPoliticianName(String politicianName) {
		this.politicianName = politicianName;
	}

	public String getPoliticianLocation() {
		return politicianLocation;
	}

	public void setPoliticianLocation(String politicianLocation) {
		this.politicianLocation = politicianLocation;
	}

}
