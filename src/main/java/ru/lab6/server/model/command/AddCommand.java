package ru.lab6.server.model.command;

import ru.lab6.common.parameters.CreationParameters;
import ru.lab6.common.parameters.LoginParameters;
import ru.lab6.common.parameters.Parameters;
import ru.lab6.common.humanbeing.HumanBeing;
import ru.lab6.common.response.Response;
import ru.lab6.common.user.User;
import ru.lab6.server.model.ApplicationContext;

public class AddCommand implements Command {
    private final ApplicationContext applicationContext;

    public AddCommand(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }


    @Override
    public Response execute (Parameters parameters) {
        if (!(parameters instanceof CreationParameters)) {
            return new Response().setErrorResponse("ошибка параметров команды", "");
        } else {
            CreationParameters creationParameters = (CreationParameters) parameters;

            LoginParameters loginParameters = new LoginParameters();
            loginParameters.login = creationParameters.login;
            loginParameters.password = creationParameters.password;
            Response response = applicationContext.getCommands().get("login").execute(loginParameters);

            if (!response.getStatus().equals("ok")) {
                return response;
            }

            User user = applicationContext.getUserDao().get(creationParameters.login);
            HumanBeing humanBeing =
                    applicationContext
                            .getHumanBeingBuilder()
                            .setName(creationParameters.name)
                            .setCar(creationParameters.car)
                            .setCoordinates(creationParameters.coordinates)
                            .setHasToothPick(creationParameters.hasToothpick)
                            .setImpactSpeed(creationParameters.impactSpeed)
                            .setMinutesOfWaiting(creationParameters.minutesOfWaiting)
                            .setMood(creationParameters.mood)
                            .setRealHero(creationParameters.realHero)
                            .setWeaponType(creationParameters.weaponType)
                            .setUser(user)
                            .build();

            applicationContext.getHumanBeingDao().save(humanBeing);

            humanBeing = applicationContext.getHumanBeingDao().get(humanBeing.getName());

            applicationContext.getRepository().add(humanBeing);

            return new Response().setEmptyResult();
        }
    }
}
