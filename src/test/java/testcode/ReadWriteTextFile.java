package testcode;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * JDK 7+.
 */
public final class ReadWriteTextFile {

    public static void main(String... args) throws IOException {
        Writer writer = null;

        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(OUTPUT_FILE_NAME), "utf-8"));
            writer.write("a\u319B");
        } catch (IOException ex) {
            // Report
        } finally {
            try {
                writer.close();
            } catch (Exception ex) {/*ignore*/}
        }
    }

    //    final static String FILE_NAME = "C:\\Temp\\cottage.jpg";
    final static String OUTPUT_FILE_NAME = "/Users/i350350/text.file";

}