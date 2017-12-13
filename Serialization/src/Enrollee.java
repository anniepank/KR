import java.util.HashMap;
import java.util.Map;

public class Enrollee {
    private String name;
    private Map<Exam, Integer> examsAndMarks = new HashMap<>();

    public String getName() {
        return name;
    }

    public Map<Exam, Integer> getExamsAndMarks() {
        return examsAndMarks;
    }

    public Enrollee(String name) {
        this.name = name;
    }

    public double getAverage() {
        double a = 0;
        for (Exam i : examsAndMarks.keySet()) {
            a += examsAndMarks.get(i);
        }
        a /= examsAndMarks.size();
        return a;
    }

    public String toString() {
        return name;
    }
}
