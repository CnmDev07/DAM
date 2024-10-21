package navas.mesa.cristobal.treintadias.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import navas.mesa.cristobal.treintadias.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityTopAppBar(
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                /*Image(
                    painter = painterResource(R.drawable.ic_city_icon),  // Cambia al recurso adecuado
                    contentDescription = null,  // Imagen decorativa
                    modifier = Modifier
                        .size(40.dp)  // Ajusta el tamaño según sea necesario
                        .padding(8.dp)
                )*/
                Text(
                    text = stringResource(R.string.app_name),  // Cambia al recurso adecuado
                    style = MaterialTheme.typography.displayLarge
                )
            }
        },
        modifier = modifier
    )
}
