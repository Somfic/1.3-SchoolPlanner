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
    private Label breakLabel;
    private SpeedSelector speedSelector;
    private ColorSelector colorSelector;
    private ClassBlock classBlock;
    private Break aBreak;
    private VBox centralPane;
    private Button confirm = new Button("Confirm");
    private Button cancel = new Button("Cancel");

    public SettingView() {
        this.borderPane = new BorderPane();
        this.titleLabel = new Label("Settings");
        this.speedLabel = new Label("Simulator Speed");
        this.colorSelectorLabel = new Label("Theme Color");
        this.classBlockLabel = new Label("Class Block Length (in minutes)");
        this.breakLabel = new Label("breakfast (length in minutes, starts after class X)");
        this.speedSelector = new SpeedSelector(this);
        this.colorSelector = new ColorSelector(this);
        this.classBlock = new ClassBlock(this);
        this.aBreak = new Break(this);
        this.centralPane = new VBox(speedLabel, speedSelector.getContent(),colorSelectorLabel, colorSelector.getContent(), classBlockLabel, classBlock.getContent(), breakLabel, aBreak.getContent(), new HBox(confirm, cancel));
        this.centralPane.setFillWidth(true);
        this.centralPane.setSpacing(15);
        
        confirm.setOnAction(event -> {
            speedSelector.confirm();
            colorSelector.confirm();
            classBlock.confirm();
            aBreak.confirm();
        });
        
        cancel.setOnAction(event -> {
            speedSelector.cancel();
            colorSelector.cancel();
            classBlock.cancel();
            aBreak.cancel();

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
