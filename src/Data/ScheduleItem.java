package Data;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class ScheduleItem {
	private Teacher teacher;
	private int startPeriod;
	private int endPeriod;
	private Classroom classroom;
	private Lesson lesson;
	private ArrayList<StudentGroup> studentGroups;
	private ArrayList<Classroom> classrooms;
	private ArrayList<Teacher> teachers;
	private ArrayList<Lesson> lessons;

    public ScheduleItem() {

    }

    public ScheduleItem(ArrayList<Teacher> teachers, ArrayList<StudentGroup> studentGroups, Classroom classroom, int startPeriod, int endPeriod, Lesson lesson) {
        this.teachers = teachers;
        this.classroom = classroom;
        this.startPeriod = startPeriod;
        this.endPeriod = endPeriod;
        this.lesson = lesson;
        this.studentGroups = studentGroups;
    }

    @JsonIgnore
    public boolean isValid() {
		return false;
	}

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
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

    public List<StudentGroup> getStudentGroups() {
        return studentGroups;
    }

    public void setStudentGroups(ArrayList<StudentGroup> studentGroups) {
        this.studentGroups = studentGroups;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public ArrayList<Classroom> getClassrooms() {
        return classrooms;
    }

    public void setClassrooms(ArrayList<Classroom> classrooms) {
        this.classrooms = classrooms;
    }

    public ArrayList<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(ArrayList<Teacher> teachers) {
        this.teachers = teachers;
    }

    public ArrayList<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(ArrayList<Lesson> lessons) {
        this.lessons = lessons;
    }
}