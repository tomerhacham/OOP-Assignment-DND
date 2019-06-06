package GoT.DnD.Persistent_Layer;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class ReadText {

    public static List<String> readAllLines(String path) {
        Path newp = Paths.get(path);
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(newp);
        } catch (FileNotFoundException e) {
            System.out.println("File not found " + newp);
        } catch (IOException e) {
            System.out.println(e.getMessage() + "\n" + e.getStackTrace());
        }
        return lines;
    }

    public static List<String> readAllLines(Path path) {
        Path newp = path;
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(newp);
        } catch (FileNotFoundException e) {
            System.out.println("File not found " + newp);
        } catch (IOException e) {
            System.out.println(e.getMessage() + "\n" + e.getStackTrace());
        }
        return lines;
    }
}
