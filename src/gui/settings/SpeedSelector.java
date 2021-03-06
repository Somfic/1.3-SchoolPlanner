package gui.settings;

import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.HBox;

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
        this.speedSlider = new Slider(minimumValue, maximumValue, initialValue);
        this.speedSlider.setBlockIncrement(incrementValue);
        this.speedSlider.setSnapToTicks(false);
        this.speedSlider.setShowTickLabels(true);
        SpinnerValueFactory valueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(minimumValue, maximumValue, initialValue, incrementValue);
        this.speedSpinner = new Spinner(valueFactory);
        this.speedSlider.valueProperty().addListener((obs, oldval, newVal) -> speedSlider.setValue(Math.round(newVal.doubleValue())));
        this.speedSlider.valueProperty().bindBidirectional(speedSpinner.getValueFactory().valueProperty());
        this.speedCurrent = (int) Math.round(speedSlider.getValue());
        this.speedMemory = this.speedCurrent;

        speedSlider.setOnMouseClicked(event ->
                this.speedCurrent = (int) Math.round(speedSlider.getValue()));

        speedSpinner.setOnMouseClicked(event ->
                this.speedCurrent = (int) Math.round((Double) speedSpinner.getValue()));

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

    public void setSpeed(int speedCurrent) {
        this.speedCurrent = speedCurrent;
        speedSlider.setValue(speedCurrent);
    }

    public HBox getContent() {
        return this.componentHBox;
    }
}
