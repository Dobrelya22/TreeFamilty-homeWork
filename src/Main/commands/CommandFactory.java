package Main.commands;

import Main.presenter.FamilyTreePresenter;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CommandFactory {
    private final Map<String, Command> commands = new HashMap<>();

    public CommandFactory(FamilyTreePresenter presenter, Scanner scanner) {
        // Регистрация всех команд
        commands.put("add", new AddPersonCommand(presenter, scanner));
        commands.put("save", new SaveTreeCommand(presenter, scanner));
        commands.put("load", new LoadTreeCommand(presenter, scanner));
        commands.put("children", new ShowChildrenCommand(presenter, scanner));
        commands.put("ancestors", new ShowAncestorsCommand(presenter, scanner));
        commands.put("siblings", new ShowSiblingsCommand(presenter, scanner));
        commands.put("show", new ShowTreeCommand(presenter));
        commands.put("find", new FindPersonCommand(presenter, scanner));


    }

    public Command getCommand(String command) {
        return commands.get(command);
    }
}
