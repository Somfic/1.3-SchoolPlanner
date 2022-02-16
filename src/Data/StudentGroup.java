package Data;
import java.util.ArrayList;
import java.util.List;

public class StudentGroup {
	private List<Student> students;
	private String name;

	public StudentGroup(String name) {
		this.students = new ArrayList<>();
        this.name = name;
	}
}
