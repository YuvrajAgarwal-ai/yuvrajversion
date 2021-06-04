package com.example.app_developement;

public class data {
    String name ,  location , description,date ,ImageId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImageId() {
        return ImageId;
    }

    public void setImageId(String imageId) {
        ImageId = imageId;
    }

    public data(String name, String location, String description, String date, String imageId) {
        this.name = name;
        this.location = location;
        this.description = description;
        this.date = date;
        ImageId = imageId;
    }
}
