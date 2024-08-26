package Main.view;

import Main.model.Person;
import java.util.List;

public interface FamilyTreeView {
    void showTree(List<Person> people);
    void showMessage(String message);
    void showPersonDetails(Person person);
}
