package Gui.SettingsScreen;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.HBox;
import javafx.util.converter.IntegerStringConverter;

import java.text.NumberFormat;

public class SpeedSelector {
    private Label speedLabel;
    private Slider speedSlider;
    private Spinner speedSpinner;
    private HBox componentHBox;
    private final int minimumValue = 1;
    private final int maximumValue = 10;
    private final int initialValue = 5;
    private final int incrementValue = 1;
    private int speedCurrent;
    private int speedMemory;
    private SpeedSelectorCallback callback;

    public SpeedSelector(SpeedSelectorCallback callback) {
        this.callback = callback;
        this.speedLabel = new Label("Speed");
        speedSlider = new Slider(minimumValue, maximumValue, initialValue);
        speedSlider.setBlockIncrement(incrementValue);
        speedSlider.setSnapToTicks(false);
        speedSlider.setShowTickLabels(true);
        SpinnerValueFactory valueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(minimumValue, maximumValue, initialValue, incrementValue);
        speedSpinner = new Spinner(valueFactory);
        speedSlider.valueProperty().bindBidirectional(speedSpinner.getValueFactory().valueProperty());

        speedSlider.setOnMousePressed(event -> {
            speedSlider.valueProperty().addListener((obs, oldval, newVal) -> {
                speedSlider.setValue(Math.round(newVal.doubleValue()));
            });
        });
//        speedSlider.getOnMouseReleased(event ->
//            this.speedCurrent = speedSlider.valueProperty().intValue() );

        this.componentHBox = new HBox(speedLabel, speedSlider, speedSpinner);
        componentHBox.setSpacing(15);
    }

    public void confirm() {
        this.speedMemory = this.speedCurrent;
        callback.onSpeedChange(speedCurrent);
    }

    public void cancel() {
        this.speedCurrent = this.speedMemory;
        speedSlider.setValue(this.speedMemory);
    }

    public int getSpeed() {
        return this.speedCurrent;
    }

    public HBox getContent() {
        return this.componentHBox;
    }
}
