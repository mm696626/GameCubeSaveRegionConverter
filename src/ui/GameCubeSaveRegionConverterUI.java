package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GameCubeSaveRegionConverterUI extends JFrame implements ActionListener {


    private JButton convertSave;
    private JLabel originalSaveLabel, regionConvertedSaveLabel;
    private String originalSavePath = "";
    private String regionConvertedSavePath = "";
    private JTextField ogsavefield, modsavefield;


    private ArrayList<JPanel> jPanels = new ArrayList<>();

    public GameCubeSaveRegionConverterUI() {
        setTitle("GameCube Save Region Converter");
        generateUI();
    }

    private void generateUI() {
        JPanel mainMenuPanel = new JPanel();
        GridLayout mainMenuGridLayout = new GridLayout(3, 2);
        mainMenuPanel.setLayout(mainMenuGridLayout);

        //main menu panel
        convertSave = new JButton("Convert Save");
        convertSave.addActionListener(this);

        originalSaveLabel = new JLabel("Original Save Path");
        regionConvertedSaveLabel = new JLabel("Converted Save Path");

        ogsavefield = new JTextField(10);
        ogsavefield.setEditable(false);

        modsavefield = new JTextField(10);
        modsavefield.setEditable(false);

        mainMenuPanel.add(originalSaveLabel);
        mainMenuPanel.add(ogsavefield);
        mainMenuPanel.add(regionConvertedSaveLabel);
        mainMenuPanel.add(modsavefield);
        mainMenuPanel.add(convertSave);

        jPanels.add(mainMenuPanel);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.add("Main Menu", jPanels.getFirst());
        add(tabbedPane);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == convertSave) {
            System.out.println("Hello");
        }
    }
}