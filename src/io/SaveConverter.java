package io;

import constants.GameCubeConstants;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class SaveConverter {

    public void convertSave(File save, File save2) {

        //replace save header with a header from an actual save of that region (first 64 bytes is header)
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

        //directly write to the region at the beginning of GCI saves (the fourth byte in the save is the region)
        try (RandomAccessFile raf = new RandomAccessFile(save, "rw")) {
            raf.seek(3);
            raf.write(regionChar);

        } catch (IOException e) {
            return;
        }
    }
}
