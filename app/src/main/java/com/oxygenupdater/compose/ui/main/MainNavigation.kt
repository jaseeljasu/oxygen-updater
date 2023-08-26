package com.oxygenupdater.compose.ui.main

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.HelpOutline
import androidx.compose.material.icons.rounded.PhoneAndroid
import androidx.compose.material.icons.rounded.SystemUpdateAlt
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.vector.ImageVector
import com.oxygenupdater.BuildConfig
import com.oxygenupdater.R
import com.oxygenupdater.compose.icons.CustomIcons
import com.oxygenupdater.compose.icons.News
import com.oxygenupdater.compose.icons.Settings

@Suppress("ConvertObjectToDataObject")
@Immutable
sealed class Screen(
    val route: String,
    val icon: ImageVector,
    @StringRes val labelResId: Int,
    val subtitle: String? = null,
) {
    /** Shown only if not null (max 3 characters) */
    var badge by mutableStateOf<String?>(null)
        internal set

    @Stable
    object Update : Screen(UpdateRoute, Icons.Rounded.SystemUpdateAlt, R.string.update_information_header)

    @Stable
    object NewsList : Screen(NewsListRoute, CustomIcons.News, R.string.news)

    @Stable
    object Device : Screen(DeviceRoute, Icons.Rounded.PhoneAndroid, R.string.device_information_header)

    @Stable
    object About : Screen(AboutRoute, Icons.Rounded.HelpOutline, R.string.about, "v${BuildConfig.VERSION_NAME}")

    @Stable
    object Settings : Screen(SettingsRoute, CustomIcons.Settings, R.string.settings)
}

const val UpdateRoute = "update"
const val NewsListRoute = "newsList"
const val DeviceRoute = "device"
const val AboutRoute = "about"
const val SettingsRoute = "settings"