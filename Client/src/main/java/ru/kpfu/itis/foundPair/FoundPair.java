package ru.kpfu.itis.foundPair;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class FoundPair extends Application {
    private static final String NAME = "Хуета";
    // тута задается длина квадрата с ячейками
    private final int size = 7;
    private final Figure3T[][] cells = new Figure3T[size][size];
    private final Logic3T logic = new Logic3T(cells);


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

    private Group buildMark(double x, double y, int size) throws FileNotFoundException {
        Group group = new Group();
        Image image = new Image(new FileInputStream("images/Bamboo.png"));
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
                    try {
                        panel.getChildren().add(
                                this.buildMark(rect.getX(), rect.getY(), 50)
                        );
                        //эту хуету надо правильно обрабатывать я не ебу как
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                this.checkState();
            }
        };
    }

    private Group buildGrid() {
        Group panel = new Group();
        for (int y = 0; y != this.size; y++) {
            for (int x = 0; x != this.size; x++) {
                Figure3T rect = this.buildRectangle(x, y, 50);
                this.cells[y][x] = rect;
                panel.getChildren().add(rect);
                rect.setOnMouseClicked(this.buildMouseEvent(panel));
            }
        }
        return panel;
    }

    @Override
    public void start(Stage stage) {
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
        border.setBottom(control);
        border.setCenter(this.buildGrid());
        stage.setScene(new Scene(border,500, 500));
        stage.setTitle(NAME);
        stage.setResizable(true);
        stage.show();
    }
}
