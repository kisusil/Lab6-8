package ru.lab6.server.model.command;

import ru.lab6.common.humanbeing.HumanBeing;
import ru.lab6.server.model.ApplicationContext;
import ru.lab6.server.model.RepositoryException;

import java.util.List;
import java.util.Optional;

public class RemoveLowerCommand implements Command {
    private final ApplicationContext applicationContext;

    public RemoveLowerCommand(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public String execute(Parameters parameters) {
        if (!(parameters instanceof CreationParameters)) {
            throw new RuntimeException("Что-то пошло не так");
        }

        CreationParameters creationParameters = (CreationParameters) parameters;
        HumanBeing newHumanBeing = applicationContext
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
        int sizeOne = humanBeings.size();
        if (humanBeings.isEmpty()) {
            return "Коллекция пустая";
        }

        humanBeings
                .stream()
                .filter(humanBeing -> newHumanBeing.compareTo(humanBeing) > 0)
                .forEach(humanBeing -> {
                    try {
                        applicationContext.getRepository().delete(humanBeing.getId());
                    } catch (RepositoryException e) {
                        throw new RuntimeException("Что-то пошло не так");
                    }
                });

        humanBeings = applicationContext.getRepository().getAll();

        if (sizeOne > humanBeings.size()){
            return "Элементы успешно удалены из коллекции";
        }

        return "Удалять нечего";
    }
}
