import javax.swing.*;
import java.awt.*;

public class Test {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        MenuDialog menuDialog = new MenuDialog("Dialog");
    }
}
