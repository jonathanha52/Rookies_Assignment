package rookies.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rookies.demo.dto.UserDto;
import rookies.demo.model.Users;
import rookies.demo.service.impl.UserService;

@CrossOrigin(origins = "*")
@RequestMapping("api/v1/users")
@RestController
public class UserController {
    
    UserService usersSerivce;

    @Autowired
    public UserController(UserService userService){
        this.usersSerivce = userService;
    }

    @GetMapping("/public/{id}")
    public Users getUserById(@PathVariable("id") long id){
        return this.usersSerivce.findById(id);
    }
    @GetMapping
    public List<Users> getAllUser(){
        return this.usersSerivce.findAll();
    }
    @DeleteMapping("/admin/{id}")
    public void deleteUser(@PathVariable("id") long id){
        this.usersSerivce.deleteUserById(id);
    }
    @PutMapping("/public/{id}")
    public void updateUser(@PathVariable("id") long id,@RequestBody UserDto userDto){
        this.usersSerivce.updateUserInfo(id, userDto);
    }

}
