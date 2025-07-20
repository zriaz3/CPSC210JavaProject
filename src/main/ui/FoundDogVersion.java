package ui;

import java.util.ArrayList;
import java.util.Scanner;

import model.Dog;
import model.ListPersonFound;
import model.PersonLost;
import model.ListPersonLost;
import model.PersonFound;

// Found dog version of the application
public class FoundDogVersion {

    private Scanner input;
    private ListPersonFound foundDogs;
    private ListPersonLost lostDogs;
    private PersonFound personFound;

    // EFFECTS: runs the found dog version of the app
    public FoundDogVersion(ListPersonFound foundDogs, ListPersonLost lostDogs, Scanner input) {
        this.foundDogs = foundDogs;
        this.lostDogs = lostDogs;
        this.input = input;
        personFound = null;
        String options = "";

        while (true) {
            System.out.println("Choose one of the following options by typing the corresponding letter:");
            System.out.println(
                    "A: File report\nB: Browse lost dogs\nC: Compare found dog to all lost dogs\n"
                            + "D: Remove report\nE: Save data\nF: Load data\nG: Quit");

            options = input.nextLine();

            if (options.equalsIgnoreCase("A")) {
                personFound = fileFoundDogReport();
            } else if (options.equalsIgnoreCase("B")) {
                browseLostDogs(lostDogs);
            } else if (options.equalsIgnoreCase("C")) {
                checkLostDogs(personFound);
            } else if (options.equalsIgnoreCase("D")) {
                removeFoundDogReport();
            } else if (options.equalsIgnoreCase("E")) {
                saveData();
            } else if (options.equalsIgnoreCase("F")) {
                loadData();
            } else if (options.equalsIgnoreCase("G")) {
                break;
            }
        }
    }

    // MODIFIES: foundDogs
    // EFFECTS: allows user to file a report and add to the list of found dogs
    private PersonFound fileFoundDogReport() {
        PersonFound personFound = (personInfo(dogInfo()));
        foundDogs.addPerson(personFound);
        return personFound;
    }

    // EFFECTS: runs the found dog through all the lost dogs in the list for a
    // possible match
    private void checkLostDogs(PersonFound personFound) {
        if (personFound == null) {
            System.out.println("No report filed, file a report and try again!");
        } else {
            ArrayList<PersonLost> matches = lostDogs.searchLostPeople(personFound);
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

    // EFFECTS gets user integer input
    private int userIntegerInput() {
        int age = 0;

        while (true) {
            String dogAge = input.nextLine();
            try {
                age = Integer.parseInt(dogAge);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Enter valid number.");
            }
        }

        return age;
    }

    // EFFECTS: gets information about the found dog
    private Dog dogInfo() {
        System.out.println("Fill out all known information about your found dog:");

        System.out.println("Your dog's name: ");
        String dogName = input.nextLine();

        System.out.println("Your dog's age(enter closest whole number i.e. 1, 2, 3): ");
        int age = userIntegerInput();

        System.out.println("Your dog's breed: ");
        String breed = input.nextLine();

        System.out.println("Your dog's most prominent fur color: ");
        String furColor = input.nextLine();

        System.out.println("Your dog's most prominent build feature(muscular, lean, tall, etc): ");
        String build = input.nextLine();

        System.out.println("Your dog's size(small/ medium/ large): ");
        String size = input.nextLine();

        System.out.println("Your dog's picture: ");
        String picture = input.nextLine();

        return new Dog(dogName, age, breed, furColor, size, build, picture);

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

        for (PersonFound person : new ArrayList<>(foundDogs.getListPersonFound())) {
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
            System.out.println(person.toString());
            while (true) {
                System.out.println("Is this your found dog? (Y/N)");
                String confirmDog = input.nextLine();
                if (confirmDog.equalsIgnoreCase("Y")) {
                    System.out.println(person.contactInfo());
                    return;
                } else if (confirmDog.equalsIgnoreCase("N")) {
                    while (true) {
                        System.out.println("Keep looking or quit? Enter quit or look");
                        String keepLooking = input.nextLine();
                        if (keepLooking.equalsIgnoreCase("quit")) {
                            return;
                        } else if (keepLooking.equalsIgnoreCase("look")) {
                            break;
                        }
                    } 
                    break;
                }
            }
        }

        System.out.println("No more lost dogs.");
    }

    private void saveData() {

    }

    private void loadData() {
        
    }
}