package model;

// Represents a person who lost a dog, having a name, phone number, location and time dog was lost.
// includes information about the dog.
public class PersonLost {
    private String name;        // Poster's name
    private String phoneNumber; // Poster's phone number
    private String location;    // location where lost
    private String timeLost;    // time dog was lost
    private Dog dog;            // Poster's dog

    // EFFECTS: creates a person who lost a dog with a name, number, location found, time found and dog info
    public PersonLost(String name, String phoneNumber, String location, String timeLost, Dog dog) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.dog  = dog;
        this.location = location;
        this.timeLost = timeLost;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
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

    // EFFECTS: return all information about person who lost the dog and the dog as a String
    public String toString() {
        return "Poster: " + name + "\nLocation: " + location + "\nTime Lost: " 
            + timeLost + "\nDog Information: " + dog.toString();
    }

    // EFFECTS: return contact info of poster as a String
    public String contactInfo() {
        return "Poster name: " + name + "\nPhone number: " + phoneNumber;
    }

}
