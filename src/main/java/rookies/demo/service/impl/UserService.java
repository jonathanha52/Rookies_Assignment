package rookies.demo.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import rookies.demo.repository.UserRepository;
import rookies.demo.service.IUserService;
import rookies.demo.model.User;

public class UserService implements IUserService{
    
    final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findByUsername(String username){
        return this.userRepository.findByUsername(username);    
    }
}
