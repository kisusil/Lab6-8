package ru.lab6.server.model.command;

import ru.lab6.common.parameters.EmptyParameters;
import ru.lab6.common.parameters.LoginParameters;
import ru.lab6.common.parameters.Parameters;
import ru.lab6.common.humanbeing.HumanBeing;
import ru.lab6.common.response.Response;
import ru.lab6.server.model.ApplicationContext;

import java.util.List;

public class ShowCommand implements Command {
    private final ApplicationContext applicationContext;

    public ShowCommand(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Response execute (Parameters parameters) {
        if (!(parameters instanceof EmptyParameters)) {
            throw new RuntimeException("Что-то пошло не так");
        }

        EmptyParameters emptyParameters = (EmptyParameters) parameters;

        LoginParameters loginParameters = new LoginParameters();
        loginParameters.login = emptyParameters.login;
        loginParameters.password = emptyParameters.password;
        Response response = applicationContext.getCommands().get("login").execute(loginParameters);

        if (!response.getStatus().equals("ok")) {
            return response;
        }

//        List<HumanBeing> humanBeings = applicationContext.getRepository().getAll();
        List<HumanBeing> humanBeings = applicationContext.getHumanBeingDao().getAll();
        /*
        humanBeings.forEach(humanBeing -> {
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
        });*/
        System.out.println("Show size: " + humanBeings.size());
        return new Response().setResultWithCollectionElements(humanBeings);
    }
}
