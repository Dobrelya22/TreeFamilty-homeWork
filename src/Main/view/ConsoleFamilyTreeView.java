package Main.view;

import Main.presenter.FamilyTreePresenter;
import Main.commands.Command;
import Main.commands.CommandFactory;
import Main.model.Person;
import java.util.Scanner;
import java.util.List;

public class ConsoleFamilyTreeView implements FamilyTreeView {
    private final CommandFactory commandFactory;

    public ConsoleFamilyTreeView(FamilyTreePresenter presenter) {
        Scanner scanner = new Scanner(System.in);
        this.commandFactory = new CommandFactory(presenter, scanner);
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            showMessage("Enter a command (add, save, load, find, show, children, ancestors, siblings, exit):");

            String commandInput = scanner.nextLine();

            if (commandInput.equals("exit")) {
                break;
            }

            Command command = commandFactory.getCommand(commandInput);
            if (command != null) {
                command.execute();
            } else {
                showMessage("Unknown command.");
            }
        }
    }

    @Override
    public void showTree(List<Person> people) {
        for (Person person : people) {
            System.out.println(person);
        }
    }

    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void showPersonDetails(Person person) {
        if (person != null) {
            System.out.println("Date of Birth: " + person.getDateOfBirth());
            System.out.println("Gender: " + person.getGender());
        }
    }
}


