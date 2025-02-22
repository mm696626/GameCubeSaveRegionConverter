package ui;

import constants.GameCubeConstants;
import io.SaveConverter;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class GameCubeSaveRegionConverterUI extends JFrame implements ActionListener {


    private JButton originalSaveBrowse, regionConvertedSaveBrowse, saveBrowse, convertSave, replaceHeader;
    private JLabel originalSaveLabel, regionConvertedSaveLabel, saveLabel;
    private String originalSavePath, regionConvertedSavePath, savePath;
    private JTextField originalSaveField, regionConvertedSaveField, saveField;
    private JRadioButton usButton, palButton, japanButton;


    private ArrayList<JPanel> jPanels = new ArrayList<>();

    public GameCubeSaveRegionConverterUI() {
        setTitle("GameCube Save Region Converter");
        generateUI();
        showWarningMessage();
    }

    private void generateUI() {
        JPanel mainMenuPanel = new JPanel();
        GridLayout mainMenuGridLayout = new GridLayout(3, 3);
        mainMenuPanel.setLayout(mainMenuGridLayout);

        JPanel testPanel = new JPanel();
        GridLayout testLayout = new GridLayout(3, 3);
        testPanel.setLayout(testLayout);

        //main menu panel

        originalSaveBrowse = new JButton("Browse");
        originalSaveBrowse.addActionListener(this);
        regionConvertedSaveBrowse = new JButton("Browse");
        regionConvertedSaveBrowse.addActionListener(this);

        convertSave = new JButton("Convert Save");
        convertSave.addActionListener(this);

        originalSaveLabel = new JLabel("Converted Region Save Path (save with the region you want to convert to)");
        regionConvertedSaveLabel = new JLabel("Original Save Path (save that you want to convert)");

        originalSaveField = new JTextField(10);
        originalSaveField.setEditable(false);

        regionConvertedSaveField = new JTextField(10);
        regionConvertedSaveField.setEditable(false);

        mainMenuPanel.add(originalSaveLabel);
        mainMenuPanel.add(originalSaveField);
        mainMenuPanel.add(originalSaveBrowse);
        mainMenuPanel.add(regionConvertedSaveLabel);
        mainMenuPanel.add(regionConvertedSaveField);
        mainMenuPanel.add(regionConvertedSaveBrowse);
        mainMenuPanel.add(convertSave);

        //other panel lol

        usButton = new JRadioButton("USA");
        palButton = new JRadioButton("PAL");
        japanButton = new JRadioButton("Japan");

        ButtonGroup group = new ButtonGroup();
        group.add(usButton);
        group.add(palButton);
        group.add(japanButton);

        saveBrowse = new JButton("Browse");
        saveBrowse.addActionListener(this);

        replaceHeader = new JButton("Convert Save");
        replaceHeader.addActionListener(this);

        saveLabel = new JLabel("Save Path");

        saveField = new JTextField(10);
        saveField.setEditable(false);

        testPanel.add(usButton);
        testPanel.add(palButton);
        testPanel.add(japanButton);

        testPanel.add(saveLabel);
        testPanel.add(saveField);
        testPanel.add(saveBrowse);
        testPanel.add(replaceHeader);

        jPanels.add(mainMenuPanel);
        jPanels.add(testPanel);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.add("Use Header from Another Save", jPanels.get(0));
        tabbedPane.add("Replace Region In Header", jPanels.get(1));
        add(tabbedPane);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == originalSaveBrowse) {
            JFileChooser fileChooser = new JFileChooser();
            FileFilter filter = new FileNameExtensionFilter("GCI File","gci");
            fileChooser.setFileFilter(filter);
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int response = fileChooser.showOpenDialog(null);
            if (response == JFileChooser.APPROVE_OPTION) {
                originalSavePath = fileChooser.getSelectedFile().getAbsolutePath();
                originalSaveField.setText(originalSavePath);
            } else {
                return;
            }

        }

        if (e.getSource() == regionConvertedSaveBrowse) {
            JFileChooser fileChooser = new JFileChooser();
            FileFilter filter = new FileNameExtensionFilter("GCI File","gci");
            fileChooser.setFileFilter(filter);
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int response = fileChooser.showOpenDialog(null);
            if (response == JFileChooser.APPROVE_OPTION) {
                regionConvertedSavePath = fileChooser.getSelectedFile().getAbsolutePath();
                regionConvertedSaveField.setText(regionConvertedSavePath);
            } else {
                return;
            }

        }

        if (e.getSource() == saveBrowse) {
            JFileChooser fileChooser = new JFileChooser();
            FileFilter filter = new FileNameExtensionFilter("GCI File","gci");
            fileChooser.setFileFilter(filter);
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int response = fileChooser.showOpenDialog(null);
            if (response == JFileChooser.APPROVE_OPTION) {
                savePath = fileChooser.getSelectedFile().getAbsolutePath();
                saveField.setText(savePath);
            } else {
                return;
            }

        }

        if (e.getSource() == convertSave) {
            File originalSave = new File(originalSavePath);
            File regionConvertedSave = new File(regionConvertedSavePath);

            if (!originalSave.exists() || !regionConvertedSave.exists()) {
                JOptionPane.showMessageDialog(this, "The provided save file path doesn't have an existing save");
                return;
            }

            if (originalSave.equals(regionConvertedSave)) {
                JOptionPane.showMessageDialog(this, "This is the same path! Please provide different paths");
                return;
            }


            SaveConverter saveConverter = new SaveConverter();
            saveConverter.convertSave(originalSave, regionConvertedSave);

            originalSaveField.setText("");
            regionConvertedSaveField.setText("");
            originalSavePath = "";
            regionConvertedSavePath = "";

            JOptionPane.showMessageDialog(this, "Done!");
        }

        if (e.getSource() == replaceHeader) {

            File saveFile = new File(savePath);

            String saveConvertedRegion = "";
            if (!usButton.isSelected() && !palButton.isSelected() && !japanButton.isSelected()) {
                JOptionPane.showMessageDialog(this, "No region was selected!");
                return;
            }
            if (usButton.isSelected()) {
                saveConvertedRegion = GameCubeConstants.US_REGION;
            }
            if (palButton.isSelected()) {
                saveConvertedRegion = GameCubeConstants.PAL_REGION;
            }
            if (japanButton.isSelected()) {
                saveConvertedRegion = GameCubeConstants.JAPAN_REGION;
            }

            SaveConverter saveConverter = new SaveConverter();
            saveConverter.convertSave(saveFile, saveConvertedRegion);

            saveField.setText("");
            savePath = "";

            JOptionPane.showMessageDialog(this, "Done!");
        }
    }

    private void showWarningMessage() {
        File warningSeen = new File("warningSeen.txt");
        if (!warningSeen.exists()) {
            JOptionPane.showMessageDialog(this, GameCubeConstants.JAPAN_SAVE_WARNING);
            createWarningSeenFile();
        }
    }

    private void createWarningSeenFile() {
        PrintWriter outputStream = null;

        try {
            outputStream = new PrintWriter( new FileOutputStream("warningSeen.txt"));
        }
        catch (FileNotFoundException f) {
            System.out.println("File does not exist");
            System.exit(0);
        }

        outputStream.close();
    }
}