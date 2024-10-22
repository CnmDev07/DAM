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
    City(R.drawable.tokio, R.string.city_name2, R.string.city_population2, R.string.country_name2),
    City(R.drawable.delhi, R.string.city_name3, R.string.city_population3, R.string.country_name3),
    City(R.drawable.shangai, R.string.city_name4,R.string.city_population4, R.string.country_name4),
    City(R.drawable.saopaulo, R.string.city_name5, R.string.city_population5, R.string.country_name5),
    City(R.drawable.ciudaddemexico, R.string.city_name6, R.string.city_population6,R.string.country_name6),
    City(R.drawable.elcairo, R.string.city_name7, R.string.city_population7, R.string.country_name7),
    City(R.drawable.daca, R.string.city_name8, R.string.city_population8, R.string.country_name8),
    City(R.drawable.bombai, R.string.city_name9, R.string.city_population9, R.string.country_name9),
    City(R.drawable.pekin, R.string.city_name10, R.string.city_population10, R.string.country_name10),
    City(R.drawable.osaka, R.string.city_name11, R.string.city_population11, R.string.country_name11),
    City(R.drawable.karachi, R.string.city_name12, R.string.city_population12, R.string.country_name12),
    City(R.drawable.chongqing, R.string.city_name13, R.string.city_population13, R.string.country_name13),
    City(R.drawable.estambul, R.string.city_name14, R.string.city_population14, R.string.country_name14),
    City(R.drawable.buenosaires, R.string.city_name15, R.string.city_population15, R.string.country_name15),
    City(R.drawable.calcuta, R.string.city_name16, R.string.city_population16, R.string.country_name16),
    City(R.drawable.lagos, R.string.city_name17, R.string.city_population17, R.string.country_name17),
    City(R.drawable.kinsasa, R.string.city_name18, R.string.city_population18, R.string.country_name18),
    City(R.drawable.los_angeles, R.string.city_name19, R.string.city_population19, R.string.country_name19),
    City(R.drawable.rio, R.string.city_name20, R.string.city_population20, R.string.country_name20),
    City(R.drawable.tiajin, R.string.city_name21, R.string.city_population21, R.string.country_name21),
    City(R.drawable.paris, R.string.city_name22, R.string.city_population22, R.string.country_name22),
    City(R.drawable.lima, R.string.city_name23, R.string.city_population23, R.string.country_name23),
    City(R.drawable.bangkok, R.string.city_name24, R.string.city_population24, R.string.country_name24),
    City(R.drawable.londres, R.string.city_name25, R.string.city_population25, R.string.country_name25),
    City(R.drawable.bagdag, R.string.city_name26, R.string.city_population26,R.string.country_name26),
    City(R.drawable.teheran, R.string.city_name27, R.string.city_population27, R.string.country_name27),
    City(R.drawable.chengdu, R.string.city_name28, R.string.city_population28, R.string.country_name28),
    City(R.drawable.bangalore, R.string.city_name29, R.string.city_population29, R.string.country_name29),
    City(R.drawable.santiago, R.string.city_name30, R.string.city_population30, R.string.country_name30)
)
