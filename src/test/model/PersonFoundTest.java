package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PersonFoundTest {

    private PersonFound testPersonFound;
    private Dog testDog;
    
    @BeforeEach
    public void runBefore() {
        testDog = new Dog("Cutie", 7, "Border Collie", "White", "Small", "Lean", "images/Cutie.PNG");
        testPersonFound = new PersonFound("Zain", "1234567891", "Vancouver", "7pm July 1st", testDog);
    }

    @Test
    public void testConstructor() {
        assertEquals("Zain", testPersonFound.getName());
        assertEquals("1234567891", testPersonFound.getPhoneNumber());
        assertEquals("Vancouver", testPersonFound.getLocation());
        assertEquals("7pm July 1st", testPersonFound.getTimeFound());
        assertEquals(testDog, testPersonFound.getDog());
    }

    @Test
    public void testToString() {
        assertEquals(testPersonFound.toString(), 
                    "Poster: Zain\nLocation: Vancouver\nTime Found: 7pm July 1st" 
                        + "\nDog Information: " + testDog.toString());
    }

    @Test 
    public void testContactInfo() {
        assertEquals(testPersonFound.contactInfo(), "Poster name: Zain\nPhone number: 1234567891");
    }
}