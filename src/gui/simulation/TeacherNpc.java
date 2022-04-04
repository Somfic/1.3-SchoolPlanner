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
    Vector2 getNextMove(Schedule schedule, int period, MapInfo mapInfo) {
        // Get the current period
        ScheduleItem currentPeriod = null;

        for (ScheduleItem item : schedule.getItems()) {
            Logger.debug("Checking item: " + item.getLesson().getName() + " - " + item.getTeacher() + " - " + item.getStartPeriod() + " / " + item.getEndPeriod());
            if (item.getStartPeriod() <= period && item.getEndPeriod() >= period && item.getTeacher().equals(teacher)) {
                currentPeriod = item;
                break;
            }
        }

        // The student is not in a class
        if (currentPeriod == null) {
            Logger.warn("Teacher " + teacher.getName() + " is not in a class");
            return new Vector2(0, 0);
        }

        // Get a seat
        return mapInfo.getClassRoom(currentPeriod.getClassroom().getName()).getTeacherSeat();
    }

    public Teacher getTeacher() {
        return teacher;
    }
}
