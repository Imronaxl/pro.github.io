package command;

import repository.ILabWorkRepository;
import model.LabWork;
import java.util.List;
import java.util.Stack;
import java.util.ArrayList;
import java.util.Comparator;
public class SortCommand implements ICommand {
    private ILabWorkRepository repository;

    public SortCommand(ILabWorkRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(String[] args) {
        Stack<LabWork> stack = repository.getAll();
        List<LabWork> list = new ArrayList<>(stack);
        list.sort(Comparator.naturalOrder());  // Сортировка по id (по умолчанию)
        stack.clear();
        stack.addAll(list);
        System.out.println("Коллекция отсортирована в естественном порядке");
    }

    @Override
    public String getDescription() {
        return "отсортировать коллекцию в естественном порядке";
    }
}