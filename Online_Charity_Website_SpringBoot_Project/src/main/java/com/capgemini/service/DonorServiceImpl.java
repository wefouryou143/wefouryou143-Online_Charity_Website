package com.capgemini.service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.entity.Donation;
import com.capgemini.entity.Donor;
import com.capgemini.exception.UniqueConstraintViolationException;
import com.capgemini.repository.DonationRepository;
import com.capgemini.repository.DonorRepository;

@Service("donorService")

public class DonorServiceImpl implements DonorService {

	@Autowired
	private DonorRepository repository;

	@Autowired
	private DonationRepository donationRepository;
	// Logger Object created, used to log messages for a specific application
	// component
	private static final Logger LOGGER = LoggerFactory.getLogger(NgoServiceImpl.class);

	@Override
	@Transactional
	public boolean addDonor(Donor donor) throws UniqueConstraintViolationException {
		LOGGER.trace("Entering Inside addDonor Method");
		boolean result = false;
		List<Donor> donors = repository.findAll();
		for (Donor user : donors) {
			if (user.getUser().getUsername().equals(donor.getUser().getUsername())) {
				LOGGER.error("Donor already exists");
				throw new UniqueConstraintViolationException("User Already Exists.");
			}
		}
		donor = repository.save(donor);
		if (donor.getDonorId() > 0)
			result = true;
		LOGGER.info("Donor Added Successfully");
		return result;
	}

	@Override
	public boolean addDonation(Donation donation) {
		LOGGER.trace("Entering Inside addDonation Method");
		boolean result = false;
		donation = donationRepository.save(donation);
		if (donation.getDonationId() > 0)
			result = true;
		LOGGER.info("Donation Added Successfully");
		return result;
	}

	@Override
	public List<Donation> findDonation() {
		LOGGER.trace("Entering Inside findDonation Method");
		List<Donation> donations = donationRepository.findAll();
		if (donations.isEmpty()) {

		}
		LOGGER.info("Donation Found Successfully");
		return donations;
	}

}
