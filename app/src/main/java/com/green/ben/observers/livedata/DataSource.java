package com.green.ben.observers.livedata;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import com.green.ben.observers.observerpattern.Observer;

import java.util.Random;

class DataSource {
    private Random random = new Random();

    /**
     * LiveData provides the current value, if there it exists, to observers when they subscribe.
     * This is similar to {@link com.green.ben.observers.observerpattern.Subject#registerObserverAndGetLast(Observer)}
     */
    private MutableLiveData<Integer> subject = new MutableLiveData<>();

    /**
     * Make a getter so that only the immutable parts are passed around.
     * i.e. Callers of this method will not be able to call setValue().
     */
    LiveData<Integer> getSubject() {
        return subject;
    }

    void generateNewValue() {
        subject.setValue(random.nextInt());
    }
}
