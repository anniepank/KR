import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Gui extends JFrame{
    private JPanel mainPanel;
    private JButton button;
    private JPanel statusPanel;
    private JTextField coordinatesStatus;

    private int curX; // mouse coordinates
    private int curY;
    private int buttonX;
    private int buttonY;
    private int buttonW;
    private int buttonH;
    private boolean isButtonTextEdited = false;
    StringBuilder sb;

    public Gui() {
        super("title");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(700,500);
        setLocation(500,250);

        mainPanel = new JPanel(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setSize(300,400);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);

        button = new JButton("Type text!");
        buttonPanel.add(button);

        statusPanel = new JPanel(new FlowLayout());
        statusPanel.setPreferredSize(new Dimension(getWidth(), 40));
        coordinatesStatus = new JTextField();
        coordinatesStatus.setEditable(false);
        coordinatesStatus.setText("X: Y: ");
        coordinatesStatus.setPreferredSize(new Dimension(300, 40));
        statusPanel.add(coordinatesStatus);
        add(statusPanel, BorderLayout.SOUTH);

        setVisible(true);

        buttonW = button.getWidth();
        buttonH = button.getHeight();

        MyMouseAdapter mma = new MyMouseAdapter();
        mainPanel.addMouseListener(mma);
        mainPanel.addMouseMotionListener(mma);

        button.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (!isButtonTextEdited)
                {
                    isButtonTextEdited = true;
                    sb = new StringBuilder();
                }
                if (e.getKeyChar() == '\b' && !sb.toString().isEmpty()) // if 'backspace' is typed
                    sb.deleteCharAt(sb.length() - 1);
                else
                    sb.append(e.getKeyChar());
                button.setText(sb.toString());
            }
        });

    }

    private class MyMouseAdapter extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            curX = e.getX();
            curY = e.getY();

            buttonX = curX - buttonW / 2;
            if (buttonX < 0) buttonX = 0;
            else if (buttonX > mainPanel.getWidth() - buttonW)
                buttonX = mainPanel.getWidth() - buttonW;

            buttonY = curY - buttonH / 2;
            if (buttonY < 0) buttonY = 0;
            if (buttonY > mainPanel.getHeight() - buttonH)
                buttonY = mainPanel.getHeight() - buttonH;

            button.setLocation(buttonX, buttonY);
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            curX = e.getX();
            curY = e.getY();

            int x = curX;
            int y = curY;
            coordinatesStatus.setText("X: " + x + "; Y: " + y);
//            Gui.this.setTitle("X: " + x + "; Y: " + y);
        }
    }

    public static void main(String[] args) {
        new Gui();
    }
}