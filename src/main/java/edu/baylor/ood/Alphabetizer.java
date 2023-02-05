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
        for (int i = 0; i < lines.getLines().size(); i++) {
            if (item.compareTo(lines.getLines().get(i)) < 0) {
                lines.getLines().add(i, item);
                inserted = true;
                break;
            }
        }
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
        lines.setCompleted();
        System.out.println("All items have been alphabetized");
    }

}
