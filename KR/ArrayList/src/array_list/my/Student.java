package array_list.my;

public class Student implements Comparable<Student> {
    private String name;
    private int course;
    private double mark;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    public double getMark() {
        return mark;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public int getCourse() {
        return course;
    }

    public Student(String name, int course, double mark) {
        this.name = name;
        this.course = course;
        this.mark = mark;
    }

    public String toString() {
        return("Name: " + name + " " + course + " grade: " + mark + "|");
    }

    @Override
    public int compareTo(Student student) {
        return name.compareTo(student.name) * 10 + new Double(mark).compareTo(student.mark);
    }
}

