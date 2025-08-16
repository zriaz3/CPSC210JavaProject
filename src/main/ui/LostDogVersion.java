package ui;

import java.util.ArrayList;
import java.util.Scanner;

import model.CurrentDog;
import model.Dog;
import model.ListPersonFound;
import model.PersonLost;
import model.ListPersonLost;
import model.PersonFound;

// Lost dog version of the application
public class LostDogVersion extends ConsoleHelper {

    // EFFECTS: runs the lost dog version
    public LostDogVersion(CurrentDog currentDog, ListPersonFound foundDogs, ListPersonLost lostDogs, Scanner input) {
        super(currentDog, foundDogs, lostDogs, input);

        while (true) {
            System.out.println("Choose one of the following options by typing the corresponding letter:");
            System.out.println(
                    "A: File report\nB: Browse found dogs\nC: Compare lost dog to all found dogs\n"
                            + "D: Remove report\nE: Quit");

            String options = input.nextLine();

            if (options.equalsIgnoreCase("A")) {
                fileLostDogReport();
            } else if (options.equalsIgnoreCase("B")) {
                browseFoundDogs(foundDogs);
            } else if (options.equalsIgnoreCase("C")) {
                checkFoundDogs();
            } else if (options.equalsIgnoreCase("D")) {
                removeLostDogReport();
            } else if (options.equalsIgnoreCase("E")) {
                break;
            }
        }
    }

    // MODIFIES: lostDogs, currentDog
    // EFFECTS: allows user to file a report and add to the list of lost dogs
    private void fileLostDogReport() {
        Dog dog = dogInfo();
        currentDog.setDog(dog);
        PersonLost personLost = personInfo(dog);
        lostDogs.addPerson(personLost);
    }

    // EFFECTS: runs the current dog through all the found dogs in the list for a
    // possible match
    private void checkFoundDogs() {
        if (currentDog.getDog() == null) {
            System.out.println("No report filed, file a report and try again!");
        } else {
            ArrayList<PersonFound> matches = foundDogs.searchFoundPeople(currentDog.getDog());
            displayDogs(matches);
        }
    }

    // EFFECTS: allows user to browse through all the found dogs one by one
    private void browseFoundDogs(ListPersonFound foundDogs) {
        ArrayList<PersonFound> personFoundDogs = foundDogs.getListPersonFound();

        if (personFoundDogs.isEmpty()) {
            System.out.println("No found dogs currently.");
        } else {
            displayDogs(personFoundDogs);
        }
    }

    // EFFECTS: gets information about person who lost the dog
    private PersonLost personInfo(Dog dog) {
        System.out.println("Fill out the following information about you and when/where the dog was lost: ");

        System.out.println("Your name: ");
        String personName = input.nextLine();

        System.out.println("Phone Number: ");
        String phoneNumber = input.nextLine();

        System.out.println("Location you lost your dog: ");
        String location = input.nextLine();

        System.out.println("Time you lost your dog: ");
        String timeLost = input.nextLine();

        return new PersonLost(personName, phoneNumber, location, timeLost, dog);
    }

    // MODIFIES: lostDogs
    // EFFECTS: checks for report and removes it if verification is successful
    private void removeLostDogReport() {
        System.out.println("Verify it is your report by answering these questions and remove it:");

        System.out.println("Your name: ");
        String name = input.nextLine();

        System.out.println("Your phone Number: ");
        String phoneNumber = input.nextLine();

        boolean isRemoved = false;

        for (PersonLost person : lostDogs.getListPersonLost()) {
            if (person.getName().equalsIgnoreCase(name) && person.getPhoneNumber().equals(phoneNumber)) {
                lostDogs.removePerson(person);
                System.out.println("Lost dog report removed.");
                isRemoved = true;
                break;
            }
        }

        if (!isRemoved) {
            System.out.println("No report found.");
        }
    }

    // EFFECTS: Displays all found dogs one by one
    private void displayDogs(ArrayList<PersonFound> listPersonFound) {
        for (PersonFound person : listPersonFound) {
            if (displayDogsHelper(person)) {
                return;
            }
        }
        System.out.println("No more found dogs.");
    }
}
