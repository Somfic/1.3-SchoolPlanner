package data;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StudentGroup {
	private List<Student> students;
	private String name;

	public StudentGroup() {

	}

	public StudentGroup(String name) {
        this.name = name;
		this.students = new ArrayList<>();

        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            Gender gender;
            if (random.nextBoolean()) {
                gender = Gender.MALE;
            } else {
                gender = Gender.FEMALE;
            }
			this.students.add(new Student(gender, createName(), (int) (Math.random() * 99999)));
		}

        this.name = name;
	}

    public String createName() {
        Random r = new Random();
        String name = r.ints(48, 123)
                .filter(num -> (num < 58 || num > 64) && (num < 91 || num > 96))
                .limit(10)
                .mapToObj(c -> (char) c).collect(StringBuffer::new, StringBuffer::append, StringBuffer::append)
                .toString();
        name.toLowerCase();
        return name;
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

    @Override
    public String toString() {
        String s = "";
        for (Student student : students) {
            s+=student + " ";
        }
        return s;
    }
}
