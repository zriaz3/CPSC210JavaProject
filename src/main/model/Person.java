package model;

// abstract person class
public abstract class Person {
    protected String name; // Poster's name
    protected String phoneNumber; // Poster's phone number
    protected String location; // location where lost/found
    protected String time; // time dog was lost/found
    protected Dog dog; // Poster's dog

    public Person(String name, String phoneNumber, String location, String time, Dog dog) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.dog = dog;
        this.location = location;
        this.time = time;
    }
    
    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Dog getDog() {
        return dog;
    }

    public String getLocation() {
        return location;
    }

    public String getTime() {
        return time;
    }

    // EFFECTS: return contact info of poster as a String
    public String contactInfo() {
        return "Poster name: " + name + "\nPhone number: " + phoneNumber;
    }

    public abstract String toString();
}
