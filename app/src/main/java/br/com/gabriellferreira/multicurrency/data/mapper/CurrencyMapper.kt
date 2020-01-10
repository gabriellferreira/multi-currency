package br.com.gabriellferreira.multicurrency.data.mapper

import androidx.annotation.DrawableRes
import br.com.gabriellferreira.multicurrency.R
import br.com.gabriellferreira.multicurrency.data.model.CurrencyData
import br.com.gabriellferreira.multicurrency.data.model.NoDataException
import br.com.gabriellferreira.multicurrency.domain.model.Currency
import br.com.gabriellferreira.multicurrency.presentation.util.extension.format
import java.util.*
import javax.inject.Inject

class CurrencyMapper @Inject constructor() {

    fun map(
        data: CurrencyData
    ): Currency {
        val foreignCurrency = ForeignCurrency.getCurrency(data.name) ?: throw NoDataException()
        return Currency(
            id = foreignCurrency.id,
            base = data.base ?: "",
            rate = data.rate ?: throw NoDataException(),
            name = foreignCurrency.coinName,
            flagIcon = foreignCurrency.flagIcon,
            exponent = foreignCurrency.exponent,
            code = foreignCurrency.name,
            rateString = (data.rate * 1.0).format(foreignCurrency.exponent)
        )
    }

    @Suppress("MagicNumber", "unused")
    enum class ForeignCurrency(
        val id: Int,
        @DrawableRes val flagIcon: Int,
        val coinName: String,
        val exponent: Int
    ) {
        AUD(
            id = 36,
            flagIcon = R.drawable.ic_australia,
            coinName = "Australian dollar",
            exponent = 2
        ),
        BGN(id = 975, flagIcon = R.drawable.ic_bulgaria, coinName = "Bulgarian lev", exponent = 2),
        BRL(id = 986, flagIcon = R.drawable.ic_brazil, coinName = "Brazilian real", exponent = 2),
        CAD(id = 124, flagIcon = R.drawable.ic_canada, coinName = "Canadian dollar", exponent = 2),
        CHF(id = 756, flagIcon = R.drawable.ic_switzerland, coinName = "Swiss franc", exponent = 2),
        CNY(id = 156, flagIcon = R.drawable.ic_china, coinName = "Renminbi yuan", exponent = 2),
        CZK(
            id = 203,
            flagIcon = R.drawable.ic_czech_republic,
            coinName = "Czech koruna",
            exponent = 2
        ),
        DKK(id = 208, flagIcon = R.drawable.ic_denmark, coinName = "Danish krone", exponent = 2),
        EUR(id = 978, flagIcon = R.drawable.ic_european_union, coinName = "Euro", exponent = 2),
        GBP(
            id = 826,
            flagIcon = R.drawable.ic_united_kingdom,
            coinName = "Pound sterling",
            exponent = 2
        ),
        HKD(
            id = 344,
            flagIcon = R.drawable.ic_hong_kong,
            coinName = "Hong Kong dollar",
            exponent = 2
        ),
        HRK(id = 191, flagIcon = R.drawable.ic_croatia, coinName = "Croatian kuna", exponent = 2),
        HUF(
            id = 348,
            flagIcon = R.drawable.ic_hungary,
            coinName = "Hungarian forint",
            exponent = 2
        ),
        IDR(
            id = 360,
            flagIcon = R.drawable.ic_indonesia,
            coinName = "Indonesian rupiah",
            exponent = 2
        ),
        ILS(
            id = 376,
            flagIcon = R.drawable.ic_israel,
            coinName = "Israeli new shekel",
            exponent = 2
        ),
        INR(id = 356, flagIcon = R.drawable.ic_india, coinName = "Indian rupee", exponent = 2),
        ISK(id = 352, flagIcon = R.drawable.ic_iceland, coinName = "Icelandic króna", exponent = 0),
        JPY(id = 392, flagIcon = R.drawable.ic_japan, coinName = "Japanese yen", exponent = 0),
        KRW(
            id = 410,
            flagIcon = R.drawable.ic_south_korea,
            coinName = "South Korean won",
            exponent = 0
        ),
        MXN(id = 484, flagIcon = R.drawable.ic_mexico, coinName = "Mexican peso", exponent = 2),
        MYR(
            id = 458,
            flagIcon = R.drawable.ic_malaysia,
            coinName = "Malaysian ringgit",
            exponent = 2
        ),
        NOK(id = 578, flagIcon = R.drawable.ic_norway, coinName = "Norwegian krone", exponent = 2),
        NZD(
            id = 554,
            flagIcon = R.drawable.ic_new_zealand,
            coinName = "New Zealand dollar",
            exponent = 2
        ),
        PHP(
            id = 608,
            flagIcon = R.drawable.ic_philippines,
            coinName = "Philippine peso",
            exponent = 2
        ),
        PLN(
            id = 985,
            flagIcon = R.drawable.ic_republic_of_poland,
            coinName = "Polish złoty",
            exponent = 2
        ),
        RON(id = 946, flagIcon = R.drawable.ic_romania, coinName = "Romanian leu", exponent = 2),
        RUB(id = 643, flagIcon = R.drawable.ic_russia, coinName = "Russian ruble", exponent = 2),
        SEK(id = 752, flagIcon = R.drawable.ic_sweden, coinName = "Swedish krona", exponent = 2),
        SGD(
            id = 702,
            flagIcon = R.drawable.ic_singapore,
            coinName = "Singapore dollar",
            exponent = 2
        ),
        THB(id = 764, flagIcon = R.drawable.ic_thailand, coinName = "Thai baht", exponent = 2),
        TRY(id = 949, flagIcon = R.drawable.ic_turkey, coinName = "Turkish lira", exponent = 2),
        USD(
            id = 840,
            flagIcon = R.drawable.ic_united_states_of_america,
            coinName = "United States dollar",
            exponent = 2
        ),
        ZAR(
            id = 710,
            flagIcon = R.drawable.ic_south_africa,
            coinName = "South African rand",
            exponent = 2
        );

        companion object {
            fun getCurrency(code: String?): ForeignCurrency? {
                return values().first {
                    it.name.toLowerCase(Locale.getDefault()) == code?.toLowerCase(Locale.getDefault())
                }
            }
        }

    }
}