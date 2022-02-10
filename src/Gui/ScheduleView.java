package Gui;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class ScheduleView extends Canvas {
    private GridPane grid;

    private Button save = new Button("Save");
    private Button load = new Button("Load");
    private Button apply = new Button("Apply");
    private Button reset = new Button("Reset");
    private Button remove = new Button("Remove");


    public ScheduleView() {
        super(1920, 1080);
        grid = new GridPane();
        grid.add(apply, 0, 0);
        grid.add(reset, 1, 1);
        grid.add(remove, 2, 0);
        grid.add(save, 3, 0);
        grid.add(load, 3, 1);

        apply.setOnAction(event -> {
            //button pressed
        });
        reset.setOnAction(event -> {
            //button pressed
        });
        remove.setOnAction(event -> {
            //button pressed
        });
        save.setOnAction(event -> {
            //button pressed
        });
        load.setOnAction(event -> {
            //button pressed
        });
    }

    public GridPane getContent() {
        return grid;
    }
}