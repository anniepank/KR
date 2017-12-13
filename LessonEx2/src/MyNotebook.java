import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MyNotebook extends JFrame {
    private JTextField textField;
    private JTextArea textArea;

    public MyNotebook(String title) {
        super(title);
        setSize(600, 500);
        textField = new JTextField("Enter text: ", 50 );
        textArea = new JTextArea();
        JPanel p = new JPanel();
        textArea.setEditable(false);

        add(textField, BorderLayout.NORTH);
        add(textArea);
        add(p, BorderLayout.SOUTH);

        JButton button = new JButton();
        p.add(button);

        textArea.append(textField.getText() + "\n");

        textField.addActionListener(new TextMove(this));
        button.addActionListener(new TextMove(this));

        setVisible(true);
    }

    JTextArea getTextArea() {
        return textArea;
    }

    JTextField getTextField() {
        return textField;
    }

    public static void main(String[] args) {
        JFrame frame = new MyNotebook("Title");
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent event) {
                System.exit(0);
            }
        });

    }
}