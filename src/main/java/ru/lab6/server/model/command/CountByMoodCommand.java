package ru.lab6.server.model.command;

import ru.lab6.common.MoodParameters;
import ru.lab6.common.Parameters;
import ru.lab6.common.humanbeing.HumanBeing;
import ru.lab6.server.model.ApplicationContext;

import java.util.List;

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
        int countByMood = 0;
        List<HumanBeing> humanBeings = applicationContext.getRepository().getAll();

        for (HumanBeing humanBeing : humanBeings) {
            if (moodParameters.mood == humanBeing.getMood()) {
                countByMood ++;
            }
        }
        return "Коллечиство элементов коллекции с заданным Mood:" + countByMood;
    }

}
