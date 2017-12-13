package serializer2;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Enrollee implements Serializable {
    private String name;
    private Map<Exam, Integer> examsAndMarks = new HashMap<>();

    public final Date creationDate = new Date();
    public String getCreationDate() {
        DateFormat dateFormatter = DateFormat.getDateTimeInstance(
                DateFormat.DEFAULT, DateFormat.DEFAULT, AppLocale.get());
        String dateOut = dateFormatter.format(creationDate);
        return dateOut;
    }

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
        return new String(AppLocale.getString(AppLocale.name) + " " + name +
                AppLocale.getString( AppLocale.creation ) + ": " + getCreationDate() );
    }
}
