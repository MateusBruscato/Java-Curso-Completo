package entities;

import java.text.ParseException;
import java.util.Date;

public class Client {

	private String name;
	private String email;
	private Date birthDate;

	
	
	//Constructors
	public Client() {
		
	}

	public Client(String name, String email, Date birthDate) throws ParseException {
		super();
		this.name = name;
		this.email = email;
		this.birthDate = birthDate;
	}

	//Get Set
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	
}
