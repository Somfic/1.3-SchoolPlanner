package Gui;

import Gui.SettingsScreen.SettingView;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
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
    private SettingView settingsPane = new SettingView();

    @Override
    public void start(Stage stage) {
        this.canvas = new Canvas(1920, 1080);

        //Making tabs
        this.tabPane = new TabPane();
        this.tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        this.tabPane.getTabs().add(new Tab("Schedule", this.schedulePane));
        this.tabPane.getTabs().add(new Tab("Simulation", this.simulationPane));
        this.tabPane.getTabs().add(new Tab("Settings", this.settingsPane.getContent()));

        //SchedulePane
        Button button = new Button("Testing pop-up function");
        button.setOnAction(event -> {
                PopUpAddItems.PupUp("Testing");
        });
        this.schedulePane.setCenter(button);
        this.schedulePane.setPrefSize(canvas.getWidth(), canvas.getHeight());

        //SimulationPane
        this.simulationPane.setPrefSize(canvas.getWidth(), canvas.getHeight());

        //SettingsPane
        //this.settingsPane.setPrefSize(canvas.getWidth(), canvas.getHeight());


        //Other
        this.scene = new Scene(new Group(this.tabPane));
        this.graphics = new FXGraphics2D(canvas.getGraphicsContext2D());

        stage.setScene(this.scene);
        stage.setResizable(false);
        stage.setTitle("School Planner");
        stage.show();
    }
}
