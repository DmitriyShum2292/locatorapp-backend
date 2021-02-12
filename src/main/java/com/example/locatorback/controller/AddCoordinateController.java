package com.example.locatorback.controller;

import com.example.locatorback.models.IncomingCoordiante;
import com.example.locatorback.services.CoordinateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AddCoordinateController {

    @Autowired
    private CoordinateService coordinateService;

    @PostMapping("/add")
    public String addCoordinate(@RequestBody IncomingCoordiante incomingCoordiante){
        coordinateService.create(incomingCoordiante);
        return "Added";
    }
}
