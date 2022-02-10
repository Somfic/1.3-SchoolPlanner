package Gui;

import Data.Classroom;
import Data.StudentGroup;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;
import Data.StudentGroup;

import javax.xml.soap.Text;
import java.awt.*;
import java.util.ArrayList;
import java.util.Observable;


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
        TextField startTime = new TextField("Start time");
        TextField endTime = new TextField("End time");
        ComboBox classRoomSelect = new ComboBox();
        ComboBox teacherSelect = new ComboBox();
        ComboBox courseSelect = new ComboBox();
        ComboBox classSelect = new ComboBox();
        classRoomSelect.setValue("Classroom");
        teacherSelect.setValue("Teacher  ");
        courseSelect.setValue("Course     ");
        classSelect.setValue("Class       ");
        HBox hbox = new HBox();
        VBox vbox = new VBox();
        hbox.getChildren().add(startTime);
        hbox.getChildren().add(classRoomSelect);
        hbox.getChildren().add(teacherSelect);
        hbox.setSpacing(10);
        vbox.getChildren().add(hbox);
        HBox hbox2 = new HBox();
        hbox2.getChildren().add(endTime);
        hbox2.getChildren().add(courseSelect);
        hbox2.getChildren().add(classSelect);
        hbox2.setSpacing(10);
        vbox.getChildren().add(hbox2);
        vbox.setSpacing(10);
        schedulePane.setBottom(vbox);


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
//        FXGraphics2D graphics = new FXGraphics2D(canvas.getGraphicsContext2D());
//
//        for (int x = 0; x < canvas.getWidth(); x++) {
//            for (int y = 0; y < canvas.getHeight(); y++) {
//                float xPercentage = (float) ((float) x / canvas.getWidth());
//                float yPercentage = (float) ((float) y / canvas.getHeight());
//
//                Color color = Color.getHSBColor(xPercentage, yPercentage, 1f);
//
//                graphics.setColor(color);
//                graphics.fillRect(x, y, 1,1);
//            }
//        }
   }
}
