package Main.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Person implements Serializable, TreeItem {
    @Serial
    private static final long serialVersionUID = 1L;

    private final String firstName;
    private final String lastName;
    private final String dateOfBirth;
    private final String gender;
    private final List<TreeItem> children;

    public Person(String firstName, String lastName, String dateOfBirth, String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.children = new ArrayList<>();
    }

    @Override
    public String getName() {
        return getFirstName() + " " + getLastName(); // Используем методы getFirstName() и getLastName()
    }

    @Override
    public List<TreeItem> getChildren() {
        return children;
    }

    @Override
    public void addChild(TreeItem child) {
        children.add(child);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return getFirstName() + " " + getLastName() + " (" + gender + "), born: " + dateOfBirth;
    }
}



