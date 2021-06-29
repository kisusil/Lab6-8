package ru.lab6.client.gui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;
import ru.lab6.client.controller.Controller;
import ru.lab6.common.humanbeing.HumanBeing;
import ru.lab6.common.response.Response;

import java.util.List;


public class WindowTable{
    private Controller controller;
    private ObjectInfo objectInfo;

    public WindowTable(Controller controller){
        this.controller=controller;

    }

    public Stage show(Stage stage) {

        VBox container = new VBox();
        container.setAlignment(Pos.CENTER);

        Button createButton = createCreateButton();
        VBox.setMargin(createButton, new Insets(20.0));

        Button filterButton = createFilterButton();
        VBox.setMargin(filterButton, new Insets(20.0));

        Canvas canvas = new Canvas(250,150);
        container.getChildren().add(createButton);
        container.getChildren().add(canvas);
        VBox labels = new VBox();
        List<String> labelsNames = ObjectInfo.getFields();
        for (String name: labelsNames){
            labels.getChildren().add(new Label(name + ": "));
        }
        VBox.setMargin(labels, new Insets(20.0));
        labels.setAlignment(Pos.CENTER_LEFT);
        container.getChildren().add(labels);
        container.getChildren().add(filterButton);


        GridPane table = new GridPane();
        table.setGridLinesVisible(true);
        for (int i=0; i<labelsNames.size(); i++){
            table.getColumnConstraints().add(new ColumnConstraints(150));
            table.add(new Label(labelsNames.get(i)), i, 0);
        }

        GridPane root = new GridPane();
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setPercentWidth(20.0);
        root.getColumnConstraints().add(columnConstraints);
        root.setGridLinesVisible(true);
        root.addColumn(0, container);
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(table);
        root.addColumn(1, scrollPane);


        stage.setTitle("Lab 8");
        stage.setScene(new Scene(root, 1200, 600));
        centerStage(stage);
        stage.show();
        return stage;
    }

    private Button createFilterButton() {
        Button button = new Button("Filter");
        button.setPrefSize(200, 50);
        button.setOnAction((event) -> {
        });
        return button;
    }

    private Button createCreateButton() {
        Button button = new Button("Create");
        button.setPrefSize(200, 50);
        button.setOnAction((event) -> {
        });
        return button;
    }

    private void centerStage(Stage stage){
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
    }

    private GridPane getTable(){
        List<String> labelsNames = ObjectInfo.getFields();
        GridPane table = new GridPane();
        table.setGridLinesVisible(true);
        for (int i=0; i<labelsNames.size(); i++){
            table.getColumnConstraints().add(new ColumnConstraints(150));
            table.add(new Label(labelsNames.get(i)), i, 0);
        }
        Response response = controller.show();
        List<HumanBeing> result = response.getElements();
        for (int i=0; i<labelsNames.size(); i++){
            table.addRow(i+1, );
        }
        return table;
    }


}
