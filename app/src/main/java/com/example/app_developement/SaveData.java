package com.example.app_developement;

public class SaveData {

    String name ,  item , description,date ,ImageId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
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

    public SaveData(String name,  String item, String description, String date, String imageId) {
        this.name = name;
        this.item = item;
        this.description = description;
        this.date = date;
        ImageId = imageId;
    }
}
