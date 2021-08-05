package com.capgemini.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.capgemini.entity.Admin;
import com.capgemini.entity.CharityEvents;
import com.capgemini.entity.Donation;
import com.capgemini.entity.Donor;
import com.capgemini.entity.Ngo;
import com.capgemini.exception.NoDonationFoundException;
import com.capgemini.exception.NoSuchEventFoundException;
import com.capgemini.exception.NoSuchNgoFoundException;
import com.capgemini.exception.NoSuchUserFoundException;
import com.capgemini.exception.UniqueConstraintViolationException;
import com.capgemini.repository.AdminRepository;
import com.capgemini.repository.CharityEventsRepository;
import com.capgemini.repository.DonationRepository;
import com.capgemini.repository.DonorRepository;
import com.capgemini.repository.NgoRepository;

@Service("adminService")
@Transactional
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository repository;

	@Autowired
	private DonorRepository donorRepository;

	@Autowired
	private DonationRepository donationRepository;

	@Autowired
	private NgoRepository ngoRepository;

	@Autowired
	private CharityEventsRepository eventRepository;

	private JavaMailSender javaMailSender;
	// Logger Object created, used to log messages for a specific application
	// component
	private static final Logger LOGGER = LoggerFactory.getLogger(AdminServiceImpl.class);

	@Override
	public boolean addAdmin(Admin admin) throws UniqueConstraintViolationException {

		LOGGER.trace("Entering Inside addAdmin Method");
		boolean result = false;
		admin.getUser().setUserPassword(new BCryptPasswordEncoder().encode(admin.getUser().getUserPassword()));
		;
		List<Admin> admins = repository.findAll();
		for (Admin user : admins) {
			if (user.getUser().getUsername().equals(admin.getUser().getUsername())) {
				LOGGER.error("Admin already exists");
				throw new UniqueConstraintViolationException("User Already Exists.");
			}
		}
		admin = repository.save(admin);
		if (admin.getAdminId() > 0)
			result = true;
		LOGGER.info("Admin Added Successfully");
		return result;
	}

	@Override
	public Admin findAdminByUserName(String username) throws NoSuchUserFoundException {
		LOGGER.trace("Entering Inside findAdminByUserName Method");
		Admin admin = repository.readAdminByUsername(username);

		if (admin != null && username.equals(admin.getUser().getUsername())) {
			LOGGER.info("Admin Username Found Successfully");
			return repository.readAdminByUsername(username);
		} else {

			LOGGER.error("Admin Username Not Found");
			throw new NoSuchUserFoundException("Admin with username " + username + " not present");
		}

	}

	@Override
	public Admin findAdminByEmail(String email) throws NoSuchUserFoundException {

		LOGGER.trace("Entering Inside findAdminByEmail Method");
		Admin admin = repository.readAdminByEmail(email);
		if (admin == null) {
			LOGGER.error("Admin Email Not Found");
			throw new NoSuchUserFoundException("Admin not found");

		}
		LOGGER.info("Admin Email Found Successfully");
		return repository.readAdminByEmail(email);
	}

	@Override
	public List<Admin> findAdminByFirstName(String firstName) throws NoSuchUserFoundException {

		LOGGER.trace("Entering Inside findAdminByFirstName Method");
		List<Admin> admins = repository.readAdminByFirstName(firstName);
		for (Admin admin : admins) {
			if (admin.getFirstName().equals(firstName)) {
				LOGGER.info("Admin FirstName matches Successfully");
				return repository.readAdminByFirstName(firstName);
			}
		}
		LOGGER.error("FirstName Not Found");
		throw new NoSuchUserFoundException("Admin not found");
	}

	@Override
	public List<Admin> findAdminByLastName(String lastName) throws NoSuchUserFoundException {
		LOGGER.trace("Entering Inside findAdminByLastName Method");
		List<Admin> admins = repository.readAdminByLastName(lastName);
		for (Admin admin : admins) {
			if (admin.getLastName().equalsIgnoreCase(lastName)) {
				LOGGER.info("Admin LastName matches Successfully");
				return repository.readAdminByLastName(lastName);
			}
		}
		LOGGER.error("LastName Not Found");
		throw new NoSuchUserFoundException("Admin not found");

	}

	@Override
	public void removeAdminByEmail(String email) throws NoSuchUserFoundException {
		LOGGER.trace("Entering Inside removeAdminByEmail Method");
		Admin admin = repository.readAdminByEmail(email);
		if (admin == null) {
			LOGGER.error("Admin Email Not Found");
			throw new NoSuchUserFoundException("Admin Not Found");
		}
		LOGGER.info("Admin Email matches Successfully");
		repository.removeAdminByEmail(email);
	}

	@Override
	public boolean removeAdminByadminId(int adminId) throws NoSuchUserFoundException {
		LOGGER.trace("Entering Inside removeAdminByadminId Method");
		if (repository.existsById(adminId)) {
			LOGGER.info("Admin ID matches Successfully");
			repository.deleteById(adminId);
			return true;
		}
		LOGGER.error("Admin ID Not Found");
		throw new NoSuchUserFoundException("Admin with id " + adminId + " not present");
	}

	@Override
	public void updateAdminEmail(int adminId, String email) throws NoSuchUserFoundException {
		LOGGER.trace("Entering Inside updateAdminEmail Method");
		Optional<Admin> admin = repository.findById(adminId);
		if (admin == null) {
			LOGGER.error("Admin Email Found");
			throw new NoSuchUserFoundException("Admin Not Found");
		}
		LOGGER.info("Admin Email matches Successfully");
		repository.updateAdminEmail(adminId, email);
	}

	@Override
	public void updateAdminPhoneNumber(int adminId, String phoneNumber) throws NoSuchUserFoundException {
		LOGGER.trace("Entering Inside updateAdminPhoneNumber Method");
		Optional<Admin> admin = repository.findById(adminId);
		if (admin == null) {
			LOGGER.error("Phone Number not Found");
			throw new NoSuchUserFoundException("Admin with id " + adminId + " not present");
		}
		LOGGER.info("Phone Number found Successfully");
		repository.updateAdminPhoneNumber(adminId, phoneNumber);
	}

	@Override
	public Ngo findNgoByName(String ngoName) throws NoSuchUserFoundException {
		LOGGER.trace("Entering Inside findNgoByName Method");
		Ngo ngo = repository.readNgoByName(ngoName);
		if (ngo.getNgoName().equals(ngoName)) {
			LOGGER.info("NGO name found");
			return repository.readNgoByName(ngoName);
		}
		LOGGER.error("NGO name Not Found");
		throw new NoSuchUserFoundException("Ngo not found");

	}

	@Override
	public List<Donor> findDonor() throws NoSuchUserFoundException {
		LOGGER.trace("Entering Inside findDonor Method");
		List<Donor> donors = donorRepository.findAll();
		if (donors.isEmpty()) {
			LOGGER.error("Donor Not Found");

			throw new NoSuchUserFoundException("No donor Found");
		}
		LOGGER.info("Donor found Successfully");
		return donors;
	}

	@Override
	public List<Admin> findAdminDetails() throws NoSuchUserFoundException {
		LOGGER.trace("Entering Inside findAdminDetails Method");

		List<Admin> admins = repository.findAll();
		if (admins.isEmpty()) {
			LOGGER.error("Admin Details Not Found");

			throw new NoSuchUserFoundException("No Admin Found");
		}
		LOGGER.info("Admin Detials found Successfully");
		return admins;
	}

	@Override
	public List<Donation> findDonation() throws NoDonationFoundException {
		LOGGER.trace("Entering Inside findDonation Method");
		List<Donation> donations = donationRepository.findAll();
		if (donations.isEmpty()) {
			LOGGER.error("No Donation Yet");

			throw new NoDonationFoundException("No donation Yet");
		}
		LOGGER.info("List of Donation found");
		return donations;

	}

	@Override
	public List<Ngo> findNgo() throws NoSuchNgoFoundException {
		LOGGER.trace("Entering Inside findNgo Method");
		List<Ngo> ngos = ngoRepository.findAll();
		if (ngos.isEmpty()) {
			LOGGER.error("NGO Not Found");
			throw new NoSuchNgoFoundException("No Such Ngo Found");
		}
		LOGGER.info("NGO found Successfully");
		return ngos;

	}

	@Override
	public boolean addNgo(Ngo ngo) throws UniqueConstraintViolationException {
		LOGGER.trace("Entering Inside addNgo Method");
		boolean result = false;
		ngo.getUser().setUserPassword(new BCryptPasswordEncoder().encode(ngo.getUser().getUserPassword()));
		;
		List<Ngo> ngos = ngoRepository.findAll();
		for (Ngo ngo1 : ngos) {
			if (ngo1.getUser().getUsername().equals(ngo.getUser().getUsername())) {
				LOGGER.error("NGO Not Found");
				throw new UniqueConstraintViolationException("User Already Exists.");
			}
		}

		ngo = ngoRepository.save(ngo);
		if (ngo.getNgoID() > 0)
			result = true;
		LOGGER.info("NGO added Successfully");
		return result;
	}

	@Override
	public void removeNgoByName(String ngoName) throws NoSuchNgoFoundException {
		LOGGER.trace("Entering Inside removeNgoByName Method");
		Ngo ngo = repository.readNgoByName(ngoName);
		if (ngo == null) {
			LOGGER.error("NGO Not Found");
			throw new NoSuchNgoFoundException("No such ngo exist with " + ngoName + " this name");
		}
		LOGGER.info("NGO name matches Successfully");
		repository.removeNgoByName(ngoName);

	}

	@Override
	public boolean addEvent(CharityEvents event) {
		LOGGER.trace("Entering Inside addEvent Method");

		boolean result = false;
		event = eventRepository.save(event);
		if (event.getEventId() > 0)
			result = true;
		LOGGER.info("Event added Successfully");
		return result;
	}

	@Override
	public List<CharityEvents> findEvents() throws NoSuchEventFoundException {
		LOGGER.trace("Entering Inside findEvents Method");
		List<CharityEvents> ngos = eventRepository.findAll();
		if (ngos.isEmpty()) {
			LOGGER.error("Event Not Found");
			throw new NoSuchEventFoundException("No Such Event Found");
		}
		LOGGER.info("Events Found Successfully");
		return ngos;
	}

	@Override
	public Double totalDonations() throws NoDonationFoundException {
		return donationRepository.sumDonations();

	}

	@Autowired
	@Override
	public void mailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;

	}

	@Override
	public void sendGreetingMail(Donor donor) {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(donor.getEmail());
		mail.setSubject("Thank You For Your Donation!");
		mail.setText("Dear " + donor.getFirstName() + ",\t\n "
				+ "Thank you for your donation of amount to We Four You.\t\n"
				+ "Thank you for supporting We Four You’s work with your generous regular donations. Your valuable gift is helping us provide long-term support to under-privileged ones.\t\n"
				+ "If you have any questions about your donation or about our work, we would love to hear from you. Please email wefouryou.143@gmail.com or call +91-8329950738.\t\n\t\n"
				+ "Sincerely,\t\n" + "We Four You Team\t\n" + "India\t\n"
				+ "Telephone: +91-8329950738 | wefouryou.143@gmail.com\t\n"
				+ "Please print or save this message for your personal records. You can use this page as a receipt for tax purposes.\t\n");

		javaMailSender.send(mail);

	}

}
