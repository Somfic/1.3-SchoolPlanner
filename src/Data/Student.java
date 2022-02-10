package Data;

public class Student extends Person {
    private int studentNumber;

    public Student(Gender gender, String name, int studentNumber) {
        super(gender, name);
        this.studentNumber = studentNumber;
    }
}
