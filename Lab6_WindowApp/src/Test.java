import javax.swing.*;
import java.io.IOException;

public class Test extends JFrame {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        new AppFrame();

        Series liner = new Linear(2, 0);
        Series exponential = new Exponential(2, 2);

        System.out.println(liner.toString(5));
        System.out.println(exponential.toString(5));

        System.out.println("Sum liner =" + liner.sum(3));
        System.out.println("Sum exponential =" + exponential.sum(4));

        try {
            liner.saveToFile("liner.txt", 5);
            exponential.saveToFile("exp.txt", 5);
        }
        catch (IOException e) {

        }


    }

}
