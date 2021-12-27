package ru.kpfu.itis;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ru.kpfu.itis.game.AppServer;
import ru.kpfu.itis.game.client.ClientException;
import ru.kpfu.itis.game.client.GameClient;
import ru.kpfu.itis.game.common.Information;
import ru.kpfu.itis.game.common.PlayerConnection;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;

import javafx.geometry.Orientation;
import ru.kpfu.itis.game.server.ServerImp;
import ru.kpfu.itis.protocol.Properties;

import java.net.Socket;

public class AppClient extends Application  {
    private static GameClient client;
    private Stage primaryStage;
    private Scene scene;
    private PlayerConnection playerConnection;
    protected Socket socket;

    public static void main(String[] args) throws ClientException {
        launch();
    }

    public void start(Stage primaryStage) throws Exception {
        client = new GameClient();
        client.connect();

        Label lbl = new Label();
        lbl.setText("Привет игрок, введи размер поля ( от 1 до 7)");
        TextField textField = new TextField();
        textField.setPrefColumnCount(11);
        Button btn = new Button("Click");
        //
        btn.setOnAction(event -> Properties.setSizeBoard(Integer.parseInt(textField.getText())));
        FlowPane root = new FlowPane(Orientation.VERTICAL, 10, 10, textField, btn, lbl);
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 400, 200);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("ololo");
        stage.show();
    }
}
