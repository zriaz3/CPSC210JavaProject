package model;

// Represents a person who found a lost dog, having a name, phone number, location and time dog was found and information about the dog.
public class PersonFound {
    private String name;     // Poster's name
    private String phoneNumber; // Poster's phone number
    private String location; // location where found
    private String timeFound; // time dog was found
    private Dog dog; // Poster's dog

    public PersonFound(String name, String phoneNumber, String location, String timeFound, Dog dog) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.dog  = dog;
        this.location = location;
        this.timeFound = timeFound;
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

    public String getTimeFound() {
        return timeFound;
    }

    // EFFECTS: return all information about person who found the dog and the dog as a String
    public String toString() {
        return "Poster: " + name + "\nLocation: " + location + "\nTime Found: " + timeFound + "\nDog Information: " + dog.toString();
    }

}
