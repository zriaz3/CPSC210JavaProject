package ui;

import java.util.ArrayList;
import java.util.Scanner;

import model.ListPersonFound;
import model.ListPersonLost;

// Lost dog version of the application
public class LostDogVersion {

    private Scanner input;
    private ListPersonFound foundDogs;
    private ListPersonLost lostDogs;

    // EFFECTS: runs the lost dog version/
    public LostDogVersion(ListPersonFound foundDogs, ListPersonLost lostDogs) {
        input = new Scanner(System.in);
        this.foundDogs = foundDogs;
        this.lostDogs = lostDogs;
        fileLostDogReport();
        checkFoundDogs();
        browseFoundDogs();
    }

    // MODIFIES: lostDogs
    // EFFECTS: allows user to file a report and add to the list of lost dogs
    private void fileLostDogReport() {

    }

    // EFFECTS: runs the lost dog through all the found dogs in the list for a possible match
    private void checkFoundDogs() {

    }

    // EFFECTS: allows user to browse through all the found dogs one by one
    private void browseFoundDogs() {

    }

}
