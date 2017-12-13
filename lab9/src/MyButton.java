import javax.swing.*;
import java.awt.*;

public class MyButton extends JButton {
    private int x;
    private int y;
    private String name;

    public MyButton(int x, int y, String name) {
        super();
        this.x = x;
        this.y = y;
        this.name = name;
        setText(name);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(100, 30);
    }

    public void center(Dimension size, int windowWidth, int windowHeight) {
        x = windowWidth / 2 - size.width / 2;
        y = windowHeight / 2 - size.height / 2;
        setBounds(x, y, size.width, size.height);
    }
}

