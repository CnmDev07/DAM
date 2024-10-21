package navas.mesa.cristobal.a30dias.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class City(
    @DrawableRes val imageResourceId: Int,
    @StringRes val name: Int,
    val country: Int,
    @StringRes val population: Int
)
