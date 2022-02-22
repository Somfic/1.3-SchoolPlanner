package Gui;

import Data.*;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class SelectButtons extends Pane {
    private HBox buttons = new HBox();

    public SelectButtons() {
        this.getChildren().add(this.buttons);
        this.buildSelectButtons();
    }

    private void buildSelectButtons() {
        //Buttons
        TextField startTime = new TextField();                                                      //Time
        TextField endTime = new TextField();
        startTime.setPromptText("Start time");
        endTime.setPromptText("End time");

        ComboBox<Classroom> classRoomSelect = new ComboBox<>();                                     //ComboBoxes
        ComboBox<Teacher> teacherSelect = new ComboBox<>();
        ComboBox<Lesson> courseSelect = new ComboBox<>();
        ComboBox<StudentGroup> classSelect = new ComboBox<>();

        classRoomSelect.setPromptText("Classroom");
        teacherSelect.setPromptText("Teacher  ");
        courseSelect.setPromptText("Course     ");
        classSelect.setPromptText("Class       ");

        classRoomSelect.getItems().add(new Classroom(30, "Classroom 1", 0));
        classRoomSelect.getItems().add(new Classroom(30, "Classroom 2", 0));
        classRoomSelect.getItems().add(new Classroom(30, "Classroom 3", 0));
        classRoomSelect.getItems().add(new Classroom(30, "Classroom 4", 0));
        classRoomSelect.getItems().add(new Classroom(30, "Classroom 5", 0));
        classRoomSelect.getItems().add(new Classroom(30, "Classroom 6", 0));

        HBox topRowButtons = new HBox(startTime, classRoomSelect, teacherSelect);                   //Layout
        HBox bottomRowButtons = new HBox(endTime, courseSelect, classSelect);
        topRowButtons.setSpacing(10);
        bottomRowButtons.setSpacing(10);

        VBox allButtons = new VBox(topRowButtons, bottomRowButtons);
        allButtons.setSpacing(10);

        //Modifications Buttons
        Button apply = new Button("Apply");                                                     //Buttons
        Button reset = new Button("Reset");
        Button remove = new Button("Remove");
        HBox applyRemoveHBox = new HBox(apply, remove);
        applyRemoveHBox.setSpacing(10);

        VBox applyRemoveResetButtons = new VBox(applyRemoveHBox, reset);                            //Layout
        applyRemoveResetButtons.setAlignment(Pos.CENTER);
        applyRemoveResetButtons.setSpacing(10);

        //Save and Load
        Button save = new Button("Save");
        Button load = new Button("Load");

        VBox saveLoadButtons = new VBox(save, load);
        saveLoadButtons.setSpacing(10);

        //final
        this.buttons.getChildren().addAll(allButtons, applyRemoveResetButtons, saveLoadButtons);
        this.buttons.setSpacing(10);
        this.buttons.setPadding(new javafx.geometry.Insets(10, 10, 10, 10));
    }
}
