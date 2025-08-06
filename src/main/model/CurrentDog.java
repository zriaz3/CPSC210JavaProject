package model;

// Represents the most recent dog report filed i.e. the current dog
public class CurrentDog {
    private Dog dog;

    // EFFECTS: creates a current dog 
    public CurrentDog(Dog dog) {
        this.dog = dog;
    }

    public Dog getDog() {
        return dog;
    }

    // MODIFIES: this
    // EFFECTS: sets the current dog to given dog
    public void setDog(Dog dog) {
        this.dog = dog;
        EventLog.getInstance().logEvent(new Event("Current dog changed"));
    }
}