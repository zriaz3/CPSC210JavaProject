package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ListPersonLostTest {

    private PersonFound testPersonFound;
    private PersonFound testPersonFound2;
    private Dog testDog;
    private Dog testDog2;
    private Dog testDog3;
    private ListPersonLost testPeopleLost;
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
        testPeopleLost = new ListPersonLost();
    }

    @Test
    public void testAddPerson() {
        testPeopleLost.addPerson(testPersonLost);
        assertTrue(testPeopleLost.getListPersonLost().contains(testPersonLost));
    }

    @Test
    public void testAddTwoPeople() {
        testPeopleLost.addPerson(testPersonLost);
        assertTrue(testPeopleLost.getListPersonLost().contains(testPersonLost));
        testPeopleLost.addPerson(testPersonLost2);
        assertTrue(testPeopleLost.getListPersonLost().contains(testPersonLost2));
    }

    @Test
    public void testRemovePerson() {
        testPeopleLost.addPerson(testPersonLost);
        assertTrue(testPeopleLost.getListPersonLost().contains(testPersonLost));
        testPeopleLost.removePerson(testPersonLost);
        assertTrue(testPeopleLost.getListPersonLost().isEmpty());
    }

    @Test
    public void testRemoveTwoPeople() {
        testPeopleLost.addPerson(testPersonLost);
        assertTrue(testPeopleLost.getListPersonLost().contains(testPersonLost));
        testPeopleLost.addPerson(testPersonLost2);
        assertTrue(testPeopleLost.getListPersonLost().contains(testPersonLost2));
        testPeopleLost.removePerson(testPersonLost);
        testPeopleLost.removePerson(testPersonLost2);
        assertTrue(testPeopleLost.getListPersonLost().isEmpty());
    }

    @Test
    public void testIsMatch() {
        assertFalse(testPeopleLost.isMatch(testDog, testPersonLost)); // testDog3 and testDog
        assertTrue(testPeopleLost.isMatch(testDog, testPersonLost2)); // testDog and testDog
        assertTrue(testPeopleLost.isMatch(testDog2, testPersonLost)); // testDog2 and testDog3
        assertFalse(testPeopleLost.isMatch(testDog2, testPersonLost2)); // testDog and testDog2
    }

    @Test
    public void testSearchLostPeople() {
        testPeopleLost.addPerson(testPersonLost);
        testPeopleLost.addPerson(testPersonLost2);
        assertTrue(testPeopleLost.searchLostPeople(testDog).contains(testPersonLost2));
        assertFalse(testPeopleLost.searchLostPeople(testDog).contains(testPersonLost));
        assertFalse(testPeopleLost.searchLostPeople(testDog2).contains(testPersonLost2));
        assertTrue(testPeopleLost.searchLostPeople(testDog2).contains(testPersonLost));
    }

}