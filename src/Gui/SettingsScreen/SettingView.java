package Gui.SettingsScreen;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class SettingView implements ColorCallback {
    private BorderPane borderPane;
    private Label titleLabel;
    private SpeedSelector speedSelector;
    private ColorSelector colorSelector;
    private VBox centralPane;

    public SettingView() {
        this.borderPane = new BorderPane();
        this.titleLabel = new Label("Settings");
        this.speedSelector = new SpeedSelector();
        this.colorSelector = new ColorSelector(this);
        this.centralPane = new VBox(speedSelector.getContent(), colorSelector.getContent());
        this.centralPane.setFillWidth(true);
        this.centralPane.setSpacing(15);

        borderPane.setTop(titleLabel);
        borderPane.setCenter(centralPane);
    }

    public BorderPane getContent() {
        return this.borderPane;
    }

    @Override
    public void onColourChange(Color color) {
    }
}
