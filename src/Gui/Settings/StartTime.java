package Gui.Settings;

import com.jfoenix.controls.JFXTimePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.time.LocalTime;


public class StartTime {
    private VBox vBox;
    private Label label;
    private StartTimeCallback callback;
    private JFXTimePicker timePicker = new JFXTimePicker();
    private LocalTime startTimeCurrent;
    private LocalTime startTimeMemory;

    public StartTime(StartTimeCallback callback) {
        this.label = new Label("Select start time of the first class block:");
        this.timePicker.set24HourView(true);
        timePicker.setOnAction(e -> {
            startTimeCurrent = timePicker.getValue();
        });

        setColor(Color.DIMGRAY);
        this.timePicker.setOverLay(false);

        this.callback = callback;

        this.vBox = new VBox(label, timePicker);
        this.vBox.setSpacing(15);
    }

    public void setColor(Color color) {
        this.timePicker.setDefaultColor(color);
    }

    public void confirm() {
        this.startTimeMemory = this.startTimeCurrent;
        callback.newStartTime(startTimeCurrent);
    }

    public void cancel() {
        this.startTimeCurrent = this.startTimeMemory;
        this.timePicker.setValue(startTimeMemory);
    }

    public VBox getContent() {
        return this.vBox;
    }
}