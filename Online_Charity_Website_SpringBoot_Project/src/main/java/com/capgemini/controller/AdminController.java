package com.capgemini.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//protocol://machine-name:port-number/context-path/path-class/path-method

import com.capgemini.entity.Admin;
import com.capgemini.entity.CharityEvents;
import com.capgemini.entity.Donation;
import com.capgemini.entity.Donor;
import com.capgemini.entity.Ngo;
import com.capgemini.exception.InvalidCredentialsException;
import com.capgemini.exception.NoDonationFoundException;
import com.capgemini.exception.NoSuchEventFoundException;
import com.capgemini.exception.NoSuchNgoFoundException;
import com.capgemini.exception.NoSuchUserFoundException;
import com.capgemini.exception.UniqueConstraintViolationException;
import com.capgemini.service.AdminService;

@RestController
@RequestMapping(path = "admins")

public class AdminController {
	@Autowired
	AdminService service;

	// http://localhost:9090/charity-api/admins/ - Post
	@PostMapping(path = "register")
	public ResponseEntity<String> saveAdmin(@Valid @RequestBody Admin admin) throws UniqueConstraintViolationException {
		ResponseEntity<String> response = null;
		boolean result = service.addAdmin(admin);
		if (result)
			response = new ResponseEntity<String>("Admin with username" + admin.getUser().getUsername() + "is added",
					HttpStatus.CREATED);
		return response;
	}

	// http://localhost:9090/charity-api/admins/searchByUsername/ -Get
	@GetMapping(path = "searchByUsername/{username}")
	public ResponseEntity<Admin> getAdminByUsername(@PathVariable("username") String username)
			throws NoSuchUserFoundException {
		ResponseEntity<Admin> response = null;
		Admin admin = service.findAdminByUserName(username);
		if (admin.getUser().getUsername().equals(username)) {
			response = new ResponseEntity<Admin>(admin, HttpStatus.OK);
		}
		return response;

	}

	// http://localhost:9090/charity-api/admins/searchByEmail/
	@GetMapping(path = "searchByEmail/{email}")
	public ResponseEntity<Admin> getAdminByEmail(@PathVariable("email") String email)
			throws NoSuchUserFoundException, InvalidCredentialsException {
		ResponseEntity<Admin> response = null;
		Admin admin = service.findAdminByEmail(email);
		response = new ResponseEntity<Admin>(admin, HttpStatus.OK);
		return response;

	}

	// http://localhost:9090/charity-api/admins/searchByFirstName/
	@GetMapping(path = "searchByFirstName/{firstName}")
	public ResponseEntity<List<Admin>> getAdminByFirstName(@PathVariable("firstName") String firstName)
			throws NoSuchUserFoundException, InvalidCredentialsException {
		ResponseEntity<List<Admin>> response = null;
		List<Admin> admin = service.findAdminByFirstName(firstName);
		response = new ResponseEntity<List<Admin>>(admin, HttpStatus.OK);
		return response;

	}

	// http://localhost:9090/charity-api/admins/searchByLastName/
	@GetMapping(path = "searchByLastName/{LastName}")
	public ResponseEntity<List<Admin>> getAdminByLastName(@PathVariable("LastName") String lastName)
			throws NoSuchUserFoundException, InvalidCredentialsException {
		ResponseEntity<List<Admin>> response = null;
		List<Admin> admin = service.findAdminByLastName(lastName);
		response = new ResponseEntity<List<Admin>>(admin, HttpStatus.OK);
		return response;

	}

	// http://localhost:9090/charity-api/admins/updateEmail/
	@PutMapping(path = "updateEmail/{adminId}/{email}")
	public ResponseEntity<String> updateAdminEmail(@PathVariable("adminId") int adminId,
			@PathVariable("email") String email) throws NoSuchUserFoundException {
		ResponseEntity<String> response = null;
		service.updateAdminEmail(adminId, email);
		response = new ResponseEntity<String>("Admin with adminId " + adminId + " is updated to " + email + " ",
				HttpStatus.CREATED);
		return response;
	}

	// http://localhost:9090/charity-api/admins/updateByPhoneNumber/
	@PutMapping(path = "updateByPhoneNumber/{adminId}/{phoneNumber}")
	public ResponseEntity<String> updateAdminPhoneNumber(@Valid @PathVariable("adminId") int adminId,
			@PathVariable("phoneNumber") String phoneNumber) throws NoSuchUserFoundException {
		ResponseEntity<String> response = null;
		service.updateAdminPhoneNumber(adminId, phoneNumber);
		response = new ResponseEntity<String>("Admin with adminId " + adminId + " is updated to " + phoneNumber + " ",
				HttpStatus.CREATED);
		return response;
	}

	// http://localhost:9090/charity-api/admins/deleteByEmail/
	@DeleteMapping(path = "deleteByEmail/{email}")
	public ResponseEntity<String> deleteAdminByEmail(@Valid @PathVariable("email") String email)
			throws NoSuchUserFoundException {
		ResponseEntity<String> response = null;
		service.removeAdminByEmail(email);
		response = new ResponseEntity<String>("Selected Admin is removed ", HttpStatus.CREATED);
		return response;
	}

