package model;

// Represents a dog that has a name, breed, fur color, age, size, build and picture.
public class Dog {
    private String name;     // Dog's name 
    private String breed;    // Dog's breed/ most similar breed
    private String furColor; // Dog's most prominent fur color
    private int age;         // Dog's age in years
    private String size;     // Dog's rough size i.e small/ medium/ large
    private String build;    // Dog's body build e.g. lean/ muscular/ tall
    private String picture;  // Picture of the dog

    public Dog(String name, int age, String breed, String furColor, String size, String build, String picture) {
        this.name = name;
        this.age = age;
        this.breed = breed;
        this.furColor = furColor;
        this.size = size;
        this.build = build;
        this.picture = picture;
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

    public String getPicture() {
        return picture;
    }

    // EFFECTS: return all the information about the dog as a string
    public String toString() {
        return "/nName: " + name + "/nAge: " + age + "/nBreed: " + breed + "/nFur Color: " 
            + furColor + "/nSize: " + size + "/nBuild: " + build + "/nPicture: " + picture;
    }

}
