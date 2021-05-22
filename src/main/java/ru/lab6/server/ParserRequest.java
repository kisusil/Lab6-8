package ru.lab6.server;

import com.google.gson.Gson;
import ru.lab6.common.parameters.*;
import ru.lab6.common.request.Request;
import ru.lab6.server.model.ApplicationContext;
import ru.lab6.server.model.command.Command;

import java.util.HashMap;
import java.util.Map;

public class ParserRequest {
    private final static Map<String, Class<? extends Parameters>> PARAMETERS_CLASS_MAP = new HashMap<String, Class<? extends Parameters>>() {{
        put("add", CreationParameters.class);
        put("add_if_max", CreationParameters.class);
        put("remove_lower", CreationParameters.class);
        put("update", UpdateParameters.class);
        put("execute_script", ExecuteScriptParameters.class);
        put("remove_by_id", IdParameters.class);
        put("show", EmptyParameters.class);
        put("save", EmptyParameters.class);
        put("print_ascending", EmptyParameters.class);
        put("info", EmptyParameters.class);
        put("help", EmptyParameters.class);
        put("clear", EmptyParameters.class);
        put("filter_greater_than_mood", MoodParameters.class);
        put("count_by_mood", MoodParameters.class);
    }};

    private final ApplicationContext applicationContext;

    public ParserRequest(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public Parameters parseParameters(Request request) {
        return new Gson().fromJson(request.getParameters(), PARAMETERS_CLASS_MAP.get(request.getCommandName()));
    }

    public Command parseCommand(Request request) {
       return applicationContext.getCommands().get(request.getCommandName());
    }
}
