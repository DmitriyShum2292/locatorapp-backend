package com.example.locatorback.repository;

import com.example.locatorback.models.TrustedUser;
import com.example.locatorback.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface TrustedUserRepository extends CrudRepository<TrustedUser,Long> {
    ArrayList<TrustedUser> findAllByUser(User user);
    ArrayList<TrustedUser> findAllByTrustedUser(String trustedUser);
    TrustedUser findById(long id);
}
