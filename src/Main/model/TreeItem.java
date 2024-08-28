package Main.model;

import java.util.List;

public interface TreeItem {
    String getName(); // Метод для получения имени (например, имя человека или кличка собаки)
    List<? extends TreeItem> getChildren(); // Метод для получения детей
    void addChild(TreeItem child); // Метод для добавления ребенка
}
