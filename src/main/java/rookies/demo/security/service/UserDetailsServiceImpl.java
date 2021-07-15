package rookies.demo.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import rookies.demo.repository.UsersRepository;
import rookies.demo.model.Users;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    private UsersRepository usersRepository;

    @Autowired
    public UserDetailsServiceImpl (UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = this.usersRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
        return UserDetailsImpl.build(user);
    }
    
}
