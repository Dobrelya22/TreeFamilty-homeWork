package Main.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FamilyTree<T extends TreeItem> {
    private final List<T> people;

    public FamilyTree() {
        this.people = new ArrayList<>();
    }

    public void addPerson(T person) {
        people.add(person);
    }

    public T findPerson(String name) {
        for (T person : people) {
            if (person.getName().equals(name)) {
                return person;
            }
        }
        return null;
    }

    public List<T> getChildrenOfPerson(String name) {
        T person = findPerson(name);
        if (person != null) {
            List<T> children = new ArrayList<>();
            for (TreeItem child : person.getChildren()) {
                if (person.getClass().isInstance(child)) { // Проверка типа перед приведением
                    children.add((T) child);
                }
            }
            return children;
        }
        return new ArrayList<>();
    }

    public List<T> getSiblingsOfPerson(String name) {
        T person = findPerson(name);
        if (person != null) {
            List<T> siblings = new ArrayList<>();
            for (T p : people) {
                if (p.getChildren().contains(person)) {
                    for (TreeItem child : p.getChildren()) {
                        if (!child.equals(person) && person.getClass().isInstance(child)) {
                            siblings.add((T) child);
                        }
                    }
                }
            }
            return siblings;
        }
        return new ArrayList<>();
    }

    public List<T> getAncestorsOfPerson(String name) {
        List<T> ancestors = new ArrayList<>();
        T person = findPerson(name);
        if (person != null) {
            for (T p : people) {
                if (p.getChildren().contains(person)) {
                    ancestors.add(p);
                    ancestors.addAll(getAncestorsOfPerson(p.getName()));
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
        people.sort(Comparator.comparing(TreeItem::getName));
    }

    public void sortByBirthDate() {
        people.sort(Comparator.comparing(person -> {
            if (person instanceof Person) {
                return ((Person) person).getDateOfBirth();
            }
            return null;
        }));
    }
}
