package ru.lab6.client.gui;

import com.sun.javafx.collections.ObservableListWrapper;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import ru.lab6.client.controller.Controller;
import ru.lab6.client.repository.TableContent;
import ru.lab6.common.humanbeing.Car;
import ru.lab6.common.humanbeing.Coordinates;
import ru.lab6.common.humanbeing.Mood;
import ru.lab6.common.humanbeing.WeaponType;
import ru.lab6.common.response.Response;

import java.util.Arrays;
import java.util.stream.Collectors;

public class CreationPane {
    private TextField name;
    private CheckBox realHero;
    private CheckBox hasToothpick;
    private TextField impactSpeed;
    private TextField minutesOfWaiting;
    private ComboBox<String> weaponType;
    private ComboBox<String> mood;
    private TextField carName;
    private TextField x;
    private TextField y;

    private Controller controller;
    private Label error;
    private TextField filter;
    private MainWindow mainWindow;


    public CreationPane(Controller controller, MainWindow mainWindow) {
        this.controller = controller;
        this.mainWindow = mainWindow;
        this.name = new TextField();
        this.realHero = new CheckBox();
        this.hasToothpick = new CheckBox();
        this.impactSpeed = new TextField();
        this.minutesOfWaiting = new TextField();
        this.weaponType = new ComboBox<>(
                new ObservableListWrapper<>(
                        Arrays.stream(WeaponType.values())
                                .map(WeaponType::name)
                                .collect(Collectors.toList())
                )
        );
        this.mood = new ComboBox<>(
                new ObservableListWrapper<>(
                        Arrays.stream(Mood.values())
                                .map(Mood::name)
                                .collect(Collectors.toList())
                )
        );
        this.carName = new TextField();
        this.x = new TextField();
        this.y = new TextField();
        this.filter = new TextField();
    }


    public Pane getPane() {
        return createCreationPane();
    }

    private Pane createCreationPane() {
        VBox container = new VBox();
        container.setPrefWidth(800);
        container.setAlignment(Pos.CENTER);

        Button createButton = createCreateButton();
        VBox.setMargin(createButton, new javafx.geometry.Insets(20.0));

        Button filterButton = createFilterButton();
        VBox.setMargin(filterButton, new javafx.geometry.Insets(20.0));

        VBox inputs = new VBox();
        inputs.getChildren().add(createNameInput());
        inputs.getChildren().add(createRealHeroInput());
        inputs.getChildren().add(createHasToothpickInput());
        inputs.getChildren().add(createImpactSpeedInput());
        inputs.getChildren().add(createMinutesOfWaitingInput());
        inputs.getChildren().add(createWeaponTypeInput());
        inputs.getChildren().add(createMoodInput());
        inputs.getChildren().add(createCarNameInput());
        inputs.getChildren().add(createXInput());
        inputs.getChildren().add(createYInput());

        VBox.setMargin(inputs, new Insets(20.0));
        inputs.setAlignment(Pos.CENTER_LEFT);

        HBox hBox = new HBox();
        hBox.setSpacing(10.0);
        hBox.getChildren().add(filter);
        HBox.setHgrow(filter, Priority.ALWAYS);
        hBox.getChildren().add(filterButton);
        VBox.setMargin(hBox, new Insets(20.0));

        container.getChildren().add(hBox);
        container.getChildren().add(inputs);

        error = new Label();
        error.setStyle("-fx-text-fill: #ff0000");
        container.getChildren().add(error);

        container.getChildren().add(createButton);

        return container;
    }

    private HBox createInputHBox() {
        HBox hBox = new HBox();
        hBox.setSpacing(10.0);
        VBox.setMargin(hBox, new Insets(5.0, 0, 5.0, 0));

        return hBox;
    }

    private Pane createNameInput() {
        HBox hBox = createInputHBox();

        HBox labelHBox = new HBox(new Label( "Name :"));
        hBox.getChildren().add(labelHBox);
        HBox.setHgrow(labelHBox, Priority.ALWAYS);
        HBox inputHBox = new HBox(name);
        hBox.getChildren().add(inputHBox);
        HBox.setHgrow(name, Priority.ALWAYS);

        return hBox;
    }

    private Pane createRealHeroInput() {
        HBox hBox = createInputHBox();

        HBox labelHBox = new HBox(new Label( "Real hero :"));
        hBox.getChildren().add(labelHBox);
        HBox.setHgrow(labelHBox, Priority.ALWAYS);
        HBox inputHBox = new HBox(realHero);
        inputHBox.setAlignment(Pos.CENTER_LEFT);
        hBox.getChildren().add(inputHBox);
        HBox.setHgrow(realHero, Priority.ALWAYS);

        return hBox;
    }

    private Pane createHasToothpickInput() {
        HBox hBox = createInputHBox();

        HBox labelHBox = new HBox(new Label( "Has toothpick :"));
        hBox.getChildren().add(labelHBox);
        HBox.setHgrow(labelHBox, Priority.ALWAYS);
        HBox inputHBox = new HBox(hasToothpick);
        inputHBox.setAlignment(Pos.CENTER_LEFT);
        hBox.getChildren().add(inputHBox);
        HBox.setHgrow(hasToothpick, Priority.ALWAYS);

        return hBox;
    }

    private Pane createImpactSpeedInput() {
        HBox hBox = createInputHBox();

        HBox labelHBox = new HBox(new Label( "Impact speed :"));
        hBox.getChildren().add(labelHBox);
        HBox.setHgrow(labelHBox, Priority.ALWAYS);
        HBox inputHBox = new HBox(impactSpeed);
        hBox.getChildren().add(inputHBox);
        HBox.setHgrow(impactSpeed, Priority.ALWAYS);

        return hBox;
    }

