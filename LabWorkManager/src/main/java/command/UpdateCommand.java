package command;

import model.LabWork;
import repository.ILabWorkRepository;

/**
 * Класс UpdateCommand реализует команду update для обновления элемента коллекции по id.
 */
public class UpdateCommand implements ICommand {
    private ILabWorkRepository repository;
    private InputHelper inputHelper;

    public UpdateCommand(ILabWorkRepository repository, InputHelper inputHelper) {
        this.repository = repository;
        this.inputHelper = inputHelper;
    }

    @Override
    public void execute(String[] args) throws Exception {
        if (args.length < 1) {
            throw new IllegalArgumentException("Не указан id элемента для обновления");
        }

        long id = Long.parseLong(args[0]);
        model.LabWork labWork = inputHelper.createLabWork();
        repository.update(id, labWork);
        System.out.println("Элемент с id " + id + " обновлен");
    }

    @Override
    public String getDescription() {
        return "обновить значение элемента коллекции по его id";
    }
}