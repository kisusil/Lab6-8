package ru.lab6.server.model.command;

import ru.lab6.common.parameters.CreationParameters;
import ru.lab6.common.parameters.Parameters;
import ru.lab6.common.humanbeing.HumanBeing;
import ru.lab6.server.model.ApplicationContext;

public class AddCommand implements Command {
    private final ApplicationContext applicationContext;

    public AddCommand(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public String execute (Parameters parameters) {
        if (!(parameters instanceof CreationParameters)) {
            throw new RuntimeException("Что-то пошло не так");
        }

        CreationParameters creationParameters = (CreationParameters) parameters;

        HumanBeing humanBeing =
                applicationContext
                        .getHumanBeingBuilder()
                        .generateId()
                        .setName(creationParameters.name)
                        .setCar(creationParameters.car)
                        .setCoordinates(creationParameters.coordinates)
                        .setHasToothPick(creationParameters.hasToothpick)
                        .setImpactSpeed(creationParameters.impactSpeed)
                        .setMinutesOfWaiting(creationParameters.minutesOfWaiting)
                        .setMood(creationParameters.mood)
                        .setRealHero(creationParameters.realHero)
                        .setWeaponType(creationParameters.weaponType)
                        .build();
        applicationContext.getRepository().add(humanBeing);
        return "Объект успешно добавлен в коллекцию";
    }
}
