package com.nab.phuong.weather.home.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.nab.phuong.core_viewmodel.ViewModelFactory
import com.nab.phuong.weather.R
import com.nab.phuong.weather.databinding.NavHostHomeActivityBinding
import com.nab.phuong.weather.home.deps.HomeDepsProvider
import com.nab.phuong.weather.home.presentation.model.RootDeviceState
import com.nab.phuong.weather.home.presentation.viewmodel.HostViewModel
import javax.inject.Inject

class NavHostHomeActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(HostViewModel::class.java)
    }

    private lateinit var binding: NavHostHomeActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        (this.applicationContext as HomeDepsProvider)
            .providesAppComponentDeps()
            .inject(navHostHomeActivity = this)
        super.onCreate(savedInstanceState)
        binding = NavHostHomeActivityBinding.inflate(layoutInflater)
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
