package Main.commands;

import Main.presenter.FamilyTreePresenter;
import java.util.Scanner;

public class AddPersonCommand implements Command {
    private final FamilyTreePresenter presenter;
    private final Scanner scanner;

    public AddPersonCommand(FamilyTreePresenter presenter, Scanner scanner) {
        this.presenter = presenter;
        this.scanner = scanner;
    }

    @Override
    public void execute() {

        System.out.println("Enter first name:");
        String firstName = scanner.nextLine();
        System.out.println("Enter last name:");
        String lastName = scanner.nextLine();
        System.out.println("Enter date of birth (YYYY-MM-DD):");
        String dateOfBirth = scanner.nextLine();
        System.out.println("Enter gender:");
        String gender = scanner.nextLine();


        presenter.addPerson(firstName, lastName, dateOfBirth, gender);



    }
}
