package service;

import model.LabWork;

import java.util.List;

/**
 * Интерфейс ILabWorkService определяет методы для выполнения команд над коллекцией LabWork.
 */
public interface ILabWorkService {
    void executeCommand(String commandName, String... args) throws Exception;
}