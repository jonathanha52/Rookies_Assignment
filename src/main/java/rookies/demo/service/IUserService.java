package rookies.demo.service;

import java.util.List;

import rookies.demo.model.Users;

public interface IUserService {
    public List<Users> findAll();
    public boolean existsByEmail(String email);
    public boolean existsByUsername(String username);
    public void deleteUserById(Long id);
}
