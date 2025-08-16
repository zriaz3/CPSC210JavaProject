package model;

import java.util.ArrayList;

import org.json.JSONArray;


// Represents a list of all the people who lost dogs. 
public class ListPersonLost extends Matcher {
    private ArrayList<PersonLost> peopleLost;

    // EFFECTS: creates an empty list of personLost
    public ListPersonLost() {
        peopleLost = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: add a person to list of people who lost dogs
    public void addPerson(PersonLost person) {
        peopleLost.add(person);
        EventLog.getInstance().logEvent(new Event("Lost dog added"));
    }

    // MODIFIES: this
    // EFFECTS: remove a person from list of people who lost dogs
    public void removePerson(PersonLost person) {
        peopleLost.remove(person);
        EventLog.getInstance().logEvent(new Event("Lost dog removed"));
    }

    public ArrayList<PersonLost> getListPersonLost() {
        EventLog.getInstance().logEvent(new Event("Looked through all lost dogs"));
        return peopleLost;
    }

    // MODIFIES: this
    // EFFECTS: searches through the list to find potentional matches
    public ArrayList<PersonLost> searchLostPeople(Dog dog) {

        ArrayList<PersonLost> matches = new ArrayList<>();

        for (PersonLost personL : peopleLost) {
            if (isMatch(dog, personL)) {
                matches.add(personL);
            }
        }
        EventLog.getInstance().logEvent(new Event("Searched lost dogs for potential matches"));
        return matches;
    }

    // EFFECTS: returns PersonLost in the listPersonLost as a JSON array
    // Modeled after sample application provided
    public JSONArray toJson() {
        JSONArray jsonArray = new JSONArray();

        for (PersonLost p : peopleLost) {
            jsonArray.put(p.toJson());
        }

        return jsonArray;
    }
}