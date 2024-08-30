package Main.commands;

import Main.presenter.FamilyTreePresenter;

public class ShowTreeCommand implements Command {
    private final FamilyTreePresenter presenter;

    public ShowTreeCommand(FamilyTreePresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void execute() {
        presenter.showTreeSortedByName();
    }
}
