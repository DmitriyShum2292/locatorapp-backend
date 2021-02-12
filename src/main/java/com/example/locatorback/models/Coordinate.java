package com.example.locatorback.models;


import javax.persistence.*;
import java.util.Date;

@Entity
public class Coordinate implements Comparable<Coordinate>{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @ManyToOne
    private User user;
    private double lat;
    private double lon;
    private String dateFormat;
    private Date date;

    public Coordinate() {
    }

    public Coordinate(User user, double lat, double lon) {
        this.user = user;
        this.lat = lat;
        this.lon = lon;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    @Override
    public int compareTo(Coordinate o) {
        return Long.compare(this.date.getTime(), o.date.getTime());
    }
}
