package com.green.ben.observers.rxjava;

import android.arch.lifecycle.ViewModel;
import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;

public class ConsumerOfData extends ViewModel {
    private DataSource dataSource = new DataSource();
    /**
     * A BiFunction takes 2 parameters and emits a value.
     * In our case it takes in 2 Integers and emits an Integer.
     */
    private BiFunction<Integer, Integer, Integer> biFunction =
            new BiFunction<Integer, Integer, Integer>() {
                @Override
                public Integer apply(Integer integer, Integer integer2) {
                    return integer + integer2;
                }
            };

    /**
     * Scan is an operator that takes a BiFunction and 'maps' it onto each value in the stream.
     * The values given to the BiFunction are:
     *  1) the previously emitted value of the BiFunction
     *  2) the value coming from the source stream
     * Another name for this is an accumulator.
     */
    public final Observable<Integer> observable = dataSource.getObservable().scan(biFunction);

    /**
     * Ignore this cheap way to make things change.
     */
    public void clicked() {
        dataSource.generateNewValue();
    }
}
