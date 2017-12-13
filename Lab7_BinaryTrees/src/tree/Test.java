package tree;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Test {

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (args[0].equals("number")) {

            BinaryTree<Integer, Integer> tree = new BinaryTree<>();

            BinaryTree.Node<Integer, Integer> treeNode = new BinaryTree.Node<>(7, 8);
            BinaryTree.Node<Integer, Integer> node_13 = new BinaryTree.Node<>(13, 8);
            BinaryTree.Node<Integer, Integer> node_14 = new BinaryTree.Node<>(14, 8);

            tree.addToTree(9, 5);
            tree.addToTree(1, 5);
            tree.addToTree(8, 5);
            tree.addToTree(3, 6);
            tree.addToTree(3, 7);
            tree.addToTree(node_13);
            tree.addToTree(node_14);
            tree.addToTree(15, 6);
            tree.addToTree(22, 6);
            tree.addToTree(19, 6);
            tree.addToTree(21, 6);
            tree.addToTree(treeNode);
            tree.addToTree(0, 8);

            run(tree, new NewElementCreator<Integer>() {
                @Override
                public Integer createKey(String s) {
                    return Integer.parseInt(s);
                }
            });
        }
        if (args[0].equals("person")) {

            BinaryTree<Person, Integer> tree = new BinaryTree<Person, Integer>();

            tree.addToTree(new Person("k", 1), 0);
            tree.addToTree(new Person("g", 2), 0);
            tree.addToTree(new Person("w", 4), 0);
            tree.addToTree(new Person("d", 7), 0);
            tree.addToTree(new Person("z", 7), 0);
            tree.addToTree(new Person("r", 7), 0);
            tree.addToTree(new Person("j", 12), 0);
            tree.addToTree(new Person("f", 10), 0);

            run(tree, new NewElementCreator<Person>() {
                @Override
                public Person createKey(String s) {
                    return new Person(s.split(" ")[0], Integer.parseInt(s.split(" ")[1]));
                }
            });
        }

    }

    interface NewElementCreator<T extends Comparable<T>> {
        T createKey(String s);
    }

    static <T extends Comparable<T>> void run(BinaryTree<T, Integer> tree, NewElementCreator<T> creator) {

        JFrame f = new JFrame("BinaryTreeView");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(tree.getWidth() * 40 + 100, tree.getHeight() * 200 + 20);

        BinaryTreeViewer<T, Integer> btv = new BinaryTreeViewer<T, Integer>(tree);

        JTextArea textArea = new JTextArea("Enter your key ");
        textArea.setEditable(false);

        JTextField keyField = new JTextField();
        keyField.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel p = new JPanel();

        p.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH; // components grow in both dimensions
        c.insets = new Insets(5, 5, 5, 5); // 5-pixel margins on all sides

        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 4;
        c.gridheight = 4;
        c.weightx = c.weighty = 1.0;
        p.add(btv, c);

        c.gridx = 4;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.weightx = c.weighty = 0.0;
        p.add(textArea, c);

        c.gridx = 4;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight = 1;
        p.add(keyField, c);

        c.gridx = 4;
        c.gridy = 2;
        c.gridwidth = 1;
        c.gridheight = 1;

        JButton enterKeyButton = new JButton("Enter");
        enterKeyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                tree.addToTree(creator.createKey(keyField.getText()), 0);
                btv.refresh();
            }
        });
        p.add(enterKeyButton, c);

        JTextField orderField = new JTextField();
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 1;
        c.gridheight = 1;
        JButton preOrderButton = new JButton("Pre-order walk");
        preOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                orderField.setText("");
                tree.preOrderTreeWalk(tree.getRoot(), new BinaryTree.Visitor<T, Integer>() {

                    @Override
                    void visit(BinaryTree.Node<T, Integer> node) {
                        orderField.setText(orderField.getText() + " " + node.key);
                    }
                });
            }
        });
        p.add(preOrderButton, c);

        c.gridx = 1;
        c.gridy = 4;
        c.gridwidth = 1;
        c.gridheight = 1;
        JButton postOrderButton = new JButton("Post-order walk");
        postOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                orderField.setText("");
                tree.postOrderTreeWalk(tree.getRoot(), new BinaryTree.Visitor<T, Integer>() {

                    @Override
                    void visit(BinaryTree.Node<T, Integer> node) {
                        orderField.setText(orderField.getText() + " " + node.key);
                    }
                });
            }
        });
        p.add(postOrderButton, c);

        c.gridx = 2;
        c.gridy = 4;
        c.gridwidth = 1;
        c.gridheight = 1;
        JButton inOrderButton = new JButton("In-order walk");
        p.add(inOrderButton, c);
        inOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                orderField.setText("");
                tree.inOrderTreeWalk(tree.getRoot(), new BinaryTree.Visitor<T, Integer>() {

                    @Override
                    void visit(BinaryTree.Node<T, Integer> node) {
                        orderField.setText(orderField.getText() + " " + node.key);
                    }
                });
            }
        });

        c.gridx = 3;
        c.gridy = 4;
        c.gridwidth = 2;
        c.gridheight = 1;
        p.add(orderField, c);

        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 1;
        c.gridheight = 1;
        JTextField findField = new JTextField();
        p.add(findField, c);

        c.gridx = 1;
        c.gridy = 5;
        c.gridwidth = 1;
        c.gridheight = 1;
        JButton findButton = new JButton("Find by key");
        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                btv.highlightNode = tree.findNode(tree.getRoot(), creator.createKey(findField.getText()));
                btv.refresh();
            }
        });
        p.add(findButton, c);

        c.gridx = 2;
        c.gridy = 5;
        c.gridwidth = 1;
        c.gridheight = 1;
        JButton deleteButton = new JButton("Delete by key");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                tree.deleteFromTree(tree.findNode(tree.getRoot(), creator.createKey(findField.getText())));
                btv.refresh();
            }
        });
        p.add(deleteButton, c);

        f.getContentPane().add(p);
        f.setVisible(true);
        btv.refresh();
    }
}
