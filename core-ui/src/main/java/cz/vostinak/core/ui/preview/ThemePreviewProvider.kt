package cz.vostinak.core.ui.preview

/**
 * Preview provider for theme combinations
 */
class ThemePreviewProvider: androidx.compose.ui.tooling.preview.PreviewParameterProvider<Theme> {
    override val values = sequenceOf(
        Theme(false),
        Theme(true)
    )
}