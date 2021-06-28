package ru.lab6.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.lab6.common.parameters.Parameters;
import ru.lab6.common.request.Request;
import ru.lab6.common.response.Response;
import ru.lab6.server.controller.Controller;
import ru.lab6.server.controller.MyController;
import ru.lab6.server.database.humanbeings.HumanBeingDaoImpl;
import ru.lab6.server.database.users.JdbcUserDao;
import ru.lab6.server.io.Console;
import ru.lab6.server.io.IO;
import ru.lab6.server.model.ApplicationContext;
import ru.lab6.server.model.collection.*;
import ru.lab6.server.model.command.*;

import java.net.Socket;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);
    static final int numOfThreads = Runtime.getRuntime().availableProcessors();
    public static void main (String[] args){
        IO io = new Console(System.in, System.out);

        io.println("Здравствуйте");
        io.println("Программа запущена");
        io.println("");

        Repository repository = new HumanBeingRepository();
        HumanBeingBuilder humanBeingBuilder = new MyHumanBeingBuilder(1);
        ApplicationContext applicationContext = new ApplicationContext(humanBeingBuilder, repository, new JdbcUserDao(), new HumanBeingDaoImpl());
        repository.read(applicationContext.getHumanBeingDao().getAll());
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

        ForkJoinPool forkJoinPool = new ForkJoinPool(numOfThreads);
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(numOfThreads);

        while (true) {
            Socket socket = server.acceptNewClient();
            forkJoinPool.execute(() -> {
                Request request = server.receiveRequest(socket);

                fixedThreadPool.execute(() -> {
                    Command command = parserRequest.parseCommand(request);
                    Parameters parameters = request.getParameters();
                    logger.info(request.getCommandName());
                    Response response = command.execute(parameters);

                    fixedThreadPool.execute(() -> {
                        server.sendResponse(response, socket);
                        server.closeConnection(socket);
                    });
                });
            });
        }
    }
}
