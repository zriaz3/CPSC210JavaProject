package ui;

import javax.swing.*;
import java.awt.*;

import model.CurrentDog;
import model.Dog;
import model.ListPersonFound;
import model.PersonLost;
import model.ListPersonLost;
import model.PersonFound;

// Found dog GUI version of the application
public class FoundDogUI extends JFrame {
    private ListPersonFound foundDogs;
    private ListPersonLost lostDogs;
    private CurrentDog currentDog;
    private FindDogAppUI findDogAppUI;

    // modelled after drawing editor
    // Github link: https://github.students.cs.ubc.ca/CPSC210/SimpleDrawingPlayer-Complete
    // EFFECTS: runs the found dog GUI version of the app
    public FoundDogUI(CurrentDog currentDog, ListPersonFound foundDogs,
            ListPersonLost lostDogs, FindDogAppUI findDogAppUI) {
        super("Found Dog");
        initializeFields();
        initializeGraphics();
    }

    // EFFECTS: initialize all fields
    private void initializeFields() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'initializeFields'");
    }

    // EFFECTS: sets up the layout of the App and add buttons
    private void initializeGraphics() {
        addButtons();
        throw new UnsupportedOperationException("Unimplemented method 'initializeGraphics'");
    }


    private void addButtons() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addButtons'");
    }
}
