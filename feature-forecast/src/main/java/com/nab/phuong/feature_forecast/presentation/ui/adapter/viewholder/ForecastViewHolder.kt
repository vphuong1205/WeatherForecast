package com.nab.phuong.feature_forecast.presentation.ui.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.nab.phuong.feature_forecast.databinding.ViewForecastVhBinding
import com.nab.phuong.feature_forecast.domain.model.Forecast

internal class ForecastViewHolder constructor(
    private val binding: ViewForecastVhBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun onBind(
        model: Forecast,
    ) {
        binding.forecast = model
    }

    companion object {

        fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
            val binding = ViewForecastVhBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
            return ForecastViewHolder(binding)
        }
    }
}
