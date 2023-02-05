package edu.baylor.ood;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.SubmissionPublisher;

public class LineStorageWrapper extends SubmissionPublisher<String> {
    private List<String> lines = new ArrayList<>();

    private boolean complete = false;

    public List<String> getLines() {
        return lines;
    }

    public void addLine(String line) {
        this.lines.add(line);
    }

    public void setCompleted() {
        complete = true;
    }

    public Iterable<String> getIterable() {
        return () -> new LineIterator();
    }

    private class LineIterator implements java.util.Iterator<String> {
        private int index = 0;

        @Override
        public boolean hasNext() {
            while (!complete) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            return index < lines.size();
        }

        @Override
        public String next() {
            if (index < lines.size()) {
                return lines.get(index++);
            } else {
                return null;
            }
        }
    }
}
