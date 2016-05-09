package com.example.msem.myfamilyapp.business;

/**
 * Created by semri on 5/7/2016.
 */
public class Person {

    private String name;

    private String photoResource;

    private Location location;

    public Person(String name, String photoResource, Location location) {
        this.name = name;
        this.photoResource = photoResource;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoResource() {
        return photoResource;
    }

    public void setPhotoResource(String photoResource) {
        this.photoResource = photoResource;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
