package edu.baylor.ood;

import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

public class Alphabetizer implements Subscriber<String> {
    private Subscription subscription;

    private LineStorageWrapper lines;

    public Alphabetizer(LineStorageWrapper lines) {
        this.lines = lines;
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(String item) {
        boolean inserted = false;

        // Find place to insert current line shift in alphabetized list.
        for (int i = 0; i < lines.getLines().size(); i++) {
            if (item.compareTo(lines.getLines().get(i)) < 0) {
                lines.getLines().add(i, item);
                inserted = true;
                break;
            }
        }

        // If a place was not found, the current line shift should be added
        // to the end of the alphabetized list.
        if (!inserted) {
            lines.addLine(item);
        }
        subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println("Error Occurred: " + throwable.getMessage());

    }

    @Override
    public void onComplete() {
        // Mark the end of lines being published.
        lines.setCompleted();
        System.out.println("All items have been alphabetized");
    }

}
