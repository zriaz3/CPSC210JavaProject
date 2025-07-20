package ui;

import java.util.Scanner;

import model.Dog;
import model.ListPersonFound;
import model.ListPersonLost;
import persistence.JsonReader;
import persistence.JsonWriter;

// Dog application 
public class FindDogApp {
    private static final String JSON_STORE = "./data/findDogApp.json";
    private Scanner input;
    private ListPersonFound foundDogs;
    private ListPersonLost lostDogs;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs the dog application
    public FindDogApp() {
        input = new Scanner(System.in);
        foundDogs = new ListPersonFound();
        lostDogs = new ListPersonLost();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runDogApp();
    }

    // EFFECTS: run lost/found version based on if person has lost or found a dog 
    public void runDogApp() {
        while (true) {
            System.out.println("Please select the status of the dog, 'lost' or 'found' or 'quit'");
            String version = input.nextLine();
            System.out.println("you selected: " + version);

            if (version.equalsIgnoreCase("quit")) {
                break;
            } else if (version.equalsIgnoreCase("lost")) {
                new LostDogVersion(foundDogs, lostDogs, input, jsonWriter, jsonReader);
            } else if (version.equalsIgnoreCase("found")) {
                new FoundDogVersion(foundDogs, lostDogs, input, jsonWriter, jsonReader);
            } else {
                System.out.println("Invalid input.");
            }
        }

        System.out.println("Goodbye");
    }

    public static void main(String[] args) {
        new FindDogApp();
    }
}
