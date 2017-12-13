import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class Faculty {
    private String name;
    public int numberOfStudents;
    private List<Enrollee> waitList = new LinkedList<>();
    private List<Enrollee> enrollees = new LinkedList<>();

    public List<Enrollee> getEnrollees() {
        return enrollees;
    }

    public List<Enrollee> getWaitList() {
        return waitList;
    }


    public String getName() {
        return name;
    }

    public Faculty(String name, int numberOfStudents) {
        this.name = name;
        this.numberOfStudents = numberOfStudents;
    }

    public void addEnrollee(Enrollee en) {
        enrollees.add(en);
    }

    public void addEnrolleeToWaitList(Enrollee en) {
        waitList.add(en);
    }

    public void enroll() {
        Collections.sort(waitList, new Comparator<Enrollee>() {
            @Override
            public int compare(Enrollee t1, Enrollee t2) {
                return (int)(-t1.getAverage() + t2.getAverage());
            }
        });
        for (int i = 0; i < numberOfStudents; i++) {
            this.addEnrollee(waitList.get(i));
        }
    }
}
