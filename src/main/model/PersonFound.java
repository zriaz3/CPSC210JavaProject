package model;

import org.json.JSONObject;

import persistence.Writable;

// Represents a person who found a lost dog, having a name, phone number, location, time dog was found 
// Includes information about the dog.
public class PersonFound implements Writable {
    private String name;        // Poster's name
    private String phoneNumber; // Poster's phone number
    private String location;    // location where found
    private String timeFound;   // time dog was found
    private Dog dog;            // Poster's dog

    // EFFECTS: creates a person who found a dog with a name, number, location found, time found and dog info
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
        return "Poster: " + name + "\nLocation: " + location + "\nTime Found: " 
                + timeFound + "\nDog Information: " + dog.toString();
    }

    // EFFECTS: return contact info of poster as a String
    public String contactInfo() {
        return "Poster name: " + name + "\nPhone number: " + phoneNumber;
    }

    // EFFECTS: returns PersonFound as a JSON
    // Modeled after sample application provided
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("phoneNumber", phoneNumber);
        json.put("location", location);
        json.put("timeFound", timeFound);
        json.put("dog", dog.toJson());
        return json;
    }
}
