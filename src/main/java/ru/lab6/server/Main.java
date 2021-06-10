package ru.lab6.server;

import ru.lab6.common.humanbeing.HumanBeing;
import ru.lab6.common.parameters.Parameters;
import ru.lab6.common.request.Request;
import ru.lab6.common.response.Response;
import ru.lab6.server.controller.Controller;
import ru.lab6.server.controller.MyController;
import ru.lab6.server.database.humanbeings.CollectionLoader;
import ru.lab6.server.database.humanbeings.CollectionLoaderException;
import ru.lab6.server.io.Console;
import ru.lab6.server.io.IO;
import ru.lab6.server.model.ApplicationContext;
import ru.lab6.server.model.collection.HumanBeingBuilder;
import ru.lab6.server.model.collection.MyHumanBeingBuilder;
import ru.lab6.server.model.collection.Repository;
import ru.lab6.server.database.users.UserDaoImpl;
import ru.lab6.server.model.command.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.*;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);
    public static void main (String[] args){
        IO io = new Console(System.in, System.out);
        String collectionFileName = System.getenv("fileName");

        if (collectionFileName == null) {
            logger.fatal("Переменная окружения не задана");
            io.println("Переменная окружения не задана");
            return;
        }

        CollectionLoader collectionLoader = new CollectionLoader(io);
        Repository repository;
        try {
            repository = collectionLoader.load();
        } catch (CollectionLoaderException e) {
            logger.fatal("CollectionLoaderException");
            io.println(e.getMessage());

            return;
        }

        io.println("Здравствуйте");
        io.println("Программа запущена");
        io.println("");

        List<HumanBeing> humanBeings = repository.getAll();
        logger.info("Коллекция загружена.");

        int maxExistedId =
                humanBeings
                .stream()
                .mapToInt(HumanBeing::getId)
                .max()
                .orElse(0);

        HumanBeingBuilder humanBeingBuilder = new MyHumanBeingBuilder(maxExistedId + 1);


        ApplicationContext applicationContext = new ApplicationContext(humanBeingBuilder, repository, new UserDaoImpl());
        Controller controller = new MyController(applicationContext);

        Map <String, Command> commands = new HashMap<>();
        commands.put("add", new AddCommand(applicationContext));
        commands.put("clear", new ClearCommand(applicationContext));
        commands.put("execute_script", new ExecuteScriptCommand(controller, applicationContext));
        commands.put("help", new HelpCommand(applicationContext));
        commands.put("info", new InfoCommand(applicationContext));
        commands.put("remove_by_id", new RemoveByIdCommand(applicationContext));
        commands.put("remove_lower", new RemoveLowerCommand(applicationContext));
        commands.put("save", new SaveCommand(applicationContext));
        commands.put("show", new ShowCommand(applicationContext));
        commands.put("update", new UpdateCommand(applicationContext));
        commands.put("add_if_max", new AddIfMaxCommand(applicationContext));
        commands.put("count_by_mood", new CountByMoodCommand(applicationContext));
        commands.put("filter_greater_than_mood", new FilterGreaterThanMoodCommand(applicationContext));
        commands.put("print_ascending", new PrintAscendingCommand(applicationContext));
        commands.put("login", new LoginCommand(applicationContext));
        commands.put("register", new RegisterCommand(applicationContext));
        logger.info("Комманды инициализированы.");

        applicationContext.setCommands(commands);

        ParserRequest parserRequest = new ParserRequest(applicationContext);

        Server server = new Server(Integer.parseInt(args[0]));
        logger.info("Сервер запущен.");

        while (true) {
            server.acceptNewClient();
            Request request = server.receiveRequest();
            Command command = parserRequest.parseCommand(request);
            Parameters parameters = request.getParameters();
            Response response = command.execute(parameters);
            logger.info(request.getCommandName());
            server.sendResponse(response);
            server.closeConnection();
        }
    }
}
