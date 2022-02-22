package Gui;

import Data.ScheduleItem;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SelectButtons extends Application {
    private Scene scene;
    private Canvas canvas;
    private TabPane tabs;
    private BorderPane schedulePane = new BorderPane();
    private BorderPane simulationPane = new BorderPane();
    private BorderPane settingsPane = new BorderPane();
    private ScheduleItem scheduleItems;



    @Override
    public void start(Stage stage) {
        canvas = new Canvas(1600, 800);
        TextField startTime = new TextField();
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

        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("School Planner");
        stage.show();
    }
}
