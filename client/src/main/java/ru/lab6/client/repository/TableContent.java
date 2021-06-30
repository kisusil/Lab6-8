package ru.lab6.client.repository;

import com.sun.javafx.collections.ObservableListWrapper;
import javafx.collections.ObservableList;
import ru.lab6.client.controller.Controller;
import ru.lab6.common.humanbeing.HumanBeing;
import ru.lab6.common.response.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TableContent {
    private final ObservableList<HumanBeingWrapper> humanBeings;
    private final Controller controller;


    public TableContent(Controller controller) {
        this.controller = controller;
        humanBeings = new ObservableListWrapper<>(new ArrayList<>());
    }


    public ObservableList<HumanBeingWrapper> getHumanBeings() {
        return humanBeings;
    }

    public void updateState() {
        humanBeings.clear();

        Response response = controller.show();

        if (!response.getStatus().equalsIgnoreCase("ok")) {
            return;
        }

        humanBeings.addAll(response
                .getElements()
                .stream()
                .map(HumanBeingWrapper::new)
                .collect(Collectors.toList()));
    }

    public void filter(String nameFilter) {
        List<HumanBeingWrapper> toDelete = humanBeings
                .stream()
                .filter(humanBeingWrapper -> !humanBeingWrapper.getName().startsWith(nameFilter))
                .collect(Collectors.toList());

        toDelete.forEach(humanBeings::remove);
    }

    public void add(HumanBeing humanBeing) {
        humanBeings.add(new HumanBeingWrapper(humanBeing));
    }
}
