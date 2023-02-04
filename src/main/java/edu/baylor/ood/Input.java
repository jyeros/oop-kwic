package edu.baylor.ood;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Input {
    public void parse(String file, LineStorageWrapper lines) throws FileNotFoundException {
        var inStream = new FileInputStream(file);

        try (var sc = new Scanner(inStream, StandardCharsets.UTF_8.name())) {
            while (sc.hasNextLine()) {
                lines.submit(sc.nextLine());
            }
        }
    }
}
