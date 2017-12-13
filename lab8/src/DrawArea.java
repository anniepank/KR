import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;

public class DrawArea extends JComponent {

    private BufferedImage image;
    private Graphics2D g2;
    private int currentX, currentY, oldX, oldY;

    public void setG2(Color color) {
        g2.setPaint(color);
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setMyImage(BufferedImage bimage) {
        g2.drawImage(bimage, 0, 0, null);
        repaint();
    }

    public DrawArea() {
        setDoubleBuffered(false);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                oldX = mouseEvent.getX();
                oldY = mouseEvent.getY();
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent mouseEvent) {
                currentX = mouseEvent.getX();
                currentY = mouseEvent.getY();

                if (g2 != null) {
                    g2.drawLine(currentX, currentY, oldX, oldY);
                    repaint();
                    oldY = currentY;
                    oldX = currentX;
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (image == null) {
            image = (BufferedImage) createImage(getSize().width, getSize().height);
            g2 = (Graphics2D) image.getGraphics();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            clear();
        }
        g.drawImage(image, 0, 0, null);
    }

    public void clear() {
        Color myColour = new Color(0, 0, 0, 0);
        g2.setPaint(myColour);
        g2.fillRect(0, 0, getSize().width, getSize().height);
        g2.setPaint(new Color(171,0,17));
        repaint();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1000, 1000);
    }
}
