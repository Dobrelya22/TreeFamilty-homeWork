package Main.commands;

import Main.presenter.FamilyTreePresenter;
import java.util.Scanner;

public class LoadTreeCommand implements Command {
    private final FamilyTreePresenter presenter;
    private final Scanner scanner;

    public LoadTreeCommand(FamilyTreePresenter presenter, Scanner scanner) {
        this.presenter = presenter;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.println("Enter filename to load:");
        String fileName = scanner.nextLine();
        try {
            presenter.loadTree(fileName);
            System.out.println("Family tree loaded from " + fileName);
        } catch (Exception e) {
            System.out.println("Failed to load tree: " + e.getMessage());
        }
    }
}
