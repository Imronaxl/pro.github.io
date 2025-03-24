package service;

import command.CommandFactory;
import command.CommandProcessor;
import model.LabWork;
import repository.ILabWorkRepository;

import java.io.IOException;

/**
 * Класс LabWorkService реализует интерфейс ILabWorkService для выполнения команд над коллекцией LabWork.
 */
public class LabWorkService implements ILabWorkService {
    private ILabWorkRepository repository;
    private CommandFactory commandFactory;
    private CommandProcessor commandProcessor;

    public LabWorkService(ILabWorkRepository repository) {
        this.repository = repository;
        this.commandFactory = new CommandFactory(repository);
        this.commandProcessor = new CommandProcessor(repository, commandFactory);
    }

    @Override
    public void executeCommand(String commandName, String... args) throws Exception {
        if (commandName.contains("execute_script")) {
            System.out.println("Использованно запрещенная команда execute_script");
            return;
        }
        commandProcessor.processCommand(commandName);
    }
}