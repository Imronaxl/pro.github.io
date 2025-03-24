package command;

import repository.ILabWorkRepository;
import java.util.Date;

/**
 * Класс InfoCommand реализует команду info для вывода информации о коллекции.
 */
public class InfoCommand implements ICommand {
    private ILabWorkRepository repository;

    public InfoCommand(ILabWorkRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(String[] args) {
        System.out.println("Тип коллекции: " + repository.getClass().getName());
        System.out.println("Дата инициализации: " + new Date());
        System.out.println("Количество элементов: " + repository.getAll().size());
    }

    @Override
    public String getDescription() {
        return "вывести информацию о коллекции";
    }
}