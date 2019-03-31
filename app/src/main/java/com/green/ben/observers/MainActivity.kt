package com.green.ben.observers

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.green.ben.observers.livedata.LiveDataFragment
import com.green.ben.observers.observerpattern.ObserverPatternFragment
import com.green.ben.observers.rxjava.RxJavaFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, ObserverPatternFragment.newInstance())
//            .replace(R.id.container, LiveDataFragment.newInstance())
//            .replace(R.id.container, RxJavaFragment.newInstance())
            .commitNow()
    }
}
