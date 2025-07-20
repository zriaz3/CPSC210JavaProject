package persistence;

import model.Dog;
import model.PersonLost;
import model.PersonFound;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Represents helper functions used in json tests
// Modeled after sample application provided
public class JsonTest {

    protected void checkDog(String name, int age, String breed, String furColor, 
                            String size, String build, String picture, Dog d) {
        assertEquals(name, d.getName());
        assertEquals(age, d.getAge());
        assertEquals(breed, d.getBreed());
        assertEquals(furColor, d.getFurColor());
        assertEquals(size, d.getSize());
        assertEquals(build, d.getBuild());
        assertEquals(picture, d.getPicture());
    }

    protected void checkPersonLost(String name, String phoneNumber, String location, 
                                   String timeLost, Dog dog, PersonLost p) {
        assertEquals(name, p.getName());
        assertEquals(phoneNumber, p.getPhoneNumber());
        assertEquals(location, p.getLocation());
        assertEquals(timeLost, p.getTimeLost());
        checkDog(dog.getName(), dog.getAge(), dog.getBreed(), dog.getFurColor(), 
                 dog.getSize(), dog.getBuild(), dog.getPicture(), p.getDog());
    }

    protected void checkPersonFound(String name, String phoneNumber, String location, 
                                    String timeFound, Dog dog, PersonFound p) {
        assertEquals(name, p.getName());
        assertEquals(phoneNumber, p.getPhoneNumber());
        assertEquals(location, p.getLocation());
        assertEquals(timeFound, p.getTimeFound());
        checkDog(dog.getName(), dog.getAge(), dog.getBreed(), dog.getFurColor(), 
                 dog.getSize(), dog.getBuild(), dog.getPicture(), p.getDog());
    }
}
