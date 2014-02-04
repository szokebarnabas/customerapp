package app;

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

@Controller
@RequestMapping("/customers")
public class CustomerController {

	private List<Customer> customerList;

	public CustomerController() {
		customerList = new ArrayList<Customer>();
	}

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
	List<Customer> getCustomerList() {
		return customerList;
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public @ResponseBody
	void addCustomer(@RequestBody Customer customer) {
		customerList.add(customer);
	}

	@RequestMapping(value = "/{customerId}", method = RequestMethod.DELETE)
	public @ResponseBody
	void deleteCustomer(@PathVariable int customerId) {
		for (Iterator<Customer> iterator = customerList.iterator(); iterator
				.hasNext();) {
			Customer customer = iterator.next();
			if (customer.getId().equals(String.valueOf(customerId))) {
				iterator.remove();
			}
		}
	}

}
