package navas.mesa.cristobal.treintadias

import CityItem
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import navas.mesa.cristobal.treintadias.data.cities
import navas.mesa.cristobal.treintadias.ui.theme.CityTopAppBar
import navas.mesa.cristobal.treintadias.ui.theme.TreintadiasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TreintadiasTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFF2A3158)
                ) {
                    CityApp()
                }
            }
        }
    }
}

@Composable
fun CityApp() {
    Scaffold(
        topBar = {
            CityTopAppBar()
        }
    ) { it ->
        LazyColumn(contentPadding = it) {
            items(cities) { city ->
                CityItem(
                    city = city,
                    modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
                )
            }
        }
    }
}
