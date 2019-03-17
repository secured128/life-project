package testcode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * JDK 7+.
 */
public final class ReadWriteBinaryFile {

    public static void main(String... args) throws IOException {
        ReadWriteBinaryFile binary = new ReadWriteBinaryFile();
        byte[] bytes = new byte[]{0x0F, 0x23};
        log("Small - size of file read in:" + bytes.length);
        binary.writeSmallBinaryFile(bytes, OUTPUT_FILE_NAME);
    }

    //    final static String FILE_NAME = "C:\\Temp\\cottage.jpg";
    final static String OUTPUT_FILE_NAME = "/Users/i350350/binary.file";

    byte[] readSmallBinaryFile(String fileName) throws IOException {
        Path path = Paths.get(fileName);
        return Files.readAllBytes(path);
    }

    void writeSmallBinaryFile(byte[] bytes, String fileName) throws IOException {
        Path path = Paths.get(fileName);
        Files.write(path, bytes); //creates, overwrites
    }

    private static void log(Object msg) {
        System.out.println(String.valueOf(msg));
    }
}