// GameCube Save Region Converter by Matt McCullough
// This is to convert Gamecube GCI saves to different region

import ui.GameCubeSaveRegionConverterUI;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        GameCubeSaveRegionConverterUI gameCubeSaveRegionConverterUI = new GameCubeSaveRegionConverterUI();
        gameCubeSaveRegionConverterUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameCubeSaveRegionConverterUI.pack();
        gameCubeSaveRegionConverterUI.setVisible(true);
    }
}