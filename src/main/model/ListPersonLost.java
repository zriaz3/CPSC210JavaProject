package model;

import java.util.ArrayList;

// Represents a list of all the people who lost dogs. 
public class ListPersonLost {

    private PersonLost personLost;
    private ArrayList<PersonLost> peopleLost;

    public ListPersonLost() {
        peopleLost = new ArrayList<>();
    }

    // EFFECTS: add a person to list of people who lost dogs
    public void addPerson(PersonLost person) {

    }

    // EFFECTS: remove a person from list of people who lost dogs
    public void removePerson(PersonLost person) {

    }

    public ArrayList<PersonLost> getPeopleLost() {
        return peopleLost;
    }

    // MODIFIES: this
    // EFFECTS: searches through the list to find potentional matches
    public ArrayList<PersonLost> searchLostPeople(PersonFound person) {
        return peopleLost;
    }

    // EFFECTS: compares found dog to lost dogs and return the person who lost if close match
    public Boolean isMatch (PersonFound personF, PersonLost personL) {
        return false;
    }

}