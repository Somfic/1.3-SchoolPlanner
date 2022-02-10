package Gui;

import javafx.geometry.Pos;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.Collections;

public class ScheduleView extends BorderPane {
    private final GridPane scheduleGridPane = new GridPane();

    public ScheduleView () {
        super.setPrefSize(1920, 1080);
        this.setCenter(this.scheduleGridPane);
        this.scheduleGridPane.setAlignment(Pos.CENTER);

        this.buildSchedule();
    }

    private void buildSchedule () {
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
        Collections.addAll(times,
                ("1  / 08:00 - 09:00"),
                ("2  / 09:00 - 10:00"),
                ("3  / 10:00 - 11:00"),
                ("4  / 11:00 - 12:00"),
                ("5  / 12:30 - 13:30"),
                ("6  / 13:30 - 14:30"),
                ("7  / 14:30 - 15:30"),
                ("8  / 16:00 - 17:00"),
                ("9  / 17:00 - 18:00"),
                ("10 / 18:00 - 19:00"));

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
}