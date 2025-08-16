package model;

import org.json.JSONObject;

import persistence.Writable;

// Represents a person who found a lost dog, having a name, phone number, location, time dog was found 
// Includes information about the dog.
public class PersonFound extends Person implements Writable {

    // EFFECTS: creates a person who found a dog with a name, number, location found, time found and dog info
    public PersonFound(String name, String phoneNumber, String location, String time, Dog dog) {
        super(name, phoneNumber, location, time, dog);
    }

    @Override
    // EFFECTS: return all information about person who found the dog and the dog as a String
    public String toString() {
        return "Poster: " + name + "\nLocation: " + location + "\nTime Found: " 
                + time + "\nDog Information: " + dog.toString();
    }

    // EFFECTS: returns PersonFound as a JSON
    // Modeled after sample application provided
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("phoneNumber", phoneNumber);
        json.put("location", location);
        json.put("timeFound", time);
        json.put("dog", dog.toJson());
        return json;
    }
}
