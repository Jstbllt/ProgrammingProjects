package rest.shop.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User {
	private static int number_user =4;
	private String id;
	private String firstname;
	private String lastname;
	private String city;
	
	public User() {
		
	}
	
	public User(String id, String firstname, String lastname, String city) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.city = city;
		this.id = id;
	}
	
	public User(String firstname, String lastname, String city) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.city = city;
		number_user +=1;
		this.id = String.valueOf(number_user);
	}

	public String getId() {
		return id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
}
