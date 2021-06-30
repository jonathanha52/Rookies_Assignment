package rookies.demo.service;

import rookies.demo.model.User;
import java.util.Optional;

public interface IUserService {
    
    public Optional<User> findByUsername(String username);

}
