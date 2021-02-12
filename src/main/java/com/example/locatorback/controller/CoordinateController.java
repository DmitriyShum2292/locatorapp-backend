package com.example.locatorback.controller;

import com.example.locatorback.models.*;
import com.example.locatorback.services.CoordinateService;
import com.example.locatorback.services.TrustedUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/personal")
public class CoordinateController {

    @Autowired
    private CoordinateService coordinateService;
    @Autowired
    private TrustedUserService trustedUserService;

    @GetMapping("/my")
    public ArrayList<OutCoordinate> getMyCoordinate(){
        return coordinateService.readAllMyCoordinates();
    }

    @PostMapping("/addtrusted")
    public void addTrustedUser(@RequestBody String email){
        trustedUserService.addNew(email);
    }

    @GetMapping("/trustedusers")
    public ArrayList<OutTrustedUsers> getMyTrustedUsers(){
        ArrayList<OutTrustedUsers> myTrustedUsers = trustedUserService.getMyTrustedUsers();
        return myTrustedUsers;
    }

    @GetMapping("/trustedusers/{id}")
    public ArrayList<OutCoordinate> getCoordinatesMyTrusted(@PathVariable(value = "id")long id){
        ArrayList<OutCoordinate> coordinate = coordinateService.readTrustedCoordinate(id);
        return coordinate;
    }
    @GetMapping("/trustedusers/itrust")
    public ArrayList<OutTrustedUsers> getUsersItrust(){
        ArrayList<OutTrustedUsers> outTrustedUsers = trustedUserService.getUsersITrust();
        return outTrustedUsers;
    }
    @GetMapping("/trustedusers/delete/{id}")
    public void deleteTrustedUser(@PathVariable(value = "id")long id){
        trustedUserService.deleteTrustedUser(id);
    }
}
