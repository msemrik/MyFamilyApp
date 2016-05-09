package com.example.msem.myfamilyapp.business;

/**
 * Created by semri on 5/7/2016.
 */
public class Location {

    private String city;
    private String provice;
    private String country;
    private String latitude;
    private String longitud;

    public Location(String city, String provice, String country, String latitude, String longitud) {
        this.city = city;
        this.provice = provice;
        this.country = country;
        this.latitude = latitude;
        this.longitud = longitud;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvice() {
        return provice;
    }

    public void setProvice(String provice) {
        this.provice = provice;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }
}
