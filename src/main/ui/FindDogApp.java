package ui;

import java.util.Scanner;

import model.ListPersonFound;
import model.ListPersonLost;

// Dog application 
public class FindDogApp {

    private Scanner input;
    private ListPersonFound foundDogs;
    private ListPersonLost lostDogs;

    // EFFECTS: runs the dog application
    public FindDogApp() {

        input = new Scanner(System.in);
        foundDogs = new ListPersonFound();
        lostDogs = new ListPersonLost();
        runDogApp();
    }

    // EFFECTS: choose which version to run based on if person has lost or found a dog
    public void runDogApp() {
        String version = "";

        while (true) {
            System.out.println("Please select the status of the dog, 'lost' or 'found' or 'quit'");
            version = input.next();
            System.out.println("you selected: " + version);

            if (version.equalsIgnoreCase("quit")) {
                break;
            } else if (version.equalsIgnoreCase("lost")) {
                new LostDogVersion(foundDogs, lostDogs);
            } else if (version.equalsIgnoreCase("found")) {
                new FoundDogVersion(foundDogs, lostDogs);
            } else {
                System.out.println("Invalid input.");
            }

        }
        
    }
    public static void main(String[] args) {
        new FindDogApp();
    }
}
