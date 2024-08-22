package Main.service;

import Main.model.FamilyTree;

import java.io.IOException;

public interface FamilyTreeStorage {
    void save(FamilyTree<?> familyTree, String fileName) throws IOException;
    FamilyTree<?> load(String fileName) throws IOException, ClassNotFoundException;
}
