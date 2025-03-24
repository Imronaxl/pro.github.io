package ui;

import command.CommandProcessor;

import java.util.Scanner;

public class ConsoleUI {
    private CommandProcessor commandProcessor;

    public ConsoleUI(CommandProcessor commandProcessor) {
        this.commandProcessor = commandProcessor;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Добро пожаловать в программу управления коллекцией LabWork!");
        System.out.println("Введите 'help' для получения списка доступных команд");

        while (true) {
            System.out.print("> ");
            String commandLine = scanner.nextLine().trim();
            if (commandLine.isEmpty()) continue;

            try {
                commandProcessor.processCommand(commandLine);
            } catch (Exception e) {
                System.err.println("Ошибка выполнения команды: " + e.getMessage());
            }
        }
    }
}