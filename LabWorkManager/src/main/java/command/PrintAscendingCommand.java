package command;

import repository.ILabWorkRepository;
import model.LabWork;
import java.util.*;

public class PrintAscendingCommand implements ICommand {
    private ILabWorkRepository repository;

    public PrintAscendingCommand(ILabWorkRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(String[] args) {
        Stack<LabWork> stack = repository.getAll();
        List<LabWork> list = new ArrayList<>(stack);
        list.sort(Comparator.naturalOrder());  // Сортировка по id (по умолчанию)
        list.forEach(System.out::println);
    }

    @Override
    public String getDescription() {
        return "вывести элементы коллекции в порядке возрастания";
    }
}