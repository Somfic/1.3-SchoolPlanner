package Gui.SettingsScreen;

import Gui.Dropdown;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.HBox;
import javafx.util.Pair;


public class LunchBreak {
    //BreakLength means the amount of minutes a break takes
    //BreakTime means after which lesson a break starts
    private Label label;
    private Spinner<Integer> BLSpinner;
    private Dropdown BTDropdown;
    private HBox hBox;
    private int minimumValue = 0;
    private int maximumValue = 60;
    private int initialValue = 30;
    private int incrementValue = 5;
    private BreakTimeCallback callback;
    private int BLCurrent;
    private int BLMemory;
    private int BTCurrent;
    private int BTMemory;
    private String[] lessons = {"N/A", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

    public LunchBreak(BreakTimeCallback callback) {
        this.label = new Label("Select ");
        SpinnerValueFactory valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(minimumValue, maximumValue, initialValue, incrementValue);
        this.BLSpinner = new Spinner(valueFactory);
        this.BLCurrent = initialValue;
        this.BLMemory = this.BLCurrent;

        this.BTDropdown = new Dropdown();
        BTDropdown.setDropdownItems(lessons);
        this.BTCurrent = initialValue;
        this.BTMemory = this.BLCurrent;

        this.callback = callback;

        this.BLSpinner.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                BLCurrent = BLSpinner.getValue();
            }
        });
        this.hBox = new HBox(label, BLSpinner, BTDropdown);
        this.hBox.setSpacing(15);
    }

    public void confirm() {
        this.BLMemory = this.BLCurrent;
        this.BTMemory = this.BTCurrent;
        callback.onBreakTimeChange(new Pair<>(BLCurrent, BTCurrent));
    }

    public void cancel() {
        this.BLCurrent = this.BLMemory;
        this.BTCurrent = this.BTMemory;
        this.BLSpinner.getValueFactory().setValue(this.BLCurrent);
    }

    public HBox getContent() {
        return this.hBox;
    }
}
