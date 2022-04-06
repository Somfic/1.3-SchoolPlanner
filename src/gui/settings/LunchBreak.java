package gui.settings;

import gui.components.Dropdown;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.HBox;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LunchBreak {
    //BreakLength means the amount of minutes a break takes
    //BreakTime means after which lesson a break starts
    private Label label;
    private Spinner<Integer> breakLengthSpinner;
    private Dropdown<String> breakTimeDropdown;
    private HBox hBox;
    private int minimumValue = 0;
    private int maximumValue = 60;
    private int initialValue = 30;
    private int incrementValue = 5;
    private BreakTimeCallback callback;
    private int breakLengthCurrent;
    private int breakLengthMemory;
    private int breakTimeCurrent;
    private int breakTimeMemory;
    private ArrayList<String> lessons = new ArrayList<>();
    public LunchBreak(BreakTimeCallback callback) {
        Collections.addAll(lessons,"1", "2", "3", "4", "5", "6", "7", "8", "9");
        this.label = new Label("Select ");
        SpinnerValueFactory valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(minimumValue, maximumValue, initialValue, incrementValue);
        this.breakLengthSpinner = new Spinner(valueFactory);
        this.breakLengthCurrent = initialValue;
        this.breakLengthMemory = this.breakLengthCurrent;

        this.breakTimeDropdown = new Dropdown();
        breakTimeDropdown.setDropdownItems(lessons);
        this.breakTimeCurrent = Integer.parseInt(lessons.get(4));
        this.breakTimeMemory = this.breakLengthCurrent;

        this.callback = callback;

        this.breakLengthSpinner.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observabreakLengthe, Object oldValue, Object newValue) {
                breakLengthCurrent = breakLengthSpinner.getValue();
            }
        });

        this.breakTimeDropdown.setOnDropdownAction(event -> {
            breakTimeCurrent = Integer.parseInt(breakTimeDropdown.getValue());
        });

        this.hBox = new HBox(label, breakLengthSpinner, breakTimeDropdown);
        this.hBox.setSpacing(15);
    }

    public void confirm() {
        this.breakLengthMemory = this.breakLengthCurrent;
        this.breakTimeMemory = this.breakTimeCurrent;
        callback.onLunchBreakTimeChange(new Pair<>(breakLengthCurrent, breakTimeCurrent));
    }

    public void cancel() {
        this.breakLengthCurrent = this.breakLengthMemory;
        this.breakTimeCurrent = this.breakTimeMemory;
        this.breakLengthSpinner.getValueFactory().setValue(this.breakLengthCurrent);
    }

    public void set(int time, int length) {
        breakTimeDropdown.setValue(time + "");
        breakLengthSpinner.getValueFactory().setValue(length);
    }

    public HBox getContent() {
        return this.hBox;
    }
}
