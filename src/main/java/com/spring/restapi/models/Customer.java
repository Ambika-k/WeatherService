package com.spring.restapi.models;

//@Entity
public class Customer {
//	@Id
//	@GeneratedValue
//	private int id;
//	private String name;
//	private String email;
//	
	//@Transient
	private String captcha;
	
	//@Transient
	private String realCaptcha;
	
	//@Transient
	private String hiddenCaptcha;
	
	public Customer() {
		super();
	}

	/*
	 * public Customer( String name, String email, String captcha, String
	 * realCaptcha, String hiddenCaptcha) { super(); this.name = name; this.email =
	 * email; this.captcha = captcha; this.realCaptcha = realCaptcha;
	 * this.hiddenCaptcha = hiddenCaptcha; }
	 * 
	 * public int getId() { return id; }
	 * 
	 * public void setId(int id) { this.id = id; }
	 * 
	 * public String getName() { return name; }
	 * 
	 * public void setName(String name) { this.name = name; }
	 * 
	 * public String getEmail() { return email; }
	 * 
	 * public void setEmail(String email) { this.email = email; }
	 */
	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}

	public String getRealCaptcha() {
		return realCaptcha;
	}

	public void setRealCaptcha(String realCaptcha) {
		this.realCaptcha = realCaptcha;
	}

	public String getHiddenCaptcha() {
		return hiddenCaptcha;
	}

	public void setHiddenCaptcha(String hiddenCaptcha) {
		this.hiddenCaptcha = hiddenCaptcha;
	}

	/*
	 * @Override public String toString() { return "User [id=" + id + ", name=" +
	 * name + ", email=" + email + ", captcha=" + captcha + ", realCaptcha=" +
	 * realCaptcha + ", hiddenCaptcha=" + hiddenCaptcha + "]"; }
	 */

	
}
