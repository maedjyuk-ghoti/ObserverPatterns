package com.green.ben.observers.observerpattern;

import java.util.ArrayList;
import java.util.Random;

public class DataSource implements Subject<Integer> {
    private Random random = new Random();

    private ArrayList<Observer<Integer>> observers;
    private Integer data;

    DataSource() {
        observers = new ArrayList<>();
        data = 0;
    }

    void generateNewValue() {
        data = random.nextInt();
        notifyObservers();
    }

    @Override
    public void registerObserver(Observer<Integer> observer) {
        observers.add(observer);
    }

    @Override
    public void registerObserverAndGetLast(Observer<Integer> observer) {
        registerObserver(observer);
        observer.update(data);
    }

    @Override
    public void unregisterObserver(Observer<Integer> observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        for (Observer<Integer> observer : observers) {
            observer.update(data);
        }
    }
}
