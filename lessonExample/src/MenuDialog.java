import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuDialog extends JFrame {

    private JFileChooser fileChooser;
    private Color color;
    private JMenuItem openItem, saveItem, exitItem, colorItem, helpItem;
    private JMenu file, edit, help;
    private AboutDialog dialog;

    public MenuDialog(String title) {

        super(title);
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        JMenu helpMenu = new JMenu("Help");

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(helpMenu);

        openItem = new JMenuItem("Open");
        saveItem = new JMenuItem("Save as..");
        exitItem = new JMenuItem("Exit");
        colorItem = new JMenuItem("Color");
        helpItem = new JMenuItem("Help");

        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);
        editMenu.add(colorItem);
        helpMenu.add(helpItem);

        DemoAction demoAction = new DemoAction();
        openItem.addActionListener(demoAction);
        saveItem.addActionListener(demoAction);
        exitItem.addActionListener(demoAction);
        colorItem.addActionListener(demoAction);
        helpItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (dialog == null) {
                    dialog = new AboutDialog(MenuDialog.this);
                }
                dialog.setVisible(true);

            }
        });
    }

    public class
    DemoAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            Object o = actionEvent.getSource();
            if (o == openItem) {
                openItemAction(actionEvent);
            }
            else if (o == saveItem) {
                saveItemAction(actionEvent);
            }
            else if (o == exitItem) {
                exitItemAction(actionEvent);
            }
            else if (o == colorItem) {
                Color color = colorItemAction(actionEvent);
            }
        }
    }

    public void exitItemAction(ActionEvent e) {
        System.exit(0);
    }

    public Color colorItemAction(ActionEvent e) {
        JColorChooser colorChooser = new JColorChooser();
        color = JColorChooser.showDialog(this,"Choose color", Color.BLACK);
        System.out.println(color);
        return color;
    }

    public void openItemAction(ActionEvent e) {
        fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int dialogResult = fileChooser.showOpenDialog(this);
        switch (dialogResult) {
            case (JFileChooser.APPROVE_OPTION) :{
                System.out.println(fileChooser.getSelectedFile().getName());
                System.out.println(fileChooser.getSelectedFile().getPath());
            }
        }
    }

    public void saveItemAction(ActionEvent e) {
        fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int dialogResult = fileChooser.showSaveDialog(this);
        switch (dialogResult) {
            case (JFileChooser.APPROVE_OPTION) :{
                System.out.println(fileChooser.getSelectedFile().getName());
                System.out.println(fileChooser.getSelectedFile().getPath());
            }
        }
    }

    public class AboutDialog extends JDialog {

        public AboutDialog(JFrame parent) {
            super(parent, "About program", false);
            JLabel label = new JLabel("kek");
            this.add(label, BorderLayout.CENTER);

            JButton okButton = new JButton("OK");
            okButton.addActionListener(actionEvent -> {
                setVisible(false);
            });

            JPanel panel = new JPanel();
            panel.add(okButton);
            this.add(panel, BorderLayout.SOUTH);

            setSize(250, 150);
        }

    }
}
