package persistence;

import model.Dog;
import model.ListPersonFound;
import model.ListPersonLost;
import org.json.JSONObject;

import java.io.*;

// Represents a writer that writes JSON representation of Dog, ListPersonFound, ListPersonLost to file
// Modeled after sample application provided
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {

    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {

    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of Dog to file
    public void write(Dog dog) {

    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of ListPersonLost to file
    public void write(ListPersonLost listPersonLost) {
        
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of ListPersonFound to file
    public void write(ListPersonFound listPersonFound) {
        
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {

    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {

    }
}

