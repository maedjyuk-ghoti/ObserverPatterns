package com.green.ben.observers.livedata

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.green.ben.observers.R

class LiveDataFragment : Fragment() {

    companion object {
        fun newInstance() = LiveDataFragment()
    }

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

        view!!.setOnClickListener { viewModel.clicked() }
        textView = view!!.findViewById(R.id.message)
        textView.text = "LiveDataFragment"

        viewModel.subject.observe(this, Observer<Int> { value -> textView.text = value.toString() })
    }
}