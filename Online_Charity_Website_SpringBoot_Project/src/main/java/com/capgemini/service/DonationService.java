package com.capgemini.service;

import com.capgemini.entity.Donor;
import com.capgemini.exception.NoDonationFoundException;

public interface DonationService {

	public boolean addDonor(Donor donor);

	public Double totalDonations() throws NoDonationFoundException;
}
