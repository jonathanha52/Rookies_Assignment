package rookies.demo.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rookies.demo.repository.UsersRepository;
import rookies.demo.service.IUserService;
import rookies.demo.dto.UserDto;
import rookies.demo.exception.IdNotFoundException;
import rookies.demo.model.Users;

@Service
public class UserService implements IUserService{
    private final UsersRepository usersRepository;
    @Autowired
    public UserService(UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }
    public Users findById(long id){
        return this.usersRepository.findById(id).orElseThrow(() -> new IdNotFoundException(id));
    }
    public void insertUser(Users user){
        this.usersRepository.save(user);
    }
    @Override
    public List<Users> findAll() {
        return this.usersRepository.findAll();
    }
    @Override
    public boolean existsByEmail(String email) {
        return this.usersRepository.existsByEmail(email);
    }
    @Override
    public boolean existsByUsername(String username) {
        return this.usersRepository.existsByUsername(username);
    }
    @Override
    public void deleteUserById(Long id){
        Users user  = this.usersRepository.findById(id).orElseThrow(() -> new IdNotFoundException(id));
        this.usersRepository.delete(user);
    }

    @Override
    @Transactional
    public void updateUserInfo(Long id, UserDto userDto) {
        System.out.println(userDto.toString());
        Users user = this.usersRepository.findById(id).orElseThrow(() -> new IdNotFoundException(id));
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
    }       

    @Override
    @Transactional
    public void updatePassword(Long id, String newPassword) {
        Users user = this.usersRepository.findById(id).orElseThrow(() -> new IdNotFoundException(id));
        user.setPassword(newPassword);
    }

}
