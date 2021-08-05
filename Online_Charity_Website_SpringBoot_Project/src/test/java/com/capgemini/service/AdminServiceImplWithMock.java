package com.capgemini.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;

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
import com.capgemini.repository.AdminRepository;
import com.capgemini.repository.CharityEventsRepository;
import com.capgemini.repository.DonationRepository;
import com.capgemini.repository.DonorRepository;
import com.capgemini.repository.NgoRepository;

@SpringBootTest
class AdminServiceImplWithMock {
	@Autowired
	private AdminService service;
	
	@Autowired
	private ApplicationContext context;
	
	@MockBean
	private AdminRepository repository;
	
	@MockBean
	private NgoRepository repository2;
	
	@MockBean
	private DonationRepository donationRepository;
	
	@MockBean
	private DonorRepository donorRepository;
	
	@MockBean
	private CharityEventsRepository eventRepository;
	

	@Test
	void testFindAdminByUserNameShouldReturnAdmin() throws NoSuchUserFoundException{
		Admin expected = context.getBean(Admin.class);
		expected.setAdminId(1);
		expected.setFirstName("Test");
		expected.setLastName("Test");
		expected.setPhoneNumber("0000000000");
		expected.setEmail("Test@gmail.com");
		expected.getAddress().setAddressId(1);
		expected.getAddress().setFlatNo("Test");
		expected.getAddress().setColony("Test");
		expected.getAddress().setCity("Test");
		expected.getAddress().setDistrict("Test");
		expected.getAddress().setState("Test");
		expected.getAddress().setPincode("400606");
		expected.getUser().setUserId(1);
		expected.getUser().setUsername("Test");
		expected.getUser().setUserPassword("Test");
		
		
		Admin expectation = expected;
		when(repository.readAdminByUsername(expected.getUser().getUsername())).thenReturn(expectation);
		
		Admin actual = service.findAdminByUserName(expected.getUser().getUsername());
		
		assertEquals(expected.getAdminId(), actual.getAdminId());
		assertEquals(expected.getFirstName(), actual.getFirstName());
		assertEquals(expected.getLastName(), actual.getLastName());
		assertEquals(expected.getPhoneNumber(), actual.getPhoneNumber());
		assertEquals(expected.getEmail(), actual.getEmail());
		assertEquals(expected.getAddress().getAddressId(), actual.getAddress().getAddressId());
		assertEquals(expected.getAddress().getFlatNo(), actual.getAddress().getFlatNo());
		assertEquals(expected.getAddress().getColony(), actual.getAddress().getColony());
		assertEquals(expected.getAddress().getCity(), actual.getAddress().getCity());
		assertEquals(expected.getAddress().getDistrict(), actual.getAddress().getDistrict());
		assertEquals(expected.getAddress().getState(), actual.getAddress().getState());
		assertEquals(expected.getAddress().getPincode(), actual.getAddress().getPincode());
		assertEquals(expected.getUser().getUserId(), actual.getUser().getUserId());
		assertEquals(expected.getUser().getUsername(), actual.getUser().getUsername());
		assertEquals(expected.getUser().getUserPassword(), actual.getUser().getUserPassword());
		
		
	}
	
	@Test
	void testFindAdminByEmailShouldReturnAdmin() throws  InvalidCredentialsException, NoSuchUserFoundException{
		Admin expected = context.getBean(Admin.class);
		expected.setAdminId(2);
		expected.setFirstName("Test");
		expected.setLastName("Test");
		expected.setPhoneNumber("0000000000");
		expected.setEmail("Test2@gmail.com");
		expected.getAddress().setAddressId(2);
		expected.getAddress().setFlatNo("Test");
		expected.getAddress().setColony("Test");
		expected.getAddress().setCity("Test");
		expected.getAddress().setDistrict("Test");
		expected.getAddress().setState("Test");
		expected.getAddress().setPincode("400606");
		expected.getUser().setUserId(2);
		expected.getUser().setUsername("Test1");
		expected.getUser().setUserPassword("Test");
	
		
		Admin expectation = expected;
		when(repository.readAdminByEmail(expected.getEmail())).thenReturn(expectation);
		
		Admin actual = service.findAdminByEmail(expected.getEmail());
		
		assertEquals(expected.getAdminId(), actual.getAdminId());
		assertEquals(expected.getFirstName(), actual.getFirstName());
		assertEquals(expected.getLastName(), actual.getLastName());
		assertEquals(expected.getPhoneNumber(), actual.getPhoneNumber());
		assertEquals(expected.getEmail(), actual.getEmail());
		assertEquals(expected.getAddress().getAddressId(), actual.getAddress().getAddressId());
		assertEquals(expected.getAddress().getFlatNo(), actual.getAddress().getFlatNo());
		assertEquals(expected.getAddress().getColony(), actual.getAddress().getColony());
		assertEquals(expected.getAddress().getCity(), actual.getAddress().getCity());
		assertEquals(expected.getAddress().getDistrict(), actual.getAddress().getDistrict());
		assertEquals(expected.getAddress().getState(), actual.getAddress().getState());
		assertEquals(expected.getAddress().getPincode(), actual.getAddress().getPincode());
		assertEquals(expected.getUser().getUserId(), actual.getUser().getUserId());
		assertEquals(expected.getUser().getUsername(), actual.getUser().getUsername());
		assertEquals(expected.getUser().getUserPassword(), actual.getUser().getUserPassword());
		
		
	}
	
