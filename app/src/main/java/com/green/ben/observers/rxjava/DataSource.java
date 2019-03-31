package com.green.ben.observers.rxjava;

import com.green.ben.observers.observerpattern.Observer;
import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

import java.util.Random;

class DataSource {
    private Random random = new Random();

    /**
     * BehaviorSubject is similar to LiveData and
     * {@link com.green.ben.observers.observerpattern.Subject#registerObserverAndGetLast(Observer)}
     */
    private BehaviorSubject<Integer> subject = BehaviorSubject.create();

    /**
     * Make a getter so that only the immutable parts are passed around.
     * Callers of this method will not be able to call setValue().
     */
    Observable<Integer> getObservable() {
        return subject;
    }

    void generateNewValue() {
        subject.onNext(random.nextInt());
    }
}
