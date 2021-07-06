package rookies.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import rookies.demo.repository.CustomerRepository;
import rookies.demo.service.ICustomerService;
import rookies.demo.exception.IdNotFoundException;
import rookies.demo.model.Customer;

@Service
public class CustomerService implements ICustomerService{
    
    final CustomerRepository userRepository;

    @Autowired
    public CustomerService(CustomerRepository userRepository){
        this.userRepository = userRepository;
    }
    
    @Override
    public Optional<Customer> findById(Long id){
        return this.userRepository.findById(id);
    }
    public Optional<Customer> findByUserName(String username){
        return this.userRepository.findByUserName(username);
    }
    @Override
    public List<Customer> findAll(){
        return this.userRepository.findAll();
    }
    @Override
    @Transactional
    public void updateCustomer(Customer customer){
        Customer result = this.userRepository.findById(customer.getId()).orElseThrow(() -> new IdNotFoundException(customer.getId()));
        result.setFirstName(customer.getFirstName());
        result.setLastName(customer.getLastName());
        result.setPassword(customer.getPassword());
        result.setUserName(customer.getUserName());
    }


    /*
    @Override
    public Optional<Customer> findByUserName(String username){
        return this.userRepository.findByUserName(username);    
    }
    @Override
    public int updateCustomer(Long id, String username,String firstName, String lastName, String password){
        return this.userRepository.updateCustomer(id, username,firstName, lastName, password);
    }
    */
    @Override
    public void deleteCustomer(Long id){
        this.userRepository.deleteById(id);
    }
    @Override
    public Customer insertCustomer(Customer customer){
        return this.userRepository.save(customer);
    }
}
