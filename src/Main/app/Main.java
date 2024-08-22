package Main.app;

import Main.model.FamilyTree;
import Main.model.Person;
import Main.service.FamilyTreeStorage;
import Main.service.FileFamilyTreeStorage;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        CommandLineInterface cli = new CommandLineInterface();
        cli.start();

        FamilyTree<Person> familyTree = new FamilyTree<>();

        // Прабабушки и прадедушки
        Person grandpaJohnSr = new Person("John Sr.", "Doe", "1940-01-01", "Male");
        Person grandmaMarySr = new Person("Mary Sr.", "Doe", "1942-02-02", "Female");
        Person grandpaJames = new Person("James", "Smith", "1938-03-03", "Male");
        Person grandmaElizabeth = new Person("Elizabeth", "Smith", "1941-04-04", "Female");

        // Бабушки и дедушки
        Person grandpaJohnJr = new Person("John Jr.", "Doe", "1960-05-05", "Male");
        Person grandmaMaryJr = new Person("Mary Jr.", "Doe", "1962-06-06", "Female");
        Person grandpaMichael = new Person("Michael", "Smith", "1963-07-07", "Male");
        Person grandmaLinda = new Person("Linda", "Smith", "1965-08-08", "Female");

        // Родители
        Person john = new Person("John", "Doe", "1980-01-01", "Male");
        Person jane = new Person("Jane", "Doe", "1982-05-15", "Female");

        // Дети
        Person babyDoe = new Person("Baby", "Doe", "2005-12-03", "Male");
        Person sisterDoe = new Person("Sister", "Doe", "2007-09-09", "Female");

        // Связи между поколениями
        grandpaJohnJr.addChild(john); // Джон младший - сын Джона старшего и Мэри
        grandmaMaryJr.addChild(john);
        grandpaMichael.addChild(jane); // Джейн - дочь Майкла и Линды
        grandmaLinda.addChild(jane);

        john.addChild(babyDoe); // Джон и Джейн - родители Baby Doe
        jane.addChild(babyDoe);
        john.addChild(sisterDoe); // Джон и Джейн - родители Sister Doe
        jane.addChild(sisterDoe);

        // Добавляем всех в дерево
        familyTree.addPerson(grandpaJohnSr);
        familyTree.addPerson(grandmaMarySr);
        familyTree.addPerson(grandpaJames);
        familyTree.addPerson(grandmaElizabeth);
        familyTree.addPerson(grandpaJohnJr);
        familyTree.addPerson(grandmaMaryJr);
        familyTree.addPerson(grandpaMichael);
        familyTree.addPerson(grandmaLinda);
        familyTree.addPerson(john);
        familyTree.addPerson(jane);
        familyTree.addPerson(babyDoe);
        familyTree.addPerson(sisterDoe);

        // Вызов методов для сортировки и вывода людей
        System.out.println("\nFamily Tree sorted by name:");
        familyTree.printSortedByName();

        System.out.println("\nFamily Tree sorted by birth date:");
        familyTree.printSortedByBirthDate();

        // Демонстрация использования методов
        System.out.println("\nChildren of John Doe:");
        for (Person child : familyTree.getChildrenOfPerson("John", "Doe")) {
            System.out.println(child);
        }

        System.out.println("\nAncestors of Baby Doe:");
        for (Person ancestor : familyTree.getAncestorsOfPerson("Baby", "Doe")) {
            System.out.println(ancestor);
        }

        System.out.println("\nSiblings of Baby Doe:");
        for (Person sibling : familyTree.getSiblingsOfPerson("Baby", "Doe")) {
            System.out.println(sibling);
        }

        // Удаление Baby Doe из дерева
        familyTree.removePerson(babyDoe);
        System.out.println("\nFamily Tree after removing Baby Doe:");
        System.out.println(familyTree);

        // Использование методов getDateOfBirth() и getGender()
        System.out.println("\nDetails of John Doe:");
        System.out.println("Date of Birth: " + john.getDateOfBirth());
        System.out.println("Gender: " + john.getGender());

        System.out.println("\nDetails of Baby Doe:");
        System.out.println("Date of Birth: " + babyDoe.getDateOfBirth());
        System.out.println("Gender: " + babyDoe.getGender());

        // Сохранение дерева в файл и его загрузка
        FamilyTreeStorage storage = new FileFamilyTreeStorage();
        try {
            storage.save(familyTree, "family_tree.dat");

            // Загружаем дерево из файла
            FamilyTree<?> loadedTree = storage.load("family_tree.dat");
            if (loadedTree != null) {
                FamilyTree<Person> castedTree = (FamilyTree<Person>) loadedTree;

                System.out.println("\nLoaded Family Tree:");
                System.out.println(castedTree);
            }

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}
