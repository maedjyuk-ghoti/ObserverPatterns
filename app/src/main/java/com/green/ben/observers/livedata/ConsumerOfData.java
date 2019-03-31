package com.green.ben.observers.livedata;

import android.arch.lifecycle.*;
import android.support.annotation.Nullable;

public class ConsumerOfData extends ViewModel {
    private DataSource dataSource = new DataSource();
    private MutableLiveData<Integer> subject = new MutableLiveData<>();
    private Observer<Integer> observer = new Observer<Integer>() {
        @Override
        public void onChanged(@Nullable Integer integer) {
            if (integer != null && subject.getValue() != null) {
                subject.setValue(subject.getValue() + integer);
            }
        }
    };

    /**
     * Start observing when this object is created.
     */
    public ConsumerOfData() {
        dataSource.getSubject().observeForever(observer);

        // seed a value
        subject.setValue(0);
    }

    /**
     * Stop observing when this object is no longer in use.
     * If the observer is not removed it will continue to get notifications and be a memory leak.
     */
    @Override
    protected void onCleared() {
        super.onCleared();
        dataSource.getSubject().removeObserver(observer);
    }

    /**
     * Make a getter so that only the immutable parts are passed around.
     * Callers of this method will not be able to call setValue().
     */
    public LiveData<Integer> getSubject() {
        return subject;
    }

    /**
     * Ignore this cheap way to make things change
     */
    public void clicked() {
        dataSource.generateNewValue();
    }
}
