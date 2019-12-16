package br.com.gabriellferreira.multicurrency.presentation.view.adapter

import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.gabriellferreira.multicurrency.R
import br.com.gabriellferreira.multicurrency.domain.model.Currency
import br.com.gabriellferreira.multicurrency.presentation.util.extension.format
import br.com.gabriellferreira.multicurrency.presentation.util.extension.inflate
import br.com.gabriellferreira.multicurrency.presentation.util.extension.loadCenterCrop
import br.com.gabriellferreira.multicurrency.presentation.view.CurrencyListContract
import kotlinx.android.synthetic.main.item_currency_cell.view.*
import java.util.*

class CurrencyAdapter(private val data: LinkedList<Currency> = LinkedList(),
                      private val view: CurrencyListContract.View?)
    : RecyclerView.Adapter<CurrencyAdapter.ViewHolder>() {

    private var baseValue: Double = 1.0

    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            baseValue = s.toString().toDoubleOrNull() ?: 0.0
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(parent.inflate(R.layout.item_currency_cell))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position], position)
    }

    override fun getItemCount(): Int = data.size

    override fun getItemId(position: Int): Long {
        return data[position].id.toLong()
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        @SuppressLint("SetTextI18n")
        internal fun bind(model: Currency, position: Int) {
            view.item_currency_flag?.loadCenterCrop(model.flagIcon)
            view.item_currency_name?.text = model.name
            view.item_currency_code?.text = model.code
            view.item_currency_rate?.setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus && position > 0) {
                    view.item_currency_rate?.addTextChangedListener(textWatcher)
                    addItemAsFirst(model, position)
                    setCurrencyAsBase(model.code)
                    baseValue = view.item_currency_rate?.text.toString().toDouble()
                    scrollRecyclerTop()
                }
            }
            view.item_currency_rate?.removeTextChangedListener(textWatcher)
            view.item_currency_rate?.setText((model.rate * baseValue).format(model.exponent))
            if (position == 0) {
                view.item_currency_rate?.addTextChangedListener(textWatcher)
            }
        }
    }

    fun add(item: Currency) {
        val index = data.indexOfFirst {
            item.id == it.id
        }
        if (index == 0) {
            return
        }
        if (index >= 0) {
            data[index] = item
            notifyItemChanged(index, item)
        } else {
            data.add(item)
            notifyItemInserted(data.indexOf(item))
        }
    }

    fun clear() {
        this.data.clear()
        notifyDataSetChanged()
    }

    fun scrollRecyclerTop() {
        view?.scrollRecyclerTop()
    }

    fun setCurrencyAsBase(code: String) {
        view?.setCurrencyAsBase(code)
    }

    fun addItemAsFirst(currency: Currency, fromPosition: Int) {
        data.removeAt(fromPosition)
        data.addFirst(currency)
        notifyItemMoved(fromPosition, 0)
        notifyItemChanged(fromPosition)
    }
}