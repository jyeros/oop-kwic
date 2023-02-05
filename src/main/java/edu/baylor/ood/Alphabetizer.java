package edu.baylor.ood;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

// TODO Add <T>
public class Alphabetizer implements Subscriber<String> {
    SortedSet<String> alphabetized;

    private Subscription subscription;

    public Alphabetizer(SortedSet<String> alphabetized){
        this.alphabetized = alphabetized;
    }

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
        subscription.request(1);
    }

    @Override
    public void onNext(String item) {
        alphabetized.add(item);
        subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println("Error Occurred: " + throwable.getMessage());

    }

    @Override
    public void onComplete() {
        System.out.println("All items have been alphabetized");
    }

}
