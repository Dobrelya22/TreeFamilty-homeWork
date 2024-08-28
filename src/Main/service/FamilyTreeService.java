package Main.service;

import Main.model.FamilyTree;
import Main.model.Person;

import java.io.IOException;
import java.util.List;

public class FamilyTreeService {
    private final FamilyTree<Person> familyTree;
    private final FamilyTreeStorage storage;

    public FamilyTreeService(FamilyTreeStorage storage) {
        this.familyTree = new FamilyTree<>();
        this.storage = storage;
    }

    public void addPerson(String firstName, String lastName, String dateOfBirth, String gender) {
        Person person = new Person(firstName, lastName, dateOfBirth, gender);
        familyTree.addPerson(person);
    }

    public void addChild(String parentFirstName, String parentLastName, String childFirstName, String childLastName) {
        String parentFullName = parentFirstName + " " + parentLastName;
        String childFullName = childFirstName + " " + childLastName;

        Person parent = familyTree.findPerson(parentFullName);
        Person child = familyTree.findPerson(childFullName);
        if (parent != null && child != null) {
            parent.addChild(child);
        }
    }

    public List<Person> getChildrenOfPerson(String firstName, String lastName) {
        String fullName = firstName + " " + lastName;
        return familyTree.getChildrenOfPerson(fullName);
    }

    public List<Person> getAncestorsOfPerson(String firstName, String lastName) {
        String fullName = firstName + " " + lastName;
        return familyTree.getAncestorsOfPerson(fullName);
    }

    public List<Person> getSiblingsOfPerson(String firstName, String lastName) {
        String fullName = firstName + " " + lastName;
        return familyTree.getSiblingsOfPerson(fullName);
    }

    public List<Person> getAllPeople() {
        return familyTree.getAllPeople();
    }

    public void sortByName() {
        familyTree.sortByName();
    }

    public void sortByBirthDate() {
        familyTree.sortByBirthDate();
    }

    public void saveTree(String fileName) throws IOException {
        storage.save(familyTree, fileName);
    }

    public void loadTree(String fileName) throws IOException, ClassNotFoundException {
        FamilyTree<Person> loadedTree = (FamilyTree<Person>) storage.load(fileName);
        if (loadedTree != null) {
            familyTree.clear();
            familyTree.addPeople(loadedTree.getAllPeople());
        }
    }

    public Person findPerson(String firstName, String lastName) {
        String fullName = firstName + " " + lastName;
        return familyTree.findPerson(fullName);
    }
}

