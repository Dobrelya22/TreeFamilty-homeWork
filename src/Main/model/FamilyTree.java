package Main.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FamilyTree<T extends Person> {
    private final List<T> people;

    public FamilyTree() {
        this.people = new ArrayList<>();
    }

    public void addPerson(T person) {
        people.add(person);
    }

    public T findPerson(String firstName, String lastName) {
        for (T person : people) {
            if (person.getFirstName().equals(firstName) && person.getLastName().equals(lastName)) {
                return person;
            }
        }
        return null;
    }

    public List<T> getChildrenOfPerson(String firstName, String lastName) {
        T person = findPerson(firstName, lastName);
        if (person != null) {
            List<T> children = new ArrayList<>();
            for (Person child : person.getChildren()) {
                children.add((T) child); // Приведение типа к T
            }
            return children;
        }
        return new ArrayList<>();
    }

    public List<T> getSiblingsOfPerson(String firstName, String lastName) {
        T person = findPerson(firstName, lastName);
        if (person != null) {
            List<T> siblings = new ArrayList<>();
            for (T p : people) {
                if (p.getChildren().contains(person)) {
                    for (Person child : p.getChildren()) {
                        if (!child.equals(person)) {
                            siblings.add((T) child); // Приведение типа к T
                        }
                    }
                }
            }
            return siblings;
        }
        return new ArrayList<>();
    }

    public List<T> getAncestorsOfPerson(String firstName, String lastName) {
        List<T> ancestors = new ArrayList<>();
        T person = findPerson(firstName, lastName);
        if (person != null) {
            for (T p : people) {
                if (p.getChildren().contains(person)) {
                    ancestors.add(p);
                    ancestors.addAll(getAncestorsOfPerson(p.getFirstName(), p.getLastName()));
                }
            }
        }
        return ancestors;
    }

    public List<T> getAllPeople() {
        return new ArrayList<>(people);
    }

    public void clear() {
        people.clear();
    }

    public void addPeople(List<T> newPeople) {
        people.addAll(newPeople);
    }

    public void sortByName() {
        people.sort(Comparator.comparing(Person::getFirstName));
    }

    public void sortByBirthDate() {
        people.sort(Comparator.comparing(Person::getDateOfBirth));
    }

}
