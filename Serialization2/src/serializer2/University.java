package serializer2;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class University implements Serializable {

    List<Exam> exams = new ArrayList<>();
    List<Enrollee> enrollees = new ArrayList<>();
    List<Faculty> faculties = new ArrayList<>();
    List<Professor> professors = new ArrayList<>();

    public final Date creationDate = new Date();

    public String getCreationDate() {
        DateFormat dateFormatter = DateFormat.getDateTimeInstance(
                DateFormat.DEFAULT, DateFormat.DEFAULT, AppLocale.get());
        String dateOut = dateFormatter.format(creationDate);
        return dateOut;
    }

    public List<Exam> getExams() {
        return exams;
    }

    public Exam createExam(String name, Professor prof) {
        Exam exam = new Exam(name, prof);
        this.exams.add(exam);
        return exam;
    }

    public Enrollee createEnrollee(String name, Faculty faculty) {
        Enrollee e = new Enrollee(name);
        this.enrollees.add(e);
        faculty.addEnrolleeToWaitList(e);
        return e;
    }

    public Faculty createFaculty(String faculty, int numberOfStudents) {
        Faculty f = new Faculty(faculty, numberOfStudents);
        this.faculties.add(f);
        return f;
    }

    public Professor createProfessor(String name) {
        Professor p = new Professor(name);
        this.professors.add(p);
        return p;
    }

    public void mark(Enrollee e,Exam ex, int mark) {
        e.getExamsAndMarks().put(ex, mark);

    }


}
