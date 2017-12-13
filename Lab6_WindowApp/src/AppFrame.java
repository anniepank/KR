import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


public class AppFrame extends JFrame {
    private JTextArea first;
    private JTextArea difference;
    private JTextArea elements;

    private Series series;

    AppFrame() {
        super("Sequences");
        //Create and set up the window.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set up the content pane.
        addComponentsToPane(getContentPane());

        //Display the window.
        pack();
        setVisible(true);
    }

    public void addComponentsToPane(Container pane) {
        pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.append("Choose type of progression: ");
        textArea.setAlignmentX(Component.CENTER_ALIGNMENT);
        pane.add(textArea);

        JRadioButton linearChoice = new JRadioButton("Linear");
        JRadioButton expChoice = new JRadioButton("Exponential");
        ButtonGroup group = new ButtonGroup();
        group.add(linearChoice);
        group.add(expChoice);

        pane.add(linearChoice);
        pane.add(expChoice);

        JPanel firstPanel = new JPanel();
        firstPanel.setLayout(new BoxLayout(firstPanel, BoxLayout.X_AXIS));
        firstPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel differencePanel = new JPanel();
        differencePanel.setLayout(new BoxLayout(differencePanel, BoxLayout.X_AXIS));
        differencePanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel numberPanel = new JPanel();
        numberPanel.setLayout(new BoxLayout(numberPanel, BoxLayout.X_AXIS));
        numberPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        first = new JTextArea("a1");
        first.setEditable(false);
        JTextField firstField = new JTextField();

        difference = new JTextArea("b");
        difference.setEditable(false);
        JTextField differenceField = new JTextField();

        JTextArea numberOfElements = new JTextArea("number of elements");
        numberOfElements.setEditable(false);
        JTextField numberOfElementsField = new JTextField();

        firstPanel.add(first);
        firstPanel.add(firstField);
        pane.add(firstPanel);

        differencePanel.add(difference);
        differencePanel.add(differenceField);
        pane.add(differencePanel);

        numberPanel.add(numberOfElements);
        numberPanel.add(numberOfElementsField);
        pane.add(numberPanel);

        expChoice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                first.setText("b1");
                difference.setText("q");
                elements.setText("");
            }
        });

        linearChoice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                first.setText("a1");
                difference.setText("b");
                elements.setText("");
            }
        });

        JButton createButton = new JButton("Create series");
        pane.add(createButton);

        elements = new JTextArea();
        elements.setEditable(false);
        JScrollPane elementsScroll = new JScrollPane(elements,
                ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER,
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        elementsScroll.setPreferredSize(new Dimension(400, 38));
        pane.add(elementsScroll);

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (firstField.getText().length() > 0 &&
                        differenceField.getText().length() > 0 &&
                        numberOfElementsField.getText().length() > 0 &&
                        (linearChoice.getModel().isSelected() || expChoice.getModel().isSelected())
                        ) {
                    double firstElement = Double.parseDouble(firstField.getText());
                    double step = Double.parseDouble(differenceField.getText());
                    int n = Integer.parseInt(numberOfElementsField.getText());

                    if (linearChoice.getModel().isSelected()) {
                        series = new Linear(step, firstElement);
                    } else {
                        series = new Exponential(step, firstElement);
                    }

                    elements.setText(series.toString(n));
                }
            }
        });

        //Saving possibility

        JTextArea savingArea = new JTextArea("Enter the name of file below");
        savingArea.setEditable(false);
        pane.add(savingArea);

        JPanel savingPanel = new JPanel();
        savingPanel.setLayout(new BoxLayout(savingPanel, BoxLayout.X_AXIS));
        JTextField fileNameField = new JTextField();
        JButton saveButton = new JButton("Save");

        savingPanel.add(fileNameField);
        savingPanel.add(saveButton);

        pane.add(savingPanel);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String filename = fileNameField.getText();

                if (filename.length() > 0 && numberOfElementsField.getText().length() > 0) {
                    try {
                        series.saveToFile(filename, Integer.parseInt(numberOfElementsField.getText()));
                        fileNameField.setText("Saved");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

}
