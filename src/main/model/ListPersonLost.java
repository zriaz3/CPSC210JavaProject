package model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;


// Represents a list of all the people who lost dogs. 
public class ListPersonLost {

    private ArrayList<PersonLost> peopleLost;

    // EFFECTS: creates an empty list of personLost
    public ListPersonLost() {
        peopleLost = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: add a person to list of people who lost dogs
    public void addPerson(PersonLost person) {
        peopleLost.add(person);
    }

    // MODIFIES: this
    // EFFECTS: remove a person from list of people who lost dogs
    public void removePerson(PersonLost person) {
        peopleLost.remove(person);
    }

    public ArrayList<PersonLost> getListPersonLost() {
        return peopleLost;
    }

    // EFFECTS: returns true if the 2 people's dogs are similar
    public Boolean isMatch(Dog dog, PersonLost personL) {
        int score = 0;
        Dog lost = personL.getDog();
        Dog found = dog;
        if (lost.getName().equalsIgnoreCase(found.getName())) {
            score++;
        }
        if (lost.getAge() == (found.getAge())) {
            score++;
        }
        if (lost.getBreed().equalsIgnoreCase(found.getBreed())) {
            score++;
        }
        if (lost.getFurColor().equalsIgnoreCase(found.getFurColor())) {
            score++;
        }
        if (lost.getSize().equalsIgnoreCase(found.getSize())) {
            score++;
        }
        if (lost.getBuild().equalsIgnoreCase(found.getBuild())) {
            score++;
        }

        return score >= 3;
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