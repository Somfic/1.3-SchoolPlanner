package Gui;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.jfree.fx.FXGraphics2D;

public class Gui extends Application {
    private Scene scene;
    private Canvas canvas;
    private FXGraphics2D graphics;

    //Views
    private ScheduleView scheduleView = new ScheduleView();

    //TabPane
    private TabPane tabPane;
    private BorderPane schedulePane = new BorderPane();
    private BorderPane simulationPane = new BorderPane();
    private BorderPane settingsPane = new BorderPane();

    @Override
    public void start(Stage stage) {
        this.canvas = new Canvas(1920, 1080);

        //Making tabs
        this.tabPane = new TabPane();
        this.tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        this.tabPane.getTabs().add(new Tab("Schedule", this.schedulePane));
        this.tabPane.getTabs().add(new Tab("Simulation", this.simulationPane));
        this.tabPane.getTabs().add(new Tab("Settings", this.settingsPane));

        //Scene and Stage
        this.scene = new Scene(new Group(this.tabPane));
        this.graphics = new FXGraphics2D(canvas.getGraphicsContext2D());

        this.schedulePane.setCenter(this.scheduleView);

        stage.setScene(this.scene);
        stage.setResizable(false);
        stage.setTitle("School Planner");
        stage.show();
        this.scheduleView.build((int) this.scheduleView.getGridPane().widthProperty().doubleValue());
    }
}
