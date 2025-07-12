package model;

public class Dog {
    private String name;     // Dog's name
    private String breed;    // Dog's breed 
    private String furColor; // Dog's most prominent fur color
    private int age;         // Dog's age in years
    private String size;     // Dog's rough size i.e small/ medium/ large
    private String build;    // Dog's body build e.g. lean/ muscular/ tall
    private String status;   // Dog's status i.e lost or found

    public Dog(String name, int age, String breed, String furColor, String size, String build, String status) {
        this.name = name;
        this.age = age;
        this.breed = breed;
        this.furColor = furColor;
        this.size = size;
        this.build = build;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getBreed() {
        return breed;
    }

    public String getFurColor() {
        return furColor;
    }

    public String getSize() {
        return size;
    }

    public String getBuild() {
        return build;
    }

    public String getStatus() {
        return status;
    }

    
}
