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
public class FoundDogUI extends JFrame {
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
    public FoundDogUI(CurrentDog currentDog, ListPersonFound foundDogs,
            ListPersonLost lostDogs, FindDogAppUI findDogAppUI) {
        super("Found Dog");
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
        buttonPanel.add(new JButton(new ReportFoundDogAction()));
        buttonPanel.add(new JButton(new RemoveFoundDogAction()));
        buttonPanel.add(new JButton(new ViewLostDogsAction()));
        buttonPanel.add(new JButton(new CheckLostDogsAction()));
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
    private void showAllInfo(PersonLost person) {
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
        dogPanel.add(new JLabel("Location and Time Lost: " + person.getLocation() + " " + person.getTimeLost()));

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

    // Handles reporting of a found dog
    private class ReportFoundDogAction extends AbstractAction {
        // MODIFIES: this
        // EFFECTS: constructs action
        ReportFoundDogAction() {
            super("Report Found Dog");
        }

        // Reference: https://stackoverflow.com/questions/1097366/java-swing-revalidate-vs-repaint
        // MODIFIES: this, currentDog, foundDogs
        // EFFECTS: gets found dog info, adds to list of found, displays found dog
        @Override
        public void actionPerformed(ActionEvent e) {
            Dog dog = dogInfo();
            currentDog.setDog(dog);
            PersonFound personFound = personInfo(dog);
            foundDogs.addPerson(personFound);

            display.removeAll();
            showDog(dog, "Found Dog Reported!");
            display.revalidate();
            display.repaint();
        }
        

        // EFFECTS: get info about person
        private PersonFound personInfo(Dog dog) {
            String personName = JOptionPane.showInputDialog("Your name: ");
            String phoneNumber = JOptionPane.showInputDialog("Your number: ");
            String location = JOptionPane.showInputDialog("Location dog was found: ");
            String timeLost = JOptionPane.showInputDialog("Time dog was found ");

            return new PersonFound(personName, phoneNumber, location, timeLost, dog);
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
                    JOptionPane.showMessageDialog(FoundDogUI.this, "Enter valid number.");
                }
            }
            return age;
        }
    }

    // Handles viewing all lost dogs
    private class ViewLostDogsAction extends AbstractAction {
        // MODIFIES: this
        // EFFECTS: constructs action
        ViewLostDogsAction() {
            super("View Lost Dogs");
        }

        // MODIFIES: this
        // EFFECTS: displays all lost dogs if present
        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<PersonLost> personLostDogs = lostDogs.getListPersonLost();

            display.removeAll();

            if (personLostDogs.isEmpty()) {
                JOptionPane.showMessageDialog(FoundDogUI.this, "No lost dogs yet!");
            } else {
                displayDogs(personLostDogs);
                display.revalidate();
                display.repaint();
            }
        }

        // MODIFIES: this
        // EFFECTS: displays all lost dogs together
        private void displayDogs(ArrayList<PersonLost> listPersonLost) {
            if (listPersonLost.isEmpty()) {
                JOptionPane.showMessageDialog(FoundDogUI.this, "No lost dogs reported yet.");
                return;
            }
            for (PersonLost person : listPersonLost) {
                showAllInfo(person);
            }
        }
    }

    // Handles comparing found dog to lost dogs
    private class CheckLostDogsAction extends AbstractAction {
        // MODIFIES: this
        // EFFECTS: constructs action
        CheckLostDogsAction() {
            super("Check Lost Dogs For Your Found Dog");
        }

        // MODIFIES: this
        // EFFECTS: checks if any matches and displays them
        @Override
        public void actionPerformed(ActionEvent e) {

            display.removeAll();

            if (currentDog.getDog() == null) {
                JOptionPane.showMessageDialog(FoundDogUI.this, "No report filed, file a report and try again!");
            } else {
                ArrayList<PersonLost> matches = lostDogs.searchLostPeople(currentDog.getDog());
                displayDogs(matches);
                display.revalidate();
                display.repaint();
            }
        }

        // MODIFIES: this
        // EFFECTS: displays all lost dogs
        private void displayDogs(ArrayList<PersonLost> listPersonLost) {
            if (listPersonLost.isEmpty()) {
                JOptionPane.showMessageDialog(FoundDogUI.this, "No lost dogs reported yet.");
                return;
            }
            for (PersonLost person : listPersonLost) {
                showAllInfo(person);
            }
        }
    }

    // Handles removing a found dog report
    private class RemoveFoundDogAction extends AbstractAction {
        // MODIFIES: this
        // EFFECTS: constructs action
        RemoveFoundDogAction() {
            super("Remove Found Dog Report");
        }

        // MODIFIES: this, foundDogs
        // EFFECTS: Remove found dog report if verification sucessful
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
                JOptionPane.showMessageDialog(FoundDogUI.this, "No report filed!");
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
