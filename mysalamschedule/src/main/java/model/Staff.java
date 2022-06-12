package model;

/**
 * User.java
 * This is a model class represents a User entity
 * @author Ramesh Fadatare
 *
 */
public class Staff {
	protected int id;
	protected String name;
	protected String address;
	protected String phone;
	protected String email;
	protected String role;
	
	public Staff() {
	}
	
	public Staff(String name,String address, String phone, String email, String role) {
		super();
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.role = role;
	}

	public Staff(int id, String name, String address, String phone, String email, String role) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.role = role;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getEmail() {
		return email;
	}
	
	
	public void setEmail(String email) {
		this.email = email;
	}
	

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
