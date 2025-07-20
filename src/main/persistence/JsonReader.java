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
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseFindDogApp(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    // throws IOException if an error occurs reading data from file
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses currentDog, listPersonLost and listPersonFound from JSON
    // object and returns it
    private Object[] parseFindDogApp(JSONObject jsonObject) {
        Dog currentDog = null;
        if (!jsonObject.isNull("currentDog")) {
            currentDog = parseDog(jsonObject.getJSONObject("currentDog"));
        }

        ListPersonLost listPersonLost = parseListPersonLost(jsonObject);
        ListPersonFound listPersonFound = parseListPersonFound(jsonObject);

        return new Object[] { currentDog, listPersonLost, listPersonFound };
    }

    // MODIFIES: listPersonLost
    // EFFECTS: parses PersonLost from JSON object and adds them to listPersonLost
    private ListPersonLost parseListPersonLost(JSONObject jsonObject) {
        ListPersonLost listPersonLost = new ListPersonLost();
        JSONArray jsonArray = jsonObject.getJSONArray("listPersonLost");

        for (Object json : jsonArray) {
            JSONObject lostPerson = (JSONObject) json;
            PersonLost person = parsePersonLost(lostPerson);
            listPersonLost.addPerson(person);
        }
        return listPersonLost;
    }

    // MODIFIES: listPersonFound
    // EFFECTS: parses PersonFound from JSON object and adds them to listPersonFound
    private ListPersonFound parseListPersonFound(JSONObject jsonObject) {
        ListPersonFound listPersonFound = new ListPersonFound();
        JSONArray jsonArray = jsonObject.getJSONArray("listPersonFound");

        for (Object json : jsonArray) {
            JSONObject foundPerson = (JSONObject) json;
            PersonFound person = parsePersonFound(foundPerson);
            listPersonFound.addPerson(person);
        }
        return listPersonFound;
    }

    // EFFECTS: parses Dog from JSON object and returns it
    private Dog parseDog(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int age = jsonObject.getInt("age");
        String breed = jsonObject.getString("breed");
        String furColor = jsonObject.getString("furColor");
        String size = jsonObject.getString("size");
        String build = jsonObject.getString("build");
        String picture = jsonObject.getString("picture");

        return new Dog(name, age, breed, furColor, size, build, picture);
    }

    // EFFECTS: parses PersonLost from JSON object and returns it
    private PersonLost parsePersonLost(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String phoneNumber = jsonObject.getString("phoneNumber");
        String location = jsonObject.getString("location");
        String timeLost = jsonObject.getString("timeLost");
        Dog dog = parseDog(jsonObject.getJSONObject("dog"));

        return new PersonLost(name, phoneNumber, location, timeLost, dog);
    }

    // EFFECTS: parses PersonFound from JSON object and returns it
    private PersonFound parsePersonFound(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String phoneNumber = jsonObject.getString("phoneNumber");
        String location = jsonObject.getString("location");
        String timeLost = jsonObject.getString("timeFound");
        Dog dog = parseDog(jsonObject.getJSONObject("dog"));

        return new PersonFound(name, phoneNumber, location, timeLost, dog);
    }
}
