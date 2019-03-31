package com.green.ben.observers.observerpattern;

import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;

public class ConsumerOfData extends ViewModel {
    private Integer runningSum = 0;
    private DataSource dataSource = new DataSource();
    private Subject<Integer> subject = new Subject<Integer>() {
        @Override
        public void registerObserver(Observer<Integer> observer) {
            observers.add(observer);
        }

        @Override
        public void registerObserverAndGetLast(Observer<Integer> observer) {
            observer.update(runningSum);
            registerObserver(observer);
        }

        @Override
        public void unregisterObserver(Observer<Integer> observer) {
            observers.remove(observer);
        }
    };
    private ArrayList<Observer<Integer>> observers = new ArrayList<>();
    private Observer<Integer> observer = new Observer<Integer>() {
        @Override
        public void update(Integer val) {
            runningSum += val;
            notifyObservers();
        }
    };

    public ConsumerOfData() {
        dataSource.registerObserverAndGetLast(observer);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        dataSource.unregisterObserver(observer);
    }

    public Subject<Integer> getSubject() {
        return subject;
    }

    private void notifyObservers() {
        for (Observer<Integer> observer : observers) {
            observer.update(runningSum);
        }
    }

    /**
     * Ignore this cheap way to make things change
     */
    public void clicked() {
        dataSource.generateNewValue();
    }
}
