package com.capgemini.service;

import java.util.List;

import com.capgemini.entity.Donation;
import com.capgemini.entity.Donor;
import com.capgemini.exception.NoDonationFoundException;
import com.capgemini.exception.UniqueConstraintViolationException;

public interface DonorService {
	public boolean addDonor(Donor donor) throws UniqueConstraintViolationException;

	public boolean addDonation(Donation donation);

	public List<Donation> findDonation() throws NoDonationFoundException;

}
