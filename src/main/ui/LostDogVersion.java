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
public class LostDogVersion {
    private Scanner input;
    private ListPersonFound foundDogs;
    private ListPersonLost lostDogs;
    private CurrentDog currentDog;

    // EFFECTS: runs the lost dog version
    public LostDogVersion(CurrentDog currentDog, ListPersonFound foundDogs, ListPersonLost lostDogs, Scanner input) {
        this.foundDogs = foundDogs;
        this.lostDogs = lostDogs;
        this.input = input;
        this.currentDog = currentDog;

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

    // EFFECTS: runs the current dog through all the found dogs in the list for a possible match
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

    // EFFECTS: gets information about the lost dog
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

        return new Dog(dogName, age, breed, furColor, size, build, picture);

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

    // EFFECTS: Displays all found dogs one by one
    private void displayDogs(ArrayList<PersonFound> listPersonFound) {
        for (PersonFound person : listPersonFound) {
            System.out.println(person.toString());
            while (true) {
                System.out.println("Is this your lost dog? (Y/N)");
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
}
