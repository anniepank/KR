public class Exam {
    private String name;
    private Professor professor;

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
}
