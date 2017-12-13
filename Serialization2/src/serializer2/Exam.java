package serializer2;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;

public class Exam implements Serializable {
    private String name;
    private Professor professor;

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

    public Exam(String name, Professor prof) {
        this.name = name;
        this.professor = prof;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public String toString(){
        return new String(AppLocale.getString( AppLocale.creation ) + ": " + getCreationDate() );
    }
}
