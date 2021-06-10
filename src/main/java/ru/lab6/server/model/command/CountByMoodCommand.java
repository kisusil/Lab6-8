package ru.lab6.server.model.command;

import ru.lab6.common.parameters.CreationParameters;
import ru.lab6.common.parameters.LoginParameters;
import ru.lab6.common.parameters.MoodParameters;
import ru.lab6.common.parameters.Parameters;
import ru.lab6.common.humanbeing.HumanBeing;
import ru.lab6.common.response.Response;
import ru.lab6.server.model.ApplicationContext;

import java.awt.geom.RectangularShape;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CountByMoodCommand implements Command {
    private final ApplicationContext applicationContext;

    public CountByMoodCommand(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Response execute (Parameters parameters) {
        if (!(parameters instanceof MoodParameters)) {
            return new Response().setErrorResponse("ошибка параметров команды", "");
        } else {

            MoodParameters moodParameters = (MoodParameters) parameters;
            List<HumanBeing> humanBeings = applicationContext.getRepository().getAll();

            LoginParameters loginParameters = new LoginParameters();
            loginParameters.login = moodParameters.login;
            loginParameters.password = moodParameters.password;
            Response response = applicationContext.getCommands().get("login").execute(loginParameters);

            if (!response.getStatus().equals("ok")) {
                return response;
            }

            long countByMood =
                    humanBeings
                            .stream()
                            .filter(humanBeing -> moodParameters.mood == humanBeing.getMood())
                            .count();

            return new Response().setStringResult(Long.toString(countByMood));
        }
    }

}
