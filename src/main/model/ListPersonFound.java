package model;

import java.util.ArrayList;

import org.json.JSONArray;

// Represents a list of all the people who found dogs. 
public class ListPersonFound extends Matcher {
    private ArrayList<PersonFound> peopleFound;

    // EFFECTS: creates an empty list of personFound
    public ListPersonFound() {
        peopleFound = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: add a person to list of people who found dogs
    public void addPerson(PersonFound person) {
        peopleFound.add(person);
        EventLog.getInstance().logEvent(new Event("Found dog added"));
    }

    // MODIFIES: this
    // EFFECTS: remove a person from list of people who found dogs
    public void removePerson(PersonFound person) {
        peopleFound.remove(person);
        EventLog.getInstance().logEvent(new Event("Found dog removed"));
    }

    public ArrayList<PersonFound> getListPersonFound() {
        EventLog.getInstance().logEvent(new Event("Looked through all found dogs"));
        return peopleFound;
    }

    // MODIFIES: this
    // EFFECTS: searches through the list to find potentional matches
    public ArrayList<PersonFound> searchFoundPeople(Dog dog) {
        ArrayList<PersonFound> matches = new ArrayList<>();

        for (PersonFound personF : peopleFound) {
            if (isMatch(dog, personF)) {
                matches.add(personF);
            }
        }
        EventLog.getInstance().logEvent(new Event("Searched found dogs for potential matches"));
        return matches;
    }

    // EFFECTS: returns PersonFound in the listPersonFound as a JSON array
    // Modeled after sample application provided
    public JSONArray toJson() {
        JSONArray jsonArray = new JSONArray();

        for (PersonFound p : peopleFound) {
            jsonArray.put(p.toJson());
        }

        return jsonArray;
    }
}