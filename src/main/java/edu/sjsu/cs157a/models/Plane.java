package edu.sjsu.cs157a.models;

public class Plane {

	public Plane() { }
	
	public Plane(String manufacturer, String model, Integer capacity) {
		super();
		this.manufacturer = manufacturer;
		this.model = model;
		this.capacity = capacity;
	} 

	public Integer getpID() {
		return pID;
	}

	public void setpID(Integer pID) {
		this.pID = pID;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pID == null) ? 0 : pID.hashCode());
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
		Plane other = (Plane) obj;
		if (pID == null) {
			if (other.pID != null)
				return false;
		} else if (!pID.equals(other.pID))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Plane [pID=" + pID + ", manufacturer=" + manufacturer + ", model=" + model + ", capacity=" + capacity
				+ "]";
	}

	private Integer pID;
	private String manufacturer;
	private String model;
	private Integer capacity;
}
