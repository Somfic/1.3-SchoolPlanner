package gui;

import data.FramesPerSecond;
import gui.components.WindowBar;
import gui.schedule.PopUpAddItems;
import gui.schedule.ScheduleView;
import gui.settings.LunchBreak;
import gui.settings.SettingCallback;
import gui.settings.SettingView;
import gui.simulation.SimulationView;
import io.InputManager;
import javafx.scene.control.Label;
import logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.jfree.fx.FXGraphics2D;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class Gui extends Application implements SettingCallback {
    private Scene scene;
    private Canvas canvas;
    private FXGraphics2D graphics;
    FramesPerSecond fps = new FramesPerSecond();

    //Views
    private ScheduleView scheduleView = new ScheduleView(this);

    //TabPane
    private VBox mainPane;
    private WindowBar windowBar;
    private TabPane tabPane;
    private BorderPane schedulePane = new BorderPane();
    private Pane simulationPane = new SimulationView();
    private SettingView settingsPane = new SettingView(this);
    private int timeMultiplier = 1;
    private int classBlockLength = 0;
    private int lunchBreakLength = 0;
    private int lunchBreakTime = 0;
    private int fastBreakLength = 0;
    private int fastBreakTime = 0;
    private ArrayList<LocalTime> startTimes = new ArrayList<>();
    private ArrayList<LocalTime> endTimes = new ArrayList<>();
    private LocalTime startTime = null;
    private LocalTime classBlockTime = null;
    private Label timeLabel = new Label("-");

    @Override
    public void start(Stage stage) {
        // Custom title bar
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
        this.tabPane.getSelectionModel().select(2); // select settings pane

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
        this.windowBar = new WindowBar(stage, settingsPane);
        this.mainPane = new VBox(windowBar.getContent(), this.tabPane);
        this.mainPane.setStyle("-fx-padding: 3");
        this.mainPane.setSpacing(3);
        this.scene = new Scene(mainPane);
        this.graphics = new FXGraphics2D(canvas.getGraphicsContext2D());

//        this.schedulePane.setTop(new WindowBar(stage).getContent());
        this.schedulePane.setCenter(this.scheduleView);
        this.schedulePane.setBottom(this.scheduleView.selectButtons);
        this.simulationPane.getChildren().add(timeLabel);



        //AnimationTimer used for the FPS count
        new AnimationTimer() {
            long last = -1;

            @Override
            public void handle(long now) {
                if (last == -1) {
                    last = now;
                }
                update((now - last) / 1000000000.0);
                last = now;
            }
        }.start();

        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(this.scene);
        InputManager.setup(this.scene);
        stage.setResizable(true);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/Icon.png")));
        stage.show();

        settingsPane.load();

        this.scheduleView.build((int) this.scheduleView.getGridPane().widthProperty().doubleValue());
    }

    LocalDateTime lastFps = LocalDateTime.now();

    public void update(double deltaTime) {
        fps.update(deltaTime);
        InputManager.update();
        if (LocalDateTime.now().isAfter(lastFps.plusSeconds(1))) {
            lastFps = LocalDateTime.now();
            Logger.debug("FPS: " + fps.getPfs());
            if (this.tabPane.getSelectionModel().isSelected(1) && startTime != null) {
                startTime = ChronoUnit.MINUTES.addTo(startTime,timeMultiplier);
                for (int i = 0; i <10 ; i++) {
                    if(startTime.isAfter(startTimes.get(i).minusHours(1))&&startTime.isBefore(startTimes.get(i))){
                        timeLabel.setText(startTime.toString() + " Classblock " + (i+1));
                        break;
                    }
                    else{
                        timeLabel.setText(startTime.toString() + " Break");
                    }

                }
            }
            else{
                startTime = settingsPane.getStartingTime();
                classBlockTime = settingsPane.getStartingTime();
                 classBlockLength = scheduleView.getClassBlockLength();
                 lunchBreakLength = scheduleView.getLunchBreakLength();
                 lunchBreakTime = scheduleView.getLunchBreakTime();
                 fastBreakLength = scheduleView.getFastBreakLength();
                 fastBreakTime = scheduleView.getFastBreakTime();
                 setArraylists();
            }
      }

    }

    @Override
    public void onSettingChange(ScheduleSettings newSettings) {
        scheduleView.updateScheduleTime(newSettings.getClassBlockLength(), newSettings.getLunchBreakTime(), newSettings.getLunchBreakLength(), newSettings.getFastBreakTime(), newSettings.getFastBreakLength(), newSettings.getTime());
        scheduleView.updateColor(newSettings.getColor(), newSettings.isTextBrightness());
        timeMultiplier = newSettings.getSpeed();
    }
    public void setArraylists(){
        LocalTime endTime;
        startTimes.clear();
        endTimes.clear();
        for (int i = 1; i <= 10; i++) {
            endTime = ChronoUnit.MINUTES.addTo(this.classBlockTime, classBlockLength);
            if (i == fastBreakTime) {
                this.classBlockTime = ChronoUnit.MINUTES.addTo(endTime, fastBreakLength);
            } else if (i == lunchBreakTime) {
                this.classBlockTime = ChronoUnit.MINUTES.addTo(endTime, lunchBreakLength);
            } else {
                this.classBlockTime = endTime;
            }
          startTimes.add(classBlockTime);
        }
    }

}
