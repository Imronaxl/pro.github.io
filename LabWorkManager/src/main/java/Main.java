import repository.LabWorkRepository;
import service.LabWorkService;
import command.CommandFactory;
import command.CommandProcessor;
import ui.ConsoleUI;

import java.io.File;
import java.io.IOException;

/**
 * Класс Main является точкой входа в программу.
 */
public class Main {
    public static void main(String[] args) {
        String fileName = System.getenv("LAB_WORK_FILE");
        if (fileName == null || fileName.isEmpty()) {
            System.err.println("Не задана переменная окружения LAB_WORK_FILE с именем файла");
            return;
        }
        
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
                System.out.println("Файл создан: " + file.getAbsolutePath());
            } catch (IOException e) {
                System.err.println("Ошибка создания файла: " + e.getMessage());
                return;
            }
        }
        
        LabWorkRepository repository = new LabWorkRepository(fileName);
        try {
            repository.loadFromFile();
//            System.out.println("Коллекция успешно загружена из файла");
        } catch (Exception e) {
            System.err.println("Ошибка загрузки коллекции из файла: " + e.getMessage());
        }
        
        CommandFactory commandFactory = new CommandFactory(repository);
        CommandProcessor commandProcessor = new CommandProcessor(repository, commandFactory);
        ConsoleUI consoleUI = new ConsoleUI(commandProcessor);
        
        consoleUI.start();
    }
}
