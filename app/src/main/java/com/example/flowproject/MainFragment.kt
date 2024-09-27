package com.example.flowproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.ViewModelProvider

class MainFragment : Fragment() {

    lateinit var networkStateTextView: TextView
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val networkStateRepository = NetworkStateRepository()
        viewModel = ViewModelProvider(this, MainViewModelFactory(networkStateRepository, requireContext())).get(MainViewModel::class.java)
        networkStateTextView = view.findViewById(R.id.internetConnectionTextView)
        lifecycleScope.launchWhenStarted {
            viewModel.isNetworkAvailable().collect { networkState ->
                when(networkState) {
                    true -> networkStateTextView.text = "There is internet"
                    else -> networkStateTextView.text = "There is no internet"
                }
            }
        }
    }
}