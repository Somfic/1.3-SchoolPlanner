package gui.simulation;

import data.Schedule;
import data.ScheduleItem;
import data.Student;
import data.StudentGroup;
import logging.Logger;
import org.dyn4j.geometry.Vector2;

public class StudentNpc extends Npc {
    private final Student student;
    private final String studentGroup;

    private Vector2 seat;

    public StudentNpc(Student student, String studentGroup) {
        super(student);
        this.student = student;
        this.studentGroup = studentGroup;
    }

    @Override
    Vector2 getNextMove(Schedule schedule, int period, MapInfo mapInfo) {
        // Get the current period
        ScheduleItem currentPeriod = null;

        for (ScheduleItem item : schedule.getItems()) {
            if (item.getStartPeriod() <= period && item.getEndPeriod() >= period) {
                for (StudentGroup studentGroup : item.getStudentGroups()) {
                    if (studentGroup.getName().equals(this.studentGroup)) {
                        currentPeriod = item;
                        break;
                    }
                }
                break;
            }
        }

        // The student is not in a class
        if (currentPeriod == null) {
            return new Vector2(0, 0);
        }

        // Get a seat
        if(seat == null) {
            seat = mapInfo.getClassRoom(currentPeriod.getClassroom().getName()).getSeat();
        }

        return seat;

    }

    @Override
    void giveUpSeat() {
        this.seat = null;
    }

    public Student getStudent() {
        return student;
    }

}
