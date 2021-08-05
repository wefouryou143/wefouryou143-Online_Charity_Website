package com.capgemini.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.entity.Donor;
import com.capgemini.repository.DonationRepository;
import com.capgemini.repository.DonorRepository;

@Service("donationService")
public class DonationServiceImpl implements DonationService {

	// Logger Object created, used to log messages for a specific application
	// component
	private static final Logger LOGGER = LoggerFactory.getLogger(NgoServiceImpl.class);

	@Autowired
	private DonorRepository repository;
	@Autowired
	private DonationRepository donationRepository;

	@Override
	public boolean addDonor(Donor donor) {
		LOGGER.trace("Entering Inside addDonor Method");
		boolean result = false;
		donor = repository.save(donor);
		if (donor.getDonorId() > 0)
			result = true;
		LOGGER.info("Donor Added Successfully");
		return result;
	}

	@Override
	public Double totalDonations() {
		return donationRepository.sumDonations();

	}

}