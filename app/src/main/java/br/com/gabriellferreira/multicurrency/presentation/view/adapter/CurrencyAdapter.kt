package br.com.gabriellferreira.multicurrency.presentation.view.adapter

import android.annotation.SuppressLint
import android.os.Handler
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
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.item_currency_cell.view.*
import java.util.*


@Suppress("unused")
class CurrencyAdapter(private val data: MutableList<Currency> = mutableListOf(),
                      private val view: CurrencyListContract.View?)
    : RecyclerView.Adapter<CurrencyAdapter.ViewHolder>() {

    val onItemClickSubject: PublishSubject<Currency> = PublishSubject.create<Currency>()

    private var baseValue: Double = 1.0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(parent.inflate(R.layout.item_currency_cell))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position], position)
    }

    override fun getItemCount(): Int = data.size

    override fun getItemId(position: Int): Long {
        return data[position].id.toLong()
    }

    val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            baseValue = s.toString().toDouble()
            notifyDataSetChanged()
        }
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        @SuppressLint("SetTextI18n")
        internal fun bind(model: Currency, position: Int) {
            view.setOnClickListener { onItemClickSubject.onNext(model) }
            view.item_currency_flag?.loadCenterCrop(model.flagIcon)
            view.item_currency_name?.text = model.name
            view.item_currency_rate?.setOnFocusChangeListener { _, hasFocus ->
                // TODO - add some logic to remove this action every time the list is scrolled
                if (hasFocus) {
                    Handler().postDelayed({
                        swapItem(position)
                        scrollRecyclerTop()
                        setCurrencyAsBase(model.code)
                    }, 500)
                }
            }
//            if (position == 0) {
//                view.item_currency_rate?.addTextChangedListener(textWatcher)
//            } else {
//                view.item_currency_rate?.removeTextChangedListener(textWatcher)
            view.item_currency_rate?.setText((model.rate * baseValue).format(model.exponent))
//            }
        }
    }

    fun add(item: Currency) {
        val index = data.indexOfFirst {
            item.id == it.id
        }
        if (index == 0) {
            //never update the first item
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

    fun swapItem(fromPosition: Int, toPosition: Int = 0) {
        Collections.swap(data, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
    }
}