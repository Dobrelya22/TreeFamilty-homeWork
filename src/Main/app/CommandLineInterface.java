package Main.app;

import Main.model.FamilyTree;
import Main.model.Person;
import Main.service.FamilyTreeStorage;
import Main.service.FileFamilyTreeStorage;

import java.io.IOException;
import java.util.Scanner;

public class CommandLineInterface {
    private FamilyTree<Person> familyTree;
    private final FamilyTreeStorage storage;

    public CommandLineInterface() {
        this.familyTree = new FamilyTree<>();
        this.storage = new FileFamilyTreeStorage();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter a command (add, find, save, load, show, exit):");
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
                    showTree();
                    break;
                default:
                    System.out.println("Unknown command.");
            }
        }
        scanner.close();
    }

    private void addPerson(Scanner scanner) {
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter date of birth (YYYY-MM-DD): ");
        String dateOfBirth = scanner.nextLine();
        System.out.print("Enter gender: ");
        String gender = scanner.nextLine();

        Person person = new Person(firstName, lastName, dateOfBirth, gender);
        familyTree.addPerson(person);
        System.out.println("Person added: " + person);
    }

    private void findPerson(Scanner scanner) {
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();

        Person person = familyTree.findPerson(firstName, lastName);
        if (person != null) {
            System.out.println("Person found: " + person);
        } else {
            System.out.println("Person not found.");
        }
    }

    private void saveTree(Scanner scanner) {
        System.out.print("Enter filename to save: ");
        String fileName = scanner.nextLine();
        try {
            storage.save(familyTree, fileName);
            System.out.println("Family tree saved to " + fileName);
        } catch (IOException e) {
            System.err.println("Failed to save tree: " + e.getMessage());
        }
    }

    private void loadTree(Scanner scanner) {
        System.out.print("Enter filename to load: ");
        String fileName = scanner.nextLine();
        try {
            familyTree = (FamilyTree<Person>) storage.load(fileName);
            System.out.println("Family tree loaded from " + fileName);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Failed to load tree: " + e.getMessage());
        }
    }

    private void showTree() {
        System.out.println("Family Tree:");
        for (Person person : familyTree) {
            System.out.println(person);
        }
    }
}
