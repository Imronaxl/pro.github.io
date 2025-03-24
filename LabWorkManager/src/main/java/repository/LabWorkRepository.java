package repository;

import com.google.gson.JsonSyntaxException;
import model.LabWork;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Класс LabWorkRepository реализует интерфейс ILabWorkRepository для работы с коллекцией LabWork.
 * Использует Gson для сериализации и десериализации данных в JSON.
 */
public class LabWorkRepository implements ILabWorkRepository {
    private Stack<LabWork> stack = new Stack<>();
    private String fileName;

    public LabWorkRepository(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public Stack<LabWork> getAll() {
        return stack;
    }

    @Override
    public void add(LabWork labWork) {
        stack.push(labWork);
    }

    @Override
    public void update(long id, LabWork labWork) {
        Stack<LabWork> tempStack = new Stack<>();
        boolean updated = false;

        while (!stack.isEmpty()) {
            LabWork current = stack.pop();
            if (current.getId() == id) {
                tempStack.push(labWork);
                updated = true;
            } else {
                tempStack.push(current);
            }
        }

        stack = tempStack;
        if (!updated) {
            throw new IllegalArgumentException("Элемент с id " + id + " не найден");
        }
    }

    @Override
    public void removeById(long id) {
        Stack<LabWork> tempStack = new Stack<>();
        boolean removed = false;

        while (!stack.isEmpty()) {
            LabWork current = stack.pop();
            if (current.getId() != id) {
                tempStack.push(current);
            } else {
                removed = true;
            }
        }

        stack = tempStack;
        if (!removed) {
            throw new IllegalArgumentException("Элемент с id " + id + " не найден");
        }
    }

    @Override
    public void clear() {
        stack.clear();
    }

    @Override
    public void saveToFile() throws IOException {
        Gson gson = new Gson();
        List<LabWork> list = new ArrayList<>(stack);
        String json = gson.toJson(list);

        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(json);
        }
    }

    @Override
    public void loadFromFile() {
        try {
            Gson gson = new Gson();
            Type listType = new TypeToken<List<LabWork>>() {
            }.getType();

            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                String json = reader.lines().reduce("", (a, b) -> a + b);
                List<LabWork> list = gson.fromJson(json, listType);

                if (list != null) {
                    stack.clear();
                    stack.addAll(list);
                }
            }
        } catch (JsonSyntaxException e) {
            System.err.println("Некорректный формат JSON в файле: " + fileName);
        } catch (FileNotFoundException e) {
            System.err.println("Файл не найден: " + fileName);
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        }

    }
}