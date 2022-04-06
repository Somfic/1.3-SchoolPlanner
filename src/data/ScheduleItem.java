package data;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class ScheduleItem {
    private int startPeriod;
    private int endPeriod;
    private Classroom classroom;
    private Lesson lesson;
    private List<StudentGroup> studentGroups;
    private List<Classroom> classrooms;
    private Teacher teacher;
    private List<Lesson> lessons;

    public ScheduleItem() {
    }

    public ScheduleItem(Teacher teacher, List<StudentGroup> studentGroups, Classroom classroom, int startPeriod, int endPeriod, Lesson lesson) {
        this.teacher = teacher;
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

    public List<Classroom> getClassrooms() {
        return classrooms;
    }

    public void setClassrooms(ArrayList<Classroom> classrooms) {
        this.classrooms = classrooms;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teachers) {
        this.teacher = teachers;
    }

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(ArrayList<Lesson> lessons) {
        this.lessons = lessons;
    }

    @Override
    public String toString() {
        return "ScheduleItem{" +
                ", teacher=" + teacher +
                ", startPeriod=" + startPeriod +
                ", endPeriod=" + endPeriod +
                ", classroom=" + classroom +
                ", lesson=" + lesson +
                ", studentGroups=" + studentGroups +
                ", classrooms=" + classrooms +
                ", lessons=" + lessons +
                '}';
    }
}