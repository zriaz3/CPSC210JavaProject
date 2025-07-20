package persistence;

import model.Dog;
import model.PersonFound;
import model.PersonLost;
import model.ListPersonLost;
import model.ListPersonFound;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {
    private static final int DOG_INDEX = 0;
    private static final int LOST_INDEX = 1;
    private static final int FOUND_INDEX = 2;

    @Test 
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Object[] data = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyListsNoDog() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyData.json");
        try {
            Object[] data = reader.read();
            Dog currentDog = (Dog) data[DOG_INDEX];
            ListPersonLost listPersonLost = (ListPersonLost) data[LOST_INDEX];
            ListPersonFound listPersonFound = (ListPersonFound) data[FOUND_INDEX];

            assertNull(currentDog);
            assertEquals(0, listPersonLost.getListPersonLost().size());
            assertEquals(0, listPersonFound.getListPersonFound().size());

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneral() {
        JsonReader reader = new JsonReader("./data/testReaderGeneral.json");
        try {
            Object[] data = reader.read();
            Dog currentDog = (Dog) data[DOG_INDEX];
            ListPersonLost listPersonLost = (ListPersonLost) data[LOST_INDEX];
            ListPersonFound listPersonFound = (ListPersonFound) data[FOUND_INDEX];

            checkDog("Cutie", 7, "Border Collie", "black", "small", "lean", ":)", currentDog);

            assertEquals(1, listPersonLost.getListPersonLost().size());
            PersonLost pl = listPersonLost.getListPersonLost().get(0);
            checkPersonLost("Me", "123456", "Vancouver", "7pm July 5th", currentDog, pl);

            assertEquals(2, listPersonFound.getListPersonFound().size());
            PersonFound pf = listPersonFound.getListPersonFound().get(0);
            checkPersonFound("NotMe", "1234567", "NotVancouver", "8pm July 5th", currentDog, pf);
            PersonFound pf2 = listPersonFound.getListPersonFound().get(1);
            checkPersonFound("NotMe2", "12345678", "NotVancouver2", "9pm July 5th", currentDog, pf2);

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}