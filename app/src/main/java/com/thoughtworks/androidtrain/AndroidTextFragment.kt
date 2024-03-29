package com.thoughtworks.androidtrain

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class AndroidTextFragment : Fragment() {
    override fun onCreateView
                (inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.android_des, container, false)
    }
}