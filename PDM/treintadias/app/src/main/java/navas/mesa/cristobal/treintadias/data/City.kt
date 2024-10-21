package navas.mesa.cristobal.treintadias.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import navas.mesa.cristobal.treintadias.R

data class City(
    @DrawableRes val imageResourceId: Int,
    @StringRes val name: Int,
    @StringRes val population: Int,
    @StringRes val country: Int
)

val cities = listOf(
    City(R.drawable.new_york, R.string.city_name1, R.string.city_population1, R.string.country_name1),

)
