package cz.vostinak.nba.ui.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

/**
 * Preview provider for theme combinations
 */
class ThemePreviewProvider: PreviewParameterProvider<Theme> {
    override val values = sequenceOf(
        Theme(false),
        Theme(true)
    )
}