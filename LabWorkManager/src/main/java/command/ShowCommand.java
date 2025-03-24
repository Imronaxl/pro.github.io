package command;

import repository.ILabWorkRepository;

/**
 * Класс ShowCommand реализует команду show для вывода всех элементов коллекции.
 */
public class ShowCommand implements ICommand {
    private ILabWorkRepository repository;

    public ShowCommand(ILabWorkRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(String[] args) {
        for (model.LabWork labWork : repository.getAll()) {
            System.out.println(labWork);
        }
    }

    @Override
    public String getDescription() {
        return "вывести все элементы коллекции";
    }
}