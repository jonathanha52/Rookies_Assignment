package rookies.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import rookies.demo.repository.UsersRepository;
import rookies.demo.service.IUserService;
import rookies.demo.exception.IdNotFoundException;
import rookies.demo.model.Users;

public class UserService implements IUserService{
    private final UsersRepository usersRepository;
    @Autowired
    public UserService(UsersRepository usersRepository){
        this.usersRepository = usersRepository;
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

}
