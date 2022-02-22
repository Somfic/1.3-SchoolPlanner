package Gui;
import Data.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.OverrunStyle;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class ScheduleView extends Pane {
    private GridPane scheduleGridPane = new GridPane();
    private Schedule schedule = new Schedule();
    private int width;

    public ScheduleView () {
        //super.setPrefSize(1920, 1080);
        this.getChildren().add(this.scheduleGridPane);
        this.scheduleGridPane.setAlignment(Pos.CENTER);

        this.TESTMETHOD();
        this.buildScheduleTable();
    }

    private void TESTMETHOD() {
        //hardcoding a schedule
        ArrayList<StudentGroup> students = new ArrayList<>();
        Collections.addAll(students, new StudentGroup("B3"), new StudentGroup("B4"));
        this.schedule.add(new ScheduleItem(new Teacher(Gender.MALE, "Pieter"), students, new Classroom(30, "Classroom 5", 4), 3, 3, new Lesson("MATH")));
        this.schedule.add(new ScheduleItem(new Teacher(Gender.MALE, "Edwin"), students, new Classroom(30, "Classroom 2", 1), 2, 3, new Lesson("OGP")));
        this.schedule.add(new ScheduleItem(new Teacher(Gender.MALE, "Johan"), students, new Classroom(30, "Classroom 3", 2), 1, 6, new Lesson("2D")));
    }

    public void build(int width) {
        //Move into place
        this.width = width;
        //this.scheduleGridPane.setTranslateX((1920f - this.width) / 2);
        //this.scheduleGridPane.setTranslateY(150);

        this.addSchedule();
    }

    private void addSchedule() {
        for (ScheduleItem scheduleItem : schedule.getItems()) {
            Pane pane = new Pane();
            pane.setMinWidth(215);
            pane.setMinHeight(50 * (scheduleItem.getEndPeriod() - scheduleItem.getStartPeriod() + 1));                          //Height = 50 * (end - start + 1)

            pane.setTranslateX(this.scheduleGridPane.getTranslateX() + 200 + 215 * scheduleItem.getClassroom().getIndex());     //TranslateX = translateX of the grid + 300 (left column) + 215 (normal column) * classroomIndex
            pane.setTranslateY(this.scheduleGridPane.getTranslateY() + 50 * scheduleItem.getStartPeriod());                     //TranslateY = translateY of the grid + 50 (cell height) * startPeriod

            //Content
            pane.getChildren().add(this.createScheduleItemContent(scheduleItem));

            //Design
            pane.setStyle("-fx-border-width: 1; -fx-border-style: solid");
            pane.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));

            //Add to view
            this.getChildren().add(pane);
        }
    }

    private VBox createScheduleItemContent(ScheduleItem scheduleItem) {
        VBox vBox = new VBox();
        vBox.setTranslateY(4.5);
        vBox.setMaxWidth(215);

        //Get string of studentGroups
        StringBuilder studentGroups = new StringBuilder();
        for (StudentGroup studentGroup : scheduleItem.getStudentGroups()) {
            studentGroups.append(studentGroup.getName()).append(" -");
        }
        //Delete last 2 characters " -"
        studentGroups.reverse().delete(0, 2).reverse();

        //Make labels
        Label lessonLabel = new Label(scheduleItem.getLesson().getName());
        Label teacherLabel = new Label(scheduleItem.getTeacher().getName());
        Label studentGroupsLabel = new Label(studentGroups.toString());
        Label divider1 = new Label("|");
        Label divider2 = new Label("|");

        //Setting style
        Font font = Font.font("Veranda", FontWeight.BOLD, 18);
        lessonLabel.setFont(font);
        teacherLabel.setFont(font);
        studentGroupsLabel.setFont(font);
        divider1.setFont(font);
        divider2.setFont(font);

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

    private void buildScheduleTable() {
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

        LocalTime startTime = LocalTime.of(8, 00);

        /**
         * get these values from SettingsScreen
         */
        int classBlockLength = 60;
        int lunchBreakTime = 3;
        int lunchBreakLength = 60;
        int fastBreakTime = 6;
        int fastBreakLength = 60;
        LocalTime endTime;

        for(int i = 1; i<=10; i++) {
            endTime = ChronoUnit.MINUTES.addTo(startTime,classBlockLength);
            times.add(i + "\t" + startTime + " - " + endTime);
            if(i == fastBreakTime) {
                startTime = ChronoUnit.MINUTES.addTo(endTime,fastBreakLength);
            } else if (i == lunchBreakTime) {
                startTime = ChronoUnit.MINUTES.addTo(endTime,lunchBreakLength);
            } else {
                startTime = endTime;
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

    public GridPane getGridPane () {
        return this.scheduleGridPane;
    }
}