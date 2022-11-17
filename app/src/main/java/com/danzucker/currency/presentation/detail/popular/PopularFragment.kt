package com.danzucker.currency.presentation.detail.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.danzucker.currency.databinding.FragmentPopularBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PopularFragment : Fragment() {

    private var currentBinding : FragmentPopularBinding? = null
    private val ui get() = currentBinding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //return super.onCreateView(inflater, container, savedInstanceState)
        currentBinding = FragmentPopularBinding.inflate(inflater)
        return ui.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        currentBinding = null
    }
}