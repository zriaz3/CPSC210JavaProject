package ui;

import java.util.Scanner;

import model.CurrentDog;
import model.Dog;
import model.ListPersonFound;
import model.ListPersonLost;
import model.Person;

public class ConsoleHelper {
    protected Scanner input;
    protected ListPersonFound foundDogs;
    protected ListPersonLost lostDogs;
    protected CurrentDog currentDog;

    public ConsoleHelper(CurrentDog currentDog, ListPersonFound foundDogs, ListPersonLost lostDogs, Scanner input) {
        this.foundDogs = foundDogs;
        this.lostDogs = lostDogs;
        this.input = input;
        this.currentDog = currentDog;
    }

    // EFFECTS gets user integer input
    public int userIntegerInput() {
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
    public Dog dogInfo() {
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

    public boolean displayDogsHelper(Person person) {
        System.out.println(person.toString());
        while (true) {
            System.out.println("Is this your found dog? (Y/N)");
            String confirmDog = input.nextLine();
            if (confirmDog.equalsIgnoreCase("Y")) {
                System.out.println(person.contactInfo());
                return true;
            } else if (confirmDog.equalsIgnoreCase("N")) {
                while (true) {
                    System.out.println("Keep looking or quit? Enter quit or look");
                    String keepLooking = input.nextLine();
                    if (keepLooking.equalsIgnoreCase("quit")) {
                        return true;
                    } else if (keepLooking.equalsIgnoreCase("look")) {
                        break;
                    }
                }
                break;
            }
        }
        return false;
    }

}
