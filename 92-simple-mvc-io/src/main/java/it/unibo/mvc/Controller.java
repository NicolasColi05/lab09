package it.unibo.mvc;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * Application controller. Performs the I/O.
 */
public final class Controller {
    private static final String PATH = System.getProperty("user.home") 
    + System.getProperty("file.separator");
    private File file;

    /**
     * set at defoult the file output.txt.
     */
    public Controller() {
        this.file = new File(PATH + "output.txt");
    }

    /**
     * @param file file to set in the controller
     */
    public void setFile(final File file) {
        this.file = file;
    }

    /**
     * @return the file
     */
    public File getFile() {
        return this.file;
    }

    /**
     * @return the path of file
     */
    public String getCurrentPath() {
        return this.file.toPath().toString();
    }

    /**
     * @param s string to write in the file
     * @throws FileNotFoundException exception launched by bufferedWriter
     */
    public void writeString(final String s) throws FileNotFoundException {
        try (BufferedWriter bw = new BufferedWriter(
             new OutputStreamWriter(new FileOutputStream(this.file), "UTF-16"))) {
            bw.write(s);
        } catch (final IOException e) {
            e.printStackTrace(); //NOPMD
        }
    }
}