    private Pane createMinutesOfWaitingInput() {
        HBox hBox = createInputHBox();

        HBox labelHBox = new HBox(new Label( "Minutes of waiting :"));
        hBox.getChildren().add(labelHBox);
        HBox.setHgrow(labelHBox, Priority.ALWAYS);
        HBox inputHBox = new HBox(minutesOfWaiting);
        hBox.getChildren().add(inputHBox);
        HBox.setHgrow(minutesOfWaiting, Priority.ALWAYS);

        return hBox;
    }

    private Pane createWeaponTypeInput() {
        HBox hBox = createInputHBox();


        HBox labelHBox = new HBox(new Label( "Weapon type :"));
        hBox.getChildren().add(labelHBox);
        HBox.setHgrow(labelHBox, Priority.ALWAYS);
        HBox inputHBox = new HBox(weaponType);
        inputHBox.setAlignment(Pos.CENTER_LEFT);
        hBox.getChildren().add(inputHBox);
        HBox.setHgrow(weaponType, Priority.ALWAYS);

        return hBox;
    }

    private Pane createMoodInput() {
        HBox hBox = createInputHBox();

        HBox labelHBox = new HBox(new Label( "Mood :"));
        hBox.getChildren().add(labelHBox);
        HBox.setHgrow(labelHBox, Priority.ALWAYS);
        HBox inputHBox = new HBox(mood);
        inputHBox.setAlignment(Pos.CENTER_LEFT);
        hBox.getChildren().add(inputHBox);
        HBox.setHgrow(mood, Priority.ALWAYS);

        return hBox;
    }

    private Pane createCarNameInput() {
        HBox hBox = createInputHBox();

        HBox labelHBox = new HBox(new Label( "Car name :"));
        hBox.getChildren().add(labelHBox);
        HBox.setHgrow(labelHBox, Priority.ALWAYS);
        HBox inputHBox = new HBox(carName);
        hBox.getChildren().add(inputHBox);
        HBox.setHgrow(carName, Priority.ALWAYS);

        return hBox;
    }

    private Pane createXInput() {
        HBox hBox = createInputHBox();

        HBox labelHBox = new HBox(new Label( "X :"));
        hBox.getChildren().add(labelHBox);
        HBox.setHgrow(labelHBox, Priority.ALWAYS);
        HBox inputHBox = new HBox(x);
        hBox.getChildren().add(inputHBox);
        HBox.setHgrow(x, Priority.ALWAYS);

        return hBox;
    }

    private Pane createYInput() {
        HBox hBox = createInputHBox();

        HBox labelHBox = new HBox(new Label( "Y :"));
        hBox.getChildren().add(labelHBox);
        HBox.setHgrow(labelHBox, Priority.ALWAYS);
        HBox inputHBox = new HBox(y);
        hBox.getChildren().add(inputHBox);
        HBox.setHgrow(y, Priority.ALWAYS);

        return hBox;
    }

    private Button createFilterButton() {
        Button button = new Button("Filter");
        button.setPrefSize(100, 30);
        button.setOnAction((event) -> mainWindow.updateTable(filter.getText()));

        return button;
    }

    private Button createCreateButton() {
        Button button = new Button("Create");
        button.setPrefSize(200, 30);
        button.setOnAction((event) -> {
            String nameValue = name.getText();
            boolean realHeroValue = realHero.isSelected();
            boolean hasToothpickValue = hasToothpick.isSelected();

            String validationResult = validateFloat(impactSpeed.getText(), "Impact speed");
            if (!validationResult.isEmpty()) {
                error.setText(validationResult);
                return;
            }

            float impactSpeedValue = Float.parseFloat(impactSpeed.getText());

            validationResult = validateLong(minutesOfWaiting.getText(), "Minutes of waiting");
            if (!validationResult.isEmpty()) {
                error.setText(validationResult);
                return;
            }
            long minutesOfWaitingValue = Long.parseLong(minutesOfWaiting.getText());

            WeaponType weaponTypeValue = WeaponType.valueOf(weaponType.getSelectionModel().getSelectedItem());
            Mood moodValue = Mood.valueOf(mood.getSelectionModel().getSelectedItem());
            String carNameValue = carName.getText();

            validationResult = validateInteger(x.getText(), "X");
            if (!validationResult.isEmpty()) {
                error.setText(validationResult);
                return;
            }
            int xValue = Integer.parseInt(x.getText());

            validationResult = validateDouble(y.getText(), "Y");
            if (!validationResult.isEmpty()) {
                error.setText(validationResult);
                return;
            }
            double yValue = Double.parseDouble(y.getText());

            Response response = controller.add(
                    nameValue,
                    new Coordinates(xValue, yValue),
                    realHeroValue,
                    hasToothpickValue,
                    impactSpeedValue,
                    minutesOfWaitingValue,
                    weaponTypeValue,
                    moodValue,
                    new Car(carNameValue));

            if (response.getStatus().equalsIgnoreCase("ok")) {
                mainWindow.updateTable("");
            } else {
                error.setText(response.getErrorDescription());
            }
        });
        return button;
    }

    private String validateInteger(String string, String fieldName) {
        try {
            Integer.parseInt(string);
            return "";
        } catch (Throwable t) {
            return fieldName + " must be integer";
        }
    }

    private String validateDouble(String string, String fieldName) {
        try {
            Double.parseDouble(string);
            return "";
        } catch (Throwable t) {
            return fieldName + " must be double";
        }
    }

    private String validateFloat(String string, String fieldName) {
        try {
            Float.parseFloat(string);
            return "";
        } catch (Throwable t) {
            return fieldName + " must be float";
        }
    }

    private String validateLong(String string, String fieldName) {
        try {
            Long.parseLong(string);
            return "";
        } catch (Throwable t) {
            return fieldName + " must be long";
        }
    }
}
