package Gui;

import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Dropdown<T> extends ComboBox<T> {
    private EventHandler<Event> eventHandler;
    private T selectedValue = null;

    public Dropdown() {
        super();

        // Show the text field
        this.setEditable(true);

        // Handle every keypress
        eventHandler = new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                //todo idk
            }
        };
        this.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            // Get the search query from the editor
            this.query = this.getEditor().getText();

            // If the query is not empty, filter the list
            if (this.query.length() > 0) {
                // Filter the list based on the query
                FilteredList<T> filteredList = new FilteredList<T>(FXCollections.observableArrayList(this.allItems));
                filteredList.setPredicate(item -> item.toString().toLowerCase().contains(this.query.toLowerCase()));

                // If the filtered list is not empty, show the results
                if (filteredList.size() > 0) {
                    this.show();
                    this.getItems().setAll(filteredList);
                } else {
                    // No search results, hide the list
                    this.hide();
                }
            } else {
                // No query, hide the dropdown
                this.hide();
                this.getItems().setAll(this.allItems);
            }
        });

        // Handle every item selection
        this.setOnAction(event -> {

            // If the selected item is valid, redirect the event to the setOnDropdownAction() method
            if (allItems.contains(this.getValue())) {
                this.selectedValue = this.getValue();
                eventHandler.handle(event);
            }
        });
    }

    private String query = "";
    private List<T> allItems = new ArrayList<>();

    public void setDropdownItems(T... items) {
        allItems = Arrays.asList(items);
        this.getItems().setAll(allItems);
    }

    public void setOnDropdownAction(EventHandler<Event> eventHandler) {
        this.eventHandler = eventHandler;
    }

    public T getDropdownValue() {
        return this.selectedValue;
    }
}