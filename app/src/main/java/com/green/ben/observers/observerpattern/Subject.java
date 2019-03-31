package com.green.ben.observers.observerpattern;

public interface Subject<T> {
    /**
     * Observer will be notified of future changes in data.
     */
    void registerObserver(Observer<T> observer);

    /**
     * Observer will get the current AND future changes in data.
     */
    void registerObserverAndGetLast(Observer<T> observer);

    /**
     * Observer will not receive any future changes in data
     */
    void unregisterObserver(Observer<T> observer);
}
