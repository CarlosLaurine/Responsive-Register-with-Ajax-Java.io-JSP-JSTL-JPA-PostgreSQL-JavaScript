package beans;

public class Phone {
	private Long id;
	private String number;
	private String type;
	private Long userRegister;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Long getUserRegister() {
		return userRegister;
	}
	public void setUserRegister(Long userRegister) {
		this.userRegister = userRegister;
	}

	
}
