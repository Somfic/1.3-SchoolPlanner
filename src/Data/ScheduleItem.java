package Data;

import java.util.ArrayList;
import java.util.List;

public class ScheduleItem {
	private Teacher teacher;
	private Classroom classroom;
	private int startPeriod;
	private int endPeriod;
	private List<StudentGroup> studentGroups;
	private Lesson lesson;

    public ScheduleItem() {

    }

    public ScheduleItem(Teacher teacher, List<StudentGroup> studentGroups, Classroom classroom, int startPeriod, int endPeriod, Lesson lesson) {
        this.teacher = teacher;
        this.studentGroups = studentGroups;
        this.classroom = classroom;
        this.startPeriod = startPeriod;
        this.endPeriod = endPeriod;
        this.lesson = lesson;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public List<StudentGroup> getStudentGroups() {
        return this.studentGroups;
    }

    public void setStudentGroups(List<StudentGroup> studentGroups) {
        this.studentGroups = studentGroups;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public int getStartPeriod() {
        return startPeriod;
    }

    public void setStartPeriod(int startPeriod) {
        this.startPeriod = startPeriod;
    }

    public int getEndPeriod() {
        return endPeriod;
    }

    public void setEndPeriod(int endPeriod) {
        this.endPeriod = endPeriod;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }
}
