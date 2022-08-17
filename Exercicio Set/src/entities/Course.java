package entities;

import java.util.ArrayList;
import java.util.List;

public class Course {

	private String name;
	private List<User> users;

	public Course(String name) {
		this.name = name;
		this.users = new ArrayList<User>();

	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
