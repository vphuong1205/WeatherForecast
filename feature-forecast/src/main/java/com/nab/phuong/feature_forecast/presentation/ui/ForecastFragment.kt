package com.nab.phuong.feature_forecast.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.nab.phuong.core_viewmodel.ViewModelFactory
import com.nab.phuong.feature_forecast.R
import com.nab.phuong.feature_forecast.databinding.FragmentFeatureForecastBinding
import com.nab.phuong.feature_forecast.deps.ForecastDepsProvider
import com.nab.phuong.feature_forecast.presentation.model.CityState
import com.nab.phuong.feature_forecast.presentation.model.ForeCastState
import com.nab.phuong.feature_forecast.presentation.ui.adapter.ForecastListAdapter
import com.nab.phuong.feature_forecast.presentation.viewmodel.ForecastViewModel
import com.nab.phuong.lib_utils.hideKeyboard
import com.nab.phuong.lib_utils.searchable
import javax.inject.Inject

class ForecastFragment : Fragment() {
    private var _binding: FragmentFeatureForecastBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(ForecastViewModel::class.java)
    }

    private val forecastListAdapter: ForecastListAdapter by lazy {
        ForecastListAdapter()
    }

    private val citySuggestionAdapter by lazy {
        ArrayAdapter(
            this.requireContext(), R.layout.view_city_vh, mutableListOf<String>()
        )
    }

    override fun onAttach(context: Context) {
        (context.applicationContext as ForecastDepsProvider)
            .providesForecastComponentDeps().inject(forecastFragment = this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFeatureForecastBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpList()
        setupSearchUI()
        initialiseObservers()
        viewModel.loadCitySuggestions()
    }

    private fun initialiseObservers() {
        viewModel.forecastState.observe(this.viewLifecycleOwner) { state ->
            when (state) {
                is ForeCastState.ListData -> {
                    binding.buttonError.isVisible = false
                    binding.progressBarLoading.isVisible = false
                    forecastListAdapter.submitList(state.data)
                }
                ForeCastState.Loading -> {
                    binding.buttonError.isVisible = false
                    binding.progressBarLoading.isVisible = true
                }
                is ForeCastState.Error -> {
                    binding.progressBarLoading.isVisible = false
                    binding.buttonError.isVisible = true
                    forecastListAdapter.submitList(listOf())
                    binding.buttonError.text = state.errorMessage
                }
                else -> Unit
            }
        }
        viewModel.cityState.observe(this.viewLifecycleOwner) { cityState ->
            when (cityState) {
                is CityState.ListData -> {
                    val suggestions = cityState.data.map { it.name }
                    citySuggestionAdapter.clear()
                    citySuggestionAdapter.addAll(suggestions)
                    citySuggestionAdapter.notifyDataSetChanged()
                }
                else -> {
                    citySuggestionAdapter.clear()
                }
            }
        }
    }

    private fun setUpList() {
        with(binding.rcForecasts) {
            addItemDecoration(
                DividerItemDecoration(
                    this.context,
                    DividerItemDecoration.VERTICAL
                )
            )
            adapter = forecastListAdapter
        }
    }

    private fun setupSearchUI() {
        with(binding.editTextCityName) {
            setAdapter(citySuggestionAdapter)
            onItemClickListener = AdapterView.OnItemClickListener { _, _, _, _ ->
                binding.editTextCityName.hideKeyboard()
            }
            doOnTextChanged { charSequence: CharSequence?, _, _, _ ->
                if (hasFocus()) {
                    showHideInputSearchTermError(
                        shouldShowError = charSequence.toString()
                            .searchable(minLength = MINIMUM_SEARCHABLE_TEXT_LENGTH).not()
                    )
                }
            }
            setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    handleKeyboardActionDone()
                    true
                } else {
                    false
                }
            }
            if (text.toString().searchable(minLength = MINIMUM_SEARCHABLE_TEXT_LENGTH).not()) {
                showHideInputSearchTermError(
                    shouldShowError = false
                )
            }
        }
        binding.buttonSearch.setOnClickListener {
            checkToSearch()
        }
    }

    private fun handleKeyboardActionDone() {
        with(binding.editTextCityName) {
            showHideInputSearchTermError(
                shouldShowError = text.toString()
                    .searchable(minLength = MINIMUM_SEARCHABLE_TEXT_LENGTH).not()
            )
            dismissDropDown()
            hideKeyboard()
        }
    }

    private fun showHideInputSearchTermError(shouldShowError: Boolean) {
        with(binding) {
            textInputLayoutCityName.isErrorEnabled = shouldShowError
            buttonSearch.isEnabled = shouldShowError.not()
            if (shouldShowError) {
                textInputLayoutCityName.error = getString(
                    R.string.feature_forecast_query_length_error,
                    MINIMUM_SEARCHABLE_TEXT_LENGTH
                )
            }
        }

    }

    private fun checkToSearch() {
        binding.buttonError.isVisible = false
        with(binding.editTextCityName) {
            if (editableText.toString().searchable(minLength = MINIMUM_SEARCHABLE_TEXT_LENGTH)) {
                clearFocus()
                hideKeyboard()
                viewModel.searchForecastByCity(cityName = binding.editTextCityName.text.toString())
            }
        }
    }

    companion object {
        private const val MINIMUM_SEARCHABLE_TEXT_LENGTH = 3
    }
}
