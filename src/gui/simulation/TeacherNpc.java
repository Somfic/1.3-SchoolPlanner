package gui.simulation;

import data.Schedule;
import data.ScheduleItem;
import data.Teacher;
import logging.Logger;
import org.dyn4j.geometry.Vector2;

public class TeacherNpc extends Npc {

    private final Teacher teacher;

    public TeacherNpc(Teacher teacher) {
        super(teacher);
        this.teacher = teacher;
    }

    @Override
    void calculateTarget(Schedule schedule, int period, MapInfo mapInfo) {
        if(this.target != null) {
            return;
        }

        // Get the current period
        ScheduleItem currentPeriod = null;

        for (ScheduleItem item : schedule.getItems()) {
            if (item.getStartPeriod() <= period && item.getEndPeriod() >= period && item.getTeacher().equals(teacher)) {
                currentPeriod = item;
                break;
            }
        }

        if (currentPeriod == null) {
            // Not in a lesson, get a break area seat
            this.target = mapInfo.getTeacherArea().getSeat();
        } else {
            // Get a seat in the classroom
            this.target = mapInfo.getClassRoom(currentPeriod.getClassroom().getName()).getTeacherSeat();
        }
    }

    public Teacher getTeacher() {
        return teacher;
    }
}
