package Gui;

import Gui.SettingsScreen.SettingCallback;
import Gui.SettingsScreen.SettingView;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Pair;
import org.jfree.fx.FXGraphics2D;

import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;

public class Gui extends Application implements SettingCallback {
    private Scene scene;
    private Canvas canvas;
    private FXGraphics2D graphics;

    //Views
    private ScheduleView scheduleView = new ScheduleView();

    //TabPane
    private VBox mainPane;
    private WindowBar windowBar;
    private TabPane tabPane;
    private BorderPane schedulePane = new BorderPane();
    private BorderPane simulationPane = new BorderPane();
    private SettingView settingsPane = new SettingView(this);

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
        this.windowBar = new WindowBar(stage);
        this.mainPane = new VBox(windowBar.getContent(), this.tabPane);
        this.mainPane.setStyle("-fx-padding: 3");
        this.mainPane.setSpacing(3);
        this.scene = new Scene(mainPane);
        this.graphics = new FXGraphics2D(canvas.getGraphicsContext2D());

//        this.schedulePane.setTop(new WindowBar(stage).getContent());
        this.schedulePane.setCenter(this.scheduleView);

        stage.initStyle(StageStyle.UNDECORATED);
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
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/Icon.png")));
        stage.show();
        this.scheduleView.build((int) this.scheduleView.getGridPane().widthProperty().doubleValue());
    }

    @Override
    public void onSettingChange(int speed, Color color, int classBlockLength, Pair<Integer, Integer> fastBreak, Pair<Integer, Integer> lunchBreak) {
        scheduleView.updateScheduleTime(classBlockLength, lunchBreak.getValue(), lunchBreak.getKey(), fastBreak.getValue(), fastBreak.getKey());
    }
}
