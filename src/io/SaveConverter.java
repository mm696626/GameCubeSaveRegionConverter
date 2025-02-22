package io;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

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
            e.printStackTrace();
        }

        try (RandomAccessFile raf = new RandomAccessFile(save2, "rw")) {

            raf.seek(0);
            raf.write(byteArray);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void convertSave(File save) {

    }
}
