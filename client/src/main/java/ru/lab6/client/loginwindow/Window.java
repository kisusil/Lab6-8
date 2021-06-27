package ru.lab6.client.loginwindow;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ru.lab6.client.Client;
import ru.lab6.client.controller.Controller;
import ru.lab6.client.controller.MyController;
import ru.lab6.client.view.Console;
import ru.lab6.client.view.IO;
import ru.lab6.common.response.Response;

public class Window extends Application {
    private Controller controller;


    @Override
    public void init() {
        int port = Integer.parseInt(getParameters().getRaw().get(0));
        IO io = new Console(System.in, System.out);
        Client client = new Client(port, io);
        controller = new MyController(client);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Hello");

        HBox root = new HBox();
        root.setAlignment(Pos.CENTER);

        VBox container = new VBox();
        container.setSpacing(10.0);
        container.setAlignment(Pos.CENTER);

        root.getChildren().add(container);

        TextField login = createLoginField();
        TextField password = createPasswordField();
        Label error = createError();

        Button loginButton = createLoginButton(login, password, error);
        Button registerButton = createRegisterButton(login, password, error);

        container.getChildren().add(login);
        container.getChildren().add(password);
        container.getChildren().add(error);
        container.getChildren().add(loginButton);
        container.getChildren().add(registerButton);


        stage.setScene(new Scene(root, 380, 550));
        stage.show();
    }

    private TextField createLoginField() {
        TextField textField = new TextField();
        textField.setPrefSize(300, 50);
        textField.setPromptText("Login");
        return textField;
    }

    private TextField createPasswordField() {
        TextField textField = new TextField();
        textField.setPrefSize(300, 50);
        textField.setPromptText("Password");
        return textField;
    }

    private Label createError() {
        Label label = new Label();
        label.setStyle("-fx-text-fill: #ff0000");
        return label;
    }

    private Button createLoginButton(TextField login, TextField password, Label error) {
        Button button = new Button("Sign in");
        button.setPrefSize(250, 50);
        button.setOnAction((event) -> {
            int loginSymbolsAmount = login.getText().length();
            int passwordSymbolsAmount = password.getText().length();

            if (loginSymbolsAmount < 3 ||
                    loginSymbolsAmount > 20 ||
                    passwordSymbolsAmount < 3 ||
                    passwordSymbolsAmount > 20) {
                error.setText("Login, password length must be between 3 and 20");
                return;
            }

            error.setText("");
            Response response = controller.login(login.getText(), password.getText());

            if (response.getStatus().equalsIgnoreCase("ok")) {
                // go to table window
            } else {
                error.setText(response.getErrorDescription());
            }
        });

        return button;
    }

    private Button createRegisterButton(TextField login, TextField password, Label error) {
        Button button = new Button("Register");
        button.setPrefSize(250, 50);
        button.setOnAction((event) -> {
            int loginSymbolsAmount = login.getText().length();
            int passwordSymbolsAmount = password.getText().length();

            if (loginSymbolsAmount < 3 ||
                    loginSymbolsAmount > 20 ||
                    passwordSymbolsAmount < 3 ||
                    passwordSymbolsAmount > 20) {
                error.setText("Login, password length must be between 3 and 20");
                return;
            }

            error.setText("");
            Response response = controller.register(login.getText(), password.getText());

            if (response.getStatus().equalsIgnoreCase("ok")) {
                 //go to table window
            } else {
                error.setText(response.getErrorDescription());
            }
        });

        return button;
    }

    public static void main(String[] args){
        launch(args);
    }
}
