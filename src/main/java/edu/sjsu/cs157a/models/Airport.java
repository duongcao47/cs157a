package edu.sjsu.cs157a.models;

public class Airport {

	public Airport() {}
	
	public Airport(String name, String city, String country, Double lat, Double lng) {
		super();
		this.name = name;
		this.city = city;
		this.country = country;
		this.lat = lat;
		this.lng = lng;
	}

	public Integer getApID() {
		return apID;
	}
	public void setApID(Integer apID) {
		this.apID = apID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getIata() {
		return iata;
	}
	public void setIata(String iata) {
		this.iata = iata;
	}
	public String getIcao() {
		return icao;
	}
	public void setIcao(String icao) {
		this.icao = icao;
	}
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	public Double getLng() {
		return lng;
	}
	public void setLng(Double lng) {
		this.lng = lng;
	}
	public Integer getAltitude() {
		return altitude;
	}
	public void setAltitude(Integer altitude) {
		this.altitude = altitude;
	}
	public Integer getTzOffSet() {
		return tzOffSet;
	}
	public void setTzOffSet(Integer tzOffSet) {
		this.tzOffSet = tzOffSet;
	}
	public String getDst() {
		return dst;
	}
	public void setDst(String dst) {
		this.dst = dst;
	}
	public String getTz() {
		return tz;
	}
	public void setTz(String tz) {
		this.tz = tz;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apID == null) ? 0 : apID.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((tz == null) ? 0 : tz.hashCode());
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
		Airport other = (Airport) obj;
		if (apID == null) {
			if (other.apID != null)
				return false;
		} else if (!apID.equals(other.apID))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (tz == null) {
			if (other.tz != null)
				return false;
		} else if (!tz.equals(other.tz))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Airport [apID=" + apID + ", name=" + name + ", city=" + city + ", country=" + country + ", iata=" + iata
				+ ", icao=" + icao + ", lat=" + lat + ", lng=" + lng + ", altitude=" + altitude + ", tzOffSet="
				+ tzOffSet + ", dst=" + dst + ", tz=" + tz + ", type=" + type + ", source=" + source + "]";
	}

	private Integer apID;
	private String name;
	private String city;
	private String country;
	private String iata;
	private String icao;
	private Double lat;
	private Double lng;
	private Integer altitude;
	private Integer tzOffSet;
	private String dst;
	private String tz;
	private String type;
	private String source;
}
