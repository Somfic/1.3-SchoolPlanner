package Gui.SettingsScreen;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.HBox;

public class ClassBlock {
    //ClassBlockLength means the amount of minutes a class block takes
    private Label label;
    private Spinner<Integer> CBLSpinner;
    private HBox hBox;
    private int minimumValue = 15;
    private int maximumValue = 75;
    private int initialValue = 60;
    private int incrementValue = 5;
    private ClassBlockCallback callback;
    private int CBLCurrent;
    private int CBLMemory;

    public ClassBlock(ClassBlockCallback callback) {
        this.label = new Label("Select ");
        SpinnerValueFactory valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(minimumValue, maximumValue, initialValue, incrementValue);
        this.CBLSpinner = new Spinner(valueFactory);
        this.CBLCurrent = initialValue;
        this.CBLMemory = this.CBLCurrent;
        this.callback = callback;

        this.CBLSpinner.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                CBLCurrent = CBLSpinner.getValue();
            }
        });

        this.hBox = new HBox(label, CBLSpinner);
        this.hBox.setSpacing(15);
    }

    public void confirm() {
        this.CBLMemory = this.CBLCurrent;
        callback.ClassBlockLengthChanged(CBLCurrent);
    }

    public void cancel() {
        this.CBLCurrent = this.CBLMemory;
        this.CBLSpinner.getValueFactory().setValue(this.CBLCurrent);
    }

    public HBox getContent() {
        return this.hBox;
    }
}
