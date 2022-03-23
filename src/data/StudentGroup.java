package data;
import java.util.ArrayList;
import java.util.List;

public class StudentGroup {
	private List<Student> students;
	private String name;

	public StudentGroup() {

	}

	public StudentGroup(String name) {
        this.name = name;
		this.students = new ArrayList<>();
        this.name = name;
	}

	public void addStudent(Student student) {
		this.students.add(student);
	}

	public void addStudents(List<Student> students) {
		this.students.addAll(students);
	}

	public List<Student> getStudents() {
		return this.students;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
