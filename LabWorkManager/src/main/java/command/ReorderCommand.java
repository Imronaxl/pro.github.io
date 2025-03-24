package command;

import repository.ILabWorkRepository;
import model.LabWork;
import java.util.*;
import java.util.stream.Collectors;

public class ReorderCommand implements ICommand {
    private ILabWorkRepository repository;

    public ReorderCommand(ILabWorkRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(String[] args) {
        Stack<LabWork> stack = repository.getAll();
        List<LabWork> list = new ArrayList<>(stack);
        list.sort(Comparator.reverseOrder());  // Обратная сортировка по id
        stack.clear();
        stack.addAll(list);
        System.out.println("Коллекция отсортирована в обратном порядке");
    }

    @Override
    public String getDescription() {
        return "отсортировать коллекцию в порядке, обратном нынешнему";
    }
}