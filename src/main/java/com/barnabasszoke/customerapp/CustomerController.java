package com.barnabasszoke.customerapp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.barnabasszoke.customerapp.domain.Address;
import com.barnabasszoke.customerapp.domain.Customer;

@Controller
@RequestMapping("/customers")
public class CustomerController {

	private List<Customer> customerList;

	public CustomerController() {
		customerList = new ArrayList<Customer>();
		customerList.add(new Customer("jonn_doe", "John", "Doe", 28, new Address("New York", "street1", "state1", "34234"), "161968143514"));
		customerList.add(new Customer("jane_doe", "Jane", "Doe", 34, new Address("London", "street2", "-", "35452"), "34534534535"));
		customerList.add(new Customer("billy_bob", "Billy", "Bob", 33, new Address("Toronto", "street3", "state2", "46235"), "63623523525"));
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<Customer> getCustomerList() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return customerList;
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public void addCustomer(@RequestBody Customer customer) {
		customerList.add(customer);
	}

	@RequestMapping(value = "/{customerId}/delete", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void deleteCustomer(@PathVariable String customerId) {
		for (Iterator<Customer> iterator = customerList.iterator(); iterator.hasNext();) {
			Customer customer = iterator.next();
			if (customer.getId().equals(customerId)) {
				iterator.remove();
			}
		}
	}

	@RequestMapping(value = "/{customerId}/modify", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public void modifyCustomer(@PathVariable String customerId, @RequestBody Customer customer) {
		for (Iterator<Customer> iterator = customerList.iterator(); iterator.hasNext();) {
			Customer c = iterator.next();
			if (c.getId().equals(String.valueOf(customerId))) {
				iterator.remove();
				customerList.add(customer);
				return;
			}
		}
	}

	@RequestMapping(value = "/{customerId}", method = RequestMethod.GET)
	@ResponseBody
	public Customer getCustomer(@PathVariable String customerId) {
		for (Iterator<Customer> iterator = customerList.iterator(); iterator.hasNext();) {
			Customer customer = iterator.next();
			if (customer.getId().equals(String.valueOf(customerId))) {
				return customer;
			}
		}
		return null;
	}

}
