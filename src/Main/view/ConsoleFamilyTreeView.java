package Main.view;

import Main.model.Person;
import java.util.List;

public class ConsoleFamilyTreeView implements FamilyTreeView {
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
