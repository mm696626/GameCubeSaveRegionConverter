package ui;

import io.SaveConverter;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class GameCubeSaveRegionConverterUI extends JFrame implements ActionListener {


    private JButton originalSaveBrowse, regionConvertedSaveBrowse, saveBrowse, convertSave, convertAgain;
    private JLabel originalSaveLabel, regionConvertedSaveLabel, saveLabel;
    private String originalSavePath, regionConvertedSavePath, savePath;
    private JTextField ogsavefield, modsavefield, saveField;


    private ArrayList<JPanel> jPanels = new ArrayList<>();

    public GameCubeSaveRegionConverterUI() {
        setTitle("GameCube Save Region Converter");
        generateUI();
    }

    private void generateUI() {
        JPanel mainMenuPanel = new JPanel();
        GridLayout mainMenuGridLayout = new GridLayout(3, 3);
        mainMenuPanel.setLayout(mainMenuGridLayout);

        JPanel testPanel = new JPanel();
        GridLayout testLayout = new GridLayout(2, 3);
        testPanel.setLayout(testLayout);

        //main menu panel

        originalSaveBrowse = new JButton("Browse");
        originalSaveBrowse.addActionListener(this);
        regionConvertedSaveBrowse = new JButton("Browse");
        regionConvertedSaveBrowse.addActionListener(this);

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
        mainMenuPanel.add(originalSaveBrowse);
        mainMenuPanel.add(regionConvertedSaveLabel);
        mainMenuPanel.add(modsavefield);
        mainMenuPanel.add(regionConvertedSaveBrowse);
        mainMenuPanel.add(convertSave);

        saveBrowse = new JButton("Browse");
        saveBrowse.addActionListener(this);

        convertAgain = new JButton("Convert Save");
        convertAgain.addActionListener(this);

        saveLabel = new JLabel("Save Path");

        saveField = new JTextField(10);
        saveField.setEditable(false);

        testPanel.add(saveLabel);
        testPanel.add(saveField);
        testPanel.add(saveBrowse);
        testPanel.add(convertAgain);

        jPanels.add(mainMenuPanel);
        jPanels.add(testPanel);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.add("Two Existing Saves", jPanels.get(0));
        tabbedPane.add("Create Converted Save from Save", jPanels.get(1));
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
                ogsavefield.setText(originalSavePath);
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
                modsavefield.setText(regionConvertedSavePath);
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
        }
    }
}