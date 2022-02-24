package Gui.SettingsScreen;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Pair;
import java.time.LocalTime;

public class SettingView implements SpeedSelectorCallback, ColorCallback, ClassBlockCallback, BreakTimeCallback, StartTimeCallback {
    private BorderPane borderPane;
    private Label titleLabel;
    private Label speedLabel;
    private Label colorSelectorLabel;
    private Label classBlockLabel;
    private Label breakfastLabel;
    private Label breakLunchLabel;
    private SpeedSelector speedSelector;
    private ColorSelector colorSelector;
    private ClassBlock classBlock;
    private FastBreak fastBreak;
    private LunchBreak lunchBreak;
    private StartTime startTime;
    private VBox centralPane;
    private Button confirm = new Button("Confirm");
    private Button cancel = new Button("Cancel");
    private int speedSave;
    private Color themeColorSave;
    private int classBlockLengthSave;
    private Pair<Integer, Integer> fastBreakSave;
    private Pair<Integer, Integer> lunchBreakSave;
    private LocalTime startingTime;
    private SettingCallback callback;

    public SettingView(SettingCallback callback) {
        this.callback = callback;
        this.borderPane = new BorderPane();
        this.titleLabel = new Label("Settings");
        this.speedLabel = new Label("Simulator Speed");
        this.colorSelectorLabel = new Label("Theme Color");
        this.classBlockLabel = new Label("Class Block Length (in minutes)");
        this.breakfastLabel = new Label("Fast break (length in minutes, Starts after Class Block X)");
        this.breakLunchLabel = new Label("Lunch break (length in minutes, Starts after Class Block X)");
        this.speedSelector = new SpeedSelector(this);
        this.colorSelector = new ColorSelector(this);
        this.classBlock = new ClassBlock(this);
        this.fastBreak = new FastBreak(this);
        this.lunchBreak = new LunchBreak(this);
        this.startTime = new StartTime(this);
        this.centralPane = new VBox(speedLabel, speedSelector.getContent(), colorSelectorLabel, colorSelector.getContent(), classBlockLabel, classBlock.getContent(), breakfastLabel, fastBreak.getContent(), breakLunchLabel, lunchBreak.getContent(), startTime.getContent(), new HBox(confirm, cancel));
        this.centralPane.setFillWidth(true);
        this.centralPane.setSpacing(15);


        confirm.setOnAction(event -> {
            speedSelector.confirm();
            colorSelector.confirm();
            classBlock.confirm();
            fastBreak.confirm();
            lunchBreak.confirm();
            startTime.confirm();
            callback.onSettingChange(new SettingCallback.ScheduleSettings(speedSave, themeColorSave, classBlockLengthSave, fastBreakSave, lunchBreakSave, startingTime));
        });
        cancel.setOnAction(event -> {
            speedSelector.cancel();
            colorSelector.cancel();
            classBlock.cancel();
            fastBreak.cancel();
            lunchBreak.cancel();
            startTime.cancel();
        });
        borderPane.setTop(titleLabel);
        borderPane.setCenter(centralPane);
    }

    public BorderPane getContent() {
        return this.borderPane;
    }

    @Override
    public void onSpeedChange(int speed) {
        this.speedSave = speed;
    }

    @Override
    public void onColorChange(Color color) {
        System.out.println("Color: " + color);
        this.themeColorSave = color;
        this.startTime.setColor(color);
    }

    @Override
    public void ClassBlockLengthChanged(int length) {
        this.classBlockLengthSave = length;
    }

    @Override
    public void onFastBreakTimeChange(Pair<Integer, Integer> time) {
        this.fastBreakSave = time;
    }

    @Override
    public void onLunchBreakTimeChange(Pair<Integer, Integer> time) {
        this.lunchBreakSave = time;
    }

    @Override
    public void newStartTime(LocalTime time) {
        this.startingTime = time;
    }
}
