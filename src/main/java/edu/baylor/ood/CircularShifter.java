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
        var words = item.split(" ");
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
        System.out.println("All items have been shifted");
    }

}
