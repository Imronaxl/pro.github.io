package command;

import repository.ILabWorkRepository;

/**
 * Класс ClearCommand реализует команду clear для очистки коллекции.
 */
public class ClearCommand implements ICommand {
    private ILabWorkRepository repository;

    public ClearCommand(ILabWorkRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(String[] args) {
        repository.clear();
        System.out.println("Коллекция очищена");
    }

    @Override
    public String getDescription() {
        return "очистить коллекцию";
    }
}