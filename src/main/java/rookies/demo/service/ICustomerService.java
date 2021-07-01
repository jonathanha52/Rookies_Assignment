package rookies.demo.service;

import rookies.demo.model.Customer;

import java.util.List;
import java.util.Optional;

public interface ICustomerService {
    
    //public Optional<Customer> findByUserName(String username);
    public List<Customer> findAll();
    //public int updateCustomer(Long id, String username, String password, String firstName, String lastName);
    public void deleteCustomer(Long id);
    public Customer insertCustomer(Customer customer);
}
