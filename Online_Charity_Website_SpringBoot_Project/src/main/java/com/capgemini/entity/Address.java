package com.capgemini.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("address")
@Scope(scopeName = "prototype")
@Entity
@Table(name = "ADDRESS_TABLE")
public class Address {

	@Id
	@Column(name = "ADDRESS_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int addressId;

	@Column(name = "FLAT_NO", length = 60, nullable = true)
	private String flatNo; // flatNo may be 4/30 so string.

	@Column(name = "COLONY", length = 60, nullable = true)
	@Pattern(regexp = "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", message = "Please Enter Valid Name")
	private String colony;

	@Column(name = "CITY", length = 60, nullable = false)
	@Pattern(regexp = "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", message = "Please Enter Valid Name")
	private String city;

	@Column(name = "DISTRICT", length = 60, nullable = false)
	@Pattern(regexp = "^[a-zA-Z]+$", message = "Please Enter Valid Name")
	private String district;

	@Column(name = "STATE", length = 60, nullable = false)
	@Pattern(regexp = "^[a-zA-Z]+$", message = "Please Enter Valid Name")
	private String state;
    
	
	@Column(name = "PINCODE",length = 6)
	@Pattern(regexp = "^[0-9]{6}", message = "Mobile Number is Invalid")
	private String pincode;

	public Address() {
		super();

	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public Address(int addressId, String flatNo, String colony, String city, String district, String state,
			String pincode) {
		super();
		this.addressId = addressId;
		this.flatNo = flatNo;
		this.colony = colony;
		this.city = city;
		this.district = district;
		this.state = state;
		this.pincode = pincode;
	}

	public String getFlatNo() {
		return flatNo;
	}

	public void setFlatNo(String flatNo) {
		this.flatNo = flatNo;
	}

	public String getColony() {
		return colony;
	}

	public void setColony(String colony) {
		this.colony = colony;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", flatNo=" + flatNo + ", colony=" + colony + ", city=" + city
				+ ", district=" + district + ", state=" + state + ", pincode=" + pincode + "]";
	}

}