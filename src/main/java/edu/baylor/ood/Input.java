package edu.baylor.ood;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Input {
    public void parse(String file, LineStorageWrapper lines) throws FileNotFoundException {
        var inStream = new FileInputStream(file);

        // Read in all lines from file.
        try (var sc = new Scanner(inStream, StandardCharsets.UTF_8.name())) {
            while (sc.hasNextLine()) {
                lines.submit(sc.nextLine());
            }
            // Mark the end of input being published.
            lines.close();
        }
    }
}
