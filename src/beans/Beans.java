package beans;

//JavaBeans Class to process temporary data during Runtime

public class Beans {
	
	private long id; 
	private String name;
	private String password;
	private String realName;
	private String phone;
	
	private String zip;
	private String street;
	private String neighborhood;
	private String city;
	private String state;
	private String ibgeCode;
	
	
	//Don't need a constructor since it will be used sometimes only to call one of its methods (as in Servlet's doPut())
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getIbgeCode() {
		return ibgeCode;
	}

	public void setIbgeCode(String ibgeCode) {
		this.ibgeCode = ibgeCode;
	}
	
	

}
