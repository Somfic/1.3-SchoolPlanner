package Gui.SettingsScreen;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.converter.IntegerStringConverter;

import java.text.NumberFormat;

public class SpeedSelector {
    private Label speedLabel;
    private Slider speedSlider;
    private Spinner speedSpinner;
    private VBox componentVBox;
    private final int minimumValue = 1;
    private final int maximumValue = 10;
    private final int initialValue = 5;
    private final int incrementValue = 1;

    public SpeedSelector() {
        this.componentVBox = new VBox();
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

        componentVBox.getChildren().add(new HBox(speedLabel, speedSlider, speedSpinner));
    }

    public VBox getContent() {
        return this.componentVBox;
    }
}
