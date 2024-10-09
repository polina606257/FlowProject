package com.example.flowproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch

class MainFragment : Fragment() {

    lateinit var networkStateTextView: TextView
    lateinit var clickBtn: Button
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
        viewModel =
            ViewModelProvider(this, MainViewModelFactory(networkStateRepository, requireContext().applicationContext))[MainViewModel::class.java]

        networkStateTextView = view.findViewById(R.id.internetConnectionTextView)
        clickBtn = view.findViewById(R.id.click_btn)

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.isInternetConnected.collect { networkState ->
                    when (networkState.isConnected) {
                        true -> networkStateTextView.text = getString(R.string.has_internet_connection)
                        else -> networkStateTextView.text = getString(R.string.no_internet_connection)
                    }
                }
            }
        }

        lifecycleScope.launch {
            clickBtn.clicksFlow().collect {
                clickBtn.text = "It is been clicked"
            }
        }
    }
}