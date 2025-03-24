package command;

import model.LabWork;
import repository.ILabWorkRepository;

/**
 * Класс AddCommand реализует команду add для добавления нового элемента в коллекцию.
 */
public class AddCommand implements ICommand {
    private ILabWorkRepository repository;
    private InputHelper inputHelper;

    public AddCommand(ILabWorkRepository repository, InputHelper inputHelper) {
        this.repository = repository;
        this.inputHelper = inputHelper;
    }

    @Override
    public void execute(String[] args) throws Exception {
        model.LabWork labWork = inputHelper.createLabWork();
        repository.add(labWork);
        System.out.println("Элемент добавлен в коллекцию");
    }

    @Override
    public String getDescription() {
        return "добавить новый элемент в коллекцию";
    }
}