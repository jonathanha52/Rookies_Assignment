package rookies.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import rookies.demo.model.Customer;

@Repository("customer")
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    public Optional<Customer> findByUserName(String userName);

}
