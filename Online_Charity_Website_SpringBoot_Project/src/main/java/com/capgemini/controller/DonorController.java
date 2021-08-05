package com.capgemini.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.entity.Donation;
import com.capgemini.entity.Donor;
import com.capgemini.exception.NoDonationFoundException;
import com.capgemini.exception.UniqueConstraintViolationException;
import com.capgemini.service.AdminService;
import com.capgemini.service.DonationService;
import com.capgemini.service.DonorService;

@RestController
@RequestMapping(path = "donors")

public class DonorController {
	@Autowired
	DonorService donorService;
	@Autowired
	DonationService donationService;
	@Autowired
	private AdminService adminService;

	// http://localhost:9090/charity-api/donors/ - Post
	@PostMapping(path = "/")
	public ResponseEntity<String> saveDonor(@Valid @RequestBody Donor donor) throws UniqueConstraintViolationException {
		ResponseEntity<String> response = null;
		boolean result = donorService.addDonor(donor);
		donor.getUser().setUserPassword(new BCryptPasswordEncoder().encode(donor.getUser().getUserPassword()));
		
		if (result)
			response = new ResponseEntity<String>("Donor with username " + donor.getUser().getUsername() + "is added",
					HttpStatus.CREATED);
		try {
			adminService.sendGreetingMail(donor);
		} catch (Exception e) {
			System.out.println(e);
		}
		return response;

	}

	// http://localhost:9090/charity-api/donors/addDonation
	@PostMapping("/addDonation")
	public ResponseEntity<String> saveDonation(@Valid @RequestBody Donation donation) {
		ResponseEntity<String> response = null;
		boolean result = donorService.addDonation(donation);
		if (result)
			response = new ResponseEntity<String>("Donation with amount" + donation.getAmount() + "is added",
					HttpStatus.CREATED);
		return response;

	}

	// http://localhost:9090/charity-api/donors/addDonor
	@PostMapping("/addDonor")
	public boolean addDonor(@Valid @RequestBody Donor donor) {
		return donationService.addDonor(donor);
	}

	// http://localhost:9090/charity-api/donors/totalAmount- Get
	@GetMapping(path = "/totalAmount")
	public Double totalDonations() throws NoDonationFoundException {
		return donationService.totalDonations();
	}

	// http://localhost:9090/charity-api/donors/viewDonation/
	@GetMapping(path = "viewDonation/")
	public ResponseEntity<List<Donation>> getDonations() throws NoDonationFoundException {
		ResponseEntity<List<Donation>> response = null;
		List<Donation> donation = donorService.findDonation();
		response = new ResponseEntity<List<Donation>>(donation, HttpStatus.OK);
		return response;

	}

}
