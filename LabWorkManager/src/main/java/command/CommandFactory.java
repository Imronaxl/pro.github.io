package command;
import repository.ILabWorkRepository;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private ILabWorkRepository repository;
    private Map<String, ICommand> commands = new HashMap<>();

    public CommandFactory(ILabWorkRepository repository) {
        this.repository = repository;

        // Регистрация всех команд
        commands.put("help", new HelpCommand(commands));
        commands.put("info", new InfoCommand(repository));
        commands.put("show", new ShowCommand(repository));
        commands.put("add", new AddCommand(repository, new InputHelper()));
        commands.put("update", new UpdateCommand(repository, new InputHelper()));
        commands.put("remove_by_id", new RemoveByIdCommand(repository));
        commands.put("clear", new ClearCommand(repository));
        commands.put("save", new SaveCommand(repository));
        commands.put("execute_script", new ExecuteScriptCommand(repository, new CommandProcessor(repository, this)));
        commands.put("remove_first", new RemoveFirstCommand(repository));
        commands.put("reorder", new ReorderCommand(repository));
        commands.put("sort", new SortCommand(repository));
        commands.put("filter_greater_than_discipline", new FilterGreaterThanDisciplineCommand(repository, new InputHelper()));
        commands.put("print_ascending", new PrintAscendingCommand(repository));
        commands.put("print_field_descending_minimal_point", new PrintFieldDescendingMinimalPointCommand(repository));
    }

    public ICommand createCommand(String commandName) {
        return commands.get(commandName);
    }
}