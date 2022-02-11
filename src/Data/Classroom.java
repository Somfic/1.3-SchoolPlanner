package Data;

public class Classroom {
	private int capacity;
	private String name;
    private int index;

    public Classroom() {

    }

    public Classroom(int capacity, String name, int index) {
        this.capacity = capacity;
        this.name = name;
        this.index = index;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
