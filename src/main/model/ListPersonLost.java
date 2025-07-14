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
        peopleLost.add(person);
    }

    // EFFECTS: remove a person from list of people who lost dogs
    public void removePerson(PersonLost person) {
        peopleLost.remove(person);
    }

    public ArrayList<PersonLost> getListPersonLost() {
        return peopleLost;
    }

    // EFFECTS: returns true if the 2 people's dogs are similar
    public Boolean isMatch(PersonFound personF, PersonLost personL) {
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
    public ArrayList<PersonLost> searchLostPeople(PersonFound personF) {

        ArrayList<PersonLost> matches = new ArrayList<>();

        for (PersonLost personL : peopleLost) {
            if (isMatch(personF, personL)) {
                matches.add(personL);
            }
        }

        return matches;
    }

}