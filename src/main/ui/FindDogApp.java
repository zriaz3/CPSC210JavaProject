package ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import model.CurrentDog;
import model.Dog;
import model.ListPersonFound;
import model.ListPersonLost;
import persistence.JsonReader;
import persistence.JsonWriter;

// Dog application 
public class FindDogApp {
    private static final int DOG_INDEX = 0;
    private static final int LOST_INDEX = 1;
    private static final int FOUND_INDEX = 2;
    private static final String JSON_STORE = "./data/findDogApp.json";
    private Scanner input;
    private ListPersonFound foundDogs;
    private ListPersonLost lostDogs;
    private CurrentDog currentDog;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs the dog application
    public FindDogApp() {
        input = new Scanner(System.in);
        foundDogs = new ListPersonFound();
        lostDogs = new ListPersonLost();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        currentDog = new CurrentDog(null);
        runDogApp();
    }

    // EFFECTS: run lost/found version based on if person has lost or found a dog or save/load data
    public void runDogApp() {
        while (true) {
            System.out.println("Please select the status of the dog, 'lost', 'found' or 'quit' or 'load', 'save' data");
            String version = input.nextLine();
            System.out.println("you selected: " + version);

            if (version.equalsIgnoreCase("quit")) {
                break;
            } else if (version.equalsIgnoreCase("lost")) {
                new LostDogVersion(currentDog, foundDogs, lostDogs, input);
            } else if (version.equalsIgnoreCase("found")) {
                new FoundDogVersion(currentDog, foundDogs, lostDogs, input);
            } else if (version.equalsIgnoreCase("load")) {
                loadData();
            } else if (version.equalsIgnoreCase("save")) {
                saveData();
            } else {
                System.out.println("Invalid input.");
            }
        }

        System.out.println("Goodbye");
    }

    // EFFECTS: saves all current DogApp data to file
    private void saveData() {
        try {
            jsonWriter.open();
            jsonWriter.write(currentDog.getDog(), lostDogs, foundDogs);
            jsonWriter.close();
            System.out.println("Data saved to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads DogApp data from file
    private void loadData() {
        try {
            Object[] data = jsonReader.read();
            currentDog.setDog((Dog) data[DOG_INDEX]);
            lostDogs = (ListPersonLost) data[LOST_INDEX];
            foundDogs = (ListPersonFound) data[FOUND_INDEX];
            System.out.println("Data loaded from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    public static void main(String[] args) {
        new FindDogApp();
    }
}
