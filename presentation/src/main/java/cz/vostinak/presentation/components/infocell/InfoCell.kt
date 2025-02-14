package cz.vostinak.presentation.components.infocell

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import cz.vostinak.core.ui.preview.Theme
import cz.vostinak.core.ui.preview.ThemePreviewProvider
import cz.vostinak.core.ui.theme.NBATheme
import cz.vostinak.presentation.R

/**
 * Player info cell.
 * @param modifier Modifier
 * @param title cell title
 * @param value cell value
 * @param onClick listener for click
 */
@Composable
fun InfoCell(
    modifier: Modifier,
    title: String,
    value: String,
    onClick: (() -> Unit)? = null
) {
    Row(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.secondaryContainer,
                shape = MaterialTheme.shapes.medium
            )
            .clickable(
                enabled = onClick != null
            ) {
                onClick?.invoke()
            }
            .padding(16.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.LightGray
            )
            Text(
                modifier = Modifier
                    .padding(top = 6.dp)
                    .fillMaxWidth(),
                text = value,
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White,
                textAlign = TextAlign.End,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        AnimatedVisibility(
            visible = onClick != null
        ) {
            Image(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .size(24.dp),
                painter = painterResource(id = R.drawable.ic_chevron_right),
                contentDescription = stringResource(R.string.content_description_more_info),
                colorFilter = ColorFilter.tint(Color.White)
            )
        }
    }
}

@Preview
@Composable
private fun ShowInfoCellSimple(@PreviewParameter(ThemePreviewProvider ::class) theme: Theme) {
    NBATheme(
        darkTheme = theme.isDarkMode
    ) {
        InfoCell(
            modifier = Modifier,
            title = "Title",
            value = "Value",
            onClick = null
        )
    }
}

@Preview
@Composable
private fun ShowInfoCellClickable(@PreviewParameter(ThemePreviewProvider ::class) theme: Theme) {
    NBATheme(
        darkTheme = theme.isDarkMode
    ) {
        InfoCell(
            modifier = Modifier,
            title = "Title",
            value = "Value",
            onClick = {}
        )
    }
}