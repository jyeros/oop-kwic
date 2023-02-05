package edu.baylor.ood;

import java.util.SortedSet;

public class Output {

    public void print(SortedSet<String> lines) {
        for(String s: lines){
            System.out.println(s);
        }
    }
}
