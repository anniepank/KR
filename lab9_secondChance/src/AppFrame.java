import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AppFrame extends JFrame {
    private JPanel mainPanel;
    private JPanel statusPanel;
    private JLabel coordinatesLabel;

    public AppFrame() {
        super("Status bar");
        mainPanel  = new JPanel();
        add(mainPanel, BorderLayout.CENTER);
        this.setResizable(false);

        this.setSize(500,500);
        setLocation(500,250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(statusPanel, BorderLayout.SOUTH);
        statusPanel.setPreferredSize(new Dimension(getWidth(), 40));

        coordinatesLabel.setText("X: Y: ");

        setVisible(true);


        MyButton button = new MyButton(getBounds().width / 2, getBounds().height / 2, "Button");
        Dimension size = button.getPreferredSize();
        button.center(size, getWidth(), getHeight());

        add(button);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                button.setLocation(mouseEvent.getX(), mouseEvent.getY());
            }
        });

        setVisible(true);
    }
}
