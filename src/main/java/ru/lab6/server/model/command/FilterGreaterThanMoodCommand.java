package ru.lab6.server.model.command;

import ru.lab6.common.parameters.LoginParameters;
import ru.lab6.common.parameters.MoodParameters;
import ru.lab6.common.parameters.Parameters;
import ru.lab6.common.humanbeing.HumanBeing;
import ru.lab6.common.response.Response;
import ru.lab6.server.model.ApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FilterGreaterThanMoodCommand implements Command {
    private final ApplicationContext applicationContext;

    public FilterGreaterThanMoodCommand(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    private String listString(List<HumanBeing> list){
        StringBuilder result = new StringBuilder("quantity: " + list.size() + "\n");

        for (HumanBeing humanBeing : list) {
            result.append("id ").append(humanBeing.getId()).append("\n");
            result.append("name ").append(humanBeing.getName()).append("\n");
            result.append("coordinateX ").append(humanBeing.getCoordinates().getX()).append("\n");
            result.append("coordinateY ").append(humanBeing.getCoordinates().getY()).append("\n");
            result.append("creationDate ").append(humanBeing.getCreationDate().toString()).append("\n");
            result.append("realHero ").append(humanBeing.getRealHero()).append("\n");
            result.append("hasToothPick ").append(humanBeing.getHasToothPick()).append("\n");
            result.append("impactSpeed ").append(humanBeing.getImpactSpeed()).append("\n");
            result.append("minutesOfWaiting ").append(humanBeing.getMinutesOfWaiting()).append("\n");
            result.append("weaponType ").append(humanBeing.getWeaponType()).append("\n");
            result.append("mood ").append(humanBeing.getMood()).append("\n");
            result.append("carName ").append(humanBeing.getCar().getName()).append("\n");
            result.append("\n");
        }
        return result.toString();

    }

    @Override
    public Response execute(Parameters parameters) {
        if (!(parameters instanceof MoodParameters)) {
            throw new RuntimeException("Что-то пошло не так");
        }

        MoodParameters moodParameters = (MoodParameters) parameters;
        List<HumanBeing> humanBeings = applicationContext.getRepository().getAll();

        LoginParameters loginParameters = new LoginParameters();
        loginParameters.login = moodParameters.login;
        loginParameters.password = moodParameters.password;
        Response response = applicationContext.getCommands().get("login").execute(loginParameters);

        if (!response.getStatus().equals("ok")) {
            return response;
        }

        if (humanBeings.isEmpty()) {
            return new Response().setErrorResponse("пустая коллекция","");
        }

        List<HumanBeing> list =
                humanBeings
                        .stream()
                        .filter(humanBeing -> moodParameters.mood.number - humanBeing.getMood().number < 0)
                        .collect(Collectors.toList());

        if (list.size()!=0) {
            return new Response().setResultWithCollectionElements(list);
        }
        return new Response().setErrorResponse("ошибка", "");
    }
}
