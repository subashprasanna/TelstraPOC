package com.cts.telstrapoc.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.cts.telstrapoc.model.CanadaAPIDetailInfo
import com.cts.telstrapoc.R
import com.cts.telstrapoc.databinding.CountryDetailRowBinding

class CountryAdapter(
    private val canadaDetail : List<CanadaAPIDetailInfo>
) : RecyclerView.Adapter<CountryAdapter.CustomViewHolder>() {

    override fun getItemCount() = canadaDetail.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CustomViewHolder(
            DataBindingUtil.inflate<CountryDetailRowBinding>(
                LayoutInflater.from(parent.context),
                R.layout.country_detail_row,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.rowBinding.info = canadaDetail[position]
    }

    inner class CustomViewHolder(
        val rowBinding: CountryDetailRowBinding
    ) : RecyclerView.ViewHolder(rowBinding.root)
}