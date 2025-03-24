package command;

import repository.ILabWorkRepository;
import java.io.BufferedReader;
import java.io.FileReader;

public class ExecuteScriptCommand implements ICommand {
    private ILabWorkRepository repository;
    private CommandProcessor commandProcessor;

    public ExecuteScriptCommand(ILabWorkRepository repository, CommandProcessor commandProcessor) {
        this.repository = repository;
        this.commandProcessor = commandProcessor;
    }

    @Override
    public void execute(String[] args) throws Exception {
        if (args.length < 1) {
            throw new IllegalArgumentException("Не указано имя файла для выполнения скрипта");
        }
        String fileName = args[0];
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains("execute_script")) {
                    System.out.println("Использованно запрещенная команда в файле: " + line);
                    return;
                 }
                commandProcessor.processCommand(line);
            }
        }
        System.out.println("Команды из файла " + fileName + " выполнены");
    }

    @Override
    public String getDescription() {
        return "считать и исполнить скрипт из указанного файла";
    }
}