	@Test
	void testFindAdminByFirstNameShouldReturnAdmin() throws NoSuchUserFoundException{
		Admin admin = context.getBean(Admin.class);
		admin.setAdminId(3);
		admin.setFirstName("Test");
		admin.setLastName("Test");
		admin.setPhoneNumber("0000000000");
		admin.setEmail("Test3@gmail.com");
		admin.getAddress().setAddressId(3);
		admin.getAddress().setFlatNo("Test");
		admin.getAddress().setColony("Test");
		admin.getAddress().setCity("Test");
		admin.getAddress().setDistrict("Test");
		admin.getAddress().setState("Test");
		admin.getAddress().setPincode("400606");
		admin.getUser().setUserId(3);
		admin.getUser().setUsername("Test3");
		admin.getUser().setUserPassword("Test");
		
		
		List<Admin> list = new ArrayList<Admin>();
		list.add(admin);
		when(repository.readAdminByFirstName("Test")).thenReturn(list);
		assertEquals(1, service.findAdminByFirstName("Test").size());
	}
	
	@Test
	void testFindAdminByLastNameShouldReturnAdmin() throws NoSuchUserFoundException{
		Admin admin = context.getBean(Admin.class);
		admin.setAdminId(3);
		admin.setFirstName("Test");
		admin.setLastName("Test");
		admin.setPhoneNumber("0000000000");
		admin.setEmail("Test3@gmail.com");
		admin.getAddress().setAddressId(3);
		admin.getAddress().setFlatNo("Test");
		admin.getAddress().setColony("Test");
		admin.getAddress().setCity("Test");
		admin.getAddress().setDistrict("Test");
		admin.getAddress().setState("Test");
		admin.getAddress().setPincode("400606");
		admin.getUser().setUserId(3);
		admin.getUser().setUsername("Test3");
		admin.getUser().setUserPassword("Test");
		

		List<Admin> list = new ArrayList<Admin>();
		list.add(admin);
		when(repository.readAdminByLastName("Test")).thenReturn(list);
		assertEquals(1, service.findAdminByLastName("Test").size());
	}
	
	@Test
	void testUpdateAdminEmail() throws InvalidCredentialsException, NoSuchUserFoundException {
		Admin expected = context.getBean(Admin.class);
		expected.setAdminId(1);
		expected.setFirstName("Test");
		expected.setLastName("Test");
		expected.setPhoneNumber("0000000000");
		expected.setEmail("Test@gmail.com");
		expected.getAddress().setAddressId(1);
		expected.getAddress().setFlatNo("Test");
		expected.getAddress().setColony("Test");
		expected.getAddress().setCity("Test");
		expected.getAddress().setDistrict("Test");
		expected.getAddress().setState("Test");
		expected.getAddress().setPincode("400606");
		expected.getUser().setUserId(1);
		expected.getUser().setUsername("Test");
		expected.getUser().setUserPassword("Test");
		
		
		when(repository.readAdminByEmail("Test@gmail.com")).thenReturn(expected);
		service.updateAdminEmail(1, "Test2@gmail.com");
		verify(repository).updateAdminEmail(1, "Test2@gmail.com");
	}
	@Test
	void testUpdateAdminPhoneNumber() throws InvalidCredentialsException, NoSuchUserFoundException{
		Admin expected = context.getBean(Admin.class);
		expected.setAdminId(1);
		expected.setFirstName("Test");
		expected.setLastName("Test");
		expected.setPhoneNumber("0000000000");
		expected.setEmail("Test@gmail.com");
		expected.getAddress().setAddressId(1);
		expected.getAddress().setFlatNo("Test");
		expected.getAddress().setColony("Test");
		expected.getAddress().setCity("Test");
		expected.getAddress().setDistrict("Test");
		expected.getAddress().setState("Test");
		expected.getAddress().setPincode("400606");
		expected.getUser().setUserId(1);
		expected.getUser().setUsername("Test");
		expected.getUser().setUserPassword("Test");
		
		
		service.updateAdminPhoneNumber(1, "8329950738");
		verify(repository).updateAdminPhoneNumber(1, "8329950738");
	}
	
