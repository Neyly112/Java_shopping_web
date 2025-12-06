package vn.tdtu.edu.springcomerce.Services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import vn.tdtu.edu.springcomerce.Services.UserService;
import vn.tdtu.edu.springcomerce.models.Role;
import vn.tdtu.edu.springcomerce.models.User;
import vn.tdtu.edu.springcomerce.models.UserCheck;
import vn.tdtu.edu.springcomerce.Repository.UserRepo;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public void saveUser(UserCheck userCheck) {
        User user = new User();
        user.setUsername(userCheck.getUsername());
        user.setFname(userCheck.getFname());
        user.setLname(userCheck.getLname());
        user.setEmail(userCheck.getEmail());
        user.setPassword(passwordEncoder.encode(userCheck.getPassword()));
        user.setRole(Role.valueOf("USER"));
        userRepo.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return user;
    }

    @Override
    public User findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }

}
