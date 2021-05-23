package ru.lab6.server.model.command;

import ru.lab6.common.parameters.CreationParameters;
import ru.lab6.common.parameters.Parameters;
import ru.lab6.common.humanbeing.HumanBeing;
import ru.lab6.common.response.Response;
import ru.lab6.server.model.ApplicationContext;
import ru.lab6.server.model.RepositoryException;

import java.util.List;

public class RemoveLowerCommand implements Command {
    private final ApplicationContext applicationContext;

    public RemoveLowerCommand(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Response execute(Parameters parameters) {
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
            return new Response("error", "Коллекция пустая");
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
            return new Response("ok small", "Элементы успешно удалены из коллекции");
        }

        return new Response("error", "Удалять нечего");
    }
}
