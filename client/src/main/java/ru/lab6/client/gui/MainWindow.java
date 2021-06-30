package ru.lab6.client.gui;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import ru.lab6.client.controller.Controller;
import ru.lab6.client.repository.HumanBeingWrapper;
import ru.lab6.client.repository.TableContent;

import java.util.List;


public class MainWindow {
    private Controller controller;
    private TableContent tableContent;
    private Canvas canvas;

    public MainWindow(Controller controller){
        this.controller = controller;
        this.tableContent = new TableContent(controller);
    }

    public void show(Stage stage) {
        Pane createPane = new CreationPane(controller, this).getPane();
        Pane tablePane = createTablePane();

        VBox canvasRoot = new VBox();
        canvas = new Canvas();
        canvas.setHeight(600);
        canvas.setWidth(1000);
        canvasRoot.setAlignment(Pos.CENTER);
        VBox.setVgrow(canvas, Priority.ALWAYS);
        canvasRoot.getChildren().add(canvas);

        GridPane tableRoot = new GridPane();
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setPercentWidth(30.0);
        tableRoot.getColumnConstraints().add(columnConstraints);
        tableRoot.setGridLinesVisible(true);
        tableRoot.addColumn(0, createPane);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(tablePane);

        tableRoot.addColumn(1, scrollPane);

        Tab tableTab = new Tab("Table");
        tableTab.setContent(tableRoot);
        Tab canvasTab = new Tab("Canvas");
        canvasTab.setContent(canvasRoot);
        TabPane tabPane = new TabPane(tableTab, canvasTab);

        stage.setTitle("Lab 8");
        stage.setScene(new Scene(tabPane, 1500, 600));
        stage.setResizable(true);
        centerStage(stage);
        stage.show();
        updateTable("");
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

    public void updateTable(String filter) {
        tableContent.updateState();
        tableContent.filter(filter);

        ObservableList<HumanBeingWrapper> humanBeingWrappers = tableContent.getHumanBeings();

        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        graphicsContext.clearRect(0,0,1000,600);
        graphicsContext.setFill(Color.BLUE);
        graphicsContext.setLineWidth(2);
        for (HumanBeingWrapper humanBeingWrapper : humanBeingWrappers) {
            graphicsContext.fillOval(500 + humanBeingWrapper.getX(), 300 - humanBeingWrapper.getY(), 20, 20);
        }
    }
}
