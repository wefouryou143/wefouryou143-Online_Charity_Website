package com.capgemini.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("donor")
@Scope(scopeName = "prototype")
@Entity
@Table(name = "DONOR_TABLE")
public class Donor {

	@Id

	@Column(name = "DONOR_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int donorId;

	@Column(name = "FIRST_NAME", length = 50, nullable = false)
	@Pattern(regexp = "^[a-zA-Z]+$", message = "Please Enter Valid Name")
	private String firstName;

	@Column(name = "LAST_NAME", length = 50, nullable = false)
	@Pattern(regexp = "^[a-zA-Z]+$", message = "Please Enter Valid Name")
	private String lastName;

	@Column(name = "DONOR_OCCUPATION", length = 50, nullable = false)
	@Pattern(regexp = "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", message = "Please Enter Valid Name")
	private String occupation;

	@Column(name = "PHONE_NUMBER", length = 10, nullable = false)
	@Pattern(regexp = "^[0-9]{10}", message = "Mobile Number is Invalid")
	private String phoneNumber;

	@Column(name = "EMAIL", length = 100, nullable = false)
	@Email(message = "Please Enter Valid Email")
	@NotBlank
	private String email;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ADDRESS_ID")
	@Autowired
	@Valid
	private Address address;

	// user is dependency for Doner
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "USER_ID")
	@Autowired
	@Valid
	private User user;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "DONOR_DONATION", joinColumns = { @JoinColumn(name = "DONOR_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "DONATION_ID") })
	@Autowired
	@Valid
	private Set<Donation> donations = new HashSet<>();

	@ManyToMany(cascade = CascadeType.ALL)
	private Set<Ngo> ngos = new HashSet<>();

	public Donor() {
		super();

	}

	public Donor(int donorId, String firstName, String lastName, String occupation, String phoneNumber, String email) {
		super();
		this.donorId = donorId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.occupation = occupation;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}

	public int getDonorId() {
		return donorId;
	}

	public void setDonorId(int donorId) {
		this.donorId = donorId;
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

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
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

	public Set<Donation> getDonations() {
		return donations;
	}

	public void setDonations(Set<Donation> donations) {
		this.donations = donations;
	}

	public Set<Ngo> getNgos() {
		return ngos;
	}

	public void setNgos(Set<Ngo> ngos) {
		this.ngos = ngos;
	}

	public void addDonation(Donation donation) {
		this.getDonations().add(donation);
	}

	@Override
	public String toString() {
		return "Donor [donorId=" + donorId + ", firstName=" + firstName + ", lastName=" + lastName + ", occupation="
				+ occupation + ", phone_Number=" + phoneNumber + ", email=" + email + "]";
	}

}