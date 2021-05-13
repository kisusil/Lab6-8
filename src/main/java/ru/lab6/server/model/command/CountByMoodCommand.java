package ru.lab6.server.model.command;

import ru.lab6.common.parameters.MoodParameters;
import ru.lab6.common.parameters.Parameters;
import ru.lab6.common.humanbeing.HumanBeing;
import ru.lab6.server.model.ApplicationContext;

import java.util.List;
import java.util.stream.Collectors;

public class CountByMoodCommand implements Command {
    private final ApplicationContext applicationContext;

    public CountByMoodCommand(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public String execute (Parameters parameters) {
        if (!(parameters instanceof MoodParameters)) {
            throw new RuntimeException("Что-то пошло не так");
        }

        MoodParameters moodParameters = (MoodParameters) parameters;
        long countByMood;
        List<HumanBeing> humanBeings = applicationContext.getRepository().getAll();

        countByMood =
                humanBeings
                    .stream()
                    .filter(humanBeing -> moodParameters.mood == humanBeing.getMood())
                    .count();
        return "Коллечиство элементов коллекции с заданным Mood:" + countByMood;
    }

}
