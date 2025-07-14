package ui;

import java.util.ArrayList;
import java.util.Scanner;

import model.ListPersonFound;
import model.ListPersonLost;

public class FoundDogVersion {

    private Scanner input;
    private ListPersonFound foundDogs;
    private ListPersonLost lostDogs;

    // EFFECTS: runs the found dog version of the app
    public FoundDogVersion(ListPersonFound foundDogs, ListPersonLost lostDogs, Scanner input) {
        this.input = input;
        this.foundDogs = foundDogs;
        this.lostDogs = lostDogs;
        return;
    }
}
