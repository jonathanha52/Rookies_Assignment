package rookies.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import rookies.demo.service.impl.UserService;
import rookies.demo.exception.UserException.UserNotFoundException;
import rookies.demo.model.User;

@RestController
public class UserController {
    
    UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/user/{username}")
    public User findByUsername(String username){
        return userService.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
    }

}
