import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

import org.jfree.fx.FXGraphics2D;

import java.awt.*;

public class Main extends Application {

    public static void main(String[] args) {
        launch(Main.class);
    }

    Scene scene;
    Canvas canvas;

    @Override
    public void start(Stage primaryStage) {
        canvas = new Canvas(600, 600);
        scene = new Scene(new Group(canvas));

        FXGraphics2D graphics = new FXGraphics2D(canvas.getGraphicsContext2D());

        draw(graphics);

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("School Planner");
        primaryStage.show();
    }

    void draw(FXGraphics2D graphics) {
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
