package ru.lab6.client.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;
import ru.lab6.client.controller.Controller;
import ru.lab6.client.repository.HumanBeingWrapper;
import ru.lab6.client.repository.TableContent;

import java.util.List;


public class WindowTable{
    private Controller controller;
    private TableContent tableContent;

    public WindowTable(Controller controller){
        this.controller = controller;
        this.tableContent = new TableContent(controller);
        tableContent.updateState();
    }

    public void show(Stage stage) {
        Pane createPane = new CreationPane(controller, tableContent).getPane();
        Pane tablePane = createTablePane();

        GridPane root = new GridPane();
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setPercentWidth(30.0);
        root.getColumnConstraints().add(columnConstraints);
        root.setGridLinesVisible(true);
        root.addColumn(0, createPane);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(tablePane);

        root.addColumn(1, scrollPane);

        stage.setTitle("Lab 8");
        stage.setScene(new Scene(root, 1500, 600));
        stage.setResizable(true);
        centerStage(stage);
        stage.show();
    }

    private Pane createTablePane() {
        VBox vBox = new VBox();

        TableView<HumanBeingWrapper> table = new TableView<>(tableContent.getHumanBeings());

        List<String> fieldNames = ObjectInfo.getFields();
        for (String fieldName : fieldNames) {
            if (fieldName.equalsIgnoreCase("realHero") || fieldName.equalsIgnoreCase("hasToothpick")) {
                TableColumn<HumanBeingWrapper, Boolean> column = new TableColumn<>(fieldName);
                column.setCellValueFactory(new PropertyValueFactory<>(fieldName));
                table.getColumns().add(column);
            } else if (fieldName.equalsIgnoreCase("id") || fieldName.equalsIgnoreCase("x")) {
                TableColumn<HumanBeingWrapper, Integer> column = new TableColumn<>(fieldName);
                column.setCellValueFactory(new PropertyValueFactory<>(fieldName));
                table.getColumns().add(column);
            } else if (fieldName.equalsIgnoreCase("y")) {
                TableColumn<HumanBeingWrapper, Double> column = new TableColumn<>(fieldName);
                column.setCellValueFactory(new PropertyValueFactory<>(fieldName));
                table.getColumns().add(column);
            } else if (fieldName.equalsIgnoreCase("impactSpeed")) {
                TableColumn<HumanBeingWrapper, Float> column = new TableColumn<>(fieldName);
                column.setCellValueFactory(new PropertyValueFactory<>(fieldName));
                table.getColumns().add(column);
            } else if (fieldName.equalsIgnoreCase("minutesOfWaiting")) {
                TableColumn<HumanBeingWrapper, Long> column = new TableColumn<>(fieldName);
                column.setCellValueFactory(new PropertyValueFactory<>(fieldName));
                table.getColumns().add(column);
            } else  {
                TableColumn<HumanBeingWrapper, String> column = new TableColumn<>(fieldName);
                column.setCellValueFactory(new PropertyValueFactory<>(fieldName));
                table.getColumns().add(column);
            }
        }

        vBox.getChildren().add(table);

        return vBox;
    }

    private void centerStage(Stage stage){
        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        stage.setX((primScreenBounds.getWidth() - stage.getWidth()) / 2);
        stage.setY((primScreenBounds.getHeight() - stage.getHeight()) / 2);
    }
}
