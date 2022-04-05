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

    public StudentNpc(Student student, String studentGroup) {
        super(student);
        this.student = student;
        this.studentGroup = studentGroup;
    }

    @Override
    void calculateTarget(Schedule schedule, int period, MapInfo mapInfo) {
        if(this.target != null) {
            return;
        }

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


        if (currentPeriod == null) {
            // The student is not in a class
            this.target = mapInfo.getBreakArea().getSeat();
        } else {
            this.target = mapInfo.getClassRoom(currentPeriod.getClassroom().getName()).getSeat();
        }
    }

    public Student getStudent() {
        return student;
    }

}
