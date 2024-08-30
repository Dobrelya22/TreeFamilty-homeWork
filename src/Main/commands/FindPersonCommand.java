package Main.commands;

import Main.presenter.FamilyTreePresenter;
import Main.model.Person;
import java.util.Scanner;

public class FindPersonCommand implements Command {
    private final FamilyTreePresenter presenter;
    private final Scanner scanner;

    public FindPersonCommand(FamilyTreePresenter presenter, Scanner scanner) {
        this.presenter = presenter;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.println("Enter first name:");
        String firstName = scanner.nextLine();
        System.out.println("Enter last name:");
        String lastName = scanner.nextLine();


        Person person = presenter.findPersonByName(firstName, lastName);


        presenter.showPersonDetails(person);
    }
}
