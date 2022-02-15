package com.example.login;

enum PetCategory{
    dogs,cats,horses,birds
}

public class Pet {

    private String contactnum;
    private String price;
    private String location;


    private PetCategory category;
    private String photo;
    private boolean gender;
    private String age;
    private String description;

    public Pet(String contactnum, String price, String location, PetCategory category, String photo, boolean gender, String age, String description) {
        this.contactnum = contactnum;
        this.price = price;
        this.location = location;

        this.category = category;
        this.photo = photo;
        this.gender = gender;
        this.age = age;
        this.description = description;
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



    public String getCategory() {
        return String.valueOf(category);
    }

    public String getPhoto() {
        return photo;
    }

    public boolean getGender() {
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

    public void setGender(boolean gender) {
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



