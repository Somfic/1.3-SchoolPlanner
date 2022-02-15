package Gui.SettingsScreen;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.HBox;

public class ClassBlock {
    //ClassBlockLength equels the amount of minutes a class block takes
    private int CBL;
    private Label label;
    private Spinner CBLSpinner;
    private HBox hBox;
    private int minimumValue = 15;
    private int maximumValue = 75;
    private int initialValue = 60;
    private int incrementValue = 5;
    private ClassBlockCallback callback;

    public ClassBlock(ClassBlockCallback callback) {
        this.label = new Label("Select ");
        SpinnerValueFactory valueFactory = new SpinnerValueFactory.DoubleSpinnerValueFactory(minimumValue, maximumValue, initialValue, incrementValue);
        this.CBLSpinner = new Spinner(valueFactory);
        this.CBL = initialValue;

        this.CBLSpinner.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
//                callback.ClassBlockLengthChanged((Integer) CBLSpinner.valueProperty().getValue());
                System.out.println(CBLSpinner.getValue());
            }
        });

        this.hBox = new HBox(label, CBLSpinner);
        this.hBox.setSpacing(15);
    }

    public HBox getContent() {
        return this.hBox;
    }
}
