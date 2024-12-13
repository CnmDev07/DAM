package navas.mesa.cristobal.mycity.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@ExperimentalMaterial3Api
@Composable
fun GastronomiaScreen(gastronomiaNombre: String?, navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = gastronomiaNombre ?: "Gastronomía desconocida") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        content = { paddingValues ->

            Column(modifier = Modifier.padding(paddingValues)) {
                Text(
                    text = gastronomiaNombre ?: "Gastronomía desconocida",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    )
}
