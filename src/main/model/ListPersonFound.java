package model;

import java.util.ArrayList;

// Represents a list of all the people who found dogs. 
public class ListPersonFound {

    private PersonFound personFound;
    private ArrayList<PersonFound> peopleFound;

    public ListPersonFound() {
        peopleFound = new ArrayList<>();
    }

    // EFFECTS: add a person to list of people who found dogs
    public void addPerson(PersonFound person) {

    }

    // EFFECTS: remove a person from list of people who found dogs
    public void removePerson(PersonFound person) {

    }

    public ArrayList<PersonFound> getPeopleFound() {
        return peopleFound;
    }

    // MODIFIES: this
    // EFFECTS: searches through the list to find potentional matches
    public ArrayList<PersonFound> searchFoundPeople(PersonLost person) {
        return peopleFound;
    }

    // EFFECTS: returns true if the 2 people's dogs are similar 
    public Boolean isMatch(PersonLost personL, PersonFound personF) {
        return false;
    }

}