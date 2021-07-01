package rookies.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rookies.demo.service.impl.CustomerService;
import rookies.demo.exception.UserException.UserNotFoundException;
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
    // @GetMapping(path="/{username}")
    // public Customer findByUsername(@PathVariable("username") String username){
    //     Customer result = null;
    //     try{
    //         return result = customerService.findByUserName(username).orElseThrow(() -> new UserNotFoundException(username));
    //     } catch (UserNotFoundException e){
    //         System.out.println(e);
    //     }
    //     return result;
    // }
    @PostMapping
    public Customer insertNewCustomer(@RequestBody Customer customer){
        System.out.print(customer.toString());
        return this.customerService.insertCustomer(customer);
        
    }
    @DeleteMapping(path="/{username}")
    public void deleteCustomer(@PathVariable("username") Long id){
        this.customerService.deleteCustomer(id);
    }
    

}
