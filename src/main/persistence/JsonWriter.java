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
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of Dog, ListPersonLost, ListPersonFound to file
    public void write(Dog currentDog, ListPersonLost listPersonLost, ListPersonFound listPersonFound) {
        JSONObject json = new JSONObject();
        if (currentDog != null) {
            json.put("currentDog", currentDog.toJson());
        } else {
            json.put("currentDog", JSONObject.NULL);
        }
        json.put("listPersonLost", listPersonLost.toJson());
        json.put("listPersonFound", listPersonFound.toJson());
        saveToFile(json.toString(TAB));
    }
    
    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}

