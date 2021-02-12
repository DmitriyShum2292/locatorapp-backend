package com.example.locatorback.services;

import com.example.locatorback.models.*;
import com.example.locatorback.repository.CoordinateRepository;
import com.example.locatorback.repository.TrustedUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

@Service
public class CoordinateService {

    @Autowired
    private CoordinateRepository coordinateRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private TrustedUserRepository trustedUserRepository;

    public void create(IncomingCoordiante incomingCoordiante){
        User user = userService.findCurrentUser();
        ArrayList<Coordinate>byUserByDate = coordinateRepository.findAllByUser(user, Sort.by("date"));
        if (byUserByDate.size()==24){
            Coordinate deleteCoord = byUserByDate.get(0);
            coordinateRepository.delete(deleteCoord);
        }
        Coordinate coordinate = new Coordinate();
        coordinate.setUser(user);
        coordinate.setLat(incomingCoordiante.getLat());
        coordinate.setLon(incomingCoordiante.getLon());
        Date date  = new Date();
        String dateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(date);
        coordinate.setDate(date);
        coordinate.setDateFormat(dateFormat);
        coordinateRepository.save(coordinate);
    }
    public ArrayList<OutCoordinate> readTrustedCoordinate(long id){
        TrustedUser trustedUser = trustedUserRepository.findById(id);
        User user = userService.findUserByEmail(trustedUser.getTrustedUser());
        ArrayList<Coordinate> coordinate = coordinateRepository.findByUser(user);
        ArrayList<OutCoordinate> outCoordinates  = new ArrayList<>();
        for (Coordinate newCoordinate:coordinate) {
            OutCoordinate outCoordinate = new OutCoordinate();
            outCoordinate.setLat(newCoordinate.getLat());
            outCoordinate.setLon(newCoordinate.getLon());
            outCoordinate.setId(newCoordinate.getId());
            outCoordinate.setDate(newCoordinate.getDateFormat());
            outCoordinates.add(outCoordinate);
        }
        return outCoordinates;
    }

    public ArrayList<OutCoordinate>  readAllMyCoordinates(){
        User user = userService.findCurrentUser();
        ArrayList<Coordinate>byUser = coordinateRepository.findAllByUser(user,Sort.by("date"));
        ArrayList<OutCoordinate>out = new ArrayList<>();
        for (Coordinate newCoordinate:byUser) {
            OutCoordinate outCoordinate = new OutCoordinate();
            outCoordinate.setId(newCoordinate.getId());
            outCoordinate.setLat(newCoordinate.getLat());
            outCoordinate.setLon(newCoordinate.getLon());
            outCoordinate.setDate(newCoordinate.getDateFormat());
            out.add(outCoordinate);
        }
        return out;
    }

}
