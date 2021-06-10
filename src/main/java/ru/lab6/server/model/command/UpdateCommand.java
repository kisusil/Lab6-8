package ru.lab6.server.model.command;

import ru.lab6.common.parameters.LoginParameters;
import ru.lab6.common.parameters.Parameters;
import ru.lab6.common.parameters.UpdateParameters;
import ru.lab6.common.humanbeing.HumanBeing;
import ru.lab6.common.response.Response;
import ru.lab6.server.model.ApplicationContext;

public class UpdateCommand implements Command {
    private final ApplicationContext applicationContext;

    public UpdateCommand(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Response execute (Parameters parameters) {
        if (!(parameters instanceof UpdateParameters)) {
            throw new RuntimeException("Что-то пошло не так");
        }

        UpdateParameters updateParameters = (UpdateParameters) parameters;
        HumanBeing humanBeing = applicationContext.getRepository().get(updateParameters.id);

        LoginParameters loginParameters = new LoginParameters();
        loginParameters.login = updateParameters.login;
        loginParameters.password = updateParameters.password;
        Response response = applicationContext.getCommands().get("login").execute(loginParameters);

        if (!response.getStatus().equals("ok")) {
            return response;
        }

        if (humanBeing == null) {
            return new Response().setErrorResponse("Человека с таким id не существует", "");
        }


        if (!humanBeing.getUser().getLogin().equals(loginParameters.login)) {
            return new Response().setErrorResponse("Вы не можете изменять данный объект", "");
        }

        humanBeing.getCar().setName(updateParameters.carName);
        humanBeing.getCoordinates().setX(updateParameters.coordinateX);
        humanBeing.getCoordinates().setY(updateParameters.coordinateY);
        humanBeing.setHasToothpick(updateParameters.hasToothpick);
        humanBeing.setImpactSpeed(updateParameters.impactSpeed);
        humanBeing.setMinutesOfWaiting(updateParameters.minutesOfWaiting);
        humanBeing.setMood(updateParameters.mood);
        humanBeing.setWeaponType(updateParameters.weaponType);
        humanBeing.setName(updateParameters.name);
        humanBeing.setRealHero(updateParameters.realHero);

        applicationContext.getUserDao().update(humanBeing);

        return new Response().setEmptyResult();
    }
}
