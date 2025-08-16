package model;

import org.json.JSONObject;

import persistence.Writable;

// Represents a person who lost a dog, having a name, phone number, location and time dog was lost.
// includes information about the dog.
public class PersonLost extends Person implements Writable {

    // EFFECTS: creates a person who lost a dog with a name, number, location found,
    // time found and dog info
    public PersonLost(String name, String phoneNumber, String location, String time, Dog dog) {
        super(name, phoneNumber, location, time, dog);
    }

    @Override
    // EFFECTS: return all information about person who lost the dog and the dog as
    // a String
    public String toString() {
        return "Poster: " + name + "\nLocation: " + location + "\nTime Lost: "
                + time + "\nDog Information: " + dog.toString();
    }

    // EFFECTS: returns PersonLost as a JSON
    // Modeled after sample application provided
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("phoneNumber", phoneNumber);
        json.put("location", location);
        json.put("timeLost", time);
        json.put("dog", dog.toJson());
        return json;
    }
}
