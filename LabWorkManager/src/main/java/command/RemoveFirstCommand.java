package command;

import repository.ILabWorkRepository;

/**
 * Класс RemoveFirstCommand реализует команду remove_first для удаления первого элемента коллекции.
 */
public class RemoveFirstCommand implements ICommand {
    private ILabWorkRepository repository;

    public RemoveFirstCommand(ILabWorkRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(String[] args) {
        if (!repository.getAll().isEmpty()) {
            repository.getAll().pop();
            System.out.println("Первый элемент коллекции удален");
        } else {
            System.out.println("Коллекция пуста");
        }
    }

    @Override
    public String getDescription() {
        return "удалить первый элемент из коллекции";
    }
}