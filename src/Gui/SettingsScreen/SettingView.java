package Gui.SettingsScreen;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class SettingView implements SpeedSelectorCallback, ColorCallback, ClassBlockCallback {
    private BorderPane borderPane;
    private Label titleLabel;
    private SpeedSelector speedSelector;
    private ColorSelector colorSelector;
    private ClassBlock classBlock;
    private VBox centralPane;
    private Button confirm = new Button("confirm");
    private Button cancel = new Button("Cancel");

    public SettingView() {
        this.borderPane = new BorderPane();
        this.titleLabel = new Label("Settings");
        this.speedSelector = new SpeedSelector(this);
        this.colorSelector = new ColorSelector(this);
        this.classBlock = new ClassBlock(this);
        this.centralPane = new VBox(speedSelector.getContent(), colorSelector.getContent(), classBlock.getContent(), new HBox(confirm, cancel));
        this.centralPane.setFillWidth(true);
        this.centralPane.setSpacing(15);
        
        confirm.setOnAction(event -> {
            speedSelector.confirm();
            colorSelector.confirm();
            classBlock.confirm();
        });
        
        cancel.setOnAction(event -> {
            speedSelector.cancel();
            colorSelector.cancel();
            classBlock.cancel();
        });
        
        borderPane.setTop(titleLabel);
        borderPane.setCenter(centralPane);
    }

    public BorderPane getContent() {
        return this.borderPane;
    }

    @Override
    public void onColourChange(Color color) {
    }

    @Override
    public void ClassBlockLengthChanged(int length) {
    }

    @Override
    public void onSpeedChange(int speed) {
    }
}
