package com.oxygenupdater.ui.dialogs

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.NotificationsNone
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.oxygenupdater.R
import com.oxygenupdater.ui.common.OutlinedIconButton
import com.oxygenupdater.ui.theme.PreviewThemes

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun ColumnScope.NotificationPermissionSheet(hide: () -> Unit, launchPermissionRequest: () -> Unit) {
    SheetHeader(R.string.notification_permission_title)

    Text(
        stringResource(R.string.notification_permission_text),
        Modifier
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
            .weight(1f, false)
            .verticalScroll(rememberScrollState()),
        style = MaterialTheme.typography.bodyMedium
    )

    Row(
        Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextButton(
            hide,
            Modifier.padding(end = 8.dp),
            colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colorScheme.error)
        ) {
            Text(stringResource(android.R.string.cancel))
        }

        /** Don't hide sheet here, [com.oxygenupdater.ui.main.NotificationPermission] will do it once granted */
        OutlinedIconButton(launchPermissionRequest, Icons.Rounded.NotificationsNone, android.R.string.ok)
    }
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@PreviewThemes
@Composable
fun PreviewNotificationPermissionSheet() = PreviewModalBottomSheet {
    NotificationPermissionSheet(hide = {}, launchPermissionRequest = {})
}