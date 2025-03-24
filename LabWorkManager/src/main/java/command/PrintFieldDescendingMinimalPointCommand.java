package command;

import repository.ILabWorkRepository;
import model.LabWork;
import java.util.List;
import java.util.Comparator;
public class PrintFieldDescendingMinimalPointCommand implements ICommand {
    private ILabWorkRepository repository;

    public PrintFieldDescendingMinimalPointCommand(ILabWorkRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute(String[] args) {
        List<Float> points = repository.getAll().stream()
                .map(LabWork::getMinimalPoint)
                .sorted(Comparator.reverseOrder())
                .toList();
        points.forEach(System.out::println);
    }

    @Override
    public String getDescription() {
        return "вывести значения поля minimalPoint всех элементов в порядке убывания";
    }
}