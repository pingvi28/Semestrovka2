package ru.kpfu.itis;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import ru.kpfu.itis.game.client.GameClient;
import ru.kpfu.itis.game.common.PlayerConnection;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

import javafx.geometry.Orientation;

import java.net.Socket;

public class AppClient extends Application  {
    private Stage primaryStage;
    private Scene scene;
    private PlayerConnection playerConnection;
    protected Socket socket;

    public static void main(String[] args) {
        launch();
    }

    public void start(Stage primaryStage) throws Exception {
        GameClient client = new GameClient();
        client.connect();

        Label lbl = new Label();
        TextField textField = new TextField();
        textField.setPrefColumnCount(11);
        Button btn = new Button("Click");
        btn.setOnAction(event -> System.out.println(textField.getText()));
        FlowPane root = new FlowPane(Orientation.VERTICAL, 10, 10, textField, btn, lbl);
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 250, 200);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("ololo");
        stage.show();
    }

}
