package com.example.locatorback.services;


import com.example.locatorback.models.IncomingUser;
import com.example.locatorback.models.User;
import com.example.locatorback.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findCurrentUser(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().
                getAuthentication().getPrincipal();
        User user = userRepository.findUserByEmail(userDetails.getUsername());
        return user;
    }
    public boolean create(IncomingUser incomingUser){
        User newUser = userRepository.findUserByEmail(incomingUser.getEmail());
        if (newUser == null){
            User user = new User();
            user.setEmail(incomingUser.getEmail());
            user.setPassword(incomingUser.getPassword());
            user.setActive(true);
            user.setAuthority("ROLE_USER");
            userRepository.save(user);
            return true;
        }
        else{
            return false;
        }
    }
    public User findUserByEmail(String email){
        User user = userRepository.findUserByEmail(email);
        return user;
    }
}
