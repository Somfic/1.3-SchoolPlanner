package Gui;

import Data.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class SelectButtons extends Pane {
    private HBox buttons = new HBox();
    //Buttons
    private TextField startTime = new TextField();                                                      //Time
    private TextField endTime = new TextField();

    private ComboBox<Classroom> classRoomSelect = new ComboBox<>();                                     //ComboBoxes
    private ComboBox<Teacher> teacherSelect = new ComboBox<>();
    private ComboBox<Lesson> courseSelect = new ComboBox<>();
    private MenuButton studentGroupSelect = new MenuButton("Class       ");

    public SelectButtons() {
        this.getChildren().add(this.buttons);
        this.buildSelectButtons();
    }

    private void buildSelectButtons() {
        this.startTime.setPromptText("Start time");
        this.endTime.setPromptText("End time");

        this.classRoomSelect.setPromptText("Classroom");
        this.teacherSelect.setPromptText("Teacher  ");
        this.courseSelect.setPromptText("Course     ");

        this.classRoomSelect.getItems().addAll(new Classroom(30, "Classroom 1", 0), new Classroom(30, "Classroom 2", 0), new Classroom(30, "Classroom 3", 0), new Classroom(30, "Classroom 4", 0), new Classroom(30, "Classroom 5", 0), new Classroom(30, "Classroom 6", 0));


        this.buildStudentGroups(new ArrayList<>());

        HBox topRowButtons = new HBox(this.startTime, this.classRoomSelect, this.teacherSelect);                   //Layout
        HBox bottomRowButtons = new HBox(this.endTime, this.courseSelect, this.studentGroupSelect);
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
        this.buttons.setPadding(new Insets(10, 10, 10, 10));
    }

    public void selectItem(ScheduleItem scheduleItem) {
        this.startTime.setText(scheduleItem.getStartPeriod() + "");
        this.endTime.setText(scheduleItem.getEndPeriod() + "");

        this.classRoomSelect.setValue(scheduleItem.getClassroom());
        this.teacherSelect.setValue(scheduleItem.getTeacher());
        this.courseSelect.setValue(scheduleItem.getLesson());
        this.buildStudentGroups((ArrayList<StudentGroup>) scheduleItem.getStudentGroups());
    }

    private void buildStudentGroups(ArrayList<StudentGroup> selectedGroups) {
        this.studentGroupSelect.getItems().clear();

        for (int i = 0; i < 4; i++) {
            String name = (i + 1) + "";
            CheckMenuItem tempItem = new CheckMenuItem(name);

            for (StudentGroup selectedGroup : selectedGroups) {
                if (selectedGroup.getName().equals(name))
                tempItem.setSelected(true);
            }

            tempItem.setOnAction(event -> System.out.println("ye"));
            this.studentGroupSelect.getItems().add(tempItem);
        }
    }
}