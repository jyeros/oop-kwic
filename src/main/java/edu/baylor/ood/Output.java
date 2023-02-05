package edu.baylor.ood;

public class Output {

    public void print(LineStorageWrapper lines) {
        for (String s : lines.getIterable()) {
            System.out.println(s);
        }
    }
}
