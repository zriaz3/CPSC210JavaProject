package ui;

import java.util.ArrayList;
import java.util.Scanner;

import model.CurrentDog;
import model.Dog;
import model.ListPersonFound;
import model.PersonLost;
import model.ListPersonLost;
import model.PersonFound;

// Found dog version of the application
public class FoundDogVersion extends ConsoleHelper {

    // EFFECTS: runs the found dog version of the app
    public FoundDogVersion(CurrentDog currentDog, ListPersonFound foundDogs, ListPersonLost lostDogs, Scanner input) {
        super(currentDog, foundDogs, lostDogs, input);

        while (true) {
            System.out.println("Choose one of the following options by typing the corresponding letter:");
            System.out.println(
                    "A: File report\nB: Browse lost dogs\nC: Compare found dog to all lost dogs\n"
                            + "D: Remove report\nE: Quit");

            String options = input.nextLine();

            if (options.equalsIgnoreCase("A")) {
                fileFoundDogReport();
            } else if (options.equalsIgnoreCase("B")) {
                browseLostDogs(lostDogs);
            } else if (options.equalsIgnoreCase("C")) {
                checkLostDogs();
            } else if (options.equalsIgnoreCase("D")) {
                removeFoundDogReport();
            } else if (options.equalsIgnoreCase("E")) {
                break;
            }
        }
    }

    // MODIFIES: foundDogs, currentDog
    // EFFECTS: allows user to file a report and add to the list of found dogs
    private void fileFoundDogReport() {
        Dog dog = dogInfo();
        currentDog.setDog(dog);
        PersonFound personFound = personInfo(dog);
        foundDogs.addPerson(personFound);
    }

    // EFFECTS: runs the curent dog through all the lost dogs in the list for a
    // possible match
    private void checkLostDogs() {
        if (currentDog.getDog() == null) {
            System.out.println("No report filed, file a report and try again!");
        } else {
            ArrayList<PersonLost> matches = lostDogs.searchLostPeople(currentDog.getDog());
            displayDogs(matches);
        }
    }

    // EFFECTS: allows user to browse through all the lost dogs one by one
    private void browseLostDogs(ListPersonLost lostDogs) {
        ArrayList<PersonLost> personLostDogs = lostDogs.getListPersonLost();

        if (personLostDogs.isEmpty()) {
            System.out.println("No lost dogs yet.");
        } else {
            displayDogs(personLostDogs);
        }
    }

    // EFFECTS: gets information about person who found the dog
    private PersonFound personInfo(Dog dog) {
        System.out.println("Fill out the following information about you and when/where the dog was found: ");

        System.out.println("Your name: ");
        String personName = input.nextLine();

        System.out.println("Phone Number: ");
        String phoneNumber = input.nextLine();

        System.out.println("Location you found the dog: ");
        String location = input.nextLine();

        System.out.println("Time you found the dog: ");
        String timeLost = input.nextLine();

        return new PersonFound(personName, phoneNumber, location, timeLost, dog);
    }

    // MODIFIES: foundDogs
    // EFFECTS: checks for report and removes it if verification is successful
    private void removeFoundDogReport() {
        System.out.println("Verify it is your report by answering these questions and remove it:");

        System.out.println("Your name: ");
        String name = input.nextLine();

        System.out.println("Your phone Number: ");
        String phoneNumber = input.nextLine();

        boolean isRemoved = false;

        for (PersonFound person : foundDogs.getListPersonFound()) {
            if (person.getName().equalsIgnoreCase(name) && person.getPhoneNumber().equals(phoneNumber)) {
                foundDogs.removePerson(person);
                System.out.println("Found dog report removed.");
                isRemoved = true;
                break;
            }
        }

        if (!isRemoved) {
            System.out.println("No report found.");
        }
    }

    // EFFECTS: Displays all found dogs one by one
    private void displayDogs(ArrayList<PersonLost> listPersonLost) {
        for (PersonLost person : listPersonLost) {
            if (displayDogsHelper(person)) {
                return;
            }
        }
        System.out.println("No more lost dogs.");
    }
}