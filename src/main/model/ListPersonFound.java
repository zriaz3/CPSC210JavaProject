package model;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

// Represents a list of all the people who found dogs. 
public class ListPersonFound {
    
    private ArrayList<PersonFound> peopleFound;

    // EFFECTS: creates an empty list of personFound
    public ListPersonFound() {
        peopleFound = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: add a person to list of people who found dogs
    public void addPerson(PersonFound person) {
        peopleFound.add(person);
    }

    // MODIFIES: this
    // EFFECTS: remove a person from list of people who found dogs
    public void removePerson(PersonFound person) {
        peopleFound.remove(person);
    }

    public ArrayList<PersonFound> getListPersonFound() {
        return peopleFound;
    }

    // EFFECTS: returns true if the 2 poster's dogs are similar
    public Boolean isMatch(PersonLost personL, PersonFound personF) {
        int score = 0;
        Dog lost = personL.getDog();
        Dog found = personF.getDog();
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
    public ArrayList<PersonFound> searchFoundPeople(PersonLost personL) {
        ArrayList<PersonFound> matches = new ArrayList<>();

        for (PersonFound personF : peopleFound) {
            if (isMatch(personL, personF)) {
                matches.add(personF);
            }
        }

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