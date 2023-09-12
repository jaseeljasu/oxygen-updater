package com.oxygenupdater.models

import android.os.Parcelable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateOf
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.oxygenupdater.BuildConfig
import com.oxygenupdater.utils.Utils
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@Parcelize
@Stable
@Entity(tableName = "news_item")
@JsonClass(generateAdapter = true)
data class NewsItem(
    @PrimaryKey
    val id: Long?,

    val title: String?,
    val subtitle: String?,
    val text: String?,

    @ColumnInfo("image_url")
    @Json(name = "image_url")
    val imageUrl: String?,

    @ColumnInfo("date_published")
    @Json(name = "date_published")
    val datePublished: String?,

    @ColumnInfo("date_last_edited")
    @Json(name = "date_last_edited")
    val dateLastEdited: String?,

    @ColumnInfo("author_name")
    @Json(name = "author_name")
    val authorName: String?,

    @ColumnInfo(defaultValue = "0")
    @Transient
    @Deprecated(
        "Don't read boolean column directly, use MutableState instead",
        ReplaceWith("readState.value"),
    )
    val read: Boolean = false,
) : Parcelable {

    @Suppress("DEPRECATION")
    @IgnoredOnParcel
    @Ignore
    val readState = mutableStateOf(read)

    @IgnoredOnParcel
    @Ignore
    @JvmField
    val apiUrl = "${BuildConfig.SERVER_DOMAIN + BuildConfig.SERVER_API_BASE}news-content/$id"

    @IgnoredOnParcel
    @Ignore
    @JvmField
    val webUrl = "${BuildConfig.SERVER_DOMAIN}article/$id/"

    @IgnoredOnParcel
    @Ignore
    @JvmField
    val epochMilli = (dateLastEdited ?: datePublished)?.let {
        LocalDateTime.parse(it.replace(" ", "T"))
            .atZone(Utils.SERVER_TIME_ZONE)
            .toInstant().toEpochMilli()
    }

    val isFullyLoaded: Boolean
        get() = id != null && title != null && text != null
}
