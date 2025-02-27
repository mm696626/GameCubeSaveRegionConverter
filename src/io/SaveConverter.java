package io;

import constants.GameCubeConstants;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class SaveConverter extends JFrame {

    public boolean convertSave(File regionConvertedSave, File originalSave) throws IOException {

        //replace save header with a header from an actual save of that region (first 64 bytes is header)
        byte[] byteArray = new byte[64];

        try (RandomAccessFile raf = new RandomAccessFile(regionConvertedSave, "r")) {
            raf.seek(0);
            int bytesRead = raf.read(byteArray);

            if (bytesRead == -1) {
                return false;
            }
        } catch (IOException e) {
            return false;
        }

        try (RandomAccessFile raf = new RandomAccessFile(originalSave, "rw")) {

            raf.seek(0);
            raf.write(byteArray);

        } catch (IOException e) {
            return false;
        }

        String convertedSaveName = getConvertedSaveName(originalSave);
        File convertedSave = new File(originalSave.getParent(), convertedSaveName);

        Files.move(originalSave.toPath(), convertedSave.toPath(), StandardCopyOption.REPLACE_EXISTING);
        return true;
    }

    private String getConvertedSaveName(File originalSave) {
        String originalSaveFileName = originalSave.getName();
        String originalSaveFileBaseName = originalSaveFileName.substring(0, originalSaveFileName.lastIndexOf('.'));
        String originalSaveFileExtension = originalSaveFileName.substring(originalSaveFileName.lastIndexOf('.'));
        return originalSaveFileBaseName + "_converted" + originalSaveFileExtension;
    }

    public boolean convertSave(File save, String region) throws IOException {

        char regionChar = 0;

        if (region.equals(GameCubeConstants.US_REGION)) {
            regionChar = 'E';
        }

        if (region.equals(GameCubeConstants.PAL_REGION)) {
            regionChar = 'P';
        }

        if (region.equals(GameCubeConstants.JAPAN_REGION)) {
            regionChar = 'J';
        }

        String gameID = getGameID(save);
        if (gameID == null) return false;

        // All possible Game ID with saves follow this format
        String regex = "^[GDP][A-Z0-9]{2}[JEP][A-Z0-9]{2}$";

        if (!gameID.matches(regex)) {
            int invalidGameIDDialogResult = JOptionPane.showConfirmDialog(this, "<html>An invalid Game ID was detected in the provided save file's header.<br>Would you like to continue anyway?</html>");
            if (invalidGameIDDialogResult != JOptionPane.YES_OPTION) {
                return false;
            }
        }

        //directly write to the region at the beginning of GCI saves (the fourth byte in the save is the region)
        try (RandomAccessFile raf = new RandomAccessFile(save, "rw")) {
            raf.seek(3);
            raf.write(regionChar);

        } catch (IOException e) {
            return false;
        }

        String convertedSaveName = getConvertedSaveName(save);
        File convertedSave = new File(save.getParent(), convertedSaveName);

        Files.move(save.toPath(), convertedSave.toPath(), StandardCopyOption.REPLACE_EXISTING);
        return true;
    }

    private String getGameID(File save) {
        byte[] byteArray = new byte[6];

        try (RandomAccessFile raf = new RandomAccessFile(save, "r")) {
            raf.seek(0);
            int bytesRead = raf.read(byteArray);

            if (bytesRead == -1) {
                return null;
            }
        } catch (IOException e) {
            return null;
        }

        String gameID = "";
        for (int i=0; i<byteArray.length; i++) {
            gameID += (char)byteArray[i];
        }
        return gameID;
    }
}
