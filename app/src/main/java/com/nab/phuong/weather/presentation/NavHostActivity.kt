package com.nab.phuong.weather.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.nab.phuong.core_viewmodel.ViewModelFactory
import com.nab.phuong.weather.R
import com.nab.phuong.weather.databinding.ForecastActivityBinding
import com.nab.phuong.weather.deps.AppDepsProvider
import com.nab.phuong.weather.presentation.model.RootDeviceState
import com.nab.phuong.weather.presentation.viewmodel.HostViewModel
import javax.inject.Inject

class NavHostActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(HostViewModel::class.java)
    }

    private lateinit var binding: ForecastActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        (this.applicationContext as AppDepsProvider)
            .providesAppComponentDeps()
            .inject(navHostActivity = this)
        super.onCreate(savedInstanceState)
        binding = ForecastActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initialiseObservers()
        viewModel.checkRootStatusInfo()
    }

    private fun initialiseObservers() {
        viewModel.rootDeviceState.observe(this) { state ->
            if (state is RootDeviceState.RootedDevice) {
                Toast.makeText(this, R.string.app_rooted_device_message, Toast.LENGTH_LONG).show()
            }
        }
    }
}
