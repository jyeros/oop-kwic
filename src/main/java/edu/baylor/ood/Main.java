package edu.baylor.ood;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length != 1) {
            System.out.println("Usage: java Main <file>");
            System.exit(1);
        }
        String file = args[0];

        LineStorageWrapper lines = new LineStorageWrapper();
        LineStorageWrapper shifts = new LineStorageWrapper();

        Input input = new Input();
        CircularShifter shifter = new CircularShifter(shifts);
        lines.subscribe(shifter);
        Alphabetizer alphabetizer = new Alphabetizer();
        shifts.subscribe(alphabetizer);
        Output output = new Output();
        input.parse(file, lines);

        // Wait for the alphabetizer to finish, probably in its onComplete method
        output.print(shifts);
    }
}
