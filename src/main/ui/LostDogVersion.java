package ui;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Scanner;

import model.Dog;
import model.ListPersonFound;
import model.PersonLost;
import model.ListPersonLost;
import model.PersonFound;

// Lost dog version of the application
public class LostDogVersion {

    private Scanner input;
    private ListPersonFound foundDogs;
    private ListPersonLost lostDogs;
    private Dog lostDog;
    private PersonLost personLost;

    // EFFECTS: runs the lost dog version/
    @SuppressWarnings("methodlength")
    public LostDogVersion(ListPersonFound foundDogs, ListPersonLost lostDogs, Scanner input) {
        this.foundDogs = foundDogs;
        this.lostDogs = lostDogs;
        this.input = input;
        personLost = null;
        String options = "";

        while (true) {
            System.out.println("Choose one of the following options by typing the corresponging letter:");
            System.out.println(
                    "A: File report\nB: Browse found dogs\nC: Compare lost dog to all found dogs\n"
                            + "D: Remove report\nE: Quit");
            options = input.nextLine();
            if (options.equalsIgnoreCase("A")) {
                PersonLost personLost = fileLostDogReport();

            } else if (options.equalsIgnoreCase("B")) {
                browseFoundDogs(foundDogs);

            } else if (options.equalsIgnoreCase("C")) {
                checkFoundDogs(personLost);

            } else if (options.equalsIgnoreCase("D")) {
                removeLostDogReport();

            } else if (options.equalsIgnoreCase("E")) {
                break;

            } else {
                System.out.println("Invalid Input");

            }
        }
        return;
    }

    // MODIFIES: lostDogs
    // EFFECTS: allows user to file a report and add to the list of lost dogs
    private PersonLost fileLostDogReport() {

        PersonLost personLost = (personInfo(dogInfo()));
        lostDogs.addPerson(personLost);
        return personLost;
    }

    // EFFECTS: runs the lost dog through all the found dogs in the list for a
    // possible match
    private void checkFoundDogs(PersonLost personLost) {
        if (personLost == null) {
            System.out.println("No report filed, file a report and try again!");
        } else {
            ArrayList<PersonFound> matches = foundDogs.searchFoundPeople(personLost);
            if (matches.isEmpty()) {
                System.out.print("No found dogs currently.\n");
            }
            displayDogs(matches);
        }
    }

    private void displayDogs(ArrayList<PersonFound> listPersonFound) {
        String confirmDog = "";
        String keepLooking = "";
        for (PersonFound person : listPersonFound) {
            person.toString();
            while (true) {
                System.out.println("Is this your lost dog? (Y/N)");
                confirmDog = input.nextLine();
                if (confirmDog.equalsIgnoreCase("Y")) {
                    System.out.print("Poster's name: " + person.getName() + "\nPosters number: " + person.getPhoneNumber());
                    break;

                } else if (confirmDog.equalsIgnoreCase("N")) {
                    System.out.print("Keep looking or quit? Enter quit or look");
                    keepLooking = input.nextLine();
                    if (keepLooking.equalsIgnoreCase("quit")) {
                        return;

                    } else if (keepLooking.equalsIgnoreCase("look")) {
                        break;
                    }
                }
            }
        }
    }

    // EFFECTS: allows user to browse through all the found dogs one by one
    private void browseFoundDogs(ListPersonFound foundDogs) {

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

    // EFFECTS: get's information about the lost dog
    private Dog dogInfo() {
        System.out.println("Fill out the following information about your lost dog:");

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

        lostDog = new Dog(dogName, age, breed, furColor, size, build, picture);

        return lostDog;

    }

    // EFFECTS: get's inforation about person who lost the dog
    private PersonLost personInfo(Dog dog) {
        System.out.println("Fill out the following information about you and when/where the dog was lost: ");

        System.out.println("Your name: ");
        String personName = input.nextLine();

        System.out.println("Phone Number: ");
        String phoneNumber = input.nextLine();

        System.out.println("Location you lost the dog: ");
        String location = input.nextLine();

        System.out.println("Time you lost your dog: ");
        String timeLost = input.nextLine();

        personLost = new PersonLost(personName, phoneNumber, location, timeLost, dog);

        return personLost;
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

        for (PersonLost person : new ArrayList<>(lostDogs.getListPersonLost())) {
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

}
