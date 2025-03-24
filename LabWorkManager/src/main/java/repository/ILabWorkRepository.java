package repository;

import model.LabWork;
import java.io.IOException;
import java.util.Stack;

/**
 * Интерфейс ILabWorkRepository определяет методы для работы с коллекцией LabWork.
 */
public interface ILabWorkRepository {
    Stack<LabWork> getAll();
    void add(LabWork labWork);
    void update(long id, LabWork labWork);
    void removeById(long id);
    void clear();
    void saveToFile() throws IOException;
    void loadFromFile() throws IOException;
}