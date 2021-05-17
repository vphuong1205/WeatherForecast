package com.nab.phuong.feature_forecast.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.nab.phuong.core_viewmodel.ViewModelFactory
import com.nab.phuong.feature_forecast.databinding.FragmentFeatureForecastBinding
import com.nab.phuong.feature_forecast.deps.ForecastDepsProvider
import javax.inject.Inject

class ForecastFragment : Fragment() {
    private var _binding: FragmentFeatureForecastBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(ForecastViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFeatureForecastBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity?.application as ForecastDepsProvider).providesForecastComponentDeps().inject(
            forecastFragment = this
        )
    }
}
