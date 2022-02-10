package Gui;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

import java.awt.*;

public class Gui extends Application {

    private Scene scene;
    private Canvas canvas;

    private TabPane tabs;
    private BorderPane schedulePane = new BorderPane();
    private BorderPane simulationPane = new BorderPane();
    private BorderPane settingsPane = new BorderPane();

    @Override
    public void start(Stage stage) {
        canvas = new Canvas(600, 600);

        tabs = new TabPane();
        tabs.getTabs().add(new Tab("Schedule", schedulePane));
        tabs.getTabs().add(new Tab("Simulation", simulationPane));
        tabs.getTabs().add(new Tab("Settings", settingsPane));

        schedulePane.setCenter(canvas);

        scene = new Scene(new Group(tabs));

        draw();

        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("School Planner");
        stage.show();
    }

    void draw() {
        FXGraphics2D graphics = new FXGraphics2D(canvas.getGraphicsContext2D());

        for (int x = 0; x < canvas.getWidth(); x++) {
            for (int y = 0; y < canvas.getHeight(); y++) {
                float xPercentage = (float) ((float) x / canvas.getWidth());
                float yPercentage = (float) ((float) y / canvas.getHeight());

                Color color = Color.getHSBColor(xPercentage, yPercentage, 1f);

                graphics.setColor(color);
                graphics.fillRect(x, y, 1,1);
            }
        }
    }
}
