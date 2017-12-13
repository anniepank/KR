import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Test {
    public static void main(String[] args) {
        System.out.println("Cool");
/*
        MyContainer<Double> container = new MyContainer<>();
        container.pushFront(5.98);
        container.pushFront(-5.34);
        container.pushFront(6.66);

        container.pushBack(6.66);
        container.pushBack(7.0);
        container.pushBack(567.99);

        System.out.println(container);

        container.insertBefore(1.0, -5.34);
        System.out.println(container);
        container.insertBefore(1.0, 6.66);
        System.out.println(container);

        try {
            container.remove(1.0);
            container.remove(1.0);
            container.remove(6.66);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(container);

        for (Double item: container) {
            System.out.println(item);
        }

        System.out.println("MAX: " + container.findMax());
        System.out.println("MIN: " + container.findMin());
        System.out.println();

        MyContainer<Student> containerOfStudents = new MyContainer<>();
        Student s1 = new Student("Alex", 2, 9);
        Student s2 = new Student("Nick", 3, 7);
        Student s3 = new Student("Emma", 4, 8);
        containerOfStudents.pushFront(s1);
        containerOfStudents.pushFront(s2);
        containerOfStudents.pushBack(s3);

        for (Student s : containerOfStudents) {
            System.out.println(s);
        }

        System.out.println("MAX: " + containerOfStudents.findMax());
        System.out.println("MIN: " + containerOfStudents.findMin());
*/
        try {
            BufferedReader br = new BufferedReader(new FileReader("/home/anya/IdeaProjects/KR_HOME/src/numbers.txt"));
            String line = br.readLine();

            MyContainer<Double> tokens = new MyContainer<>();
            StringTokenizer tokenizer = new StringTokenizer(line, ",");
            while (tokenizer.hasMoreElements()) {
                tokens.pushFront(new Double(tokenizer.nextToken()));
            }

            for (Double d : tokens) {
                System.out.println(d + " ");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader("/home/anya/IdeaProjects/KR_HOME/src/students.txt"));
            String line = br.readLine();

            MyContainer<Student> tokens = new MyContainer<>();
            StringTokenizer tokenizer = new StringTokenizer(line, ",");
            while (tokenizer.hasMoreElements()) {
                StringTokenizer tokenizerForStudent = new StringTokenizer(tokenizer.nextToken(), " ");
                Student s = new Student(
                        tokenizerForStudent.nextToken(),
                        new Integer(tokenizerForStudent.nextToken()),
                        new Double(tokenizerForStudent.nextToken())
                );
                tokens.pushFront(s);
            }

            for (Student s : tokens) {
                System.out.println(s);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
