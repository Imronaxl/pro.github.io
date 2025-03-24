package command;

import repository.ILabWorkRepository;

/**
 * Класс RemoveByIdCommand реализует команду remove_by_id для удаления элемента коллекции по id.
 */
public class RemoveByIdCommand implements ICommand {
    private ILabWorkRepository repository;

    public RemoveByIdCommand(ILabWorkRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(String[] args) throws Exception {
        if (args.length < 1) {
            throw new IllegalArgumentException("Не указан id элемента для удаления");
        }

        long id = Long.parseLong(args[0]);
        repository.removeById(id);
        System.out.println("Элемент с id " + id + " удален");
    }

    @Override
    public String getDescription() {
        return "удалить элемент из коллекции по его id";
    }
}