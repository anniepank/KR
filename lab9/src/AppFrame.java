import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class AppFrame extends JFrame {

    public boolean ctrlIsPressed = false;
    public boolean mouseIsPressed = false;

    private MyButton button;
    private JPanel panel;
    private JPanel statusPanel;
    private JTextArea coordinatesStatus;

    private int buttonWidth;
    private int buttonHeiht;
    private int buttonX;
    private int buttonY;
    private boolean editText = false;
    StringBuilder sb;

    public AppFrame() {
        super("Status bar");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(500,250);

        panel = new JPanel(new BorderLayout());
        add(panel, BorderLayout.CENTER);


        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(null);
        buttonPanel.setSize(300,400);
        panel.add(buttonPanel, BorderLayout.CENTER);

        button = new MyButton(getBounds().width / 2, getBounds().height / 2, "Button");
        button.setSize(100, 30);
        buttonPanel.add(button);

        statusPanel = new JPanel();
        statusPanel.setSize(panel.getWidth(), 40);

        coordinatesStatus = new JTextArea();
        coordinatesStatus.setEditable(false);
        coordinatesStatus.setText("X: Y: ");
        coordinatesStatus.setSize(panel.getWidth(), 40);

        statusPanel.add(coordinatesStatus);
        add(statusPanel, BorderLayout.SOUTH);

        setVisible(true);

        buttonWidth= button.getWidth();
        buttonHeiht = button.getHeight();

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                buttonX = mouseEvent.getX();
                buttonY = mouseEvent.getY();

                if (buttonX < 0) {
                    buttonX = 0;
                }
                else if (buttonX > panel.getWidth() - buttonWidth){
                    buttonX = panel.getWidth() - buttonWidth;
                }

                if (buttonY < 0) {
                    buttonY = 0;
                }
                if (buttonY > panel.getHeight() - buttonHeiht){
                    buttonY = panel.getHeight() - buttonHeiht;
                }
                button.setLocation(buttonX, buttonY);
            }
        });

        panel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent mouseEvent) {
                coordinatesStatus.setText("X: " + mouseEvent.getX() + " Y: " + mouseEvent.getY());

            }

            @Override
            public void mouseDragged(MouseEvent mouseEvent) {
                if (mouseIsPressed && ctrlIsPressed) {
                    button.setLocation(mouseEvent.getX(), mouseEvent.getY());
                    coordinatesStatus.setText("X: " + mouseEvent.getX() + " Y: " + mouseEvent.getY());
                }
            }
        });

        button.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent mouseEvent) {
                coordinatesStatus.setText("X: " + (mouseEvent.getX() + button.getX()) + " Y: " + (mouseEvent.getY() + button.getY()));
                if (mouseIsPressed && ctrlIsPressed) {
                    button.setLocation(mouseEvent.getX(), mouseEvent.getY());
                }
            }

            @Override
            public void mouseDragged(MouseEvent mouseEvent) {
                coordinatesStatus.setText("X: " + (mouseEvent.getX() + button.getX()) + " Y: " + (mouseEvent.getY() + button.getY()));
                if (mouseIsPressed && ctrlIsPressed) {
                    button.setLocation(mouseEvent.getX() + button.getX(), mouseEvent.getY() + button.getY());

                }
            }
        });
        button.setFocusable(false);
        panel.setFocusable(true); //WTF??
        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {
                if (!editText) {
                    editText = true;
                    sb = new StringBuilder();
                }
                if (keyEvent.getKeyChar() == '\b' && !sb.toString().isEmpty()) {
                    sb.deleteCharAt(sb.length() - 1);
                }
                else {
                    sb.append(keyEvent.getKeyChar());
                }
                button.setText(sb.toString());
            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == KeyEvent.VK_CONTROL) {
                    ctrlIsPressed = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == KeyEvent.VK_CONTROL) {
                    ctrlIsPressed = false;
                }
            }
        });

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                mouseIsPressed  = true;
            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
                mouseIsPressed = false;
            }
        });

        panel.addMouseListener(new MouseAdapter() {
                     @Override
            public void mouseReleased(MouseEvent mouseEvent) {
                mouseIsPressed = false;
            }
        });

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent componentEvent) {
                Dimension size = button.getPreferredSize();
                button.center(size, getWidth(), getHeight());
                statusPanel.setSize(panel.getWidth(), 40);
            }
        });
    }
}
