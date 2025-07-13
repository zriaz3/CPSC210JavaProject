package model;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

// Represents a list of all the people who found dogs. 
public class ListPersonFound {

    private PersonFound personFound;
    private ArrayList<PersonFound> peopleFound;

    public ListPersonFound() {
        peopleFound = new ArrayList<>();
    }

    // EFFECTS: add a person to list of people who found dogs
    public void addPerson(PersonFound person) {
        peopleFound.add(person);
    }

    // EFFECTS: remove a person from list of people who found dogs
    public void removePerson(PersonFound person) {
        peopleFound.remove(person);
    }

    public ArrayList<PersonFound> getPeopleFound() {
        return peopleFound;
    }

    // EFFECTS: returns true if the 2 people's dogs are similar
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
}