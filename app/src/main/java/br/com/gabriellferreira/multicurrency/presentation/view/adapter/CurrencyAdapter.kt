package br.com.gabriellferreira.multicurrency.presentation.view.adapter

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.gabriellferreira.multicurrency.R
import br.com.gabriellferreira.multicurrency.domain.model.Currency
import br.com.gabriellferreira.multicurrency.presentation.util.extension.format
import br.com.gabriellferreira.multicurrency.presentation.util.extension.inflate
import br.com.gabriellferreira.multicurrency.presentation.util.extension.loadCenterCrop
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.item_currency_cell.view.*

@Suppress("unused")
class CurrencyAdapter(private var data: MutableList<Currency> = mutableListOf())
    : RecyclerView.Adapter<CurrencyAdapter.ViewHolder>() {

    val onItemClickSubject: PublishSubject<Currency> = PublishSubject.create<Currency>()

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
            view.setOnClickListener { onItemClickSubject.onNext(model) }
            view.item_currency_flag?.loadCenterCrop(model.flagIcon)
            view.item_currency_name?.text = model.name
            view.item_currency_rate?.text = model.rate.format(model.exponent)
        }
    }

    fun add(item: Currency) {
        val index = data.indexOfFirst {
            item.id == it.id
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
}