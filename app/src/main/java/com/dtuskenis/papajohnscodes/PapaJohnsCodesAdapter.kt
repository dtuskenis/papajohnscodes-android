package com.dtuskenis.papajohnscodes

import androidx.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.view_code.view.*

class PapaJohnsCodesAdapter(private val onItemSelected: (PapaJohnsCode) -> Unit): RecyclerView.Adapter<PapaJohnsCodesAdapter.ViewHolder>() {

    var data: List<PapaJohnsCode> = emptyList()
    get() = field.toList()
    set(value) {
        field = value.toList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(parent)

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) = viewHolder.updateWith(data[position])

    inner class ViewHolder(parent: ViewGroup): RecyclerView.ViewHolder(parent.inflate(R.layout.view_code)) {

        private val codeView = itemView.code
        private val descriptionView = itemView.description

        fun updateWith(code: PapaJohnsCode) {
            codeView.text = code.rawValue
            descriptionView.text = code.description

            itemView.setOnClickListener { onItemSelected(code) }
        }
    }

    companion object {
        private fun ViewGroup.inflate(@LayoutRes layoutId: Int) = LayoutInflater.from(context).inflate(layoutId, this, false)
    }
}