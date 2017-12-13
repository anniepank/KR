import javax.imageio.ImageIO;
import javax.imageio.plugins.jpeg.JPEGHuffmanTable;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class AppFrame extends JFrame {

    private int windowWidth = 700;
    private int windowHeight = 600;

    private JList listLeft;
    private JList listRight;
    private DefaultListModel listModelLeft;
    private DefaultListModel listModelRight;

    private JRadioButton chooseButton;
    private JRadioButton coverButton;
    private JRadioButton pushButton;

    private boolean mouseEntered = false;

    public AppFrame() {
        super("Tabs");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(windowWidth, windowHeight);
        setResizable(false);

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        JPanel p = new JPanel();
        p.setLayout(new GridBagLayout());

        JTabbedPane tabbedPane = new JTabbedPane();

        JComponent panel1 = makeTextPanel("Panel #1");

        // Create Tab1
        panel1.setLayout(new BorderLayout());
        panel1.setBackground(Color.RED);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());

        rightPanel.setPreferredSize(new Dimension(windowWidth / 3, windowHeight));
        leftPanel.setPreferredSize(new Dimension(windowWidth / 3, windowHeight));

        listModelLeft = new DefaultListModel();
        listModelLeft.addElement("Jane Doe");
        listModelLeft.addElement("John Smith");
        listModelLeft.addElement("Kathy Green");

        listLeft = new JList(listModelLeft);
        listLeft.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listLeft.setSelectedIndex(0);
        //add selection listener
        JScrollPane listScrollPaneLeft = new JScrollPane(listLeft);
        leftPanel.add(listScrollPaneLeft);

        listModelRight = new DefaultListModel();
        listModelRight.addElement("Jane Doe");
        listModelRight.addElement("John Smith");
        listModelRight.addElement("Kathy Green");

        listRight = new JList(listModelRight);
        listRight.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listRight.setSelectedIndex(0);
        //add selection listener
        JScrollPane listScrollPaneRight = new JScrollPane(listRight);
        rightPanel.add(listScrollPaneRight);

        panel1.add(leftPanel, BorderLayout.WEST);
        panel1.add(centerPanel, BorderLayout.CENTER);
        panel1.add(rightPanel, BorderLayout.EAST);

        JPanel topCenterPanel = new JPanel();
        topCenterPanel.setPreferredSize(new Dimension(windowWidth / 3, 100));
        topCenterPanel.setLayout(new BorderLayout());

        JButton topButton = new JButton();
        topCenterPanel.add(topButton);
        topButton.setBackground(Color.BLUE);

        try{
            Image img = ImageIO.read(new File("/home/anya/IdeaProjects/Sem3/lab10/src/chevron-right.bmp"));
            topButton.setIcon(new ImageIcon(img));
        } catch (IOException e) {
            e.printStackTrace();
        }

        topButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (!listLeft.isSelectionEmpty()) {
                    int index = listLeft.getSelectedIndex();
                    listModelRight.addElement(listModelLeft.get(index));
                    listModelLeft.remove(index);
                    updateLists();
                }
            }
        });

        JPanel bottomCenterPanel = new JPanel();
        bottomCenterPanel.setPreferredSize(new Dimension(windowWidth / 3, 100));
        bottomCenterPanel.setLayout(new BorderLayout());

        JButton bottomButton = new JButton();
        bottomCenterPanel.add(bottomButton);
        bottomButton.setBackground(Color.PINK);

        try{
            Image img = ImageIO.read(new File("/home/anya/IdeaProjects/Sem3/lab10/src/chevron-left.bmp"));
            bottomButton.setIcon(new ImageIcon(img));
        } catch (IOException e) {
            e.printStackTrace();
        }

        bottomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (!listRight.isSelectionEmpty()) {
                    int index = listRight.getSelectedIndex();
                    listModelLeft.addElement(listModelRight.get(index));
                    listModelRight.remove(index);
                    updateLists();
                }
            }
        });

        JPanel emptyCenterPanel = new JPanel();
        emptyCenterPanel.setBackground(Color.WHITE);

        centerPanel.add(topCenterPanel, BorderLayout.NORTH);
        centerPanel.add(emptyCenterPanel, BorderLayout.CENTER);
        centerPanel.add(bottomCenterPanel, BorderLayout.SOUTH);

        tabbedPane.addTab("Tab 1", null, panel1, "Does nothing");

        JComponent panel2 = makeTextPanel("");
        panel2.setPreferredSize(new Dimension(600, 500));

        // Create Tab2
        JPanel buttonsPanel = new JPanel();
        ButtonGroup group = new ButtonGroup();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));

        chooseButton = new JRadioButton("Choose button");
        coverButton = new JRadioButton("Cover button");
        pushButton = new JRadioButton("Push button");

        group.add(chooseButton);
        group.add(coverButton);
        group.add(pushButton);

        buttonsPanel.add(chooseButton);
        buttonsPanel.add(coverButton);
        buttonsPanel.add(pushButton);

        panel2.add(buttonsPanel);

        setIconsFirstly();

        chooseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setIcons();
            }
        });

        coverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setIcons();
            }
        });

        pushButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setIcons();
            }
        });

        coverButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                try {
                    Image img = ImageIO.read(new File("/home/anya/IdeaProjects/Sem3/lab10/src/battery1.png"));
                    coverButton.setIcon(new ImageIcon(img));
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                try {
                    Image img = ImageIO.read(new File("/home/anya/IdeaProjects/Sem3/lab10/src/battery2.png"));
                    coverButton.setIcon(new ImageIcon(img));
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        pushButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                if (mouseEntered) {
                    try {
                        Image img = ImageIO.read(new File("/home/anya/IdeaProjects/Sem3/lab10/src/human-handsup.png"));
                        pushButton.setIcon(new ImageIcon(img));
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
                try {
                    Image img = ImageIO.read(new File("/home/anya/IdeaProjects/Sem3/lab10/src/human.png"));
                    pushButton.setIcon(new ImageIcon(img));
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        pushButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                mouseEntered = true;
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                mouseEntered = false;
            }
        });
        tabbedPane.addTab("Tab 2", null, panel2, null);
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);

        p.add(tabbedPane);

        getContentPane().add(p);
        pack();
        setVisible(true);
    }

    private void updateLists() {
        listLeft.setModel(listModelLeft);
        listRight.setModel(listModelRight);
    }

    protected JComponent makeTextPanel(String text) {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridBagLayout());
        panel.add(filler);
        return panel;
    }

    private void setIcons() {
        try {
            if (chooseButton.isSelected()) {
                Image img = ImageIO.read(new File("/home/anya/IdeaProjects/Sem3/lab10/src/airplane-takeoff.png"));
                chooseButton.setIcon(new ImageIcon(img));
            } else {
                Image img = ImageIO.read(new File("/home/anya/IdeaProjects/Sem3/lab10/src/airplane-landing.png"));
                chooseButton.setIcon(new ImageIcon(img));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setIconsFirstly() {
        try {
            Image img = ImageIO.read(new File("/home/anya/IdeaProjects/Sem3/lab10/src/airplane-landing.png"));
            chooseButton.setIcon(new ImageIcon(img));

            img = ImageIO.read(new File("/home/anya/IdeaProjects/Sem3/lab10/src/human.png"));
            pushButton.setIcon(new ImageIcon(img));

            img = ImageIO.read(new File("/home/anya/IdeaProjects/Sem3/lab10/src/battery2.png"));
            coverButton.setIcon(new ImageIcon(img));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
