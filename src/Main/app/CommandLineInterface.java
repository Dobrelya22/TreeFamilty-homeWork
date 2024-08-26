package Main.app;

import Main.presenter.FamilyTreePresenter;
import Main.view.ConsoleFamilyTreeView;

import java.util.Scanner;

public class CommandLineInterface {
    private final FamilyTreePresenter presenter;
    private final ConsoleFamilyTreeView view;

    public CommandLineInterface(FamilyTreePresenter presenter, ConsoleFamilyTreeView view) {
        this.presenter = presenter;
        this.view = view;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            view.showMessage("Enter a command (add, find, save, load, show, children, ancestors, siblings, exit):");
            String command = scanner.nextLine();

            if (command.equals("exit")) {
                break;
            }

            switch (command) {
                case "add":
                    addPerson(scanner);
                    break;
                case "find":
                    findPerson(scanner);
                    break;
                case "save":
                    saveTree(scanner);
                    break;
                case "load":
                    loadTree(scanner);
                    break;
                case "show":
                    presenter.showTreeSortedByName();
                    break;
                case "children":
                    showChildren(scanner);
                    break;
                case "ancestors":
                    showAncestors(scanner);
                    break;
                case "siblings":
                    showSiblings(scanner);
                    break;
                default:
                    view.showMessage("Unknown command.");
            }
        }
        scanner.close();
    }

    private void addPerson(Scanner scanner) {
        view.showMessage("Enter first name:");
        String firstName = scanner.nextLine();
        view.showMessage("Enter last name:");
        String lastName = scanner.nextLine();
        view.showMessage("Enter date of birth (YYYY-MM-DD):");
        String dateOfBirth = scanner.nextLine();
        view.showMessage("Enter gender:");
        String gender = scanner.nextLine();

        presenter.addPerson(firstName, lastName, dateOfBirth, gender);
    }

    private void findPerson(Scanner scanner) {
        view.showMessage("Enter first name:");
        String firstName = scanner.nextLine();
        view.showMessage("Enter last name:");
        String lastName = scanner.nextLine();
        presenter.showPersonDetails(presenter.findPersonByName(firstName, lastName));
    }

    private void showChildren(Scanner scanner) {
        view.showMessage("Enter first name:");
        String firstName = scanner.nextLine();
        view.showMessage("Enter last name:");
        String lastName = scanner.nextLine();
        presenter.showChildrenOfPerson(firstName, lastName);
    }

    private void showAncestors(Scanner scanner) {
        view.showMessage("Enter first name:");
        String firstName = scanner.nextLine();
        view.showMessage("Enter last name:");
        String lastName = scanner.nextLine();
        presenter.showAncestorsOfPerson(firstName, lastName);
    }

    private void showSiblings(Scanner scanner) {
        view.showMessage("Enter first name:");
        String firstName = scanner.nextLine();
        view.showMessage("Enter last name:");
        String lastName = scanner.nextLine();
        presenter.showSiblingsOfPerson(firstName, lastName);
    }

    private void saveTree(Scanner scanner) {
        view.showMessage("Enter filename to save:");
        String fileName = scanner.nextLine();
        try {
            presenter.saveTree(fileName);
            view.showMessage("Family tree saved to " + fileName);
        } catch (Exception e) {
            view.showMessage("Failed to save tree: " + e.getMessage());
        }
    }

    private void loadTree(Scanner scanner) {
        view.showMessage("Enter filename to load:");
        String fileName = scanner.nextLine();
        try {
            presenter.loadTree(fileName);
            view.showMessage("Family tree loaded from " + fileName);
        } catch (Exception e) {
            view.showMessage("Failed to load tree: " + e.getMessage());
        }
    }
}
