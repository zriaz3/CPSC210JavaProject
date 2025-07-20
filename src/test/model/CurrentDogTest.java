package model;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class CurrentDogTest {
    
    private Dog testDog;
    private Dog testDog2;
    private CurrentDog currentDog;

    @BeforeEach
    void runBefore() {
        testDog = new Dog("Cutie", 7, "Border Collie", "White", "Small", "Lean", "images/Cutie.PNG");
        testDog2 = new Dog("Gru", 5, "Street", "Black", "Big", "Muscular", "images/Gru.PNG");
        currentDog = new CurrentDog(testDog);
    }

    @Test
    public void testConstructor() {
        assertEquals(currentDog.getDog(), testDog);
    }

    @Test
    public void testSetDog() {
        currentDog.setDog(testDog2);
        assertEquals(currentDog.getDog(), testDog2);
    }
}
