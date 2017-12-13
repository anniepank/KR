package tree;

import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;

public class BinaryTreeViewer<T1 extends Comparable<T1>, T2 extends Comparable<T2>> extends JPanel {

    public BinaryTree.Node<T1, T2> highlightNode;

    /* The tree currently being display */
    protected BinaryTree<T1, T2> tree;

    /* The max. height of any tree drawn so far.  This
       is used to avoid the tree jumping around when nodes
       are removed from the tree. */
    protected int maxHeight;
    protected int width;

    /* The font for the tree nodes. */
    protected Font font = new Font("Roman", 0, 14);

    /* koeff for width of window */
    protected final int koefficient = 20;

    protected int windowWidth;
    protected int windowHeight;

    public BinaryTreeViewer(BinaryTree<T1, T2> tree) {

        this.tree = tree;
        this.maxHeight = tree.getHeight();
        this.width = tree.getWidth();

        this.windowWidth = width * koefficient + 20;
        this.windowHeight = maxHeight * koefficient + 20;
    }

    public static void createFrame(int koefficient, int width, int height) {
        JFrame f = new JFrame("BinaryTreeView");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        f.setSize(width * koefficient + 20, height * koefficient + 20);
        f.setVisible(true);
    }

    public void drawRoot(BinaryTree.Node<T1, T2> root, Graphics graphics) {
        if (root == null) {
            return;
        }

        int x = windowWidth / 2 - 20;
        int y = 10 + 20;

        graphics.drawOval(x, y, 40, 40);
        graphics.drawString(root.value.toString(), windowWidth / 2 , 60);
    }

    public void drawTree(BinaryTree.Node<T1, T2> node, Graphics graphics, int currentX, int currentY, int currentWidth) {

        graphics.drawOval(currentX, currentY, 40,40);
        graphics.drawString(node.key.toString(), currentX + 10, currentY + 30);

        if (node == highlightNode) {
            graphics.drawOval(currentX - 2, currentY - 2, 44,44);
        }

        if (node.left != null) {
            graphics.drawLine(currentX + 20, currentY + 40, currentX - currentWidth / 4 + 20, currentY + 70 + 40);
            drawTree(node.left, graphics, currentX - currentWidth / 4, currentY + 70, currentWidth / 2);
        }
        if (node.right != null) {
            graphics.drawLine(currentX + 20, currentY + 40, currentX + currentWidth / 4 + 20, currentY + 70 + 40);
            drawTree(node.right, graphics, currentX + currentWidth / 4, currentY + 70, currentWidth / 2);
        }
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
       // drawRoot(tree.getRoot(), graphics);

        drawTree(tree.getRoot(), graphics, getWidth() / 2, 30, getWidth());
    }

    /*
     * Invoke this method whenever you would like the window
     * to be refreshed, such as after updating the tree in some
     * way.
     */
    public void refresh() {
        revalidate();
        repaint();
    }
}
