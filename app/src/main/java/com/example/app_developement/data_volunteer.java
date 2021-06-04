package com.example.app_developement;

public class data_volunteer {
    String name , email , imageId , description ,location;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public data_volunteer(String name, String email, String imageId, String description, String location) {
        this.name = name;
        this.email = email;
        this.imageId = imageId;
        this.description = description;
        this.location = location;
    }
}
