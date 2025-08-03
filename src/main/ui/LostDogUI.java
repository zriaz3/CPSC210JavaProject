package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

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

    // modelled after DrawingEditor
    // https://github.students.cs.ubc.ca/CPSC210/SimpleDrawingPlayer-Complete\
    // MODIFIES: this
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

    // MODIFIES: this
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

    // MODIFIES: this
    // EFFECTS: adds all required buttons
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

    // MODIFIES: this
    // EFFECTS: initialize display
    private void addDisplay() {
        display = new JPanel();
        display.setLayout(new BoxLayout(display, BoxLayout.Y_AXIS));
        JScrollPane scroll = new JScrollPane(display);
        add(scroll, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: display given Dog information
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

        getPicture(dogPanel, dog);
        display.add(dogPanel);
    }

    // Reference: https://stackoverflow.com/questions/66502178/how-to-add-spacing-between-jpanel-and-jframes-contentpane
    // MODIFIES: this
    // EFFECTS: displays all info about person and dog
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

        getPicture(dogPanel, dog);
        dogPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        display.add(dogPanel);
    }

    // Reference: https://stackoverflow.com/questions/299495/how-to-add-an-image-to-a-jpanel
    // MODIFIES: dogPanel
    // EFFECTS: adds image to dogPanel if available
    private void getPicture(JPanel dogPanel, Dog dog) {
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
    }

    // Handles reporting of a Lost dog
    private class ReportLostDogAction extends AbstractAction {
        // MODIFIES: this
        // EFFECTS: constructs action
        ReportLostDogAction() {
            super("Report Lost Dog");
        }

        // Reference: https://stackoverflow.com/questions/1097366/java-swing-revalidate-vs-repaint
        // MODIFIES: this, currentDog, lostDogs
        // EFFECTS: gets lost dog info, adds to list of lost, displays lost dog
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

        // EFFECTS: get info about person
        private PersonLost personInfo(Dog dog) {
            String personName = JOptionPane.showInputDialog("Your name: ");
            String phoneNumber = JOptionPane.showInputDialog("Your number: ");
            String location = JOptionPane.showInputDialog("Location dog was found: ");
            String timeLost = JOptionPane.showInputDialog("Time dog was found ");

            return new PersonLost(personName, phoneNumber, location, timeLost, dog);
        }

        // EFFECTS: get info about dog
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

        // EFFECTS: gets user integer input
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

    // Handles viewing all found dogs
    private class ViewFoundDogsAction extends AbstractAction {
        // MODIFIES: this
        // EFFECTS: constructs action
        ViewFoundDogsAction() {
            super("View Found Dogs");
        }

        // MODIFIES: this
        // EFFECTS: displays all found dogs if present
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

        // MODIFIES: this
        // EFFECTS: displays all found dogs together
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

    // Handles comparing lost dog to found dogs
    private class CheckFoundDogsAction extends AbstractAction {
        // MODIFIES: this
        // EFFECTS: constructs action
        CheckFoundDogsAction() {
            super("Check Found Dogs For Your Lost Dog");
        }

        // MODIFIES: this
        // EFFECTS: checks if any matches and displays them
        @Override
        public void actionPerformed(ActionEvent e) {
            if (currentDog.getDog() == null) {
                JOptionPane.showMessageDialog(LostDogUI.this, "No report filed, file a report and try again!");
            } else {
                ArrayList<PersonFound> matches = foundDogs.searchFoundPeople(currentDog.getDog());
                displayDogs(matches);
            }
        }

        // MODIFIES: this
        // EFFECTS: displays all found dogs
        private void displayDogs(ArrayList<PersonFound> listPersonFound) {
            display.removeAll();
            if (listPersonFound.isEmpty()) {
                JOptionPane.showMessageDialog(LostDogUI.this, "No found dogs reported yet.");
                return;
            }
            for (PersonFound person : listPersonFound) {
                showAllInfo(person);
                display.revalidate();
                display.repaint();
            }
        }
    }   

    // Handles removing a found dog report
    private class RemoveLostDogAction extends AbstractAction {
        // MODIFIES: this
        // EFFECTS: constructs action
        RemoveLostDogAction() {
            super("Remove Lost Dog Report");
        }

        // MODIFIES: this, foundDogs
        // EFFECTS: Remove found dog report if verification sucessful
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

    // Handles checking current dog
    private class CheckCurrentDogAction extends AbstractAction {
        // MODIFIES: this
        // EFFECTS: constructs action
        CheckCurrentDogAction() {
            super("View Current Dog");
        }

        // MODIFIES: this
        // EFFECTS: displays current dog if present
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

    // Handles returning to main menu 
    private class ReturnAction extends AbstractAction {
        // MODIFIES: this
        // EFFECTS: constructs action
        ReturnAction() {
            super("Return to main menu");
        }

        // MODIFIES: this
        // EFFECTS: closes FoundDogUI and goes back to FindDogAppUI
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            findDogAppUI.setVisible(true);

        }
    }
}