package ru.lab6.server.model.command;

import ru.lab6.common.parameters.Parameters;
import ru.lab6.common.parameters.UpdateParameters;
import ru.lab6.common.humanbeing.HumanBeing;
import ru.lab6.server.model.ApplicationContext;

public class UpdateCommand implements Command {
    private final ApplicationContext applicationContext;

    public UpdateCommand(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public String execute (Parameters parameters) {
        if (!(parameters instanceof UpdateParameters)) {
            throw new RuntimeException("Что-то пошло не так");
        }

        UpdateParameters updateParameters = (UpdateParameters) parameters;
        HumanBeing humanBeing = applicationContext.getRepository().get(updateParameters.id);

        if (humanBeing == null) {
            return "Человека с таким id нет";
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

        return "Объект успешно изменен";
    }


}
