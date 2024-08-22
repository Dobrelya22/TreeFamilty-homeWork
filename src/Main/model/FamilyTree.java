package Main.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class FamilyTree<T extends Person> implements Serializable, Iterable<T> {
    private final List<T> people;

    public FamilyTree() {
        this.people = new ArrayList<>();
    }

    public void addPerson(T person) {
        people.add(person);
    }

    public void removePerson(T person) {
        people.remove(person);
        for (T p : people) {
            p.getChildren().remove(person);
        }
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
            return person.getChildren().stream()
                    .map(child -> (T) child)  // Преобразование с подавлением предупреждения
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    public List<T> getSiblingsOfPerson(String firstName, String lastName) {
        T person = findPerson(firstName, lastName);
        if (person != null) {
            return people.stream()
                    .filter(p -> p.getChildren().contains(person))
                    .flatMap(p -> p.getChildren().stream())
                    .filter(sibling -> !sibling.equals(person))
                    .map(sibling -> (T) sibling)  // Преобразование с подавлением предупреждения
                    .collect(Collectors.toList());
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

    @Override
    public Iterator<T> iterator() {
        return people.iterator();
    }

    // Сортировка по имени
    public void sortByName() {
        people.sort(Comparator.comparing(T::getFirstName));
    }

    // Сортировка по дате рождения
    public void sortByBirthDate() {
        people.sort(Comparator.comparing(T::getDateOfBirth));
    }

    public void printSortedByName() {
        sortByName();
        people.forEach(person -> System.out.println(person.getFirstName()));
    }

    public void printSortedByBirthDate() {
        sortByBirthDate();
        people.forEach(person -> System.out.println(person.getDateOfBirth()));
    }
}
