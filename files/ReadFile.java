package files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ReadFile {

    public ReadFile() { };

    public String read() {
        String input = "";
        try {
            input = new String(Files.readAllBytes(Paths.get("input.txt")));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return input;
    }
}
