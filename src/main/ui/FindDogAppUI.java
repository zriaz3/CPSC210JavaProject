package ui;

import java.util.Scanner;

import javax.swing.*;

import model.CurrentDog;
import model.ListPersonFound;
import model.ListPersonLost;
import persistence.JsonReader;
import persistence.JsonWriter;

// GUI version of find dog app
public class FindDogAppUI extends JFrame {

    public static final int WIDTH = 750;
    public static final int HEIGHT = 750;

    private static final int DOG_INDEX = 0;
    private static final int LOST_INDEX = 1;
    private static final int FOUND_INDEX = 2;

    private static final String JSON_STORE = "./data/findDogApp.json";

    private ListPersonFound foundDogs;
    private ListPersonLost lostDogs;
    private CurrentDog currentDog;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // modelled after drawing editor
    // EFFECTS: initializes and runs the Find Dog app
    public FindDogAppUI() {
        super("Find Dog App");
        initializeFields();
        initializeGraphics();
        addButtons();
    }

    // EFFECTS: initialze all required fields to their empty states
    private void initializeFields() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'initializeFields'");
    }

    // EFFECTS: sets up the layout of the App
    private void initializeGraphics() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'initializeGraphics'");
    }

    // EFFECTS: adds buttons for loading/saving, running lost/found version and functionality
    private void addButtons() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addButtons'");
    }

    // EFFECTS: saves all current DogApp data to file
    private void saveData() {

    }

    // MODIFIES: this
    // EFFECTS: loads DogApp data from file
    private void loadData() {

    }

    public static void main(String[] args) {
        new FindDogAppUI();
    }
}