	@Test
	void testFindAdminDetails() throws NoSuchUserFoundException{
		Admin admin = context.getBean(Admin.class);
		admin.setAdminId(3);
		admin.setFirstName("Test");
		admin.setLastName("Test");
		admin.setPhoneNumber("0000000000");
		admin.setEmail("Test3@gmail.com");
		admin.getAddress().setAddressId(3);
		admin.getAddress().setFlatNo("Test");
		admin.getAddress().setColony("Test");
		admin.getAddress().setCity("Test");
		admin.getAddress().setDistrict("Test");
		admin.getAddress().setState("Test");
		admin.getAddress().setPincode("400606");
		admin.getUser().setUserId(3);
		admin.getUser().setUsername("Test3");
		admin.getUser().setUserPassword("Test");
		
		
		List<Admin> list = new ArrayList<Admin>();
		list.add(admin);
		when(repository.findAll()).thenReturn(list);
		assertEquals(1, service.findAdminDetails().size());
	}
	
	@Test
	void testRemoveAdminByEmail() throws NoSuchUserFoundException{
		Admin expected = context.getBean(Admin.class);
		expected.setAdminId(1);
		expected.setFirstName("Test");
		expected.setLastName("Test");
		expected.setPhoneNumber("0000000000");
		expected.setEmail("Test@gmail.com");
		expected.getAddress().setAddressId(1);
		expected.getAddress().setFlatNo("Test");
		expected.getAddress().setColony("Test");
		expected.getAddress().setCity("Test");
		expected.getAddress().setDistrict("Test");
		expected.getAddress().setState("Test");
		expected.getAddress().setPincode("400606");
		expected.getUser().setUserId(1);
		expected.getUser().setUsername("Test");
		expected.getUser().setUserPassword("Test");
		
		
		when(repository.readAdminByEmail("Test@gmail.com")).thenReturn(expected);
		service.removeAdminByEmail("Test@gmail.com");
		verify(repository).removeAdminByEmail("Test@gmail.com");
	}
	
	@Test
	void testRemoveAdminByAdminId() throws NoSuchUserFoundException{
		Admin expected = context.getBean(Admin.class);
		expected.setAdminId(1);
		expected.setFirstName("Test");
		expected.setLastName("Test");
		expected.setPhoneNumber("0000000000");
		expected.setEmail("Test@gmail.com");
		expected.getAddress().setAddressId(1);
		expected.getAddress().setFlatNo("Test");
		expected.getAddress().setColony("Test");
		expected.getAddress().setCity("Test");
		expected.getAddress().setDistrict("Test");
		expected.getAddress().setState("Test");
		expected.getAddress().setPincode("400606");
		expected.getUser().setUserId(1);
		expected.getUser().setUsername("Test");
		expected.getUser().setUserPassword("Test");
		
		
		when(repository.existsById(expected.getAdminId())).thenReturn(true);
		Optional<Admin> expectation = Optional.of(expected);
		when(repository.findById(expected.getAdminId())).thenReturn(expectation);
		boolean result = service.removeAdminByadminId(1);
		verify(repository).deleteById(1);
	}
	


