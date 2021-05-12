package ru.lab6.server.model.command;

import ru.lab6.common.CreationParameters;
import ru.lab6.common.Parameters;
import ru.lab6.common.humanbeing.HumanBeing;
import ru.lab6.server.model.ApplicationContext;

import java.util.List;

public class AddIfMaxCommand implements Command {
    private final ApplicationContext applicationContext;

    public AddIfMaxCommand(ApplicationContext applicationContext) {this.applicationContext = applicationContext; }

    @Override
    public String execute(Parameters parameters) {
        if (!(parameters instanceof CreationParameters)) {
            throw new RuntimeException("Что-то пошло не так");
        }

        CreationParameters creationParameters = (CreationParameters) parameters;

        HumanBeing humanBeing = applicationContext
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
        List<HumanBeing> humanBeings = applicationContext.getRepository().getAll();
        int indexMax = humanBeings.size() - 1;
        HumanBeing maxHumanBeing = humanBeings.get(indexMax);
        if (humanBeing.compareTo(maxHumanBeing)>0) {
            applicationContext.getRepository().add(humanBeing);
            return "Объект успешно добавлен в коллекцию";
        } else { return "Введенный элемент не максимальный"; }
    }
}
