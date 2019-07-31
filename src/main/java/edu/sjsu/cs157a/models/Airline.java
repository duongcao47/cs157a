package edu.sjsu.cs157a.models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Airline {
	
	public Airline() {}

	public Airline(String companyName, String hq, Date founded, String icao) {
		super();
		this.companyName = companyName;
		this.hq = hq;
		this.founded = founded;
		this.icao = icao;
	}

	public Integer getAlID() {
		return alID;
	}

	public void setAlID(Integer alID) {
		this.alID = alID;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getHq() {
		return hq;
	}

	public void setHq(String hq) {
		this.hq = hq;
	}

	public Date getFounded() {
		return founded;
	}

	public void setFounded(Date founded) {
		this.founded = founded;
	}

	public String getIcao() {
		return icao;
	}

	public void setIcao(String icao) {
		this.icao = icao;
	}

	public List<Plane> getFleet() {
		return fleet;
	}

	public void setFleet(List<Plane> fleet) {
		this.fleet = fleet;
	}
	
	public void addToFleet(Plane p) { 
		fleet.add(p);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alID == null) ? 0 : alID.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Airline other = (Airline) obj;
		if (alID == null) {
			if (other.alID != null)
				return false;
		} else if (!alID.equals(other.alID))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Airline [alID=" + alID + ", companyName=" + companyName + ", hq=" + hq + ", founded=" + founded
				+ ", icao=" + icao + ", fleet=" + fleet + "]";
	}

	private Integer alID;
	private String companyName;
	private String hq;
	private Date founded;
	private String icao;
	private List<Plane> fleet;
}
