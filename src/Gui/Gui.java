package Gui;

import Gui.SettingsScreen.SettingView;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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

        // Custom title bar
        //stage.initStyle(StageStyle.UNDECORATED);
        this.canvas = new Canvas(1920, 900);

        //Making tabs
        this.tabPane = new TabPane();
        this.tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        Tab scheduleTab = new Tab("Schedule", schedulePane);
        scheduleTab.setClosable(false);

        Tab simulationTab = new Tab("Simulation", simulationPane);
        simulationTab.setClosable(false);

        Tab settingsTab = new Tab("Settings", settingsPane.getContent());
        settingsTab.setClosable(false);

        this.tabPane = new TabPane(scheduleTab, simulationTab, settingsTab);

        //SchedulePane
        Button button = new Button("Testing pop-up function");
        button.setOnAction(event -> {
                PopUpAddItems.PupUp("Testing");
        });
        //this.schedulePane.setCenter(button);
        //this.schedulePane.setPrefSize(canvas.getWidth(), canvas.getHeight());

        //SimulationPane
        //this.simulationPane.setPrefSize(canvas.getWidth(), canvas.getHeight());

        //SettingsPane
        //this.settingsPane.setPrefSize(canvas.getWidth(), canvas.getHeight());


        //Other
      //  this.scene = new Scene(scheduleView.getContent());
        this.scene = new Scene(new Group(this.tabPane));
        this.graphics = new FXGraphics2D(canvas.getGraphicsContext2D());

        this.schedulePane.setCenter(this.scheduleView);

        // Bottom buttons
        HBox buttons = new HBox();

        // Buttons
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
        buttons.getChildren().add(vbox);

        // Apply, Remove, and Reset
        Button apply = new Button("Apply");
        Button reset = new Button("Reset");
        Button remove = new Button("Remove");
        HBox applyRemoveButtons = new HBox(apply, remove);
        applyRemoveButtons.setSpacing(10);

        VBox applyRemoveResetButtons = new VBox(applyRemoveButtons, reset);
        applyRemoveResetButtons.setAlignment(Pos.CENTER);
        applyRemoveResetButtons.setSpacing(10);

        buttons.getChildren().add(applyRemoveResetButtons);

        // Save and Load
        Button save = new Button("Save");
        Button load = new Button("Load");

        VBox saveLoadButtons = new VBox(save, load);
        saveLoadButtons.setSpacing(10);

        buttons.getChildren().add(saveLoadButtons);

        buttons.setSpacing(10);
        buttons.setPadding(new javafx.geometry.Insets(10, 10, 10, 10));

        this.schedulePane.setBottom(buttons);


        stage.setScene(this.scene);
        stage.setResizable(true);
        stage.setTitle("School Planner");
        stage.show();
        this.scheduleView.build((int) this.scheduleView.getGridPane().widthProperty().doubleValue());
    }
}
