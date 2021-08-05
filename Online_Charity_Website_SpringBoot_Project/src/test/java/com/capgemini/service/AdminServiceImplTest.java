package com.capgemini.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import com.capgemini.entity.Admin;
import com.capgemini.exception.InvalidCredentialsException;
import com.capgemini.exception.NoSuchUserFoundException;
import com.capgemini.exception.UniqueConstraintViolationException;

@SpringBootTest
class AdminServiceImplTest {
	@Autowired
	private AdminService service;
	
	@Autowired
	private ApplicationContext context;
	
	@Test
	void testFindAdminByUserNameShouldReturnAdmin() throws NoSuchUserFoundException, UniqueConstraintViolationException{
		Admin expected = context.getBean(Admin.class);
		expected.setFirstName("Test");
		expected.setLastName("Test");
		expected.setPhoneNumber("0000000000");
		expected.setEmail("Test@gmail.com");
		expected.getAddress().setFlatNo("Test");
		expected.getAddress().setColony("Test");
		expected.getAddress().setCity("Test");
		expected.getAddress().setDistrict("Test");
		expected.getAddress().setState("Test");
		expected.getAddress().setPincode("000000");
		expected.getUser().setUsername("Test");
		expected.getUser().setUserPassword("Test");
		
		
		service.addAdmin(expected);
		
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
	void testFindAdminByEmailShouldReturnAdmin() throws  InvalidCredentialsException, UniqueConstraintViolationException, NoSuchUserFoundException{
		Admin expected = context.getBean(Admin.class);
		expected.setFirstName("Test");
		expected.setLastName("Test");
		expected.setPhoneNumber("0000000000");
		expected.setEmail("Test2@gmail.com");
		expected.getAddress().setFlatNo("Test");
		expected.getAddress().setColony("Test");
		expected.getAddress().setCity("Test");
		expected.getAddress().setDistrict("Test");
		expected.getAddress().setState("Test");
		expected.getAddress().setPincode("000000");
		expected.getUser().setUsername("Test1");
		expected.getUser().setUserPassword("Test");
		
		
		service.addAdmin(expected);
		
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
	void testShouldThrowNoSuchUserFoundException() {
		Admin expected = context.getBean(Admin.class);
		 assertThrows(NoSuchUserFoundException.class, ()->{
			service.findAdminByUserName(expected.getUser().getUsername());
		});	
	}
	
	
}