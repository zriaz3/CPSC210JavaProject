package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DogTest {

    private Dog testDog;

    @BeforeEach
    void runBefore() {
        testDog = new Dog("Cutie", 7, "Border Collie", "White", "Small", "Lean", "images/Cutie.PNG");
    }

    @Test
    public void testConstructor() {
        assertEquals("Cutie", testDog.getName());
        assertEquals(7, testDog.getAge());
        assertEquals("Border Collie", testDog.getBreed());
        assertEquals("White", testDog.getFurColor());
        assertEquals("Small", testDog.getSize());
        assertEquals("Lean", testDog.getBuild());
        assertEquals("images/Cutie.PNG", testDog.getPicture());
    }

    @Test
    public void testToString() {
        assertEquals(testDog.toString(),
                    "/nName: Cutie/nAge: 7/nBreed: Border Collie/nFur Color: White"
                        + "/nSize: Small/nBuild: Lean/nPicture: images/Cutie.PNG");
    }
}
