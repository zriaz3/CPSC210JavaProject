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
public class LostDogUI extends JFrame {
    public static final int WIDTH = 750;
    public static final int HEIGHT = 400;

    private ListPersonFound foundDogs;
    private ListPersonLost lostDogs;
    private CurrentDog currentDog;
    private FindDogAppUI findDogAppUI;
    private JPanel display;

    // modelled after drawing editor
    // Github link:
    // https://github.students.cs.ubc.ca/CPSC210/SimpleDrawingPlayer-Complete
    // EFFECTS: runs the found dog GUI version of the app
    public LostDogUI(CurrentDog currentDog, ListPersonFound foundDogs,
            ListPersonLost lostDogs, FindDogAppUI findDogAppUI) {
        super("Lost Dog");
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
        buttonPanel.add(new JButton(new ReportLostDogAction()));
        buttonPanel.add(new JButton(new RemoveLostDogAction()));
        buttonPanel.add(new JButton(new ViewFoundDogsAction()));
        buttonPanel.add(new JButton(new CheckFoundDogsAction()));
        buttonPanel.add(new JButton(new CheckCurrentDogAction()));
        buttonPanel.add(new JButton(new ReturnAction()));

        add(buttonPanel, BorderLayout.NORTH);
    }

    private void addDisplay() {
        display = new JPanel();
        display.setLayout(new BoxLayout(display, BoxLayout.Y_AXIS));
        JScrollPane scroll = new JScrollPane(display);
        add(scroll, BorderLayout.CENTER);
    }

    // Stackoverflow link:
    // https://stackoverflow.com/questions/299495/how-to-add-an-image-to-a-jpanel
    private void showDog(Dog dog, String message) {
        JPanel dogPanel = new JPanel();
        dogPanel.setLayout(new BoxLayout(dogPanel, BoxLayout.Y_AXIS));
        dogPanel.add(new JLabel(message));
        dogPanel.add(new JLabel("Name: " + dog.getName()));
        dogPanel.add(new JLabel("Age: " + dog.getAge()));
        dogPanel.add(new JLabel("Breed: " + dog.getBreed()));
        dogPanel.add(new JLabel("Color: " + dog.getFurColor()));
        dogPanel.add(new JLabel("Size: " + dog.getSize()));
        dogPanel.add(new JLabel("Build: " + dog.getBuild()));

        if (dog.getPicture() != null && !dog.getPicture().isEmpty()) {
            try {
                ImageIcon imgIcon = new ImageIcon(dog.getPicture());
                Image img = imgIcon.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
                JLabel picLabel = new JLabel(new ImageIcon(img));
                dogPanel.add(picLabel);
            } catch (Exception e) {
                dogPanel.add(new JLabel("No available picture."));
            }
        }
        display.add(dogPanel);
    }

    private void showAllInfo(PersonFound person) {
        Dog dog = person.getDog();
        JPanel dogPanel = new JPanel();
        dogPanel.setLayout(new BoxLayout(dogPanel, BoxLayout.Y_AXIS));
        dogPanel.add(new JLabel("Name: " + dog.getName()));
        dogPanel.add(new JLabel("Age: " + dog.getAge()));
        dogPanel.add(new JLabel("Breed: " + dog.getBreed()));
        dogPanel.add(new JLabel("Color: " + dog.getFurColor()));
        dogPanel.add(new JLabel("Size: " + dog.getSize()));
        dogPanel.add(new JLabel("Build: " + dog.getBuild()));
        dogPanel.add(new JLabel("Reported by: " + person.getName()));
        dogPanel.add(new JLabel("Phone number: " + person.getPhoneNumber()));
        dogPanel.add(new JLabel("Location and Time Found: " + person.getLocation() + " " + person.getTimeFound()));

        if (dog.getPicture() != null && !dog.getPicture().isEmpty()) {
            try {
                ImageIcon imgIcon = new ImageIcon(dog.getPicture());
                Image img = imgIcon.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
                JLabel picLabel = new JLabel(new ImageIcon(img));
                dogPanel.add(picLabel);
            } catch (Exception e) {
                dogPanel.add(new JLabel("No available picture."));
            }
        }
        display.add(dogPanel);
    }

