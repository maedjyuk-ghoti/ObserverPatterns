package com.green.ben.observers.rxjava

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.green.ben.observers.R
import io.reactivex.disposables.CompositeDisposable

class RxJavaFragment : Fragment() {

    companion object {
        fun newInstance() = RxJavaFragment()
    }

    /**
     * Unlike LiveData which unsubscribes on its own based on the LifeCycleOwner (most of the time),
     *  rx requires that we manually take care of unsubscribing.
     * To do this, we just have to keep track of disposables (the return value from subscribe() calls) and dispose of it
     *  at the right time.
     */
    private lateinit var disposables: CompositeDisposable
    private lateinit var viewModel: ConsumerOfData
    private lateinit var textView: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ConsumerOfData::class.java)
    }

    @SuppressLint("SetTextI18n")
    override fun onStart() {
        super.onStart()
        disposables = CompositeDisposable()

        view!!.setOnClickListener { viewModel.clicked() }
        textView = view!!.findViewById(R.id.message)
        textView.text = "RxJavaFragment"

        disposables.add(viewModel.observable.subscribe { value -> textView.text = value.toString() })
    }

    override fun onStop() {
        super.onStop()
        disposables.clear()
    }
}