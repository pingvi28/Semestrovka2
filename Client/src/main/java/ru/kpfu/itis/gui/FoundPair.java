package ru.kpfu.itis.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;

public class FoundPair extends Application {
    private static final String NAME = "Found a pair";
    // тута задается длина квадрата с ячейками
    private final int size = 7;
    private final Figure3T[][] cells = new Figure3T[size][size];
    private final Logic3T logic = new Logic3T(cells);
    private double wight;
    private double height;

    //тут рисуются ячейки
    private Figure3T buildRectangle(int x, int y, int size) {
        Figure3T rect = new Figure3T();
        rect.setX(x * size);
        rect.setY(y * size);
        rect.setHeight(size);
        rect.setWidth(size);
        rect.setFill(Color.WHITE);
        rect.setStroke(Color.BLACK);
        return rect;
    }

    private Group buildMark(double x, double y, int size) {
        Group group = new Group();
        Image image = null;
        try(FileInputStream fileInputStream = new FileInputStream("Server/src/main/resources/12.png")){
            image = new Image(fileInputStream);
        } catch (IOException e) {
            System.out.println("(FP#buildMark) " + e.getMessage() + " : " + e.getCause());
        }
        ImageView  imageView = new ImageView(image);
        imageView.setX(x);
        imageView.setY(y);
        imageView.setFitHeight(size);
        imageView.setFitWidth(size);
        group.getChildren().add(imageView);
        return group;
    }

    private boolean checkState() {
        boolean gap = this.logic.hasGap();
        return gap;
    }


    private EventHandler<MouseEvent> buildMouseEvent(Group panel) {
        return event -> {
            Figure3T rect = (Figure3T) event.getTarget();
            if (this.checkState()) {
                if (event.getButton() == MouseButton.PRIMARY) {
                    rect.take(true);

                    panel.getChildren().add(
                            this.buildMark(rect.getX(), rect.getY(), (int) (height/10)));
                }
                this.checkState();
            }
        };
    }

    private Group buildGrid() {
        Group panel = new Group();
        for (int y = 0; y != this.size; y++) {
            for (int x = 0; x != this.size; x++) {
                Figure3T rect = this.buildRectangle(x, y, (int) (height/10));
                this.cells[y][x] = rect;
                panel.getChildren().add(rect);
                rect.setOnMouseClicked(this.buildMouseEvent(panel));
            }
        }
        return panel;
    }

    private MenuBar buildMenuBar(){
        MenuBar menuBar = new MenuBar();

        Menu fileMenu = new Menu("File");

        MenuItem newItem = new MenuItem("New");
//        newItem.setOnAction(
//                event -> border.setCenter(this.buildGrid())
//
//        );
        MenuItem exitItem = new MenuItem("Exit");
        exitItem.setOnAction(event -> System.exit(0));

        fileMenu.getItems().addAll(newItem, exitItem);

        menuBar.getMenus().addAll(fileMenu);

        return menuBar;
    }

    private void getParamScreen(){
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        this.height = screenBounds.getHeight();
        this.wight = screenBounds.getWidth();
    }

    @Override
    public void start(Stage stage) {
        getParamScreen();
        BorderPane border = new BorderPane();
        HBox control = new HBox();
        control.setPrefHeight(40);
        control.setSpacing(10.0);
        control.setAlignment(Pos.BASELINE_CENTER);

        Button start = new Button("Начать");
        start.setOnMouseClicked(
                event -> border.setCenter(this.buildGrid())

        );

        control.getChildren().addAll(start);
        border.setTop(buildMenuBar());
        border.setBottom(control);
        //border.setCenter(this.buildGrid());
        stage.setScene(new Scene(border,(wight * 3 ) / 4, (height * 3) / 4));
        stage.setMinHeight((height * 3) / 4);
        stage.setMinWidth((wight * 3 ) / 4);
        stage.setTitle(NAME);
        stage.setResizable(true);
        stage.show();
    }
}
