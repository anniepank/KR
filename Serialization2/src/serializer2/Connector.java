package serializer2;

import java.io.*;

public class Connector {

    private String filename;

    public Connector(String filename) {
        this.filename = filename;
    }

    public void write(University uni) throws IOException {
        FileOutputStream fos = new FileOutputStream(filename);
        try (ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(uni);
        /*
            //writing professors

            oos.writeInt(uni.professors.size());
            for (Professor prof : uni.professors) {
                oos.writeObject(prof);
            }

            //writing exams

            oos.writeInt(uni.exams.size());
            for (Exam e : uni.exams) {
                oos.writeObject(e.getName());
                oos.writeInt(uni.professors.indexOf(e.getProfessor()));

            }

            //writing enrollees

            oos.writeInt(uni.enrollees.size());
            for (Enrollee e : uni.enrollees) {
                oos.writeObject(e.getName());
                oos.writeInt(e.getExamsAndMarks().size());
                for(Exam exam : e.getExamsAndMarks().keySet()) {
                    oos.writeInt(uni.exams.indexOf(exam));
                    oos.writeInt(e.getExamsAndMarks().get(exam));
                }
            }

            //writing faculties

            oos.writeInt(uni.faculties.size());
            for (Faculty f : uni.faculties) {
                oos.writeObject(f.getName());
                oos.writeInt(f.numberOfStudents);
                oos.writeInt(f.getWaitList().size());
                for (Enrollee e : f.getWaitList()) {
                    oos.writeInt(uni.enrollees.indexOf(e));
                }

                oos.writeInt(f.getEnrollees().size());
                for (Enrollee e : f.getEnrollees()) {
                    oos.writeInt(uni.enrollees.indexOf(e));
                }
            }
            */

            oos.flush();
        }

    }

    public University read() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(filename);
        try ( ObjectInputStream oin = new ObjectInputStream(fis)) {


            University uni = (University)oin.readObject();
            /*
            int n = oin.readInt();
            for (int i = 0; i < n; i++) {
                Professor p = (Professor) oin.readObject();
                uni.professors.add(p);
            }

            n = oin.readInt();
            for (int i = 0; i < n; i++) {
                String name = (String)oin.readObject();
                int profIndex = oin.readInt();
                Exam exam = new Exam(name, uni.professors.get(profIndex));
                uni.exams.add(exam);
            }

            n = oin.readInt();
            for (int i = 0; i < n; i++) {
                String name = (String)oin.readObject();
                Enrollee enrollee = new Enrollee(name);
                uni.enrollees.add(enrollee);
                int numberfMarks = oin.readInt();
                for (int numberOfExams = 0; numberOfExams < numberfMarks; numberOfExams++) {
                    int examNumber = oin.readInt();
                    int mark = oin.readInt();
                    uni.mark(enrollee, uni.exams.get(examNumber), mark);
                }

            }

            n = oin.readInt();
            for (int i = 0; i < n; i++) {
                String name = (String)oin.readObject();
                int numberOfStudents = oin.readInt();
                Faculty f = new Faculty(name, numberOfStudents);
                uni.faculties.add(f);

                int numberInWaitList = oin.readInt();
                for (int k = 0; k < numberInWaitList; k++) {
                    f.addEnrolleeToWaitList(uni.enrollees.get(oin.readInt()));
                }

                int numberOfEnrolled = oin.readInt();
                for (int k = 0; k < numberOfEnrolled; k++) {
                    f.addEnrollee(uni.enrollees.get(oin.readInt()));
                }

            }
            */
            return uni;
        }

    }

}