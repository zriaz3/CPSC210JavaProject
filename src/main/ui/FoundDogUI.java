package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import model.CurrentDog;
import model.Dog;
import model.ListPersonFound;
import model.PersonLost;
import model.ListPersonLost;
import model.PersonFound;

// Found dog GUI version of the application
public class FoundDogUI extends JFrame {
    public static final int WIDTH = 750;
    public static final int HEIGHT = 750;

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
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        addButtons();
        addDisplay();
        setVisible(true);
    }

    private void addButtons() {
        JPanel buttonPanel = new JPanel(new GridLayout(1, 5));
        buttonPanel.add(new JButton(new ReportFoundDogAction()));
        buttonPanel.add(new JButton(new ViewLostDogsAction()));
        buttonPanel.add(new JButton(new CheckLostDogsAction()));
        buttonPanel.add(new JButton(new removeFoundDogAction()));
        buttonPanel.add(new JButton(new returnAction()));

        add(buttonPanel, BorderLayout.NORTH);
    }

    private void addDisplay() {
        display = new JTextArea();
        display.setEditable(false);
        JScrollPane jScroll = new JScrollPane(display);
        add(jScroll, BorderLayout.SOUTH);
    }

    private class ReportFoundDogAction extends AbstractAction {
        ReportFoundDogAction() {
            super("Report Found Dog");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(FoundDogUI.this, "View Lost Dogs clicked!");
         }
    }

    private class ViewLostDogsAction extends AbstractAction {
        ViewLostDogsAction() {
            super("View Lost Dogs");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(FoundDogUI.this, "View Lost Dogs clicked!");
        }
    }

    private class CheckLostDogsAction extends AbstractAction {
        CheckLostDogsAction() {
            super("Check Lost Dogs");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(FoundDogUI.this, "View Lost Dogs clicked!");
        }
    }

    private class removeFoundDogAction extends AbstractAction {
        removeFoundDogAction() {
            super("Remove Found Dog Report");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(FoundDogUI.this, "View Lost Dogs clicked!");
        }
    }

    private class returnAction extends AbstractAction {
        returnAction() {
            super("Return to main menu");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JOptionPane.showMessageDialog(FoundDogUI.this, "View Lost Dogs clicked!");
         }
    }
}
