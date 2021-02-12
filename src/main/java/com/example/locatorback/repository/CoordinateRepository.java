package com.example.locatorback.repository;

import com.example.locatorback.models.Coordinate;
import com.example.locatorback.models.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface CoordinateRepository extends JpaRepository<Coordinate,Long> {
    ArrayList<Coordinate> findAllByUser(User user, Sort sort);
    ArrayList<Coordinate> findByUser(User user);
}
