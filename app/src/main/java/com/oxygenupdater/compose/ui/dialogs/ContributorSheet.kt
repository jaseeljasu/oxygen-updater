package com.oxygenupdater.compose.ui.dialogs

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CheckCircleOutline
import androidx.compose.material3.ButtonDefaults.textButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.oxygenupdater.R
import com.oxygenupdater.compose.ui.common.CheckboxText
import com.oxygenupdater.compose.ui.common.OutlinedIconButton
import com.oxygenupdater.compose.ui.theme.PreviewThemes
import com.oxygenupdater.utils.ContributorUtils
import com.oxygenupdater.utils.hasRootAccess

@Composable
fun ColumnScope.ContributorSheet(
    hide: () -> Unit,
    showEnrollment: Boolean = false,
) {
    SheetHeader(R.string.contribute_title)

    Text(
        stringResource(R.string.contribute_explanation),
        Modifier
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
            .weight(1f, false)
            .verticalScroll(rememberScrollState()),
        style = MaterialTheme.typography.bodyMedium
    )

    val runningInPreview = LocalInspectionMode.current
    if (!runningInPreview && (!showEnrollment || !ContributorUtils.isAtLeastQAndPossiblyRooted)) {
        return // don't show enrollment UI
    }

    var contribute by remember { mutableStateOf(true) }
    CheckboxText(contribute, { contribute = it }, R.string.contribute_agree, Modifier.padding(end = 16.dp))

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
            colors = textButtonColors(contentColor = MaterialTheme.colorScheme.error)
        ) {
            Text(stringResource(android.R.string.cancel))
        }

        val context = LocalContext.current
        OutlinedIconButton({
            if (contribute) hasRootAccess {
                if (it) {
                    ContributorUtils.flushSettings(context, true)
                    hide()
                } else Toast.makeText(context, R.string.contribute_allow_storage, Toast.LENGTH_LONG).show()
            } else {
                ContributorUtils.flushSettings(context, false)
                hide()
            }
        }, Icons.Rounded.CheckCircleOutline, R.string.contribute_save)
    }
}

@PreviewThemes
@Composable
fun PreviewContributorSheet() = PreviewModalBottomSheet {
    ContributorSheet(hide = {}, showEnrollment = true)
}
