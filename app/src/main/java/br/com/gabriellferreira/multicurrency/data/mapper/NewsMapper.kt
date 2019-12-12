package br.com.gabriellferreira.multicurrency.data.mapper

import br.com.gabriellferreira.multicurrency.data.model.InvalidDataConversionException
import br.com.gabriellferreira.multicurrency.data.model.NewsData
import br.com.gabriellferreira.multicurrency.domain.model.News
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class NewsMapper @Inject constructor() {

    companion object {
        const val FORMAT_MEDIUM_440 = "mediumThreeByTwo440"
        const val FORMAT_MEDIUM_210 = "mediumThreeByTwo210"
        const val DEFAULT_MEDIA_URL = "http://via.placeholder.com/150x150"
    }

    //TODO - to be extracted in case of internationalisation
    private val nyTimesDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    private val appDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

    fun map(newsData: NewsData) =
            News(url = newsData.url ?: "",
                    column = newsData.column ?: "",
                    section = newsData.section ?: "",
                    title = newsData.title ?: "",
                    source = newsData.source ?: "",
                    publishedDate = parseNYTimesDate(newsData.publishedDate),
                    thumbnailUrl = getMediaUrl(newsData) ?: DEFAULT_MEDIA_URL,
                    abstract = newsData.abstract ?: "")

    private fun parseNYTimesDate(publishedDate: String?): String {
        return try {
            val nyDate = nyTimesDateFormat.parse(publishedDate!!)
                    ?: throw InvalidDataConversionException()
            appDateFormat.format(nyDate)
        } catch (e: Exception) {
            ""
        }
    }

    //Disclaimer
    private fun getMediaUrl(newsData: NewsData) =
            newsData.media
                    ?.first()
                    ?.metadataList
                    ?.find {
                        it.format == FORMAT_MEDIUM_440 || it.format == FORMAT_MEDIUM_210
                    }
                    ?.url
}