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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("ngo")
@Scope(scopeName = "prototype")
@Entity
@Table(name = "NGO_TABLE")
public class Ngo {
	@Id
	@Column(name = "NGO_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ngoID;

	@Column(name = "NGO_NAME", length = 50, nullable = false, unique = true)
	@Pattern(regexp = "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", message = "Please Enter Valid Name")
	private String ngoName;

	@Column(name = "EMAIL", length = 20, nullable = false)
	@Email(message = "Please Enter Valid Email")
	@NotBlank
	private String email;

	@Column(name = "PHONE_NUMBER", nullable = false)
	@Pattern(regexp = "^[0-9]{10}", message = "Mobile Number is Invalid")
	private String phoneNumber;

	@Column(name = "NGO_LICENCE_NO", length = 10, nullable = false)
	private long licenceNo;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ADDRESS_ID")
	@Autowired
	@Valid
	private Address address;

	// user is dependency for NGO
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "USER_ID")
	@Autowired
	@Valid
	private User user;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "EVENT_ID")
	@Autowired
	@Valid
	private CharityEvents event;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "NGO_DONOR", joinColumns = { @JoinColumn(name = "NGO_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "DONER_ID") })
	private Set<Donor> donors = new HashSet<>();

	public Ngo() {

	}

	public Ngo(int ngoID, String ngoName, String email, String phoneNumber, long licenceNo) {
		super();
		this.ngoID = ngoID;
		this.ngoName = ngoName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.licenceNo = licenceNo;
	}

	public void setNgoID(int ngoID) {
		this.ngoID = ngoID;
	}

	public Ngo(Address address) {
		super();
		this.address = address;
	}

	public Ngo(User user) {
		super();
		this.user = user;
	}

	public String getNgoName() {
		return ngoName;
	}

	public void setNgoName(String ngoName) {
		this.ngoName = ngoName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public long getLicenceNo() {
		return licenceNo;
	}

	public void setLicenceNo(long licenceNo) {
		this.licenceNo = licenceNo;
	}

	public int getNgoID() {
		return ngoID;
	}

	public Set<Donor> getDonors() {
		return donors;
	}

	public void setDonors(Set<Donor> donors) {
		this.donors = donors;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public void addDonor(Donor donor) {
		this.getDonors().add(donor);
	}

	@Override
	public String toString() {
		return "Ngo [ngoID=" + ngoID + ", ngoName=" + ngoName + ", email=" + email + ", phoneNumber=" + phoneNumber
				+ ", licenceNo=" + licenceNo + "]";
	}

}