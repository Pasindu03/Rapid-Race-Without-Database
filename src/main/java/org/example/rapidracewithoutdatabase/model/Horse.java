package org.example.rapidracewithoutdatabase.model;

import javafx.scene.image.Image;

public class Horse {
    private int Id;
    private String Name;
    private int Age;
    private String Breed;
    private String JockeyName;
    private int RacesParticipated;
    private int RacesWon;
    private String Group;
    private Image image;
    private int raceTime;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public String getBreed() {
        return Breed;
    }

    public void setBreed(String breed) {
        Breed = breed;
    }

    public String getJockeyName() {
        return JockeyName;
    }

    public void setJockeyName(String jockeyName) {
        JockeyName = jockeyName;
    }

    public int getRacesParticipated() {
        return RacesParticipated;
    }

    public void setRacesParticipated(int racesParticipated) {
        RacesParticipated = racesParticipated;
    }

    public int getRacesWon() {
        return RacesWon;
    }

    public void setRacesWon(int racesWon) {
        RacesWon = racesWon;
    }

    public String getGroup() {
        return Group;
    }

    public void setGroup(String group) {
        Group = group;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getRaceTime() {
        return raceTime;
    }

    public void setRaceTime(int raceTime) {
        this.raceTime = raceTime;
    }

    @Override
    public String toString() {
        return String.format("ID : " + Id + ", Name : " + Name + ", Age : " + Age + ", Breed : " + Breed + ", Jockey Name : " + JockeyName + ", Races Participated : " + RacesParticipated + ", Races Won : " + RacesWon);
    }

    public Horse() {}

    public Horse(int id, String name, int age, String breed, String jockeyName, int racesParticipated, int racesWon, String group, Image image) {
        Id = id;
        Name = name;
        Age = age;
        Breed = breed;
        JockeyName = jockeyName;
        RacesParticipated = racesParticipated;
        RacesWon = racesWon;
        Group = group;
        this.image = image;
    }

    public Horse(int id, String name, int age, String breed, String jockeyName, int racesParticipated, int racesWon, String group) {
        Id = id;
        Name = name;
        Age = age;
        Breed = breed;
        JockeyName = jockeyName;
        RacesParticipated = racesParticipated;
        RacesWon = racesWon;
        Group = group;
    }
}
