package model;

public class CurrentDog {
    private Dog dog;

    public CurrentDog(Dog dog) {
        this.dog = dog;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }
}