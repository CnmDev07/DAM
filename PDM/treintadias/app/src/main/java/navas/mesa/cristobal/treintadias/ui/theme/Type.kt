package navas.mesa.cristobal.treintadias.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import navas.mesa.cristobal.treintadias.R

val Firacode = FontFamily(
    Font(R.font.firacode_regular),
    Font(R.font.firacode_bold, FontWeight.Bold)
)


val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = Firacode,
        fontWeight = FontWeight.Bold,
        fontSize = 36.sp,
        //lineHeight = 24.sp,
        //letterSpacing = 0.5.sp
    ),
    displayMedium = TextStyle(
        fontFamily = Firacode,
        fontWeight = FontWeight.Normal
    ),
    labelSmall = TextStyle(

            fontFamily = Firacode,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp
        ),
    bodyLarge = TextStyle(
        fontFamily = Firacode,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    )

    )
