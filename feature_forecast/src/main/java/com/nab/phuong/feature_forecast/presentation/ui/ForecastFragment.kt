package com.nab.phuong.feature_forecast.presentation.ui

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
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
import com.nab.phuong.feature_forecast.presentation.ForecastViewModel
import com.nab.phuong.feature_forecast.presentation.ui.adapter.ForecastListAdapter
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
                        shouldShowError = charSequence.toString().searchable().not()
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
        }
        binding.buttonSearch.setOnClickListener {
            checkToSearch()
        }
    }

    private fun handleKeyboardActionDone() {
        with(binding.editTextCityName) {
            showHideInputSearchTermError(
                shouldShowError = text.toString().searchable().not()
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
            if (editableText.toString().searchable()) {
                clearFocus()
                hideKeyboard()
            }
        }
    }

    private fun String?.searchable(): Boolean =
        !(this.isNullOrEmpty() || this.trim().length < MINIMUM_SEARCHABLE_TEXT_LENGTH)

    private fun View.hideKeyboard() {
        val imm = this.context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(this.windowToken, 0)
    }

    companion object {
        private const val MINIMUM_SEARCHABLE_TEXT_LENGTH = 3
    }
}