	@Test
	void testFindAllNgos() throws NoSuchNgoFoundException{
		Ngo ngo = context.getBean(Ngo.class);
		ngo.setNgoID(1);
		ngo.setNgoName("Test");
		ngo.setEmail("Test@gmail.com");
		ngo.setPhoneNumber("0000000000");
		ngo.setLicenceNo(0000000000);
		ngo.getAddress().setAddressId(1);
		ngo.getAddress().setFlatNo("Test");
		ngo.getAddress().setColony("Test");
		ngo.getAddress().setCity("Test");
		ngo.getAddress().setDistrict("Test");
		ngo.getAddress().setState("Test");
		ngo.getAddress().setPincode("400606");
		ngo.getUser().setUserId(1);
		ngo.getUser().setUsername("Test");
		ngo.getUser().setUserPassword("Test");
		
		
		List<Ngo> list = new ArrayList<Ngo>();
		list.add(ngo);
		when(repository2.findAll()).thenReturn(list);
		assertEquals(1, service.findNgo().size());
	}
	
	@Test
	void testDeleteNgoByName() throws NoSuchNgoFoundException{
		Ngo ngo = context.getBean(Ngo.class);
		ngo.setNgoID(1);
		ngo.setNgoName("Test");
		ngo.setEmail("Test@gmail.com");
		ngo.setPhoneNumber("0000000000");
		ngo.setLicenceNo(0000000000);
		ngo.getAddress().setAddressId(1);
		ngo.getAddress().setFlatNo("Test");
		ngo.getAddress().setColony("Test");
		ngo.getAddress().setCity("Test");
		ngo.getAddress().setDistrict("Test");
		ngo.getAddress().setState("Test");
		ngo.getAddress().setPincode("400606");
		ngo.getUser().setUserId(1);
		ngo.getUser().setUsername("Test");
		ngo.getUser().setUserPassword("Test");
		
		
		service.removeNgoByName("Test");
		verify(repository).removeNgoByName("Test");
	}
	
	@Test
	void testFindAllDonation() throws NoDonationFoundException{
		Donation donation = context.getBean(Donation.class);
		donation.setDonationId(1);
		donation.setBankName("Test");
		donation.setAccountNumber(00000000000);
		donation.setAmount(1000);
		donation.setIfscCode("Test");
		//donation.setDate(16/07/2021);
		donation.setPurposeOfDonation("Test");
		
		List<Donation> list  = new ArrayList<Donation>();
		list.add(donation);
		when(donationRepository.findAll()).thenReturn(list);
		assertEquals(1, service.findDonation().size());
	}
	
	@Test
	void testFindAllDonors() throws NoSuchUserFoundException{
		Donor donor = context.getBean(Donor.class);
		donor.setDonorId(1);
		donor.setFirstName("Test");
		donor.setLastName("Test");
		donor.setOccupation("Test");
		donor.setPhoneNumber("0000000000");
		donor.setEmail("Test@gmail.com");
		donor.getAddress().setAddressId(1);
		donor.getAddress().setFlatNo("Test");
		donor.getAddress().setColony("Test");
		donor.getAddress().setCity("Test");
		donor.getAddress().setDistrict("Test");
		donor.getAddress().setState("Test");
		donor.getAddress().setPincode("000000");
		donor.getUser().setUserId(1);
		donor.getUser().setUsername("Test");
		donor.getUser().setUserPassword("Test");
		donor.getNgos();
		
		List<Donor> list  = new ArrayList<Donor>();
		list.add(donor);
		when(donorRepository.findAll()).thenReturn(list);
		assertEquals(1, service.findDonor().size());
	}
	
	@Test
	void testFindAllCharityEvents() throws NoSuchEventFoundException{
		CharityEvents events = context.getBean(CharityEvents.class);
		events.setEventId(1);
		events.setEventName("Test");
		events.setEventType("Test");
		events.setEventOrganizer("Test");
		events.setSponseredBy("Test");
		events.setCharitableInstitute("Test");
		
		List<CharityEvents> list = new ArrayList<CharityEvents>();
		list.add(events);
		when(eventRepository.findAll()).thenReturn(list);
		assertEquals(1, service.findEvents().size());
	}
	
//	@Test
//	void testInvalidCredentialsException() {
//		Admin admin = context.getBean(Admin.class);
//		when(admin.getEmail()).thenReturn(null);
//		when(repository.readAdminByEmail(admin.getEmail())).thenReturn(admin);
//		assertThrows(InvalidCredentialsException.class, ()->{
//			service.findAdminByEmail("test123");
//		});
//	}

}