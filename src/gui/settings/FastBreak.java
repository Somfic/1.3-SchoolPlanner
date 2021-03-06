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
import java.util.Collections;


public class FastBreak {
    //BreakLength means the amount of minutes a break takes
    //BreakTime means after which lesson a break starts
    private Label label;
    private Spinner<Integer> breakLengthSpinner;
    private Dropdown<String> BTDropdown;
    private HBox hBox;
    private int minimumValue = 0;
    private int maximumValue = 60;
    private int initialValue = 15;
    private int incrementValue = 5;
    private BreakTimeCallback callback;
    private int breakLengthCurrent;
    private int breakLengthMemory;
    private int BTCurrent;
    private int BTMemory;
    //    private String[] lessons = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
    private ArrayList<String> lessons = new ArrayList<>();


    public FastBreak(BreakTimeCallback callback) {
        Collections.addAll(lessons, "1", "2", "3", "4", "5", "6", "7", "8", "9");
        this.label = new Label("Select ");
        SpinnerValueFactory valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(minimumValue, maximumValue, initialValue, incrementValue);
        this.breakLengthSpinner = new Spinner(valueFactory);
        this.breakLengthCurrent = initialValue;
        this.breakLengthMemory = this.breakLengthCurrent;

        this.BTDropdown = new Dropdown();
        BTDropdown.setDropdownItems(lessons);
        this.BTCurrent = Integer.parseInt(lessons.get(0));
        this.BTMemory = this.breakLengthCurrent;

        this.callback = callback;
        this.breakLengthSpinner.valueProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observabreakLengthe, Integer oldValue, Integer newValue) {
                breakLengthCurrent = breakLengthSpinner.getValue();
            }
        });

        this.BTDropdown.setOnDropdownAction(event -> {
            BTCurrent = Integer.parseInt(BTDropdown.getValue());
        });

        this.hBox = new HBox(label, breakLengthSpinner, BTDropdown);
        this.hBox.setSpacing(15);
    }

    public void confirm() {
        this.breakLengthMemory = this.breakLengthCurrent;
        this.BTMemory = this.BTCurrent;
        callback.onFastBreakTimeChange(new Pair<>(breakLengthCurrent, BTCurrent));
    }

    public void cancel() {
        this.breakLengthCurrent = this.breakLengthMemory;
        this.BTCurrent = this.BTMemory;
        this.breakLengthSpinner.getValueFactory().setValue(this.breakLengthCurrent);
    }

    public void set(int time, int length) {
        BTDropdown.setValue(time + "");
        breakLengthSpinner.getValueFactory().setValue(length);
    }

    public HBox getContent() {
        return this.hBox;
    }
}
