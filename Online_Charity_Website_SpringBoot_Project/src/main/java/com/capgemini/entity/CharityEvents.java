package com.capgemini.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

@Component("event")
@Scope(scopeName = "prototype")
@Entity
@Table(name = "CHARITY_EVENT_TABLE")
public class CharityEvents {
	@Id
	@Column(name = "EVENT_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int eventId;

	@Column(name = "EVENT_NAME", length = 50, unique = true)
	@Pattern(regexp = "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", message = "Please Enter Valid Name")
	private String eventName;

	@Column(name = "EVENT_TYPE", length = 50)
	@Pattern(regexp = "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", message = "Please Enter Valid Name")
	private String eventType;

	@Column(name = "EVENT_DATE")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate eventDate;

	@Column(name = "EVENT_ORGANIZER", length = 50)
	@Pattern(regexp = "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", message = "Please Enter Valid Name")
	private String eventOrganizer;

	@Column(name = "EVENT_SPONSERED_BY", length = 50)
	@Pattern(regexp = "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", message = "Please Enter Valid Name")
	private String sponseredBy;

	@Column(name = "EVENT_CHARITABLE_INSTITUTE", length = 50)
	@Pattern(regexp = "^[A-Za-z\\s]{1,}[\\.]{0,1}[A-Za-z\\s]{0,}$", message = "Please Enter Valid Name")
	private String charitableInstitute;

	@OneToOne(cascade = CascadeType.ALL)
	@Autowired
	private Ngo ngo;

	@OneToOne(cascade = CascadeType.ALL)
	@Autowired
	private Donor donor;

	public CharityEvents() {

	}

	public CharityEvents(int eventId, String eventName, String eventType, LocalDate eventDate, String eventOrganizer,
			String sponseredBy, String charitableInstitute) {
		super();
		this.eventId = eventId;
		this.eventName = eventName;
		this.eventType = eventType;
		this.eventDate = eventDate;
		this.eventOrganizer = eventOrganizer;
		this.sponseredBy = sponseredBy;
		this.charitableInstitute = charitableInstitute;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getEventOrganizer() {
		return eventOrganizer;
	}

	public void setEventOrganizer(String eventOrganizer) {
		this.eventOrganizer = eventOrganizer;
	}

	public String getSponseredBy() {
		return sponseredBy;
	}

	public void setSponseredBy(String sponseredBy) {
		this.sponseredBy = sponseredBy;
	}

	public String getCharitableInstitute() {
		return charitableInstitute;
	}

	public void setCharitableInstitute(String charitableInstitute) {
		this.charitableInstitute = charitableInstitute;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public LocalDate getEventDate() {
		return eventDate;
	}

	public void setEventDate(LocalDate eventDate) {
		this.eventDate = eventDate;
	}

	public Ngo getNgo() {
		return ngo;
	}

	public void setNgo(Ngo ngo) {
		this.ngo = ngo;
	}

	public Donor getDonor() {
		return donor;
	}

	public void setDonor(Donor donor) {
		this.donor = donor;
	}

	@Override
	public String toString() {
		return "CharityEvents [eventId=" + eventId + ", eventName=" + eventName + ", eventType=" + eventType
				+ ", eventDate=" + eventDate + ", eventOrganizer=" + eventOrganizer + ", sponseredBy=" + sponseredBy
				+ ", charitableInstitute=" + charitableInstitute + ", ngo=" + ngo + ", donor=" + donor + "]";
	}

}