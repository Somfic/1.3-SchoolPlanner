package Gui;

import Gui.Components.ResizeHelper;
import Gui.Components.WindowBar;
import Gui.Schedule.PopUpAddItems;
import Gui.Schedule.ScheduleView;
import Gui.Settings.SettingCallback;
import Gui.Settings.SettingView;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.jfree.fx.FXGraphics2D;

public class Gui extends Application implements SettingCallback {
    private Scene scene;
    private Canvas canvas;
    private FXGraphics2D graphics;

    //Views
    private ScheduleView scheduleView;

    //TabPane
    private VBox mainPane;
    private WindowBar windowBar;
    private TabPane tabPane;
    private BorderPane schedulePane = new BorderPane();
    private VBox scheduleBox = new VBox(3);
    private BorderPane simulationPane = new BorderPane();
    private SettingView settingsPane = new SettingView(this);

    @Override
    public void start(Stage stage) {

        // Custom title bar
        this.canvas = new Canvas(1920, 900);

        //Making tabs
        this.tabPane = new TabPane();
        this.tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);

//        Tab scheduleTab = new Tab("Schedule", schedulePane);
        Tab scheduleTab = new Tab("Schedule", scheduleBox);
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
        this.windowBar = new WindowBar(stage);
        this.mainPane = new VBox(windowBar.getContent(), this.tabPane);
        this.mainPane.setStyle("-fx-padding: 3");
        this.mainPane.setSpacing(3);
        this.scheduleView = new ScheduleView();
        this.scene = new Scene(mainPane, 1500, 700);
        this.scheduleView.updateSize(this.scene.getWidth(), this.scene.getHeight());
        this.graphics = new FXGraphics2D(canvas.getGraphicsContext2D());

//        this.schedulePane.setCenter(this.scheduleView);
//        this.schedulePane.setBottom(this.scheduleView.selectButtons);
        this.scheduleBox.getChildren().add(this.scheduleView);
        this.scheduleBox.getChildren().add(this.scheduleView.selectButtons);

        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(this.scene);
        ResizeHelper.addResizeListener(stage);
        stage.setMinHeight(350);
        stage.setMinWidth(250);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/Icon.png")));
        stage.show();
        this.scheduleView.build();

        ChangeListener<Number> stageSizeListener = (observable, oldValue, newValue) ->
                this.scheduleView.updateSize(stage.getWidth(), stage.getHeight());

        stage.widthProperty().addListener(stageSizeListener);
        stage.heightProperty().addListener(stageSizeListener);
    }


    @Override
    public void onSettingChange(ScheduleSettings newSettings) {
        scheduleView.updateScheduleTime(newSettings.getClassBlockLength(), newSettings.getLunchBreak().getValue(), newSettings.getLunchBreak().getKey(), newSettings.getFastBreak().getValue(), newSettings.getFastBreak().getKey(), newSettings.getStartingTime());
        scheduleView.updateColor(newSettings.getColor());
    }
}
