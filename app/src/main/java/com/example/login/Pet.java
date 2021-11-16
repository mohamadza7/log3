package com.example.login;
enum PetCategory{
    dogs,cats,horses,birds
}

public class Pet {

    private String contactnum;
    private int price;
    private String location;
    private String kind;
    private PetCategory category;
    private String photo;
    private boolean gender;
    private String age;
    private String description;

    public Pet() {
    }

    public Pet(String contactnum, int price, String location, String kind, PetCategory category, String photo, boolean gender, String age, String description) {
        this.contactnum = contactnum;
        this.price = price;
        this.location = location;
        this.kind = kind;
        this.category = category;
        this.photo = photo;
        this.gender = gender;
        this.age = age;
        this.description = description;
    }

    public String getContactnum() {
        return contactnum;
    }

    public void setContactnum(String contactnum) {
        this.contactnum = contactnum;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public PetCategory getCategory() {
        return category;
    }

    public void setCategory(PetCategory category) {
        this.category = category;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "contactnum='" + contactnum + '\'' +
                ", price=" + price +
                ", location='" + location + '\'' +
                ", kind='" + kind + '\'' +
                ", category=" + category +
                ", photo='" + photo + '\'' +
                ", gender=" + gender +
                ", age='" + age + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}


