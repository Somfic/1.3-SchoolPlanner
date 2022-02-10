package Data;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ScheduleItem {
	private Teacher teacher;
	private StudentGroup Class;
	private Classroom Classroom;
	private int startPeriod;
	private int endPeriod;
	private StudentGroup[] studentGroup;
	private Classroom classroom;
	private Lesson lesson;
	private ArrayList<StudentGroup> studentgroups;
	private ArrayList<Classroom> classrooms;
	private ArrayList<Teacher> teachers;
	private ArrayList<Lesson> lessons;

    public ScheduleItem(Teacher teacher, StudentGroup aClass, Data.Classroom classroom, int startPeriod, int endPeriod, StudentGroup[] studentGroup, Data.Classroom classroom1, Lesson lesson) {
        this.teacher = teacher;
        Class = aClass;
        Classroom = classroom;
        this.startPeriod = startPeriod;
        this.endPeriod = endPeriod;
        this.studentGroup = studentGroup;
        this.classroom = classroom1;
        this.lesson = lesson;
    }

    public boolean isValid() {
		return false;
	}










	public ArrayList<StudentGroup> getStudentGroups(){
        return studentgroups;
    }
    public ArrayList<Classroom> getClassRooms(){
        return classrooms;
    }
    public ArrayList<Teacher> getTeachers(){
        return teachers;
    }
    public ArrayList<Lesson> getLessons(){
        return lessons;
    }

}

