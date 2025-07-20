package persistence;

import org.json.JSONObject;

// Represents method to return objects as a JSON object
// Modeled after sample application provided
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
