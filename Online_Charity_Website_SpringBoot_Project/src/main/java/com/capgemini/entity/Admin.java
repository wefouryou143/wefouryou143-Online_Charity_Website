package com.capgemini.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("admin")
@Scope(scopeName = "prototype")
@Entity
@Table(name = "ADMIN_TABLE")
public class Admin {

	@Id
	@Column(name = "ADMIN_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int adminId;

	@Column(name = "FIRST_NAME", length = 50, nullable = false)
	@Pattern(regexp = "^[a-zA-Z]+$", message = "Please Enter Valid Name")
	private String firstName;

	@Column(name = "LAST_NAME", length = 50, nullable = false)
	@Pattern(regexp = "^[a-zA-Z]+$", message = "Please Enter Valid Name")
	private String lastName;

	@Column(name = "PHONE_NUMBER", nullable = false)
	@Pattern(regexp = "^[0-9]{10}", message = "Mobile Number is Invalid")
	private String phoneNumber;

	@Column(name = "EMAIL", length = 20, nullable = false, unique = true)
	@Email(message = "Please Enter Valid Email")
	@NotBlank
	private String email;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ADDRESS_ID")
	@Autowired
	@Valid
	private Address address;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "USER_ID")
	@Autowired
	@Valid
	private User user;

	public Admin() {

	}

	public Admin(int adminId, String firstName, String lastName, String phoneNumber, String email) {
		super();
		this.adminId = adminId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}

	public Admin(Address address) {
		super();
		this.address = address;
	}

	public Admin(User user) {
		super();
		this.user = user;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getAdminId() {
		return adminId;
	}

	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", firstName=" + firstName + ", lastName=" + lastName + ", phoneNumber="
				+ phoneNumber + ", email=" + email + ", address=" + address + ", user=" + user + "]";
	}

}