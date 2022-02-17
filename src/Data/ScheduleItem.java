package Data;

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

}
