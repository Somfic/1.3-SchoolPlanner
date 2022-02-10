package Data;

public class ScheduleItem {
	private Teacher teacher;
	private StudentGroup Class;
	private Classroom Classroom;
	private int startPeriod;
	private int endPeriod;
	private StudentGroup[] studentGroup;
	private Classroom classroom;
	private Lesson lesson;

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

}
