package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ListPersonFoundTest {

    private PersonFound testPersonFound;
    private PersonFound testPersonFound2;
    private Dog testDog;
    private Dog testDog2;
    private Dog testDog3;
    private ListPersonFound testPeopleFound;
    private PersonLost testPersonLost;
    private PersonLost testPersonLost2;
    
    @BeforeEach
    public void runBefore() {
        testDog = new Dog("Cutie", 7, "Border Collie", "White", "Small", "Lean", "images/Cutie.PNG");
        testDog2 = new Dog("Gru", 5, "Street", "Black", "Big", "Muscular", "images/Gru.PNG");
        testDog3 = new Dog("Brownie", 5, "Street", "Black", "Big", "Lean", "images/Brownie.PNG");
        testPersonFound = new PersonFound("Zain", "1234567891", "Vancouver", "7pm July 1st", testDog);
        testPersonFound2 = new PersonFound("Bee", "1234567541", "Burnaby", "3pm July 10th", testDog2);
        testPersonLost = new PersonLost("Max", "5656737383", "Burnaby", "2pm July 11th", testDog3);
        testPersonLost2 = new PersonLost("Me", "5657838822", "Vancouver", "10pm July 1st", testDog);
        testPeopleFound = new ListPersonFound();
    }

    @Test
    public void testAddPerson() {
        testPeopleFound.addPerson(testPersonFound);
        assertTrue(testPeopleFound.getListPersonFound().contains(testPersonFound));
    }

    @Test
    public void testAddTwoPeople() {
        testPeopleFound.addPerson(testPersonFound);
        assertTrue(testPeopleFound.getListPersonFound().contains(testPersonFound));
        testPeopleFound.addPerson(testPersonFound2);
        assertTrue(testPeopleFound.getListPersonFound().contains(testPersonFound2));
    }

    @Test
    public void testRemovePerson() {
        testPeopleFound.addPerson(testPersonFound);
        assertTrue(testPeopleFound.getListPersonFound().contains(testPersonFound));
        testPeopleFound.removePerson(testPersonFound);
        assertTrue(testPeopleFound.getListPersonFound().isEmpty());
    }

    @Test
    public void testRemoveTwoPeople() {
        testPeopleFound.addPerson(testPersonFound);
        assertTrue(testPeopleFound.getListPersonFound().contains(testPersonFound));
        testPeopleFound.addPerson(testPersonFound2);
        assertTrue(testPeopleFound.getListPersonFound().contains(testPersonFound2));
        testPeopleFound.removePerson(testPersonFound);
        testPeopleFound.removePerson(testPersonFound2);
        assertTrue(testPeopleFound.getListPersonFound().isEmpty());
    }

    @Test
    public void testIsMatch() {
        assertFalse(testPeopleFound.isMatch(testDog3, testPersonFound)); // testDog3 and testDog
        assertTrue(testPeopleFound.isMatch(testDog3, testPersonFound2)); // testDog3 and testDog2
        assertTrue(testPeopleFound.isMatch(testDog, testPersonFound)); // testDog and testDog
        assertFalse(testPeopleFound.isMatch(testDog, testPersonFound2)); // testDog and testDog2
    }

    @Test
    public void testSearchFoundPeople() {
        testPeopleFound.addPerson(testPersonFound);
        testPeopleFound.addPerson(testPersonFound2);
        assertTrue(testPeopleFound.searchFoundPeople(testDog3).contains(testPersonFound2));
        assertFalse(testPeopleFound.searchFoundPeople(testDog3).contains(testPersonFound));
        assertFalse(testPeopleFound.searchFoundPeople(testDog).contains(testPersonFound2));
        assertTrue(testPeopleFound.searchFoundPeople(testDog).contains(testPersonFound));
    }

}