package Main.commands;

import Main.presenter.FamilyTreePresenter;
import java.util.Scanner;

public class SaveTreeCommand implements Command {
    private final FamilyTreePresenter presenter;
    private final Scanner scanner;

    public SaveTreeCommand(FamilyTreePresenter presenter, Scanner scanner) {
        this.presenter = presenter;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.println("Enter filename to save:");
        String fileName = scanner.nextLine();
        try {
            presenter.saveTree(fileName);
            System.out.println("Family tree saved to " + fileName);
        } catch (Exception e) {
            System.out.println("Failed to save tree: " + e.getMessage());
        }
    }
}
