package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import model.CurrentDog;
import model.Dog;
import model.EventLog;
import model.Event;
import model.ListPersonFound;
import model.ListPersonLost;
import persistence.JsonReader;
import persistence.JsonWriter;

// GUI version of find dog app
public class FindDogAppUI extends JFrame {

    public static final int WIDTH = 750;
    public static final int HEIGHT = 400;

    private static final int DOG_INDEX = 0;
    private static final int LOST_INDEX = 1;
    private static final int FOUND_INDEX = 2;

    private static final String JSON_STORE = "./data/findDogApp.json";

    private ListPersonFound foundDogs;
    private ListPersonLost lostDogs;
    private CurrentDog currentDog;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // modelled after DrawingPlayer from class
    // Github link:
    // https://github.students.cs.ubc.ca/CPSC210/SimpleDrawingPlayer-Complete
    // MODIFIES: this
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

    // MODIFIES: this
    // EFFECTS: sets up the layout of the App and adds buttons
    private void initializeGraphics() {
        setLayout(new BorderLayout());
        setSize(WIDTH, HEIGHT);
        addButtons();
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                for (Event event : EventLog.getInstance()) {
                    System.out.println(event);
                }
                System.exit(0);
            }
        });
    }

    // modelled after AlarmSystem
    // Github link: https://github.students.cs.ubc.ca/CPSC210/AlarmSystem
    // MODIFIES: this
    // EFFECTS: adds buttons for loading/saving, running lost/found version and
    // functionality
    private void addButtons() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 2));

        buttonPanel.add(new JButton(new LostDogAction()));
        buttonPanel.add(new JButton(new FoundDogAction()));
        buttonPanel.add(new JButton(new SaveAction()));
        buttonPanel.add(new JButton(new LoadAction()));

        add(buttonPanel, BorderLayout.CENTER);
    }

    // Runs the lost dog version
    private class LostDogAction extends AbstractAction {
        // MODIFIES: this
        // EFFECTS: constructs action
        LostDogAction() {
            super("Lost Dog Mode");
        }

        // MODIFIES: this
        // EFFECTS: hides current UI and runs LostDogUI
        @Override
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
            new LostDogUI(currentDog, foundDogs, lostDogs, FindDogAppUI.this);
        }
    }

    // Runs the found dog version
    private class FoundDogAction extends AbstractAction {
        // MODIFIES: this
        // EFFECTS: constructs action
        FoundDogAction() {
            super("Found Dog Mode");
        }

        // MODIFIES: this
        // EFFECTS: hides current UI and runs FoundDogUI
        @Override
        public void actionPerformed(ActionEvent e) {
            setVisible(false);
            new FoundDogUI(currentDog, foundDogs, lostDogs, FindDogAppUI.this);
        }
    }

    // Saves app data
    private class SaveAction extends AbstractAction {
        // MODIFIES: this
        // EFFECTS: constructs action
        SaveAction() {
            super("Save Data");
        }

        // EFFECTS: saves all current data
        @Override
        public void actionPerformed(ActionEvent e) {
            saveData();
        }
    }

    // Loads app data
    private class LoadAction extends AbstractAction {
        // MODIFIES: this
        // EFFECTS: constructs action
        LoadAction() {
            super("Load Data");
        }

        // MODIFIES: this
        // EFFECTS: loads data from file
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