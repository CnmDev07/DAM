package navas.mesa.cristobal.mycity.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Categoria(
    @StringRes val name: Int,
    @DrawableRes val imageResId: Int
)
