package persistence;

import model.Dog;
import model.ListPersonFound;
import model.ListPersonLost;
import model.PersonFound;
import model.PersonLost;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.io.*;

// Modeled after sample application provided
class JsonWriterTest extends JsonTest {
    private static final int DOG_INDEX = 0;
    private static final int LOST_INDEX = 1;
    private static final int FOUND_INDEX = 2;

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyData() {
        try {
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyData.json");
            writer.open();
            Dog currentDog = null;
            ListPersonLost listPersonLost = new ListPersonLost();
            ListPersonFound listPersonFound = new ListPersonFound();
            writer.write(currentDog, listPersonLost, listPersonFound);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyData.json");
            Object[] data = reader.read();
            assertNull(data[DOG_INDEX]);
            assertEquals(0, ((ListPersonLost) data[LOST_INDEX]).getListPersonLost().size());
            assertEquals(0, ((ListPersonFound) data[FOUND_INDEX]).getListPersonFound().size());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @SuppressWarnings("methodlength")
    @Test
    void testWriterGeneral() {
        try {
            Dog currentDog = new Dog("Cutie", 7, "Border Collie", "black", "small", "lean", ":)");

            PersonLost pl = new PersonLost("Me", "123456", "Vancouver", "7pm July 5th", currentDog);
            ListPersonLost listPersonLost = new ListPersonLost();
            listPersonLost.addPerson(pl);

            PersonFound pf = new PersonFound("NotMe", "1234567", "NotVancouver", "8pm July 5th", currentDog);
            PersonFound pf2 = new PersonFound("NotMe2", "12345678", "NotVancouver2", "9pm July 5th", currentDog);
            ListPersonFound listPersonFound = new ListPersonFound();
            listPersonFound.addPerson(pf);
            listPersonFound.addPerson(pf2);

            JsonWriter writer = new JsonWriter("./data/testWriterGeneral.json");
            writer.open();
            writer.write(currentDog, listPersonLost, listPersonFound);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneral.json");
            Object[] data = reader.read();
            Dog readDog = (Dog) data[DOG_INDEX];
            ListPersonLost readListPersonLost = (ListPersonLost) data[LOST_INDEX];
            ListPersonFound readListPersonFound = (ListPersonFound) data[FOUND_INDEX];

            checkDog("Cutie", 7, "Border Collie", "black", "small", "lean", ":)", readDog);

            assertEquals(1, readListPersonLost.getListPersonLost().size());
            PersonLost rpl = readListPersonLost.getListPersonLost().get(0);
            checkPersonLost("Me", "123456", "Vancouver", "7pm July 5th", readDog, rpl);

            assertEquals(2, readListPersonFound.getListPersonFound().size());
            PersonFound rpf = readListPersonFound.getListPersonFound().get(0);
            PersonFound rpf2 = readListPersonFound.getListPersonFound().get(1);
            checkPersonFound("NotMe", "1234567", "NotVancouver", "8pm July 5th", readDog, rpf);
            checkPersonFound("NotMe2", "12345678", "NotVancouver2", "9pm July 5th", readDog, rpf2);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
