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

import com.capgemini.entity.CharityEvents;
import com.capgemini.entity.Donation;
import com.capgemini.entity.Ngo;
import com.capgemini.exception.NoDonationFoundException;
import com.capgemini.exception.NoSuchEventFoundException;
import com.capgemini.exception.NoSuchNgoFoundException;
import com.capgemini.service.AdminService;
import com.capgemini.service.NgoService;

@RestController
@RequestMapping(path = "ngos")
public class NgoController {
	@Autowired
	NgoService ngoService;

	@Autowired
	private AdminService adminService;

	// http://localhost:9090/charity-api/ngos/ - Post
	@PostMapping(path = "/")
	public ResponseEntity<String> saveNgo(@Valid @RequestBody Ngo ngo) {
		ResponseEntity<String> response = null;
		boolean result = ngoService.addNgo(ngo);
		ngo.getUser().setUserPassword(new BCryptPasswordEncoder().encode(ngo.getUser().getUserPassword()));
		;
		if (result)
			response = new ResponseEntity<String>(ngo.getNgoName() + " is added Successfully", HttpStatus.CREATED);
		return response;

	}

	// http://localhost:9090/charity-api/ngos/viewNgo/
	@GetMapping(path = "viewNgo/")
	public ResponseEntity<List<Ngo>> getNgos() throws NoSuchNgoFoundException {
		ResponseEntity<List<Ngo>> response = null;
		List<Ngo> ngo = ngoService.findNgo();
		response = new ResponseEntity<List<Ngo>>(ngo, HttpStatus.OK);
		return response;

	}

	// http://localhost:9090/charity-api/ngos/viewDonation/
	@GetMapping(path = "viewDonation/")
	public ResponseEntity<List<Donation>> getDonations() throws NoDonationFoundException {
		ResponseEntity<List<Donation>> response = null;
		List<Donation> donation = ngoService.findDonation();
		response = new ResponseEntity<List<Donation>>(donation, HttpStatus.OK);
		return response;

	}

	// http://localhost:9090/charity-api/ngos/viewEvent/
	@GetMapping(path = "viewEvent/")
	public ResponseEntity<List<CharityEvents>> getEvents() throws NoSuchEventFoundException {
		ResponseEntity<List<CharityEvents>> response = null;
		List<CharityEvents> event = ngoService.findEvent();
		response = new ResponseEntity<List<CharityEvents>>(event, HttpStatus.OK);
		return response;

	}

	// http://localhost:9090/charity-api/ngos/totalAmount- Get
	@GetMapping(path = "/totalAmount")
	public Double totalDonations() throws NoDonationFoundException {
		return ngoService.totalDonations();
	}

	// http://localhost:9090/charity-api/ngos/addEvent/ - Post
	@PostMapping(path = "addEvent/")
	public ResponseEntity<String> saveEvent(@Valid @RequestBody CharityEvents event) throws NoSuchEventFoundException {
		ResponseEntity<String> response = null;
		boolean result = adminService.addEvent(event);
		if (result)
			response = new ResponseEntity<String>(event.getEventName() + " is added Successfully", HttpStatus.CREATED);
		try {
			ngoService.sendEventMail(event);
		} catch (Exception e) {
			System.out.println(e);
		}

		return response;
	}

}
