package rookies.demo.service;

import java.util.List;

import rookies.demo.dto.UserDto;
import rookies.demo.model.Users;

public interface IUserService {
    public List<Users> findAll();
    public boolean existsByEmail(String email);
    public boolean existsByUsername(String username);
    public void deleteUserById(Long id);
    public void updateUserInfo(Long id, UserDto userDto);
    public void updatePassword(Long id, String newPassword);
}
