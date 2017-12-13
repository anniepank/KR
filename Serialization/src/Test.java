import java.io.IOException;
import java.util.List;

public class Test {

    public static University createUni() {

        University uni = new University();
        Faculty famcs = uni.createFaculty("famcs", 2);
        Enrollee a = uni.createEnrollee("Steven", famcs);
        Enrollee b = uni.createEnrollee("Lewis", famcs);
        Enrollee c = uni.createEnrollee("Antony", famcs);

        Professor prof = uni.createProfessor("King");

        Exam math = uni.createExam("math", prof);
        Exam language = uni.createExam("language", prof);
        Exam physics = uni.createExam("physics", prof);

        uni.mark(a, language, 5);
        uni.mark(a, math, 5);
        uni.mark(a, physics, 5);

        uni.mark(b, language, 4);
        uni.mark(b, math, 2);
        uni.mark(b, physics, 2);

        uni.mark(c, language, 4);
        uni.mark(c, math, 4);
        uni.mark(c, physics, 5);

        famcs.enroll();

        return uni;
    }

    public static void main(String[] args) {

        try {
            Connector connector = new Connector("band.dat");
            connector.write(createUni());

            University uni = connector.read();

            for (Faculty f : uni.faculties) {
                for (Enrollee e: f.getEnrollees()) {
                    System.out.println(e);
                }
            }
        }
        catch (IOException e) {
            System.err.println(e);
        } catch (ClassNotFoundException e) {
            System.err.println(e);
        }
    }
}
