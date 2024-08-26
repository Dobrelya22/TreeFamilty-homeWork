package Main.presenter;

import Main.model.FamilyTree;
import Main.model.Person;
import Main.service.FamilyTreeStorage;
import Main.view.FamilyTreeView;

import java.io.IOException;
import java.util.List;

public class FamilyTreePresenter {
    private final FamilyTree<Person> familyTree;
    private final FamilyTreeView view;
    private final FamilyTreeStorage storage;

    public FamilyTreePresenter(FamilyTreeStorage storage, FamilyTreeView view) {
        this.familyTree = new FamilyTree<>();
        this.view = view;
        this.storage = storage;
    }

    public void addPerson(String firstName, String lastName, String dateOfBirth, String gender) {
        Person person = new Person(firstName, lastName, dateOfBirth, gender);
        familyTree.addPerson(person);
    }

    public void addChild(String parentFirstName, String parentLastName, String childFirstName, String childLastName) {
        Person parent = familyTree.findPerson(parentFirstName, parentLastName);
        Person child = familyTree.findPerson(childFirstName, childLastName);
        if (parent != null && child != null) {
            parent.addChild(child);
        }
    }

    public void showTreeSortedByName() {
        familyTree.sortByName();
        view.showTree(familyTree.getAllPeople());
    }

    public void showTreeSortedByBirthDate() {
        familyTree.sortByBirthDate();
        view.showTree(familyTree.getAllPeople());
    }

    public void showChildrenOfPerson(String firstName, String lastName) {
        List<Person> children = familyTree.getChildrenOfPerson(firstName, lastName);
        view.showTree(children);
    }

    public void showAncestorsOfPerson(String firstName, String lastName) {
        List<Person> ancestors = familyTree.getAncestorsOfPerson(firstName, lastName);
        view.showTree(ancestors);
    }

    public void showSiblingsOfPerson(String firstName, String lastName) {
        List<Person> siblings = familyTree.getSiblingsOfPerson(firstName, lastName);
        view.showTree(siblings);
    }

    public Person findPersonByName(String firstName, String lastName) {
        return familyTree.findPerson(firstName, lastName);
    }

    public void showPersonDetails(Person person) {
        view.showPersonDetails(person);
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
}

