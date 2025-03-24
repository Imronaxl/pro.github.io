package command;

import repository.ILabWorkRepository;

import java.io.IOException;

/**
 * Класс SaveCommand реализует команду save для сохранения коллекции в файл.
 */
public class SaveCommand implements ICommand {
    private ILabWorkRepository repository;

    public SaveCommand(ILabWorkRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(String[] args) throws IOException {
        repository.saveToFile();
        System.out.println("Коллекция сохранена в файл");
    }

    @Override
    public String getDescription() {
        return "сохранить коллекцию в файл";
    }
}