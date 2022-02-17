package Data;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ScheduleItem {
	private Teacher teacher;
	private StudentGroup Class;
	private int startPeriod;
	private int endPeriod;
	private List<StudentGroup> studentGroup;
	private Classroom classroom;
	private Lesson lesson;
	private ArrayList<StudentGroup> studentgroups;
	private ArrayList<Classroom> classrooms;
	private ArrayList<Teacher> teachers;
	private ArrayList<Lesson> lessons;

    public ScheduleItem(Teacher teacher, StudentGroup aClass, Classroom classroom, int startPeriod, int endPeriod, Lesson lesson) {
        this.teacher = teacher;
        this.Class = aClass;
        this.classroom = classroom;
        this.startPeriod = startPeriod;
        this.endPeriod = endPeriod;
        this.studentGroup = new ArrayList<>();
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

