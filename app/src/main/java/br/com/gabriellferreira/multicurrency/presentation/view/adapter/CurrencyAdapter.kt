package br.com.gabriellferreira.multicurrency.presentation.view.adapter

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import br.com.gabriellferreira.multicurrency.R
import br.com.gabriellferreira.multicurrency.domain.model.Currency
import br.com.gabriellferreira.multicurrency.presentation.util.extension.inflate
import br.com.gabriellferreira.multicurrency.presentation.util.extension.loadCenterCrop
import br.com.gabriellferreira.multicurrency.presentation.util.extension.parse
import br.com.gabriellferreira.multicurrency.presentation.view.viewmodel.CurrencyListViewModel
import kotlinx.android.synthetic.main.item_currency_cell.view.*

class CurrencyAdapter(val viewModel: CurrencyListViewModel) :
    ListAdapter<Currency, CurrencyAdapter.ViewHolder>(CurrencyLinkedList()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(parent.inflate(R.layout.item_currency_cell))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, position)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, payloads: MutableList<Any>) =
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            val item = getItem(position)
            @Suppress("UNCHECKED_CAST")
            holder.bind(item, position, payloads.first() as MutableList<String>)
        }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        @SuppressLint("SetTextI18n")
        internal fun bind(
            model: Currency,
            position: Int,
            payloads: MutableList<String> = mutableListOf()
        ) {
            if (payloads.isEmpty()) {
                println("Checks brother - Bind - empty")
                view.item_currency_flag?.loadCenterCrop(model.flagIcon)
                view.item_currency_name?.text = model.name
                view.item_currency_code?.text = model.code
                view.item_currency_rate?.setOnFocusChangeListener { view, hasFocus ->
                    if (hasFocus && position > 0) {
                        val baseValue = Double.parse(view.item_currency_rate?.text.toString())
                        viewModel.setCurrencyAsBase(model.code, baseValue)
                        view.item_currency_rate?.addTextChangedListener(viewModel.textWatcher)
                    }
                }
                view.item_currency_rate?.setText(model.rateString)
                if (position == 0) {
                    view.item_currency_rate?.addTextChangedListener(viewModel.textWatcher)
                }
            } else {
                println("Checks brother - Bind - not empty")
                if (position == 0) {
                    return
                }
                view.item_currency_rate?.removeTextChangedListener(viewModel.textWatcher)
                if (payloads.contains("rate")) {
                    view.item_currency_rate?.setText(model.rateString)
                }
            }
        }
    }
}

class CurrencyLinkedList : DiffUtil.ItemCallback<Currency>() {

    override fun areItemsTheSame(oldItem: Currency, newItem: Currency): Boolean {
        return oldItem.code == newItem.code
    }

    override fun areContentsTheSame(oldItem: Currency, newItem: Currency): Boolean {
        println("Checks brother - ${oldItem.rateString == newItem.rateString}")
        return oldItem.rateString == newItem.rateString
    }

    override fun getChangePayload(oldItem: Currency, newItem: Currency): Any? {
        val diff = mutableListOf<String>()

        if (oldItem.rateString != newItem.rateString) {
            diff.add("rate")
        }
        return diff
    }
}