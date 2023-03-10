package edu.baylor.ood;

import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

public class CircularShifter implements Subscriber<String> {

    private LineStorageWrapper shifts;

    private Subscription subscription;

    public CircularShifter(LineStorageWrapper shifts) {
        this.shifts = shifts;
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(String item) {
        // Split words on a line.
        var words = item.split(" ");
        // Submit original line wording.
        shifts.submit(String.join(" ", words));
        // Find and submit all combinations of words on a line.
        for (int i = 0; i < words.length - 1; i++) {
            var tmp = words[0];
            for (int j = 0; j < words.length - 1; j++) {
                words[j] = words[j + 1];
            }
            words[words.length - 1] = tmp;
            shifts.submit(String.join(" ", words));
        }

        subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println("Error Occurred: " + throwable.getMessage());
    }

    @Override
    public void onComplete() {
        // Mark the end of line shifts being published.
        shifts.close();
        System.out.println("All items have been shifted");
    }

}
