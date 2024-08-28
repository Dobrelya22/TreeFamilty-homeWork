package Main.presenter;

import Main.model.Person;
import Main.service.FamilyTreeService;
import Main.view.FamilyTreeView;

import java.io.IOException;
import java.util.List;

public class FamilyTreePresenter {
    private final FamilyTreeService service;
    private final FamilyTreeView view;

    public FamilyTreePresenter(FamilyTreeService service, FamilyTreeView view) {
        this.service = service;
        this.view = view;
    }

    public void addPerson(String firstName, String lastName, String dateOfBirth, String gender) {
        service.addPerson(firstName, lastName, dateOfBirth, gender);
        view.showMessage("Person added successfully.");
    }

    public void addChild(String parentFirstName, String parentLastName, String childFirstName, String childLastName) {
        service.addChild(parentFirstName, parentLastName, childFirstName, childLastName);
        view.showMessage("Child added successfully.");
    }

    public void showTreeSortedByName() {
        service.sortByName();
        view.showTree(service.getAllPeople());
    }

    public void showTreeSortedByBirthDate() {
        service.sortByBirthDate();
        view.showTree(service.getAllPeople());
    }

    public void showChildrenOfPerson(String firstName, String lastName) {
        List<Person> children = service.getChildrenOfPerson(firstName, lastName);
        view.showTree(children);
    }

    public void showAncestorsOfPerson(String firstName, String lastName) {
        List<Person> ancestors = service.getAncestorsOfPerson(firstName, lastName);
        view.showTree(ancestors);
    }

    public void showSiblingsOfPerson(String firstName, String lastName) {
        List<Person> siblings = service.getSiblingsOfPerson(firstName, lastName);
        view.showTree(siblings);
    }

    public Person findPersonByName(String firstName, String lastName) {
        return service.findPerson(firstName, lastName);
    }

    public void showPersonDetails(Person person) {
        view.showPersonDetails(person);
    }

    public void saveTree(String fileName) throws IOException {
        service.saveTree(fileName);
    }

    public void loadTree(String fileName) throws IOException, ClassNotFoundException {
        service.loadTree(fileName);
    }
}


