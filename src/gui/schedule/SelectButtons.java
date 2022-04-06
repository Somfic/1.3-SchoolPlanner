package gui.schedule;

import data.*;
import gui.components.Dropdown;
import io.FileManager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import logging.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class SelectButtons extends Pane {
    private HBox buttons = new HBox();
    //Buttons
    private TextField startTime = new TextField();
    private TextField endTime = new TextField();
    private Dropdown<Classroom> classRoomSelect = new Dropdown<>();
    private Dropdown<Teacher> teacherSelect = new Dropdown<>();
    private Dropdown<Lesson> courseSelect = new Dropdown<>();
    private MenuButton studentGroupSelect = new MenuButton("Class");
    private ScheduleView scheduleView;
    private ArrayList<Teacher> teachers = new ArrayList<>();
    private ArrayList<StudentGroup> allStudents = new ArrayList<>();
    private ArrayList<StudentGroup> selectedStudents = new ArrayList<>();
    private ArrayList<Lesson> lessons = new ArrayList<>();

    public SelectButtons(ScheduleView scheduleView) {
        for (int i = 0; i < 4; i++) {
            allStudents.add(new StudentGroup(String.valueOf(i + 1)));
        }
        this.scheduleView = scheduleView;
        this.getChildren().add(this.buttons);
        this.buildSelectButtons();
        this.startTime.setPrefWidth(150);
        this.endTime.setPrefWidth(150);
        this.classRoomSelect.setPrefWidth(150);
        this.teacherSelect.setPrefWidth(150);
        this.courseSelect.setPrefWidth(150);
        this.studentGroupSelect.setPrefWidth(150);
    }

    private void buildSelectButtons() {
        this.startTime.setPromptText("Start time");
        this.endTime.setPromptText("End time");

        this.classRoomSelect.setPromptText("Classroom");
        this.teacherSelect.setPromptText("Teacher");
        this.courseSelect.setPromptText("Course");

        this.startTime.setPrefWidth(150);
        this.endTime.setPrefWidth(150);
        this.classRoomSelect.setPrefWidth(150);
        this.teacherSelect.setPrefWidth(150);
        this.courseSelect.setPrefWidth(150);
        this.studentGroupSelect.setPrefWidth(150);

        this.courseSelect.setDropdownItems(new ArrayList<>(Arrays.asList(new Lesson("Math"), new Lesson("2D Graphics"), new Lesson("OGP"),
                new Lesson("OOSD"))));
        this.teacherSelect.setDropdownItems(new ArrayList<>(Arrays.asList(
                new Teacher(Gender.MALE, "Johan"), new Teacher(Gender.MALE, "Pieter"), new Teacher(Gender.MALE, "Edwin"),
                new Teacher(Gender.MALE, "Etienne"), new Teacher(Gender.FEMALE, "Joli"), new Teacher(Gender.FEMALE, "Jessica"))));
        this.classRoomSelect.setDropdownItems(new ArrayList<>(Arrays.asList(new Classroom(30, "Classroom 1", 0),
                new Classroom(30, "Classroom 2", 1), new Classroom(30, "Classroom 3", 2),
                new Classroom(30, "Classroom 4", 3), new Classroom(30, "Classroom 5", 4),
                new Classroom(30, "Classroom 6", 5))));
        this.buildStudentGroups(new ArrayList());

        HBox topRowButtons = new HBox(this.startTime, this.classRoomSelect, this.teacherSelect);                        //Layout
        HBox bottomRowButtons = new HBox(this.endTime, this.courseSelect, this.studentGroupSelect);
        topRowButtons.setSpacing(10);
        bottomRowButtons.setSpacing(10);

        VBox allButtons = new VBox(topRowButtons, bottomRowButtons);
        allButtons.setSpacing(10);

        //Modifications Buttons
        Button apply = new Button("Apply");                                                                      //Buttons
        Button reset = new Button("Reset");
        Button remove = new Button("Remove");
        HBox applyRemoveHBox = new HBox(apply, remove);
        applyRemoveHBox.setSpacing(10);

        VBox applyRemoveResetButtons = new VBox(applyRemoveHBox, reset);                                                //Layout
        applyRemoveResetButtons.setAlignment(Pos.CENTER);
        applyRemoveResetButtons.setSpacing(10);

        apply.setOnAction(event -> {
            try {
                selectedStudents.clear();
                for (int i = 0; i < studentGroupSelect.getItems().size(); i++) {
                    if (((CheckMenuItem) studentGroupSelect.getItems().get(i)).isSelected())
                        selectedStudents.add(allStudents.get(i));
                }
                scheduleView.applyScheduleItem(teacherSelect.getDropdownValue(), selectedStudents, classRoomSelect.getDropdownValue(),
                        Integer.parseInt(startTime.getText()), Integer.parseInt(endTime.getText()), courseSelect.getDropdownValue());
            } catch (Exception e) {
                Logger.warn(e, "Could not apply schedule item");
            }
        });

        reset.setOnAction(event -> {
            try {
                selectedStudents.clear();
                scheduleView.resetSchedule();
            } catch (Exception e) {
                Logger.warn(e, "Could not reset schedule");
            }
        });

        remove.setOnAction(event -> {
            try {
                selectedStudents.clear();
                for (int i = 0; i < studentGroupSelect.getItems().size() - 1; i++) {
                    CheckMenuItem temp = (CheckMenuItem) studentGroupSelect.getItems().get(i);
                    if (temp.isSelected()) {
                        selectedStudents.add(allStudents.get(i));
                    }
                }
//                scheduleView.removeScheduleItem(Arrays.asList(teacherSelect.getDropdownValue()), students, classRoomSelect.getDropdownValue(), Integer.parseInt(startTime.getText()), Integer.parseInt(endTime.getText()), courseSelect.getDropdownValue());
            } catch (Exception e) {
                Logger.warn(e, "Could not remove schedule item");
            }
        });

        //Save and Load
        Button save = new Button("Save");
        save.setOnAction(e -> {
            try {
                FileChooser chooser = buildFileChooser("Save schedule");
                File file = chooser.showSaveDialog(null);

                if (file != null) {
                    String json = Schedule.get().toJson();
                    FileManager.write(file.getAbsolutePath(), json);
                }
            } catch (Exception ex) {
                Logger.warn(ex, "Could not save schedule");

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error exporting schedule");
                alert.setHeaderText("Oh no!");
                alert.setContentText("Could not export schedule.\n\n" + ex.getMessage());
                alert.showAndWait();
            }
        });

        Button load = new Button("Load");
        load.setOnAction(e -> {
            try {
                FileChooser chooser = buildFileChooser("Load schedule");

                File file = chooser.showOpenDialog(null);

                if (file != null && file.exists()) {
                    String json = FileManager.read(file.getAbsolutePath());
                    Schedule schedule = Schedule.fromJson(json);

                    if (schedule == null) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error importing schedule");
                        alert.setHeaderText("Oh no!");
                        alert.setContentText("Could not import schedule.\n\n" + "Schedule is empty");
                        alert.showAndWait();
                    } else {
                        this.scheduleView.setSchedule(schedule);
                    }
                }
            } catch (Exception ex) {
                Logger.warn(ex, "Could not load schedule");

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error importing schedule");
                alert.setHeaderText("Oh no!");
                alert.setContentText("Could not import schedule.\n\n" + ex.getMessage());
                alert.showAndWait();
            }
        });

        VBox saveLoadButtons = new VBox(save, load);
        saveLoadButtons.setSpacing(10);

        // add teacher or subject
        Button addItems = new Button("Add new item");
        addItems.setOnAction(e -> {
            PopUpAddItems.PupUp("Add teacher or Subject", this);
        });
        VBox addButtons = new VBox(addItems);

        //final
        this.buttons.getChildren().addAll(allButtons, applyRemoveResetButtons, saveLoadButtons, addButtons);
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

    public void addTeacher(Teacher teacher) {
        teachers.add(teacher);
        teacherSelect.addAllDropdownItems(teachers);
    }

    public void addSubject(Lesson lesson) {
        lessons.add(lesson);
        courseSelect.addAllDropdownItems(lessons);
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

            tempItem.setOnAction(event -> {
            });
            this.studentGroupSelect.getItems().add(tempItem);
        }
    }

    private FileChooser buildFileChooser(String title) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(title);
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Schedule", "*.schedule"));
        fileChooser.setInitialFileName("schedule.schedule");
        fileChooser.setInitialDirectory(new java.io.File("."));
        return fileChooser;
    }
}