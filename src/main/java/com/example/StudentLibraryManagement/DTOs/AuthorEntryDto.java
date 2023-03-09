package com.example.StudentLibraryManagement.DTOs;

public class AuthorEntryDto {

    private String name;
    private int age;
    private String country;
    private double ratings;

    public AuthorEntryDto() {
    }

    public AuthorEntryDto(String name, int age, String country, double ratings) {
        this.name = name;
        this.age = age;
        this.country = country;
        this.ratings = ratings;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getRatings() {
        return ratings;
    }

    public void setRatings(double ratings) {
        this.ratings = ratings;
    }
}
