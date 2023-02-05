package edu.baylor.ood;

import java.io.FileNotFoundException;
import java.util.SortedSet;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length != 1) {
            System.out.println("Usage: java Main <file>");
            System.exit(1);
        }
        String file = args[0];

        LineStorageWrapper lines = new LineStorageWrapper();
        LineStorageWrapper shifts = new LineStorageWrapper();
        SortedSet<String> alphabetized = new TreeSet<>();
        Input input = new Input();
        CircularShifter shifter = new CircularShifter(shifts);
        lines.subscribe(shifter);
        Alphabetizer alphabetizer = new Alphabetizer(alphabetized);
        shifts.subscribe(alphabetizer);
        Output output = new Output();
        input.parse(file, lines);

        // Wait for the alphabetizer to finish, probably in its onComplete method
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        output.print(alphabetized);
    }
}
