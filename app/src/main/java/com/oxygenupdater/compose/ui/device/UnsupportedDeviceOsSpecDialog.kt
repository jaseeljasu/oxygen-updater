package com.oxygenupdater.compose.ui.device

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.oxygenupdater.R
import com.oxygenupdater.compose.ui.common.CheckboxText
import com.oxygenupdater.compose.ui.dialogs.NonCancellableDialog
import com.oxygenupdater.compose.ui.theme.PreviewAppTheme
import com.oxygenupdater.compose.ui.theme.PreviewThemes
import com.oxygenupdater.internal.settings.PrefManager
import com.oxygenupdater.models.DeviceOsSpec

@Composable
fun UnsupportedDeviceOsSpecDialog(spec: DeviceOsSpec) {
    var show by remember(spec) { mutableStateOf(spec != DeviceOsSpec.SUPPORTED_OXYGEN_OS) }
    if (!show) return

    val ignore = remember { mutableStateOf(false) }
    AlertDialog({
        show = false
        PrefManager.putBoolean(PrefManager.PROPERTY_IGNORE_UNSUPPORTED_DEVICE_WARNINGS, ignore.value)
    }, confirmButton = {}, dismissButton = {
        TextButton({
            show = false
            PrefManager.putBoolean(PrefManager.PROPERTY_IGNORE_UNSUPPORTED_DEVICE_WARNINGS, ignore.value)
        }) {
            Text(stringResource(R.string.download_error_close))
        }
    }, title = {
        Text(stringResource(R.string.unsupported_device_warning_title))
    }, text = {
        Column {
            Text(stringResource(remember(spec) {
                when (spec) {
                    DeviceOsSpec.CARRIER_EXCLUSIVE_OXYGEN_OS -> R.string.carrier_exclusive_device_warning_message
                    DeviceOsSpec.UNSUPPORTED_OXYGEN_OS -> R.string.unsupported_device_warning_message
                    DeviceOsSpec.UNSUPPORTED_OS -> R.string.unsupported_os_warning_message
                    else -> R.string.unsupported_os_warning_message
                }
            }))

            CheckboxText(
                ignore,
                R.string.device_warning_checkbox_title,
                Modifier
                    .padding(top = 8.dp)
                    .offset((-12).dp), // bring in line with Text
                Modifier.alpha(ContentAlpha.medium)
            )
        }
    }, properties = NonCancellableDialog)
}

@PreviewThemes
@Composable
fun PreviewCarrierExclusiveDialog() = PreviewAppTheme {
    UnsupportedDeviceOsSpecDialog(DeviceOsSpec.CARRIER_EXCLUSIVE_OXYGEN_OS)
}

@PreviewThemes
@Composable
fun PreviewUnsupportedOxygenOsDialog() = PreviewAppTheme {
    UnsupportedDeviceOsSpecDialog(DeviceOsSpec.UNSUPPORTED_OXYGEN_OS)
}

@PreviewThemes
@Composable
fun PreviewUnsupportedOsDialog() = PreviewAppTheme {
    UnsupportedDeviceOsSpecDialog(DeviceOsSpec.UNSUPPORTED_OS)
}
