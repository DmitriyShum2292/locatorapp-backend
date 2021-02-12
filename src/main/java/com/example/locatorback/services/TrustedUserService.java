package com.example.locatorback.services;

import com.example.locatorback.models.OutTrustedUsers;
import com.example.locatorback.models.TrustedUser;
import com.example.locatorback.models.User;
import com.example.locatorback.repository.TrustedUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TrustedUserService {

    @Autowired
    private TrustedUserRepository trustedUserRepository;
    @Autowired
    private UserService userService;

    public void addNew(String email){
        User user = userService.findCurrentUser();
        TrustedUser trustedUser = new TrustedUser();
        if (userService.findUserByEmail(email) != null) {
            trustedUser.setUser(userService.findUserByEmail(email));
            trustedUser.setTrustedUser(user.getEmail());
            trustedUserRepository.save(trustedUser);
        }
    }
    public ArrayList<OutTrustedUsers> getMyTrustedUsers(){
        User user = userService.findCurrentUser();
        ArrayList<TrustedUser> myTrustedUsers = trustedUserRepository.findAllByUser(user);
        ArrayList<OutTrustedUsers>users = new ArrayList<>();
        for (TrustedUser trustedUser:myTrustedUsers) {
            OutTrustedUsers trustedUsers = new OutTrustedUsers();
            trustedUsers.setId(trustedUser.getId());
            trustedUsers.setEmail(trustedUser.getTrustedUser());
            users.add(trustedUsers);
        }
        return users;
    }
    public ArrayList<OutTrustedUsers> getUsersITrust(){
        User user = userService.findCurrentUser();
        ArrayList<TrustedUser> iTrust = trustedUserRepository.findAllByTrustedUser(user.getEmail());
        ArrayList<OutTrustedUsers>users = new ArrayList<>();
        for (TrustedUser trustedUser:iTrust) {
            OutTrustedUsers trustedUsers = new OutTrustedUsers();
            trustedUsers.setId(trustedUser.getId());
            trustedUsers.setEmail(trustedUser.getUser().getEmail());
            users.add(trustedUsers);
        }
        return users;
    }
    public void deleteTrustedUser(long id){
    TrustedUser trustedUser = trustedUserRepository.findById(id);
    trustedUserRepository.delete(trustedUser);
    }

}
