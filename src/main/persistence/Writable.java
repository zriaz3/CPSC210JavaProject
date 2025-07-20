package persistence;

import org.json.JSONObject;

// Represents method to return objects as a JSON object
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
