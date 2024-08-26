package Main.app;

import Main.presenter.FamilyTreePresenter;
import Main.service.FileFamilyTreeStorage;
import Main.view.ConsoleFamilyTreeView;

public class Main {
    public static void main(String[] args) {
        FamilyTreePresenter presenter = createPresenter();
        populateFamilyTree(presenter);
        displayFamilyTree(presenter);

        // Запуск интерфейса командной строки
        runCommandLineInterface(presenter, new ConsoleFamilyTreeView());
    }

    private static FamilyTreePresenter createPresenter() {
        ConsoleFamilyTreeView view = new ConsoleFamilyTreeView();
        return new FamilyTreePresenter(new FileFamilyTreeStorage(), view);
    }

    private static void populateFamilyTree(FamilyTreePresenter presenter) {
        // Прабабушки и прадедушки
        presenter.addPerson("John Sr.", "Doe", "1940-01-01", "Male");
        presenter.addPerson("Mary Sr.", "Doe", "1942-02-02", "Female");
        presenter.addPerson("James", "Smith", "1938-03-03", "Male");
        presenter.addPerson("Elizabeth", "Smith", "1941-04-04", "Female");

        // Бабушки и дедушки
        presenter.addPerson("John Jr.", "Doe", "1960-05-05", "Male");
        presenter.addPerson("Mary Jr.", "Doe", "1962-06-06", "Female");
        presenter.addPerson("Michael", "Smith", "1963-07-07", "Male");
        presenter.addPerson("Linda", "Smith", "1965-08-08", "Female");

        // Родители
        presenter.addPerson("John", "Doe", "1980-01-01", "Male");
        presenter.addPerson("Jane", "Doe", "1982-05-15", "Female");

        // Дети
        presenter.addPerson("Baby", "Doe", "2005-12-03", "Male");
        presenter.addPerson("Sister", "Doe", "2007-09-09", "Female");

        // Связи между поколениями
        presenter.addChild("John Jr.", "Doe", "John", "Doe");
        presenter.addChild("Mary Jr.", "Doe", "John", "Doe");
        presenter.addChild("Michael", "Smith", "Jane", "Doe");
        presenter.addChild("Linda", "Smith", "Jane", "Doe");

        presenter.addChild("John", "Doe", "Baby", "Doe");
        presenter.addChild("Jane", "Doe", "Baby", "Doe");
        presenter.addChild("John", "Doe", "Sister", "Doe");
        presenter.addChild("Jane", "Doe", "Sister", "Doe");
    }

    private static void displayFamilyTree(FamilyTreePresenter presenter) {
        System.out.println("\nFamily Tree sorted by name:");
        presenter.showTreeSortedByName();

        System.out.println("\nFamily Tree sorted by birth date:");
        presenter.showTreeSortedByBirthDate();
    }

    private static void runCommandLineInterface(FamilyTreePresenter presenter, ConsoleFamilyTreeView view) {
        CommandLineInterface cli = new CommandLineInterface(presenter, view);
        cli.start();
    }
}

