package com.capgemini.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.capgemini.entity.CharityEvents;
import com.capgemini.entity.Donation;
import com.capgemini.entity.Ngo;
import com.capgemini.exception.NoDonationFoundException;
import com.capgemini.exception.NoSuchEventFoundException;
import com.capgemini.exception.NoSuchNgoFoundException;
import com.capgemini.repository.CharityEventsRepository;
import com.capgemini.repository.DonationRepository;
import com.capgemini.repository.NgoRepository;

@Service("ngoService")
public class NgoServiceImpl implements NgoService {

	@Autowired
	private NgoRepository ngoRepository;

	@Autowired
	private DonationRepository donationRepository;

	@Autowired
	private CharityEventsRepository eventRepository;

	private JavaMailSender javaMailSender;

	// Logger Object created, used to log messages for a specific application
	// component
	private static final Logger LOGGER = LoggerFactory.getLogger(NgoServiceImpl.class);

	@Override
	public boolean addNgo(Ngo ngo) {
		LOGGER.trace("Entering Inside addNgo Method");
		boolean result = false;
		ngo = ngoRepository.save(ngo);
		if (ngo.getNgoID() > 0)
			result = true;
		LOGGER.info("Ngo Added Successfully");
		return result;
	}

	@Override
	public List<Ngo> findNgo() throws NoSuchNgoFoundException {
		LOGGER.trace("Entering Inside findNgo Method");
		List<Ngo> ngos = ngoRepository.findAll();
		if (ngos.isEmpty()) {
			throw new NoSuchNgoFoundException("No Such Ngo Found");
		}
		LOGGER.info("Ngo Found Successfully");
		return ngos;

	}

	@Override
	public List<Donation> findDonation() throws NoDonationFoundException {
		LOGGER.trace("Donation Found Successfully");
		List<Donation> donations = donationRepository.findAll();
		if (donations.isEmpty()) {
			LOGGER.error("Donation Not Found");
			throw new NoDonationFoundException("No donation Yet");
		}
		LOGGER.info("Donation Found Successfully");
		return donations;
	}

	@Override
	public List<CharityEvents> findEvent() throws NoSuchEventFoundException {
		LOGGER.trace("Entering Inside findEvent Method");
		List<CharityEvents> events = eventRepository.findAll();
		if (events.isEmpty()) {
			LOGGER.error("Event Not Found");
			throw new NoSuchEventFoundException("No Event Yet");
		}
		LOGGER.info("Event Found Successfully");
		return events;
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
	public void sendEventMail(CharityEvents event) {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(event.getDonor().getEmail());
		mail.setSubject(" Invitation to attend a " + event.getEventName() + " Event");
		mail.setText("Dear " + event.getDonor().getFirstName() + ",\t\n " +

				"We," + event.getNgo().getNgoName() + " behalf of our management take immense pleasure in inviting "
				+ event.getDonor().getFirstName() + " " + event.getDonor().getLastName()
				+ " along with your family to attend " + event.getEventName() + " conducted by our organization on "
				+ event.getEventDate()
				+ " . We request you to attend the event along with family and friends to encourage the hidden talent of these young explorers."

				+ "The main aim behind holding a fund raising event is to mainly focus on the career of these homeless children and give them a good education and medical aid. Hence it would be great if you showed your presence there. Awaiting your response."
				+ "If you have any questions about your donation or about our work, we would love to hear from you. Please email wefouryou.143@gmail.com or call +91-8329950738.\t\n\t\n"
				+ "Sincerely,\t\n" + "We Four You Team\t\n" + "India\t\n"
				+ "Telephone: +91-8329950738 | wefouryou.143@gmail.com\t\n");

		javaMailSender.send(mail);

	}

}
