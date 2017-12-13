package array_list.my;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class Test {
    public static void main(String[] args) {
        System.out.println("Yay");

        MyArrayList<Double> container = new MyArrayList<>();
        container.add(5.0);
        container.add(550.0);
        container.add(550.0);
        container.add(-5.0);
        container.add(-5.0);

        container.add(3, 2.0);
        container.add(0, 1.0);
        System.out.println(container);

        container.remove(2);
        System.out.println(container);
        container.remove(550.0);
        System.out.println(container);

        container.remove(550.00);
        System.out.println(container);

        try {
            BufferedReader br = new BufferedReader(new FileReader("/home/anya/IdeaProjects/KR/ArrayList/src/array_list/my/numbers.txt"));
            String line = br.readLine();

            MyArrayList<Double> tokens = new MyArrayList<>();
            StringTokenizer tokenizer = new StringTokenizer(line, ",");
            while (tokenizer.hasMoreElements()) {
                tokens.add(new Double(tokenizer.nextToken()));
            }

            for (Double d : tokens) {
                System.out.println(d + " ");
            }

            System.out.println("MAX: " + tokens.findMax());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader("/home/anya/IdeaProjects/KR/ArrayList/src/array_list/my/students.txt"));
            String line = br.readLine();

            MyArrayList<Student> tokens = new MyArrayList<>();
            StringTokenizer tokenizer = new StringTokenizer(line, ",");
            while (tokenizer.hasMoreElements()) {
                StringTokenizer tokenizerForStudent = new StringTokenizer(tokenizer.nextToken(), " ");
                Student s = new Student(
                        tokenizerForStudent.nextToken(),
                        new Integer(tokenizerForStudent.nextToken()),
                        new Double(tokenizerForStudent.nextToken())
                );
                tokens.add(s);
            }

            for (Student d : tokens) {
                System.out.println(d + " ");
            }
            System.out.println("MAX: " + tokens.findMax());


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
