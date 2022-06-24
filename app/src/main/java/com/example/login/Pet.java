package com.example.login;

import android.graphics.drawable.Drawable;

import java.io.Serializable;
import java.util.Locale;

enum PetCategory{
    dogs,cats,horses,birds
}

public class Pet implements Serializable {

    private String description;
    private PetCategory category;
    private String photo;
    private String contactnum;
    private String price;
    private String location;
    private String gender;
    private String age;

    public Pet(String contactnum, String price, String location, PetCategory category, String photo, String gender, String age, String description) {
        this.contactnum = contactnum;
        this.price = price;
        this.location = location;

        this.category = category;
        this.photo = photo;
        this.gender = gender;
        this.age = age;
        this.description = description;
    }

    public Pet(String phone, String price, String location, String toString, String photo, boolean gender, String age, String description) {
    }

    public String getContactnum() {
        return contactnum;
    }

    public String getPrice() {
        return price;
    }

    public String getLocation() {
        return location;
    }



    public PetCategory getCategory() {

        return category;
    }

    public String getPhoto() {
        return photo;
    }

    public String getGender() {
        return gender;
    }

    public String getAge() {
        return age;
    }

    public String getDescription() {
        return description;
    }

    public void setContactnum(String contactnum) {
        this.contactnum = contactnum;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setLocation(String location) {
        this.location = location;
    }



    public void setCategory(PetCategory category) {
        this.category = category;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "contactnum='" + contactnum + '\'' +
                ", price='" + price + '\'' +
                ", location='" + location + '\'' +

                ", category=" + category +
                ", photo='" + photo + '\'' +
                ", gender=" + gender +
                ", age='" + age + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}



