package com.capgemini.service;

import java.util.List;

import org.springframework.mail.javamail.JavaMailSender;

import com.capgemini.entity.CharityEvents;
import com.capgemini.entity.Donation;
import com.capgemini.entity.Ngo;
import com.capgemini.exception.NoDonationFoundException;
import com.capgemini.exception.NoSuchEventFoundException;
import com.capgemini.exception.NoSuchNgoFoundException;

public interface NgoService {
	public boolean addNgo(Ngo ngo); 
	public List<Ngo> findNgo() throws NoSuchNgoFoundException;
	public List<Donation> findDonation() throws NoDonationFoundException;
	public List<CharityEvents> findEvent() throws NoSuchEventFoundException;
	public Double totalDonations() throws NoDonationFoundException;
	
	void sendEventMail(CharityEvents event);
	public void mailService(JavaMailSender javaMailSender);

}
