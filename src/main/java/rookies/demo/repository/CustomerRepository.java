package rookies.demo.repository;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import rookies.demo.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    /*
    @Query(value="select (id, username, last_name, first_name, password) from customer where username = :username", nativeQuery = true)
    public Optional<Customer> findByUserName(@Param("username") String userName);

    //PATCH
    @Transactional
    @Query(value="update customer set first_name = :first_name, last_name = :last_name, password = :password where id = :id ", nativeQuery=true)
    public int updateCustomer(@Param("id") Long id, @Param("username") String username, @Param("first_name") String firstName, @Param("last_name") String last_name, @Param("password") String password);

    @Transactional
    @Query(value="update customer set last_name = :last_name where username = :username ", nativeQuery=true)
    public int setLastNameByUserName(@Param("username") String username, @Param("last_name") String lastName);
    //DELETE
    */

}
