package com.capgemini.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.context.annotation.Scope;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

@Component("donation")
@Scope(scopeName = "prototype")
@Entity
@Table(name = "DONATION_TABLE")
public class Donation {

	@Id
	@Column(name = "DONATION_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int donationId;

	@Column(name = "BANK_NAME", length = 50, nullable = false)
	@Pattern(regexp = "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", message = "Please Enter Valid Name")
	private String bankName;

	@Column(name = "ACCOUNT_NUMBER", length = 11, nullable = false)
	private long accountNumber;

	@Column(name = "AMOUNT", nullable = false)
	@Min(value = 500, message = "Please Make Minimum Donation Of 500.Thank You!")
	private double amount;

	@Column(name = "IFSC_CODE", length = 11, nullable = false)
	@Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Please Enter Valid Ifsc Code")
	@Size(max = 6)
	private String ifscCode;

	@Column(name = "DATE", nullable = false)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date date;

	@Column(name = "PURPOSE_OF_DONATION", length = 100, nullable = false)
	private String purposeOfDonation;

	public Donation() {

	}

	public Donation(int donationId, String bankName, long accountNumber, double amount, String ifscCode, Date date,
			String purposeOfDonation) {
		super();
		this.donationId = donationId;
		this.bankName = bankName;
		this.accountNumber = accountNumber;
		this.amount = amount;
		this.ifscCode = ifscCode;
		this.date = date;
		this.purposeOfDonation = purposeOfDonation;
	}

	public int getDonationId() {
		return donationId;
	}

	public void setDonationId(int donationId) {
		this.donationId = donationId;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getPurposeOfDonation() {
		return purposeOfDonation;
	}

	public void setPurposeOfDonation(String purposeOfDonation) {
		this.purposeOfDonation = purposeOfDonation;
	}

	@Override
	public String toString() {
		return "Donation [donationId=" + donationId + ", bankName=" + bankName + ", accountNumber=" + accountNumber
				+ ", amount=" + amount + ", ifscCode=" + ifscCode + ", date=" + date + ", purposeOfDonation="
				+ purposeOfDonation + "]";
	}

}
