package vn.tdtu.edu.springcomerce.Services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vn.tdtu.edu.springcomerce.models.User;
import vn.tdtu.edu.springcomerce.models.UserCheck;

import java.util.List;

@Service
public interface UserService  {
    List<User> getAllUsers();
    void saveUser(UserCheck user);
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
    User findByUsername(String username);
    void deleteUser(Long id);
}
