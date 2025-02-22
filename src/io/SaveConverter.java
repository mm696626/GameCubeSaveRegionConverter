package io;

import constants.GameCubeConstants;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

public class SaveConverter {

    public void convertSave(File save, File save2) {

        byte[] byteArray = new byte[64];

        try (RandomAccessFile raf = new RandomAccessFile(save, "r")) {
            raf.seek(0);
            int bytesRead = raf.read(byteArray);

            if (bytesRead == -1) {
                return;
            }
        } catch (IOException e) {
            return;
        }

        try (RandomAccessFile raf = new RandomAccessFile(save2, "rw")) {

            raf.seek(0);
            raf.write(byteArray);

        } catch (IOException e) {
            return;
        }

    }

    public void convertSave(File save, String region) {
        byte[] byteArray = new byte[6];

        try (RandomAccessFile raf = new RandomAccessFile(save, "r")) {
            raf.seek(0);
            int bytesRead = raf.read(byteArray);

            if (bytesRead == -1) {
                return;
            }
        } catch (IOException e) {
            return;
        }

        String gameID = "";
        for (int i=0; i<byteArray.length; i++) {
            gameID += (char)byteArray[i];
        }
        System.out.println(gameID);

        String truncatedGameID = gameID.substring(0, 4);
        System.out.println(truncatedGameID);
    }
}
