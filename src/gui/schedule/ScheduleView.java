package gui.schedule;

import data.*;
import gui.Gui;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.OverrunStyle;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ScheduleView extends Pane {
    private Gui parent;
    private GridPane scheduleGridPane = new GridPane();
    private Label lessonLabel, teacherLabel, studentGroupsLabel, divider1, divider2;
    private ArrayList<Label> labels = new ArrayList<>(5);
    private int width;
    private int classBlockLength;
    private int lunchBreakTime;
    private int lunchBreakLength;
    private int fastBreakTime;
    private int fastBreakLength;
    private LocalTime startTime;

    private Color color = Color.RED;
    private boolean textBrightness = true;

    public SelectButtons selectButtons = new SelectButtons(this);

    public ScheduleView(Gui parent) {
        this.parent = parent;
        this.getChildren().add(this.scheduleGridPane);
        this.scheduleGridPane.setAlignment(Pos.CENTER);
//        this.TESTMETHOD();
        this.startTime = LocalTime.of(8, 00);
        this.buildScheduleTable(startTime);
    }

    private List<ScheduleChangeCallback> callbacks = new ArrayList<>();
    public void addCallback(ScheduleChangeCallback callback) {
        callbacks.add(callback);
    }

    private void TESTMETHOD() {
        //hardcoding a schedule
//        ArrayList<StudentGroup> students = new ArrayList<>();
//        Collections.addAll(students, new StudentGroup("1"), new StudentGroup("2"));
//        this.schedule.add(new ScheduleItem(Arrays.asList(new Teacher(Gender.MALE, "Pieter")), students,
//                new Classroom(30, "Classroom 5", 4), 3, 3, new Lesson("MATH")));
//        this.schedule.add(new ScheduleItem(Arrays.asList(new Teacher(Gender.MALE, "Edwin")), students,
//                new Classroom(30, "Classroom 2", 1), 2, 3, new Lesson("OGP")));
//        this.schedule.add(new ScheduleItem(Arrays.asList(new Teacher(Gender.MALE, "Johan")), students,
//                new Classroom(30, "Classroom 3", 2), 1, 6, new Lesson("2D")));
    }

    public void applyScheduleItem(List<Teacher> teachers, ArrayList<StudentGroup> students, Classroom classroom, int startPeriod, int endPeriod, Lesson lesson) {
        clear();
        Schedule.get().add(new ScheduleItem(teachers, students, classroom, startPeriod, endPeriod, lesson));
        this.addSchedule();
    }

    public void removeScheduleItem(List<Teacher> teachers, ArrayList<StudentGroup> students, Classroom classroom, int startPeriod, int endPeriod, Lesson lesson) {
        clear();
        Schedule.get().remove(new ScheduleItem(teachers, students, classroom, startPeriod, endPeriod, lesson));   //FIXME
        this.addSchedule();
    }

    public void resetSchedule() {
        clear();
        Schedule.get().reset();
        this.addSchedule();
    }

    public void build(int width) {
        //Move into place
        this.width = width;
        this.addSchedule();
    }

    private void addSchedule() {
        for (ScheduleItem scheduleItem : Schedule.get().getItems()) {
            Pane pane = new Pane();
            pane.setMinWidth(215);
            //Height = 50 * (end - start + 1)
            pane.setMinHeight(50 * (scheduleItem.getEndPeriod() - scheduleItem.getStartPeriod() + 1));

            //TranslateX = translateX of the grid + 300 (left column) + 215 (normal column) * classroomIndex
            pane.setTranslateX(this.scheduleGridPane.getTranslateX() + 200 + 215 * scheduleItem.getClassroom().getIndex());
            //TranslateY = translateY of the grid + 50 (cell height) * startPeriod
            pane.setTranslateY(this.scheduleGridPane.getTranslateY() + 50 * scheduleItem.getStartPeriod());

            //Content
            pane.getChildren().add(this.createScheduleItemContent(scheduleItem));

            //Design
            pane.setStyle("-fx-border-width: 1; -fx-border-style: solid");
            pane.setBackground(new Background(new BackgroundFill(this.color, CornerRadii.EMPTY, Insets.EMPTY)));

            pane.setOnMouseClicked(event -> this.selectButtons.selectItem(scheduleItem));

            //Add to view
            this.getChildren().add(pane);
        }

        callbacks.forEach(ScheduleChangeCallback::onChange);
    }

    private VBox createScheduleItemContent(ScheduleItem scheduleItem) {
        VBox vBox = new VBox();
        vBox.setTranslateY(4.5);
        vBox.setMaxWidth(215);

        //Get string of studentGroups
        StringBuilder studentGroups = new StringBuilder();
        for (StudentGroup studentGroup : scheduleItem.getStudentGroups()) {
            studentGroups.append(studentGroup.getName()).append(", ");
        }

//        //Get string of teacherGroups
//        StringBuilder teacherGroups = new StringBuilder();
//        for (StudentGroup teacherGroups : scheduleItem.getStudentGroups()) {
//            teacherGroups.append(teacherGroups.getName()).append(", ");
//        }
        //Delete last 2 characters ", "
        studentGroups.reverse().delete(0, 2).reverse();
//        teacherGroups.reverse().delete(0, 2).reverse();

        //Make labels
        this.lessonLabel = new Label(scheduleItem.getLesson().getName());
        this.teacherLabel = new Label(scheduleItem.getTeachers().toString());
        this.studentGroupsLabel = new Label(studentGroups.toString());
        this.divider1 = new Label("|");
        this.divider2 = new Label("|");

        if (!labels.isEmpty()) {
            labels.clear();
        }
        this.labels.add(this.lessonLabel);
        this.labels.add(this.teacherLabel);
        this.labels.add(this.studentGroupsLabel);
        this.labels.add(this.divider1);
        this.labels.add(this.divider2);

        // Setting font color
        if (!this.textBrightness)
            for (Label label : labels)
                label.setTextFill(Color.BLACK);
        else
            for (Label label : labels)
                label.setTextFill(Color.LIGHTGRAY);

        //Setting style
        Font font = Font.font("Veranda", FontWeight.BOLD, 18);
        for (Label label : labels)
            label.setFont(font);


        divider1.setMaxWidth(3);
        divider2.setMaxWidth(3);

        lessonLabel.setTextOverrun(OverrunStyle.CLIP);
        teacherLabel.setTextOverrun(OverrunStyle.CLIP);
        studentGroupsLabel.setTextOverrun(OverrunStyle.WORD_ELLIPSIS);

        //Add labels to VBox and limit their length according to the length of the scheduleItem
        switch (scheduleItem.getEndPeriod() - scheduleItem.getStartPeriod()) {
            case 0:     //Small size
                lessonLabel.setMaxWidth(65);

                teacherLabel.setMaxWidth(65);
                studentGroupsLabel.setMaxWidth(65);

                HBox smallHBox = new HBox(lessonLabel, divider1, teacherLabel, divider2, studentGroupsLabel);
                smallHBox.setSpacing(3);
                vBox.getChildren().add(smallHBox);
                break;

            case 1:     //Medium size
                lessonLabel.setMaxWidth(101);
                teacherLabel.setMaxWidth(101);
                HBox mediumHBox = new HBox(lessonLabel, divider1, teacherLabel);
                mediumHBox.setSpacing(5);
                vBox.getChildren().addAll(mediumHBox, studentGroupsLabel);
                break;

            default:    //Full size
                vBox.getChildren().addAll(lessonLabel, teacherLabel, studentGroupsLabel);
        }

        return vBox;
    }

    public void clear() {
        Object[] children = this.getChildren().toArray();
        for (Object child : children)
            if (child.getClass().getName().equals("javafx.scene.layout.Pane"))
                this.getChildren().remove(child);
    }

    private void buildScheduleTable(LocalTime startTime) {
        //Top row
        this.scheduleGridPane.addRow(0,
                new ScheduleCell("Class block / Time", 2, true),
                new ScheduleCell("Classroom 1", 2, false),
                new ScheduleCell("Classroom 2", 2, false),
                new ScheduleCell("Classroom 3", 2, false),
                new ScheduleCell("Classroom 4", 2, false),
                new ScheduleCell("Classroom 5", 2, false),
                new ScheduleCell("Classroom 6", 2, false));

        //Left column
        //Generate times
        ArrayList<String> times = new ArrayList<>();

        LocalTime endTime;

        for (int i = 1; i <= 10; i++) {
            endTime = ChronoUnit.MINUTES.addTo(this.startTime, classBlockLength);
            times.add(i + "\t" + this.startTime + " - " + endTime);
            if (i == fastBreakTime) {
                this.startTime = ChronoUnit.MINUTES.addTo(endTime, fastBreakLength);
            } else if (i == lunchBreakTime) {
                this.startTime = ChronoUnit.MINUTES.addTo(endTime, lunchBreakLength);
            } else {
                this.startTime = endTime;
            }
        }

        //Generate rows: time + 6 blank cells
        int rowIndex = 0;
        int type = 1;
        for (String time : times) {
            type = (type + 1) % 2;
            rowIndex++;

            int columnIndex = 0;
            this.scheduleGridPane.add(new ScheduleCell(time, type, true), columnIndex, rowIndex);

            for (columnIndex++; columnIndex < 7; columnIndex++) {
                this.scheduleGridPane.add(new ScheduleCell(type, false), columnIndex, rowIndex);
            }
        }
    }

    public void updateScheduleTime(int classBlockLength, int lunchBreakTime, int lunchBreakLength, int fastBreakTime,
                                   int fastBreakLength, LocalTime startTime) {
        this.fastBreakLength = fastBreakLength;
        this.fastBreakTime = fastBreakTime;
        this.lunchBreakLength = lunchBreakLength;
        this.lunchBreakTime = lunchBreakTime;
        this.classBlockLength = classBlockLength;
        this.scheduleGridPane.getChildren().clear();
        if (startTime != null)
            this.startTime = startTime;
        this.buildScheduleTable(startTime);
    }

    /**
     * Change color of the schedule items visable and yet to be made.
     * @param color = theme color of schedule item.
     * @param brightness = black text is true, light gray text if false.
     */
    public void updateColor(Color color, boolean brightness) {
        this.color = color;
        this.textBrightness = brightness;
        this.addSchedule();
    }

    public GridPane getGridPane() {
        return this.scheduleGridPane;
    }

    public void setSchedule(Schedule schedule) {
        Schedule.set(schedule);
        this.addSchedule();
    }
}