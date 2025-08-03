package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import model.CurrentDog;
import model.Dog;
import model.ListPersonFound;
import model.PersonLost;
import model.ListPersonLost;
import model.PersonFound;

// Found dog GUI version of the application
public class FoundDogUI extends JFrame {
    public static final int WIDTH = 750;
    public static final int HEIGHT = 400;

    private ListPersonFound foundDogs;
    private ListPersonLost lostDogs;
    private CurrentDog currentDog;
    private FindDogAppUI findDogAppUI;
    private JTextArea display;

    // modelled after drawing editor
    // Github link:
    // https://github.students.cs.ubc.ca/CPSC210/SimpleDrawingPlayer-Complete
    // EFFECTS: runs the found dog GUI version of the app
    public FoundDogUI(CurrentDog currentDog, ListPersonFound foundDogs,
            ListPersonLost lostDogs, FindDogAppUI findDogAppUI) {
        super("Found Dog");
        this.currentDog = currentDog;
        this.foundDogs = foundDogs;
        this.lostDogs = lostDogs;
        this.findDogAppUI = findDogAppUI;
        initializeGraphics();
    }

    // EFFECTS: sets up the layout of the App and add buttons/display
    private void initializeGraphics() {
        setLayout(new BorderLayout());
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        addButtons();
        addDisplay();
        setVisible(true);
    }

    private void addButtons() {
        JPanel buttonPanel = new JPanel(new GridLayout(3, 3));
        buttonPanel.add(new JButton(new ReportFoundDogAction()));
        buttonPanel.add(new JButton(new RemoveFoundDogAction()));
        buttonPanel.add(new JButton(new ViewLostDogsAction()));
        buttonPanel.add(new JButton(new CheckLostDogsAction()));
        buttonPanel.add(new JButton(new CheckCurrentDogAction()));
        buttonPanel.add(new JButton(new ReturnAction()));

        add(buttonPanel, BorderLayout.NORTH);
    }

    private void addDisplay() {
        display = new JTextArea();
        display.setEditable(false);
        JScrollPane scroll = new JScrollPane(display);
        add(scroll, BorderLayout.CENTER);
    }

    private class ReportFoundDogAction extends AbstractAction {
        ReportFoundDogAction() {
            super("Report Found Dog");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Dog dog = dogInfo();
            currentDog.setDog(dog);
            PersonFound personFound = personInfo(dog);
            foundDogs.addPerson(personFound);
            display.setText("Found Dog Reported!\n" + dog);
        }

        private PersonFound personInfo(Dog dog) {

            String personName = JOptionPane.showInputDialog("Your name: ");
            String phoneNumber = JOptionPane.showInputDialog("Your number: ");
            String location = JOptionPane.showInputDialog("Location dog was found: ");
            String timeLost = JOptionPane.showInputDialog("Time dog was found ");

            return new PersonFound(personName, phoneNumber, location, timeLost, dog);
        }

        private Dog dogInfo() {

            String dogName = JOptionPane.showInputDialog("Dog's name: ");
            int age = userIntegerInput();
            String breed = JOptionPane.showInputDialog("Dog's breed: ");
            String color = JOptionPane.showInputDialog("Dog's color: ");
            String size = JOptionPane.showInputDialog("Dog's size: ");
            String build = JOptionPane.showInputDialog("Dog's build: ");
            String picture = JOptionPane.showInputDialog("Dog's picture: ");

            return new Dog(dogName, age, breed, color, size, build, picture);
        }

        private int userIntegerInput() {
            int age = 0;

            while (true) {
                String dogAge = JOptionPane.showInputDialog("Dog's age: ");
                try {
                    age = Integer.parseInt(dogAge);
                    break;
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(FoundDogUI.this, "Enter valid number.");
                }
            }
            return age;
        }
    }

    private class ViewLostDogsAction extends AbstractAction {
        ViewLostDogsAction() {
            super("View Lost Dogs");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<PersonLost> personLostDogs = lostDogs.getListPersonLost();

            if (personLostDogs.isEmpty()) {
                JOptionPane.showMessageDialog(FoundDogUI.this, "No lost dogs yet!");
            } else {
                displayDogs(personLostDogs);
            }
        }

        private void displayDogs(ArrayList<PersonLost> listPersonLost) {
            if (listPersonLost.isEmpty()) {
                display.setText("No lost dogs reported yet.");
                return;
            }
            StringBuilder sb = new StringBuilder();
            for (PersonLost person : listPersonLost) {
                sb.append(person.toString()).append("\n\n");
            }
            display.setText(sb.toString());
        }
    }

    private class CheckLostDogsAction extends AbstractAction {
        CheckLostDogsAction() {
            super("Check Lost Dogs For Your Found Dog");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (currentDog.getDog() == null) {
                JOptionPane.showMessageDialog(FoundDogUI.this, "No report filed, file a report and try again!");
            } else {
                ArrayList<PersonLost> matches = lostDogs.searchLostPeople(currentDog.getDog());
                displayDogs(matches);
            }
        }

        private void displayDogs(ArrayList<PersonLost> listPersonLost) {
            if (listPersonLost.isEmpty()) {
                display.setText("No lost dogs reported yet.");
                return;
            }
            StringBuilder sb = new StringBuilder();
            for (PersonLost person : listPersonLost) {
                sb.append(person.toString()).append("\n\n");
            }
            display.setText(sb.toString());
        }
    }

    private class RemoveFoundDogAction extends AbstractAction {
        RemoveFoundDogAction() {
            super("Remove Found Dog Report");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String name = JOptionPane
                    .showInputDialog("Verify it is your report by answering these questions and remove it:"
                            + "\n\nYour name: ");
            String phoneNumber = JOptionPane.showInputDialog("Your phone Number: ");

            boolean isRemoved = false;

            for (PersonFound person : new ArrayList<>(foundDogs.getListPersonFound())) {
                if (person.getName().equalsIgnoreCase(name) && person.getPhoneNumber().equals(phoneNumber)) {
                    foundDogs.removePerson(person);
                    JOptionPane.showMessageDialog(FoundDogUI.this, "Found dog report removed.");
                    isRemoved = true;
                    break;
                }
            }

            if (!isRemoved) {
                JOptionPane.showMessageDialog(FoundDogUI.this, "No report found.");
            }
        }
    }

    private class CheckCurrentDogAction extends AbstractAction {
        CheckCurrentDogAction() {
            super("View Current Dog");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (currentDog.getDog() == null) {
                JOptionPane.showMessageDialog(FoundDogUI.this, "No report filed!");
            } else {
                display.setText(currentDog.getDog().toString());
            }
        }
    }

    private class ReturnAction extends AbstractAction {
        ReturnAction() {
            super("Return to main menu");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            findDogAppUI.setVisible(true);

        }
    }
}
