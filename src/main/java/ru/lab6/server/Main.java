package ru.lab6.server;

import ru.lab6.common.humanbeing.HumanBeing;
import ru.lab6.server.controller.Controller;
import ru.lab6.server.controller.MyController;
import ru.lab6.server.io.Console;
import ru.lab6.server.io.IO;
import ru.lab6.server.model.ApplicationContext;
import ru.lab6.server.model.HumanBeingBuilder;
import ru.lab6.server.model.MyHumanBeingBuilder;
import ru.lab6.server.model.Repository;
import ru.lab6.server.model.command.*;


import java.util.*;

public class Main {
    public static void main (String[] args){
        IO io = new Console(System.in, System.out);
        String collectionFileName = System.getenv("fileName");

        if (collectionFileName == null) {
            io.println("Переменная окружения не задана");
            return;
        }

        CollectionSaver collectionSaver = new CollectionSaver(collectionFileName);
        CollectionLoader collectionLoader = new CollectionLoader(collectionFileName, io);
        Repository repository;
        try {
            repository = collectionLoader.load();
        } catch (CollectionLoaderException e) {
            io.println(e.getMessage());
            return;
        }

        io.println("Здравствуйте");
        io.println("Программа запущена");
        io.println("");

        List<HumanBeing> humanBeings = repository.getAll();

        int maxExistedId =
                humanBeings
                .stream()
                .mapToInt(HumanBeing::getId)
                .max()
                .orElse(0);

        HumanBeingBuilder humanBeingBuilder = new MyHumanBeingBuilder(maxExistedId + 1);

        ApplicationContext applicationContext = new ApplicationContext(humanBeingBuilder, repository, collectionSaver);
        Controller controller = new MyController(applicationContext);

        Map <String, Command> commands = new HashMap<>();
        commands.put("add", new AddCommand(applicationContext));
        commands.put("clear", new ClearCommand(applicationContext));
        commands.put("executeScript", new ExecuteScriptCommand(controller));
        commands.put("help", new HelpCommand());
        commands.put("info", new InfoCommand(applicationContext));
        commands.put("removeById", new RemoveByIdCommand(applicationContext));
        commands.put("removeLower", new RemoveLowerCommand(applicationContext));
        commands.put("save", new SaveCommand(applicationContext));
        commands.put("show", new ShowCommand(applicationContext));
        commands.put("update", new UpdateCommand(applicationContext));
        commands.put("addIfMax", new AddIfMaxCommand(applicationContext));
        commands.put("countByMood", new CountByMoodCommand(applicationContext));
        commands.put("filterGreaterThanMood", new FilterGreaterThanMoodCommand(applicationContext));
        commands.put("printAscending", new PrintAscendingCommand(applicationContext));

        applicationContext.setCommands(commands);

    }
}
