package command;

import repository.ILabWorkRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class    CommandProcessor {
    private ILabWorkRepository repository;
    private CommandFactory commandFactory;

    public CommandProcessor(ILabWorkRepository repository, CommandFactory commandFactory) {
        this.repository = repository;
        this.commandFactory = commandFactory;
    }

    public void processCommand(String commandLine) throws Exception {
        String[] parts = commandLine.trim().split("\\s+", 2);
        String commandName = parts[0];
        String[] args = parts.length > 1 ? parts[1].split("\\s+") : new String[0];

        if (commandName.equals("exit")) {
            System.out.println("Программа завершена");
            System.exit(0);
        } else if (commandName.equals("execute_script")) {
            if (args.length < 1) {
                throw new IllegalArgumentException("Не указано имя файла для выполнения скрипта");
            }
            executeScript(args[0]);
        } else {
            ICommand command = commandFactory.createCommand(commandName);
            if (command != null) {
                command.execute(args);
            } else {
                throw new IllegalArgumentException("Неизвестная команда: " + commandName);
            }
        }
    }

    private void executeScript(String fileName) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    if (line.contains("execute_script")) {
                        System.out.println("Использованно запрещенная команда execute_script");
                        return;
                    }
                    System.out.println("Выполнение команды из файла: " + line);
                    processCommand(line);
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла скрипта: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            System.err.println("Ошибка выполнения команды из файла: " + e.getMessage());
            throw e;
        }
        System.out.println("Команды из файла " + fileName + " выполнены успешно.");
    }
}