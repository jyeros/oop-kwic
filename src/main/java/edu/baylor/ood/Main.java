package edu.baylor.ood;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        if (args.length != 1) {
            System.out.println("Usage: java Main <file>");
            System.exit(1);
        }
        String file = args[0];

        // Initialize space to store lines and line shifts.
        LineStorageWrapper lines = new LineStorageWrapper();
        LineStorageWrapper shifts = new LineStorageWrapper();

        // Initialize publishers and subscribers.
        Input input = new Input();
        CircularShifter shifter = new CircularShifter(shifts);
        lines.subscribe(shifter);
        Alphabetizer alphabetizer = new Alphabetizer(shifts);
        shifts.subscribe(alphabetizer);
        Output output = new Output();

        // Input lines and output all alphabetized shifts.
        input.parse(file, lines);
        output.print(shifts);
    }
}
