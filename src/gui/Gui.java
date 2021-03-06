package gui;

import gui.components.WindowBar;
import gui.schedule.ScheduleView;
import gui.settings.SettingCallback;
import gui.settings.SettingView;
import gui.simulation.SimulationView;
import io.FileManager;
import io.InputManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Gui extends Application implements SettingCallback {
    private Scene scene;
    private Canvas canvas;

    //Views
    private ScheduleView scheduleView = new ScheduleView(this);

    //TabPane
    private VBox mainPane;
    private WindowBar windowBar;
    private TabPane tabPane;
    private BorderPane schedulePane = new BorderPane();
    private SimulationView simulationPane;
    private SettingView settingsPane = new SettingView();

    @Override
    public void start(Stage stage) {
        // Custom title bar
        this.canvas = new Canvas(600, 700);

        //Making tabs
        this.tabPane = new TabPane();
        this.tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

        this.simulationPane = new SimulationView();
        scheduleView.addCallback(simulationPane);

        settingsPane.addCallback(this);
        settingsPane.addCallback(simulationPane);

        Tab scheduleTab = new Tab("Schedule", schedulePane);
        scheduleTab.setClosable(false);

        Tab simulationTab = new Tab("Simulation", simulationPane);
        simulationTab.setClosable(false);

        Tab settingsTab = new Tab("Settings", settingsPane.getContent());
        settingsTab.setClosable(false);

        this.tabPane = new TabPane(scheduleTab, simulationTab, settingsTab);
        this.tabPane.getSelectionModel().select(2); // select settings pane

        simulationPane.setTabPane(tabPane);

        //SchedulePane
//        Button button = new Button("Testing pop-up function");
//        button.setOnAction(event -> {
//            PopUpAddItems.PupUp("Testing");
//        });

        //this.schedulePane.setCenter(button);
        //this.schedulePane.setPrefSize(canvas.getWidth(), canvas.getHeight());

        //SimulationPane
        //this.simulationPane.setPrefSize(canvas.getWidth(), canvas.getHeight());

        //SettingsPane
        //this.settingsPane.setPrefSize(canvas.getWidth(), canvas.getHeight());

        //Other
        this.windowBar = new WindowBar(stage, settingsPane);
        this.mainPane = new VBox(windowBar.getContent(), this.tabPane);
        this.mainPane.setStyle("-fx-padding: 3");
        this.mainPane.setSpacing(3);
        this.scene = new Scene(mainPane);
        InputManager.setup(scene);

        this.scene.widthProperty().addListener((observable, oldValue, newValue) -> {
            this.canvas.setWidth(newValue.doubleValue());
        });

        this.scene.heightProperty().addListener((observable, oldValue, newValue) -> {
            this.canvas.setHeight(newValue.doubleValue());
        });

        this.schedulePane.setCenter(this.scheduleView);
        this.schedulePane.setBottom(this.scheduleView.selectButtons);

        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(this.scene);
        stage.setResizable(true);
        stage.getIcons().add(new Image(FileManager.getResource("./Icon.png")));
        stage.show();

        settingsPane.load();

        this.scheduleView.build((int) this.scheduleView.getGridPane().widthProperty().doubleValue());

        simulationPane.start();
    }

    @Override
    public void onSettingChange(ScheduleSettings newSettings) {
        scheduleView.updateScheduleTime(newSettings.getClassBlockLength(), newSettings.getLunchBreakTime(), newSettings.getLunchBreakLength(), newSettings.getFastBreakTime(), newSettings.getFastBreakLength(), newSettings.getStartTime());
        scheduleView.updateColor(newSettings.getColor(), newSettings.isTextBrightness());
    }
}
