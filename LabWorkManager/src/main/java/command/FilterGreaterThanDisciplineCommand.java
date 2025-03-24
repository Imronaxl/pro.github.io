package command;

import model.Discipline;
import model.LabWork;
import repository.ILabWorkRepository;
import java.util.InputMismatchException;
import java.util.Scanner;

public class FilterGreaterThanDisciplineCommand implements ICommand {
    private ILabWorkRepository repository;
    private InputHelper inputHelper;

    public FilterGreaterThanDisciplineCommand(ILabWorkRepository repository, InputHelper inputHelper) {
        this.repository = repository;
        this.inputHelper = inputHelper;
    }

    @Override
    public void execute(String[] args) throws Exception {
        Discipline discipline = inputHelper.readDiscipline();
        for (LabWork labWork : repository.getAll()) {
            if (labWork.getDiscipline() != null && labWork.getDiscipline().compareTo(discipline) > 0) {
                System.out.println(labWork);
            }
        }
    }

    @Override
    public String getDescription() {
        return "вывести элементы, значение поля discipline которых больше заданного";
    }
}