package Main.commands;

import Main.presenter.FamilyTreePresenter;
import java.util.Scanner;

public class ShowSiblingsCommand implements Command {
    private final FamilyTreePresenter presenter;
    private final Scanner scanner;

    public ShowSiblingsCommand(FamilyTreePresenter presenter, Scanner scanner) {
        this.presenter = presenter;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.println("Enter first name:");
        String firstName = scanner.nextLine();
        System.out.println("Enter last name:");
        String lastName = scanner.nextLine();
        presenter.showSiblingsOfPerson(firstName, lastName);
    }
}
