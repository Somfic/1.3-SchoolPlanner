package Gui.SettingsScreen;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class SettingView {
    private BorderPane borderPane = new BorderPane();
    private SpeedSelector speedSelector = new SpeedSelector();
    private Label titleLabel = new Label("Settings");

    public SettingView() {
        borderPane.setTop(titleLabel);
        borderPane.setCenter(speedSelector.getContent());
    }

    public BorderPane getContent() {
        return this.borderPane;
    }
}
