package rookies.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rookies.demo.service.impl.CustomerService;
import rookies.demo.exception.IdNotFoundException;
import rookies.demo.exception.UserException.UsernameNotFoundException;
import rookies.demo.model.*;
@RequestMapping("api/v1/customer")
@RestController
public class ControllerController {
    
    CustomerService customerService;

    @Autowired
    public ControllerController(CustomerService customerService){
        this.customerService = customerService;
    }
    
    @GetMapping
    public List<Customer> findAll(){
        return customerService.findAll();
    }
    @GetMapping(path="/{username}")
    public Customer findByUserName(@PathVariable("username") String username){
        Customer result = null;
        try{
            result = this.customerService.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException(username) );
        }catch(UsernameNotFoundException e){
            System.out.println(e);
        }
        return result;
    }
    @GetMapping(path="/{id}")
    public Customer findByUserName(@PathVariable("id") Long id){
        Customer result = null;
        try{
            result = this.customerService.findById(id).orElseThrow(() -> new IdNotFoundException(id) );
        }catch(UsernameNotFoundException e){
            System.out.println(e);
        }
        return result;
    }

    @PostMapping
    public Customer insertNewCustomer(@RequestBody Customer customer){
        System.out.print(customer.toString());
        return this.customerService.insertCustomer(customer);
        
    }
    @DeleteMapping(path="/{id}")
    public void deleteCustomer(@PathVariable("id") Long id){
        this.customerService.deleteCustomer(id);
    }

    @PutMapping
    public void insertCustomer(@RequestBody Customer customer){
        this.customerService.insertCustomer(customer);
    }
    @PutMapping(path="/{id}")
    public void updateCustomer(@RequestBody Customer customer){
        this.customerService.updateCustomer(customer);
    }


}
