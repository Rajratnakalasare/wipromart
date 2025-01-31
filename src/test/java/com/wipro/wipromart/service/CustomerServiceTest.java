package com.wipro.wipromart.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.wipro.wipromart.entity.Customer;
import com.wipro.wipromart.exception.ResourceNotFoundException;
import com.wipro.wipromart.repository.CustomerRepository;

@SpringBootTest
public class CustomerServiceTest {

	@InjectMocks
	private CustomerService customerService = new CustomerServiceImpl();
	
	@Mock
	private CustomerRepository customerRepository;
	
	@Test
	void testGetCustomerById() {
		
		Customer customer = new Customer();
		customer.setCustomerId(200);
		customer.setFirstName("Rahul");
		customer.setLastName("Dravid");
		customer.setEmail("rahul@tmail.com");
		customer.setMobile("8832889134");
		customer.setCity("Bangalore");
		
		Optional<Customer> optionalCustomer = Optional.of(customer);
		
		when(customerRepository.findById(200L)).thenReturn(optionalCustomer);
		
		Customer actualCustomer = customerService.getCustomerById(200);
		
		assertEquals("Rahul",actualCustomer.getFirstName());		
		assertEquals("Bangalore",actualCustomer.getCity());		
		
	}
	
	@Test
	void testGetCustomerByIdWithException() {
		
		when(customerRepository.findById(200L)).thenThrow(ResourceNotFoundException.class);
				
		assertThrows(ResourceNotFoundException.class, ()-> customerService.getCustomerById(200));		
	}
	
	@Test
	void testSaveCustomer() {
		
		Customer customer = new Customer();
		customer.setCustomerId(200);
		customer.setFirstName("Rahul");
		customer.setLastName("Dravid");
		customer.setEmail("rahul@tmail.com");
		customer.setMobile("8832889134");
		customer.setCity("Bangalore");
		
		when(customerRepository.save(customer)).thenReturn(customer);
		
		Customer newCustomer = customerService.saveCustomer(customer);
		
		assertEquals(200,newCustomer.getCustomerId());		
		assertEquals("Rahul",newCustomer.getFirstName());	
		assertEquals("8832889134",newCustomer.getMobile());
		
		
}
	@Test
	void testGetAllCustomers() {
		
		Customer customer = new Customer();
		customer.setCustomerId(200);
		customer.setFirstName("Rahul");
		customer.setLastName("Dravid");
		customer.setEmail("rahul@tmail.com");
		customer.setMobile("8832889134");
		customer.setCity("Bangalore");
		
		Customer customer1 = new Customer();
		customer1.setCustomerId(300);
		customer1.setFirstName("Saurav");
		customer1.setLastName("Ganguly");
		customer1.setEmail("saurav@tmail.com");
		customer1.setMobile("8832889876");
		customer1.setCity("Kolkata");
		
		Customer customer2 = new Customer();
		customer2.setCustomerId(400);
		customer2.setFirstName("Mahendra");
		customer2.setLastName("Dhoni");
		customer2.setEmail("dhoni@tmail.com");
		customer2.setMobile("7763889134");
		customer2.setCity("Ranchi");
		
		List<Customer> myCustomers = new ArrayList<>();
		myCustomers.add(customer);
		myCustomers.add(customer1);
		myCustomers.add(customer2);
		
		
		when(customerRepository.findAll()).thenReturn(myCustomers);
		
		List<Customer>customerList=customerService.getAllCustomers();
		
		assertEquals(3, customerList.size());
	}
	
	@Test
	void testDeleteCustomer() {
		
		Customer customer = new Customer();
		customer.setCustomerId(200);
		customer.setFirstName("Rahul");
		customer.setLastName("Dravid");
		customer.setEmail("rahul@tmail.com");
		customer.setMobile("8832889134");
		customer.setCity("Bangalore");
		
		Optional<Customer> optionalCustomer = Optional.of(customer);
		
		when(customerRepository.findById(200L)).thenReturn(optionalCustomer);
		
		//when(productRepository.delete(optionalProduct.get()))
		
		doNothing().when(customerRepository).delete(customer);
		
	    customerService.deleteCustomer(200); // return type is void 
	    	    
	    
	    verify(customerRepository,times(1)).delete(customer);			    
	
	}
}
