import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Paint extends JFrame {

    public Paint() {

        super("PAINT");
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        addComponentsToPane(getContentPane());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setVisible(true);
    }

    public void addComponentsToPane(Container pane) {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File..");
        JMenu colorMenu = new JMenu("Color..");
        menuBar.add(fileMenu);
        menuBar.add(colorMenu);

        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem greyColor = new JMenuItem("grey color");
        JMenuItem redColor = new JMenuItem("red color");
        JMenuItem amazingColor = new JMenuItem("amazing color");

        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        colorMenu.add(greyColor);
        colorMenu.add(redColor);
        colorMenu.add(amazingColor);

        DrawArea drawArea = new DrawArea();
        JScrollPane scroll = new JScrollPane(drawArea);

        pane.add(scroll);

        greyColor.addActionListener(actionEvent -> drawArea.setG2(new Color(160, 160, 160)));

        amazingColor.addActionListener(actionEvent -> drawArea.setG2(new Color(13, 255, 244)));

        redColor.addActionListener(actionEvent -> drawArea.setG2(new Color(171,0,17)));

        setJMenuBar(menuBar);

        saveItem.addActionListener(actionEvent -> {
            BufferedImage image = drawArea.getImage();

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

            int dialogResult = fileChooser.showSaveDialog(Paint.this);

            switch (dialogResult) {
                case (JFileChooser.APPROVE_OPTION) : {
                    File outputFile = new File(fileChooser.getSelectedFile().getAbsolutePath());
                    try {
                        ImageIO.write(image, "png", outputFile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
        });

        openItem.addActionListener(actionEvent -> {
            BufferedImage bimage;

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            int dialogResult = fileChooser.showOpenDialog(this);
            switch (dialogResult) {
                case (JFileChooser.APPROVE_OPTION) :{
                    try {
                        bimage = ImageIO.read(new File(fileChooser.getSelectedFile().getAbsolutePath()));
                        drawArea.setMyImage(bimage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