	// http://localhost:9090/charity-api/admins/deleteByadminId/
	@DeleteMapping(path = "deleteByadminId/{adminId}")
	public ResponseEntity<String> deleteAdminByadminId(@Valid @PathVariable("adminId") int adminId)
			throws NoSuchUserFoundException {
		ResponseEntity<String> response = null;
		service.removeAdminByadminId(adminId);
		response = new ResponseEntity<String>("Selected Admin is removed ", HttpStatus.CREATED);
		return response;
	}

	// http://localhost:9090/charity-api/admins/searchNgoByName/ --------------done
	@GetMapping(path = "searchNgoByName//{ngoName}")
	public ResponseEntity<Ngo> getNgoByName(@PathVariable("ngoName") String ngoName)
			throws NoSuchUserFoundException, InvalidCredentialsException {
		ResponseEntity<Ngo> response = null;
		Ngo ngo = service.findNgoByName(ngoName);
		response = new ResponseEntity<Ngo>(ngo, HttpStatus.OK);

		return response;

	}

	// http://localhost:9090/charity-api/admins/viewDonor/
	@GetMapping(path = "viewDonor/")
	public ResponseEntity<List<Donor>> getDonors() throws NoSuchUserFoundException, InvalidCredentialsException {
		ResponseEntity<List<Donor>> response = null;
		List<Donor> donor = service.findDonor();
		response = new ResponseEntity<List<Donor>>(donor, HttpStatus.OK);
		return response;

	}

	// http://localhost:9090/charity-api/admins/viewAdmin/
	@GetMapping(path = "viewAdmin/")
	public ResponseEntity<List<Admin>> getAdmins() throws NoSuchUserFoundException, InvalidCredentialsException {
		ResponseEntity<List<Admin>> response = null;
		List<Admin> admin = service.findAdminDetails();
		response = new ResponseEntity<List<Admin>>(admin, HttpStatus.OK);
		return response;

	}

	// http://localhost:9090/charity-api/admins/viewDonation/
	@GetMapping(path = "viewDonation/")
	public ResponseEntity<List<Donation>> getDonations() throws NoDonationFoundException {
		ResponseEntity<List<Donation>> response = null;
		List<Donation> donation = service.findDonation();
		response = new ResponseEntity<List<Donation>>(donation, HttpStatus.OK);
		return response;

	}

	// http://localhost:9090/charity-api/admins/viewNgo/
	@GetMapping(path = "viewNgo/")
	public ResponseEntity<List<Ngo>> getNgos() throws NoSuchNgoFoundException {
		ResponseEntity<List<Ngo>> response = null;
		List<Ngo> ngo = service.findNgo();
		response = new ResponseEntity<List<Ngo>>(ngo, HttpStatus.OK);
		return response;

	}

	// http://localhost:9090/charity-api/admins/addNgo/ - Post
	@PostMapping(path = "addNgo/")
	public ResponseEntity<String> saveNgo(@Valid @RequestBody Ngo ngo)
			throws UniqueConstraintViolationException, NoSuchUserFoundException {
		ResponseEntity<String> response = null;
		boolean result = service.addNgo(ngo);
		if (result)
			response = new ResponseEntity<String>("Admin with username" + ngo.getUser().getUsername() + "is added",
					HttpStatus.CREATED);
		return response;

	}

	// http://localhost:9090/charity-api/admins/deleteByNgoName/
	@DeleteMapping(path = "deleteByNgoName/{ngoName}")
	public ResponseEntity<String> deleteAdminByNgoName(@PathVariable("ngoName") String ngoName)
			throws NoSuchNgoFoundException {
		ResponseEntity<String> response = null;
		service.removeNgoByName(ngoName);
		response = new ResponseEntity<String>("Selected Ngo is removed ", HttpStatus.CREATED);
		return response;
	}

	// http://localhost:9090/charity-api/admins/addEvent/ - Post
	@PostMapping(path = "addEvent/")
	public ResponseEntity<String> saveEvent(@Valid @RequestBody CharityEvents event) throws NoSuchEventFoundException {
		ResponseEntity<String> response = null;
		boolean result = service.addEvent(event);
		if (result)
			response = new ResponseEntity<String>(event.getEventName() + " is added Successfully", HttpStatus.CREATED);
		return response;

	}

	// http://localhost:9090/charity-api/admins/viewEvents/
	@GetMapping(path = "viewEvents/")
	public ResponseEntity<List<CharityEvents>> getEvents() throws NoSuchEventFoundException {
		ResponseEntity<List<CharityEvents>> response = null;
		List<CharityEvents> events = service.findEvents();
		response = new ResponseEntity<List<CharityEvents>>(events, HttpStatus.OK);
		return response;

	}

	// http://localhost:9090/charity-api/admins/totalAmount- Get
	@GetMapping(path = "/totalAmount")
	public Double totalDonations() throws NoDonationFoundException {
		return service.totalDonations();
	}

}
