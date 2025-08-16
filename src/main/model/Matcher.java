package model;

public abstract class Matcher {

    // EFFECTS: returns true if given dog is similar to person's dog
    public Boolean isMatch(Dog dog, Person person) {
        int score = 0;
        Dog lost = dog;
        Dog found = person.getDog();
        if (lost.getName().equalsIgnoreCase(found.getName())) {
            score++;
        }
        if (lost.getAge() == (found.getAge())) {
            score++;
        }
        if (lost.getBreed().equalsIgnoreCase(found.getBreed())) {
            score++;
        }
        if (lost.getFurColor().equalsIgnoreCase(found.getFurColor())) {
            score++;
        }
        if (lost.getSize().equalsIgnoreCase(found.getSize())) {
            score++;
        }
        if (lost.getBuild().equalsIgnoreCase(found.getBuild())) {
            score++;
        }

        return score >= 3;
    }
}
