package com.company;

public class Player {
    private String Name,Country,Club,Position;
    private  int Age,Number;
    private double WeeklySalary,Height;

    public Player(String name, String country, int age, double height, String club, String position, int number,double weeklySalary) {
        Name = name;
        Country = country;
        Club = club;
        Position = position;
        Age = age;
        Number = number;
        WeeklySalary = weeklySalary;
        Height = height;
    }
    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }
    public String getCountry() {
        return Country;
    }
    public void setCountry(String country) {
        Country = country;
    }
    public String getClub() {
        return Club;
    }
    public void setClub(String club) {
        Club = club;
    }
    public String getPosition() {
        return Position;
    }
    public void setPosition(String position) {
        Position = position;
    }
    public int getAge() {
        return Age;
    }
    public void setAge(int age) {
        Age = age;
    }
    public int getNumber() {
        return Number;
    }
    public void setNumber(int number) {
        Number = number;
    }
    public double getWeeklySalary() {
        return WeeklySalary;
    }
    public void setWeeklySalary(double weeklySalary) {
        WeeklySalary = weeklySalary;
    }
    public double getHeight() {
        return Height;
    }
    public void setHeight(double height) {
        Height = height;
    }
}
