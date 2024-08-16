package Main;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FamilyTree {
    private final List<Person> people;

    public FamilyTree() {
        this.people = new ArrayList<>();
    }

    public void addPerson(Person person) {
        people.add(person);
    }

    public void removePerson(Person person) {
        people.remove(person);
        for (Person p : people) {
            p.getChildren().remove(person);
        }
    }

    public Person findPerson(String firstName, String lastName) {
        for (Person person : people) {
            if (person.getFirstName().equals(firstName) && person.getLastName().equals(lastName)) {
                return person;
            }
        }
        return null;
    }

    public List<Person> getChildrenOfPerson(String firstName, String lastName) {
        Person person = findPerson(firstName, lastName);
        if (person != null) {
            return person.getChildren();
        }
        return new ArrayList<>();
    }

    public List<Person> getSiblingsOfPerson(String firstName, String lastName) {
        Person person = findPerson(firstName, lastName);
        if (person != null) {
            return people.stream()
                    .filter(p -> p.getChildren().contains(person))
                    .flatMap(p -> p.getChildren().stream())
                    .filter(sibling -> !sibling.equals(person))
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    public List<Person> getAncestorsOfPerson(String firstName, String lastName) {
        List<Person> ancestors = new ArrayList<>();
        Person person = findPerson(firstName, lastName);
        if (person != null) {
            for (Person p : people) {
                if (p.getChildren().contains(person)) {
                    ancestors.add(p);
                    ancestors.addAll(getAncestorsOfPerson(p.getFirstName(), p.getLastName()));
                }
            }
        }
        return ancestors;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Person person : people) {
            sb.append(person.toString()).append("\n");
        }
        return sb.toString();
    }
}
