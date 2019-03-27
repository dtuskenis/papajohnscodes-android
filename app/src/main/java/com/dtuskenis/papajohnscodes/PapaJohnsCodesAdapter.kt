package com.dtuskenis.papajohnscodes

import android.content.Context
import android.support.annotation.LayoutRes
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import kotlinx.android.synthetic.main.view_code.view.*

class PapaJohnsCodesAdapter(private val onItemSelected: (PapaJohnsCode) -> Unit): RecyclerView.Adapter<PapaJohnsCodesAdapter.ViewHolder>() {

    var data: List<PapaJohnsCode> = emptyList()
    get() = field.toList()
    set(value) {
        field = value.toList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(parent.context)

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) = viewHolder.updateWith(data[position])

    inner class ViewHolder(context: Context): RecyclerView.ViewHolder(context.inflate(R.layout.view_code)) {

        private val codeView = itemView.code
        private val descriptionView = itemView.description

        fun updateWith(code: PapaJohnsCode) {
            codeView.text = code.code
            descriptionView.text = code.description

            itemView.setOnClickListener { onItemSelected(code) }
        }
    }

    companion object {
        private fun Context.inflate(@LayoutRes layoutId: Int) = LayoutInflater.from(this).inflate(layoutId, null)
    }
}