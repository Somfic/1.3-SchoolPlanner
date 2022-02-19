package Gui.SettingsScreen;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Pair;

public class SettingView implements SpeedSelectorCallback, ColorCallback, ClassBlockCallback, BreakTimeCallback {
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
    private Breakfast breakfast;
    private BreakLunch breakLunch;
    private VBox centralPane;
    private Button confirm = new Button("Confirm");
    private Button cancel = new Button("Cancel");

    public SettingView() {
        this.borderPane = new BorderPane();
        this.titleLabel = new Label("Settings");
        this.speedLabel = new Label("Simulator Speed");
        this.colorSelectorLabel = new Label("Theme Color");
        this.classBlockLabel = new Label("Class Block Length (in minutes)");
        this.breakfastLabel = new Label("Breakfast (length in minutes, Starts after Class Block X)");
        this.breakLunchLabel = new Label("Lunch break (length in minutes, Starts after Class Block X)");
        this.speedSelector = new SpeedSelector(this);
        this.colorSelector = new ColorSelector(this);
        this.classBlock = new ClassBlock(this);
        this.breakfast = new Breakfast(this);
        this.breakLunch = new BreakLunch(this);
        this.centralPane = new VBox(speedLabel, speedSelector.getContent(),colorSelectorLabel, colorSelector.getContent(), classBlockLabel, classBlock.getContent(), breakfastLabel, breakfast.getContent(), breakLunchLabel, breakLunch.getContent(), new HBox(confirm, cancel));
        this.centralPane.setFillWidth(true);
        this.centralPane.setSpacing(15);
        
        confirm.setOnAction(event -> {
            speedSelector.confirm();
            colorSelector.confirm();
            classBlock.confirm();
            breakfast.confirm();
        });
        
        cancel.setOnAction(event -> {
            speedSelector.cancel();
            colorSelector.cancel();
            classBlock.cancel();
            breakfast.cancel();

        });
        
        borderPane.setTop(titleLabel);
        borderPane.setCenter(centralPane);
    }

    public BorderPane getContent() {
        return this.borderPane;
    }

    @Override
    public void onColorChange(Color color) {
    }

    @Override
    public void ClassBlockLengthChanged(int length) {
    }

    @Override
    public void onSpeedChange(int speed) {
    }

    @Override
    public void onBreakTimeChange(Pair<Integer, Integer> time) {
    }
}