    private class ReportLostDogAction extends AbstractAction {
        ReportLostDogAction() {
            super("Report Lost Dog");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Dog dog = dogInfo();
            currentDog.setDog(dog);
            PersonLost personLost = personInfo(dog);
            lostDogs.addPerson(personLost);

            display.removeAll();
            showDog(dog, "Lost Dog Reported!");
            display.revalidate();
            display.repaint();
        }

        private PersonLost personInfo(Dog dog) {

            String personName = JOptionPane.showInputDialog("Your name: ");
            String phoneNumber = JOptionPane.showInputDialog("Your number: ");
            String location = JOptionPane.showInputDialog("Location dog was found: ");
            String timeLost = JOptionPane.showInputDialog("Time dog was found ");

            return new PersonLost(personName, phoneNumber, location, timeLost, dog);
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
                    JOptionPane.showMessageDialog(LostDogUI.this, "Enter valid number.");
                }
            }
            return age;
        }
    }

    private class ViewFoundDogsAction extends AbstractAction {
        ViewFoundDogsAction() {
            super("View Found Dogs");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<PersonFound> personFoundDogs = foundDogs.getListPersonFound();

            display.removeAll();

            if (personFoundDogs.isEmpty()) {
                JOptionPane.showMessageDialog(LostDogUI.this, "No found dogs yet!");
            } else {
                displayDogs(personFoundDogs);
                display.revalidate();
                display.repaint();
            }
        }

        private void displayDogs(ArrayList<PersonFound> listPersonFound) {
            if (listPersonFound.isEmpty()) {
                JOptionPane.showMessageDialog(LostDogUI.this, "No found dogs reported yet.");
                return;
            }
            for (PersonFound person : listPersonFound) {
                showAllInfo(person);
            }
        }
    }

    private class CheckFoundDogsAction extends AbstractAction {
        CheckFoundDogsAction() {
            super("Check Found Dogs For Your Lost Dog");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (currentDog.getDog() == null) {
                JOptionPane.showMessageDialog(LostDogUI.this, "No report filed, file a report and try again!");
            } else {
                ArrayList<PersonFound> matches = foundDogs.searchFoundPeople(currentDog.getDog());
                displayDogs(matches);
            }
        }

        private void displayDogs(ArrayList<PersonFound> listPersonFound) {
            if (listPersonFound.isEmpty()) {
                JOptionPane.showMessageDialog(LostDogUI.this, "No found dogs reported yet.");
                return;
            }
            for (PersonFound person : listPersonFound) {
                showAllInfo(person);
            }
        }
    }   

    private class RemoveLostDogAction extends AbstractAction {
        RemoveLostDogAction() {
            super("Remove Lost Dog Report");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String name = JOptionPane
                    .showInputDialog("Verify it is your report by answering these questions and remove it:"
                            + "\nYour name: ");
            String phoneNumber = JOptionPane.showInputDialog("Your phone Number: ");

            boolean isRemoved = false;

            for (PersonLost person : new ArrayList<>(lostDogs.getListPersonLost())) {
                if (person.getName().equalsIgnoreCase(name) && person.getPhoneNumber().equals(phoneNumber)) {
                    lostDogs.removePerson(person);
                    JOptionPane.showMessageDialog(LostDogUI.this, "Lost dog report removed.");
                    isRemoved = true;
                    break;
                }
            }

            if (!isRemoved) {
                JOptionPane.showMessageDialog(LostDogUI.this, "No report found.");
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
                JOptionPane.showMessageDialog(LostDogUI.this, "No report filed!");
            } else {
                display.removeAll();
                showDog(currentDog.getDog(), "Current Dog: ");
                display.revalidate();
                display.repaint();
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