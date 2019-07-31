package edu.sjsu.cs157a.models;

import java.sql.Date;
import java.util.Set;

public class User {
	
	public User() { } 

	public User(String fname, String lname, String email, String password, Date birthDate, Boolean isAdmin) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.password = password;
		this.birthDate = birthDate;
		this.age = age;
		this.isAdmin = isAdmin;
	}

	public Integer getuID() {
		return uID;
	}

	public void setuID(Integer uID) {
		this.uID = uID;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	} 
	
	public Set<Flight> getTrips() {
		return trips;
	}

	public void setTrips(Set<Flight> trips) {
		this.trips = trips;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uID == null) ? 0 : uID.hashCode());
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
		User other = (User) obj;
		if (uID == null) {
			if (other.uID != null)
				return false;
		} else if (!uID.equals(other.uID))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [uID=" + uID + ", fname=" + fname + ", lname=" + lname + ", email=" + email + ", password="
				+ password + ", birthDate=" + birthDate + ", age=" + age + ", isAdmin=" + isAdmin + ", trips=" + trips
				+ "]";
	}

	private Integer uID;
	private String fname;
	private String lname; 
	private String email;
	private String password;
	private Date birthDate;
	private Integer age;
	private Boolean isAdmin;
	private Set<Flight> trips;
}
