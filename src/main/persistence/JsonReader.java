package persistence;

import model.Dog;
import model.ListPersonFound;
import model.ListPersonLost;
import model.PersonLost;
import model.PersonFound;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads all app data from JSON data stored in file;
// Modeled after sample application provided
public class JsonReader {
    private String source;

    // EFFECTS: constructs a reader to read from source file
    public JsonReader(String source) {
     this.source = source;
    }

    // EFFECTS reads app data from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Object[] read() throws IOException {
        return null;
    }

    private String readFile(String source) throws IOException {
        return null;
    }

    private Object[] parseFindDogApp(JSONObject jsonObject) {
        return null;
    }

    private Dog parseDog(JSONObject jsonObject) {
        return new Dog(source, 0, source, source, source, source, source);
    }

    private ListPersonLost parseListPersonLost(JSONArray jsonArray) {
        return new ListPersonLost();
    }

    private ListPersonFound parseListPersonFound(JSONArray jsonArray) {
        return new ListPersonFound();

}
}
