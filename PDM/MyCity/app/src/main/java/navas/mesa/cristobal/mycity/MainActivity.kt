package navas.mesa.cristobal.mycity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import navas.mesa.cristobal.mycity.data.Sitios
import navas.mesa.cristobal.mycity.screens.CategoriaScreen
import navas.mesa.cristobal.mycity.screens.FiestaScreen
import navas.mesa.cristobal.mycity.screens.GastronomiaScreen
import navas.mesa.cristobal.mycity.screens.HistoriaScreen
import navas.mesa.cristobal.mycity.screens.MainScreen
import navas.mesa.cristobal.mycity.screens.SitiosScreen
import navas.mesa.cristobal.mycity.ui.theme.MyCityTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyCityTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "main"){
                    composable("main"){ MainScreen(navController)}
                    composable("categoria/{categoriaNombre}"){ backStackEntry ->
                        val categoriaNombre = backStackEntry.arguments?.getString("categoriaNombre")
                        CategoriaScreen(categoriaNombre = categoriaNombre, navController  )
                    }
                    composable("fiestas/{fiestaNombre}"){navBackStackEntry ->  
                        val fiestaNombre =navBackStackEntry.arguments?.getString("fiestaNombre")
                        FiestaScreen(fiestaNombre = fiestaNombre)
                    }
                    composable("gastronomia/{gastronomiaNombre}"){backStackEntry ->  
                        val gastronomiaNombre = backStackEntry.arguments?.getString("gastronomiaNombre")
                        GastronomiaScreen(gastronomiaNombre = gastronomiaNombre)
                    }
                    composable("historia/{historiaNombre}"){backStackEntry ->
                        val historiaNombre = backStackEntry.arguments?.getString("historiaNombre")
                        HistoriaScreen(historiaNombre = historiaNombre)
                    }
                    composable("sitios/{sitiosNombre}"){backStackEntry ->
                        val sitiosNombre = backStackEntry.arguments?.getString("sitiosNombre")
                        SitiosScreen(sitioNombre = sitiosNombre)
                        
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyCityTheme {
        Greeting("Android")
    }
}