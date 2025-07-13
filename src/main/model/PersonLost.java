package model;

public class PersonLost {
    private String name;     // Poster's name
    private String status;   // Dog lost 
    private int phoneNumber; // Poster's phone number
    private String location; // location where lost
    private String timeLost; // time dog was lost
    private Dog dog; // Poster's dog

    public PersonLost(String name, String status, int phoneNumber, String location, String timeLost, Dog dog) {
        this.name = name;
        this.status = status;
        this.phoneNumber = phoneNumber;
        this.dog  = dog;
        this.location = location;
        this.timeLost = timeLost;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public Dog getDog() {
        return dog;
    }

    public String getLocation() {
        return location;
    }

    public String getTimeLost() {
        return timeLost;
    }

    // return all information about person who lost the dog and the dog as a String
    public String toString() {
        return "Poster: " + name + "/nLocation: " + location + "/nTime Lost: " 
            + timeLost + "/nDog Information: " + dog.toString();
    }

}
