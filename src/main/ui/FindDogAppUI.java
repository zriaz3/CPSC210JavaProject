package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

import model.CurrentDog;
import model.Dog;
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
    // Github link: https://github.students.cs.ubc.ca/CPSC210/SimpleDrawingPlayer-Complete
    // EFFECTS: initializes and runs the Find Dog app
    public FindDogAppUI() {
        super("Find Dog App");
        
        foundDogs = new ListPersonFound();
        lostDogs = new ListPersonLost();
        currentDog = new CurrentDog(null);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        initializeGraphics();
    }

    // EFFECTS: sets up the layout of the App and adds buttons
    private void initializeGraphics() {
        setLayout(new BorderLayout());
        setSize(WIDTH, HEIGHT);
        addButtons();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // modelled after AlarmControllerUI
    // Github link: https://github.students.cs.ubc.ca/CPSC210/AlarmSystem
    // EFFECTS: adds buttons for loading/saving, running lost/found version and functionality
    private void addButtons() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 2));

        buttonPanel.add(new JButton());
        buttonPanel.add(new JButton(new FoundDogAction()));
        buttonPanel.add(new JButton(new SaveAction()));
        buttonPanel.add(new JButton(new LoadAction()));

        add(buttonPanel, BorderLayout.CENTER);

    }

    // private class LostDogAction extends AbstractAction {
    //     LostDogAction() {
    //         super("Lost Dog Mode");
    //     }

    //     @Override
    //     public void actionPerformed(ActionEvent e) {
    //         setVisible(false);
    //         new LostDogUI(currentDog, foundDogs, lostDogs, FindDogAppUI.this);
    //     }
    // }

    private class FoundDogAction extends AbstractAction {
        FoundDogAction() {
            super("Found Dog Mode");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
            new FoundDogUI(currentDog, foundDogs, lostDogs, FindDogAppUI.this);
        }
    }

    private class SaveAction extends AbstractAction {
        SaveAction() {
            super("Save Data");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            saveData();
        }
    }

    private class LoadAction extends AbstractAction {
        LoadAction() {
            super("Load Data");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            loadData();
        }
    }

    // EFFECTS: saves all current DogApp data to file
    private void saveData() {
        try {
            jsonWriter.open();
            jsonWriter.write(currentDog.getDog(), lostDogs, foundDogs);
            jsonWriter.close();
            JOptionPane.showMessageDialog(this, "Data saved to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "Unable to write to file: " + JSON_STORE);
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
            JOptionPane.showMessageDialog(this, "Data loaded from " + JSON_STORE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Unable to read from file: " + JSON_STORE);
        }
    }

    public static void main(String[] args) {
        new FindDogAppUI();
    }
}