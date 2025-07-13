package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PersonLostTest {

    private PersonLost testPersonLost;
    private Dog testDog;
    
    @BeforeEach
    public void runBefore() {
        testDog = new Dog("Cutie", 7, "Border Collie", "White", "Small", "Lean", "images/Cutie.PNG");
        testPersonLost = new PersonLost("Zain", 123456789, "Vancouver", "7pm July 1st", testDog);
    }

    @Test
    public void testConstructor() {
        assertEquals("Zain", testPersonLost.getName());
        assertEquals(123456789, testPersonLost.getPhoneNumber());
        assertEquals("Vancouver", testPersonLost.getLocation());
        assertEquals("7pm July 1st", testPersonLost.getTimeLost());
        assertEquals(testDog, testPersonLost.getDog());
    }

    @Test
    public void testToString() {
        assertEquals(testPersonLost.toString(), 
                    "Poster: Zain/nLocation: Vancouver/nTime Lost: 7pm July 1st" 
                        + "/nDog Information: " + testDog.toString());
    }
}
